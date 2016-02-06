package nearby;

/**
 * Created by YI on 2016/2/6.
 */
public class Node {
    private int x;
    private int y;
    private int type = 0; //0-common 1-stair 2-escalator 3-elevator
    private int direct = 0; //-1-down 0-all 1-up
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Node(int x, int y, int type, int direct) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.direct = direct;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setType(int type) {
        this.type = type;
    }
    public void setDirect(int direct) {
        if (type != 0) {
            this.direct = direct;
        }
    }
}
