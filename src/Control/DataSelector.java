package Control;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;

import java.util.*;

/**
 * 对从数据库返回的json数据，筛选字段。
 *
 */
public class DataSelector {

    private static Object[] FieldNames = null;
    /**
     *获得字段数据
     * @param iterable
     */
    public static LinkedList<Object[]> Select(MongoCursor<DBObject> iterable){


        LinkedList<Object[]> data = new LinkedList<>();
        //遍历MongDB返回数据
        int dataNum = 0;
        while(iterable.hasNext()) {
            Map map=iterable.next().toMap();

            data.add(map.values().toArray());

            if(dataNum==0){
                FieldNames = map.keySet().toArray();
            }
            dataNum++;
        }

        return data;

    }

    public static LinkedList<Object[]> SelectScore(MongoCursor<DBObject> iterable,String SID){
        LinkedList<Object[]> data = new LinkedList<>();
        //遍历MongDB返回数据
        int dataNum = 0;

        while(iterable.hasNext()) {
            Map map=iterable.next().toMap();


            BasicDBList dbList = (BasicDBList) map.get("STUDENTS");
            for(int i=0;i<dbList.size();i++){
                DBObject dbObject =(DBObject)dbList.get(i);
                String sid = (String)dbObject.get("SID");

                if(sid.equals(SID)){
                    map.put("SCORE",dbObject.get("SCORE"));
                    break;
                }
            }

            map.remove("STUDENTS");
            map.remove("TEACHER");
            data.add(map.values().toArray());

            if(dataNum==0){
                FieldNames = map.keySet().toArray();
            }
            dataNum++;
        }
        return data;
    }

    /**
     * 获得全部字段名
     * @return
     */
    public static Object[] GetFieldNames(){
        return  FieldNames;
    }
}
