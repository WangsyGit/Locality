package nearby;

import java.util.ArrayList;

/**
 * Created by YI on 2016/2/6.
 */
public class Floor {
    private ArrayList<Node> nd;
    private int[][] graph;
    private int num = 0;
    public Floor() {
        nd = new ArrayList<Node>();
    }
    public void addNode(Node n) {
        nd.add(n);
    }
    public void deleteNode(Node n) {
        nd.remove(n);
    }
    public void setNum(int num) {
        this.num = num;
    }
    public void setGraph(int[][] graph) {
        this.graph = graph;
    }
    public Node getNode(int i) {
        if (i >= 0 && i < nd.size()) {
            return nd.get(i);
        } else {
            return null;
        }
    }
    public int getNum() {
        return num;
    }

    public static Path searchPath(ArrayList<Node> nd, Node start, Node end, int[][] g) {
        int[] d = new int[nd.size()];
        Node[] pi = new Node[nd.size()];
        for (int i = 0;i < nd.size();i++) {
            d[i] = Integer.MAX_VALUE;
            pi[i] = null;
        }
        d[nd.indexOf(start)] = 0;
        ArrayList<Node> S = new ArrayList<>();
        ArrayList<Node> Q = new ArrayList<>();
        for (int i = 0; i < nd.size(); i++) {
            Q.add(nd.get(i));
        }
        while(Q.size() > 0) {
            Node u = Q.get(0);
            int key = 0;
            for (int i = 1; i < Q.size(); i++) {
                if(d[i] < d[key]) {
                    u = Q.get(i);
                    key = i;
                }
            }
            Q.remove(key);
            S.add(u);
            int uNum = nd.indexOf(u);
            for (int i = 0; i < nd.size(); i++) {
                int dis = g[uNum][i];
                if (dis != 0 && dis < Integer.MAX_VALUE) {
                    int weight = d[uNum] + g[uNum][i];
                    if(d[i] > weight) {
                        d[i] = weight;
                        pi[i] = u;
                    }
                }
            }
        }
        Path p = new Path();
        int now = nd.indexOf(end);
        int temp;
        while (pi[now] != start) {
            temp = nd.indexOf(pi[now]);
            p.addNode(nd.get(now));
            p.addLength(g[temp][now]);
            now = temp;
        }
        p.addNode(start);
        p.addLength(g[nd.indexOf(start)][now]);
        return p;
    }
}
