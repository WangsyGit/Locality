package nearby;

import java.util.ArrayList;

/**
 * Created by YI on 2016/2/6.
 */
public class Path {
    private int length = 0;
    private ArrayList<Node> path;
    public Path() {
        path = new ArrayList<Node>();
    }
    public void addNode(Node n) {
        path.add(n);
    }
    public void addLength(int add) {
        length += add;
    }
    public Node getNode(int i) {
        return path.get(path.size() - i - 1);
    }
}
