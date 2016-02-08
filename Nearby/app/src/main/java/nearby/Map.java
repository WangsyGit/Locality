package nearby;

import java.util.ArrayList;

public class Map implements java.io.Serializable
{
    private ArrayList<Floor> fl;
    public Map() {
        fl = new ArrayList<Floor>();
    }
    public void addFloor(Floor f) {
        fl.add(f);
        f.setNum(fl.size());
    }
    public void deleteFloor(Floor f) {
        fl.remove(f);
    }
}