package nearby;

/**
 * Created by YI on 2016/2/6.
 */
public class Locale extends Node {
    private String name;
    private String info;
    public Locale(int x, int y, String name, String info) {
        super(x, y);
        this.name = name;
        this.info = info;
    }
    public String getName() {
        return name;
    }
    public String getInfo() {
        return info;
    }
}
