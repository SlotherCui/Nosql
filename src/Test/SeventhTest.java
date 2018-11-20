package Test;

import Model.ColName;
import Model.MongoDAO;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class SeventhTest {

    public static void  one(){
        MongoCursor<DBObject> itero =  MongoDAO.FindAll(ColName.course);

        while(itero.hasNext()){
            DBObject dbObject =itero.next();
            JSONArray jsonArray = JSONArray.fromObject(dbObject.get("STUDENTS"));

            if(jsonArray.size()!=0){
                System.out.println(dbObject.get("NAME"));
            }

        }

    }

    public static void  two(){
        MongoCursor<DBObject> itero =  MongoDAO.FindAll(ColName.student);
        while(itero.hasNext()){
            DBObject student =itero.next();

            //遍历所有选课并求出平均成绩
            JSONArray jsonArray = JSONArray.fromObject(student.get("COURSES"));
            Iterator<JSONObject> jsonIterator=jsonArray.iterator();

            int SumScore = 0;
            int Count = 0;
            int AvgScore=0;
            while(jsonIterator.hasNext()){

                JSONObject  jsonObject= jsonIterator.next();
                SumScore+=Integer.parseInt((String) jsonObject.get("SCORE"));
                Count++;
            }
            if(Count != 0)
                AvgScore = SumScore / Count;

            System.out.print(student.get("SID")+" ");
            System.out.println(AvgScore);

        }

    }


    public static void  three(){
        MongoCursor<DBObject> itero =  MongoDAO.FindAll(ColName.student);
        while(itero.hasNext()){
            DBObject student =itero.next();

            //遍历所有选课并求出平均成绩
            JSONArray jsonArray = JSONArray.fromObject(student.get("COURSES"));
            int size = jsonArray.size();
            System.out.println(size);

        }
    }

    public static  void  four(){
        MongoCursor<DBObject> itero =  MongoDAO.FindAll(ColName.student);
        while(itero.hasNext()){
            DBObject student =itero.next();

            //遍历所有选课并求出最高成绩
            JSONArray jsonArray = JSONArray.fromObject(student.get("COURSES"));
            Iterator<JSONObject> jsonIterator=jsonArray.iterator();

            int MaxScore = 0;
            String Cid = "";
            while(jsonIterator.hasNext()){

                JSONObject  jsonObject= jsonIterator.next();
                int score =Integer.parseInt((String) jsonObject.get("SCORE"));
                if(score>MaxScore){
                    MaxScore=score;
                    Cid = (String) jsonObject.get("CID");
                }
            }


            System.out.print(student.get("SID")+" ");
            System.out.print(Cid+" ");
            System.out.println(MaxScore);
        }
    }

    public static  void  five(){
        MongoCursor<DBObject> itero =  MongoDAO.FindAll(ColName.student);
        while(itero.hasNext()){
            DBObject student =itero.next();

            //遍历所有选课并求出最高成绩
            JSONArray jsonArray = JSONArray.fromObject(student.get("COURSES"));
            Iterator<JSONObject> jsonIterator=jsonArray.iterator();

            int Anum = 0;
            int Bnum = 0;
            int Cnum = 0;
            int Dnum = 0;
            while(jsonIterator.hasNext()){

                JSONObject  jsonObject= jsonIterator.next();
                int score =Integer.parseInt((String) jsonObject.get("SCORE"));
                if(score>100){
                    Anum++;
                } else if (score >80) {
                    Bnum++;

                } else if(score>60){
                    Cnum++;
                }else{
                    Dnum++;
                }
            }


            System.out.print(student.get("SID")+" ");
            System.out.println(Anum+" "+Bnum+" "+Cnum+" "+Dnum);

        }
    }

    public static void  six(){
        MongoCursor<DBObject> itero =  MongoDAO.FindAll(ColName.course);
        while(itero.hasNext()){
            DBObject dbObject =itero.next();
            JSONArray jsonArray = JSONArray.fromObject(dbObject.get("STUDENTS"));

            System.out.print(dbObject.get("CID")+" ");
            System.out.print(jsonArray.size()+" ");
            int SumScore = 0;
            int Count = 0;
            int AvgScore=0;
            Iterator<JSONObject> jsonIterator=jsonArray.iterator();
            while (jsonIterator.hasNext()){

                JSONObject  jsonObject= jsonIterator.next();
                SumScore+=Integer.parseInt((String) jsonObject.get("SCORE"));
                Count++;

            }
            if(Count != 0)
                AvgScore = SumScore / Count;

            System.out.println(AvgScore);
        }

    }

    public static void  seven(){
        MongoCursor<DBObject> itero =  MongoDAO.FindAll(ColName.course);
        while(itero.hasNext()){
            DBObject course =itero.next();

            //遍历所有选课并求出最高成绩
            JSONArray jsonArray = JSONArray.fromObject(course.get("STUDENTS"));
            Iterator<JSONObject> jsonIterator=jsonArray.iterator();

            int MaxScore = 0;
            String Sid = "";
            while(jsonIterator.hasNext()){

                JSONObject  jsonObject= jsonIterator.next();
                int score =Integer.parseInt((String) jsonObject.get("SCORE"));
                if(score>MaxScore){
                    MaxScore=score;
                    Sid = (String) jsonObject.get("SID");
                }
            }


            System.out.print(course.get("CID")+" ");
            System.out.print(Sid+" ");
            System.out.println(MaxScore);
        }
    }


    public static void  eight(){
        MongoCursor<DBObject> itero =  MongoDAO.FindAll(ColName.course);
        while(itero.hasNext()){
            DBObject dbObject =itero.next();
            JSONArray jsonArray = JSONArray.fromObject(dbObject.get("STUDENTS"));

            System.out.print(dbObject.get("CID")+" ");
            int SumScore = 0;
            int Count = 0;
            int AvgScore=0;
            Iterator<JSONObject> jsonIterator=jsonArray.iterator();
            while (jsonIterator.hasNext()){

                JSONObject  jsonObject= jsonIterator.next();
                SumScore+=Integer.parseInt((String) jsonObject.get("SCORE"));
                Count++;

            }
            if(Count != 0)
                AvgScore = SumScore / Count;

            System.out.println(AvgScore);
        }
    }

    public static void  nine(){
        MongoCursor<DBObject> itero =  MongoDAO.FindAll(ColName.course);
        while(itero.hasNext()){
            DBObject dbObject =itero.next();
            JSONArray jsonArray = JSONArray.fromObject(dbObject.get("STUDENTS"));


            System.out.print(jsonArray.size()+" ");

        }
    }

}
