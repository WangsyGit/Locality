package stu.f40503.nearby;

import com.ids.model.map.Floor;
import com.ids.sdk.android.model.Location;
import com.ids.sdk.android.model.Poi;
import com.ids.sdk.android.map.NavigationInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by YI on 2016/2/14.
 */
public class Navigation {
    public static Object[] oneSourceSearch(Set<Poi> s, HashMap<Integer, HashMap<Integer, Float>> g, Poi start) {
        HashMap<Integer, Float> d = new HashMap();
        HashMap<Integer, Poi> pi = new HashMap();
        Iterator<Poi> pois = s.iterator();
        Poi cur;
        for (int i = 0; i < s.size(); i++) {
            cur = pois.next();
            d.put(cur.getPoiId(), Float.MAX_VALUE);
            pi.put(cur.getPoiId(), null);
        }
        d.put(start.getPoiId(), (float)0.0);
        LinkedList<Poi> S = new LinkedList<>();
        LinkedList<Poi> Q = new LinkedList<>();
        pois = s.iterator();
        for (int i = 0; i < s.size(); i++) {
            Q.add(pois.next());
        }
        while(Q.size() > 0) {
            Poi u = Q.get(0);
            int key = 0;
            for (int i = 1; i < Q.size(); i++) {
                if(d.get(Q.get(i).getPoiId()) < d.get(Q.get(key).getPoiId())) {
                    u = Q.get(i);
                    key = i;
                }
            }
            Q.remove(key);
            S.add(u);
            pois = s.iterator();
            int uId = u.getPoiId();
            int vId;
            for (int i = 0; i < s.size(); i++) {
                cur = pois.next();
                vId = cur.getPoiId();
                float dis = g.get(uId).get(vId);
                if (dis != 0 && dis < Integer.MAX_VALUE) {
                    float weight = d.get(uId) + g.get(uId).get(vId);
                    if(d.get(vId) > weight) {
                        d.put(vId,weight);
                        pi.put(vId,u);
                    }
                }
            }
        }
        Object[] o = new Object[2];
        o[0] = d;
        o[1] = pi;
        return o;
    }

    public static LinkedList<NavigationInfo> searchPath(Set<Poi> set, PoiInfo info, Poi start, Poi end, int way) {
        HashMap<Integer, HashMap<Integer, Float>> g = info.getGraph();
        int sf = start.getFloorLevel();
        int ef = end.getFloorLevel();
        if (sf == ef) {
            Object[] o = oneSourceSearch(set,g,start);
            HashMap<Integer, Float> d = (HashMap<Integer, Float>)o[0];
            HashMap<Integer, Poi> pi = (HashMap<Integer, Poi>)o[1];
            LinkedList<NavigationInfo> n = addNav(null, d, pi, start, end);
            return n;
        } else {
            Poi preEnd = null;
            Poi reStart = null;
            Object[] o1 = oneSourceSearch(set,g,start);
            HashMap<Integer, Float> d1 = (HashMap<Integer, Float>)o1[0];
            HashMap<Integer, Poi> pi1 = (HashMap<Integer, Poi>)o1[1];
            ArrayList<ArrayList<Poi>> seriesList = info.getSeries(way);
            ArrayList<Poi> series;
            if (seriesList == null) {
                LinkedList<NavigationInfo> n = addNav(null, d1, pi1, start, end);
                return n;
            }
            Object[] o2;
            HashMap<Integer, Float> d2 = null;
            HashMap<Integer, Poi> pi2 = null;
            float temp;
            float min = Float.MAX_VALUE;
            for (int i = 0; i < seriesList.size(); i++) {
                series = seriesList.get(i);
                Poi e = null;
                Poi s = null;
                for (int j = 0; j < series.size(); j++) {
                    if (series.get(j).getFloorLevel() == sf) {
                        e = series.get(j);
                    } else if (series.get(j).getFloorLevel() == ef) {
                        s = series.get(j);
                    }
                }
                if (e != null && s != null) {
                    o2 = oneSourceSearch(set, g, s);
                    temp = d1.get(e.getPoiId()) + g.get(e).get(s) + ((HashMap<Integer, Float>)o2[0]).get(end.getPoiId());
                    if (temp < min) {
                        d2 = (HashMap<Integer, Float>)o2[0];
                        pi2 = (HashMap<Integer, Poi>)o2[1];
                        preEnd = e;
                        reStart = s;
                    }
                }
            }
            if (d2 == null) {
                return null;
            } else {
                LinkedList<NavigationInfo> n = addNav(null, d1, pi1, start, preEnd);
                n.addFirst(new NavigationInfo(new Location(preEnd),new Location(reStart),(float)(Math.PI / 2),
                        (sf < ef ? "向下" : "向上"),reStart.getName(),g.get(preEnd).get(reStart)));
                n = addNav(null, d2, pi2, reStart, end);
                return n;
            }
        }
    }

    public static LinkedList<NavigationInfo> addNav(LinkedList<NavigationInfo> nav, HashMap<Integer, Float> d,
                                        HashMap<Integer, Poi> pi, Poi start, Poi end) {
        if (nav == null) {
            nav = new LinkedList();
        }
        Poi to = end;
        Poi from = pi.get(end.getPoiId());
        float turn;//rad
        String turnDiscription;
        nav.addFirst(new NavigationInfo(new Location(from),new Location(to),(float)0.0,"抵达终点",to.getName(),
                d.get(to.getPoiId()) - d.get(from.getPoiId())));
        if (from == start) {
            return nav;
        }
        Poi next = to;
        to = from;
        from = pi.get(from.getPoiId());
        while (true) {
            turn = (float)((2 * Math.PI - Math.atan((next.getY() - to.getY()) / (next.getX() - to.getX()))
                    + Math.atan((to.getY() - from.getY()) / (to.getX() - from.getX()))) % (2 * Math.PI));
            if (turn >= (Math.PI * 17 / 9) && turn <= (Math.PI / 9)) {
                turnDiscription = "直行";
            } else if (turn < Math.PI) {
                turnDiscription = "向右转";
            } else {
                turnDiscription = "向左转";
            }
            nav.addFirst(new NavigationInfo(new Location(from),new Location(to),turn,turnDiscription,to.getName(),
                    d.get(to.getPoiId()) - d.get(from.getPoiId())));
            if (from == start) {
                return nav;
            }
        }
    }
}
