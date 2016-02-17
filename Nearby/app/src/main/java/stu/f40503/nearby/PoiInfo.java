package stu.f40503.nearby;

import java.util.ArrayList;
import java.util.HashMap;

import com.ids.sdk.android.model.Poi;

/**
 * Created by YI on 2016/2/14.
 */
public class PoiInfo {
    public static final int STAIR = 0;
    public static final int ESCALATOR = 1;
    public static final int ELEVATOR = 2;
    private HashMap<Integer, Object[]> poiList = new HashMap();
    private HashMap<Integer, HashMap<Integer, Float>> graph = new HashMap();
    private ArrayList<ArrayList<Poi>> stairs = new ArrayList();
    private ArrayList<ArrayList<Poi>> escalators = new ArrayList();
    private ArrayList<ArrayList<Poi>> elevators = new ArrayList();
    private PoiInfo(java.util.Set<Poi> set) {
        graph = new HashMap();
        /*
        * save data
        * */
    }
    private static PoiInfo single;
    //simple instance
    public static PoiInfo getInstance(java.util.Set<Poi> set) {
        if (single == null) {
            single = new PoiInfo(set);
        }
        return single;
    }
    public HashMap<Integer, HashMap<Integer, Float>> getGraph() {
        return graph;
    }
    public String getPoiInfo(int poiId) {
        return (String)poiList.get(poiId)[0];
    }
    public int getPoiType(int poiId) {
        return (int)poiList.get(poiId)[1];
    }
    public ArrayList<ArrayList<Poi>> getStairs() {
        return stairs;
    }
    public ArrayList<ArrayList<Poi>> getEscalators() {
        return escalators;
    }
    public ArrayList<ArrayList<Poi>> getElevators() {
        return elevators;
    }
    public ArrayList<ArrayList<Poi>> getSeries(int way) {
        if (way == STAIR) {
            return stairs;
        } else if (way == ESCALATOR) {
            return escalators;
        } else if (way == ELEVATOR) {
            return elevators;
        } else {
            return null;
        }
    }
}
