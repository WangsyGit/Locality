package nearby;

import java.io.ObjectOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by YI on 2016/2/6.
 */
public class MakeMap {
    public void main(String[] args) {
        Map map = new Map();

        //floor 1
        Floor f1 = new Floor();
        f1.addNode(new Locale(0,0,"101","教室 空闲时间：星期一"));
        f1.addNode(new Locale(0,0,"102","会议室 负责教师：王某某 电话："));
        f1.addNode(new Node(0,0,1,0));
        f1.addNode(new Locale(0,0,"103","自习教室"));
        f1.addNode(new Locale(0,0,"104","教室  空闲时间：星期二下午"));
        f1.addNode(new Locale(0,0,"105","教务处"));
        f1.addNode(new Node(0,0,2,0));
        f1.addNode(new Locale(0,0,"106","教室 空闲时间：星期五"));
        int[][] g1 = {
                {0,1,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,1},
                {1,0,1,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,1,0,1,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,1,0,1,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,1,0,1,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,1,0,1,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,1,0,1},
                {1,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,1,0}};
        f1.setGraph(g1);
        map.addFloor(f1);

        //floor 2
        Floor f2 = new Floor();
        f2.addNode(new Locale(0,0,"201","教室 空闲时间：星期一晚"));
        f2.addNode(new Locale(0,0,"202","会议室 负责教师：张某某 电话："));
        f2.addNode(new Node(0,0,1,0));
        f2.addNode(new Locale(0,0,"203","自习教室"));
        f2.addNode(new Locale(0,0,"204","教室  空闲时间：星期二下午"));
        f2.addNode(new Locale(0,0,"205","教务处"));
        f2.addNode(new Node(0,0,2,0));
        f2.addNode(new Locale(0,0,"206","教室 空闲时间：星期五"));
        f2.addNode(new Locale(0,0,"",""));
        f2.addNode(new Locale(0,0,"",""));
        f2.addNode(new Locale(0,0,"",""));
        int[][] g2 = {
                {0,1,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,1},
                {1,0,1,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,1,0,1,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,1,0,1,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,1,0,1,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,1,0,1,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,1,0,1},
                {1,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,1,0}};
        f2.setGraph(g2);
        map.addFloor(f2);

        //make map
        try
        {
            ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("software.map")));
            output.writeObject(map);
            output.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
