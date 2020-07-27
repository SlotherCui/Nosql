package Test;

import Control.DataSelector;
import Model.ColName;
import Model.MongoDAO;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.conversions.Bson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SevenTest {

    public static String[]  Tilte= new String[9];

    public static Answer[] Answers = new Answer[9];

    private static int num=0;

    public SevenTest() {

        Tilte[0]="列出有学生选课的所有课程名称（distinct）";
        Tilte[1]="找出平均成绩排名前10的学生";
        Tilte[2]="找出选课数目排名前10的学生";
        Tilte[3]="找出每位同学的最高成绩以及最高成绩对应的课程名";
        Tilte[4]="求每位同学的成绩分布：优秀、良好、合格、不合格的课程门数";
        Tilte[5]="求每门课程的选修人数和平均成绩";
        Tilte[6]="求每门课程最高成绩以及最高成绩对应的学生姓名";
        Tilte[7]="求平均成绩排名前10的课程";
        Tilte[8]="求选课人数排名前10的课程";


        Answers[0] = new Answer() {
            @Override
            public Iterator<DBObject> answer() {
                return MongoDAO.FindByBosn(ColName.course,"{$where:\"this.STUDENTS.length>0\"}");
            }
        };
        Answers[1] = new Answer() {
            @Override
            public Iterator<DBObject> answer() {


//                List<Bson>list = new LinkedList();
//                list.add(BasicDBObject.parse("{\"$unwind\": \"$COURSES\"}"));
//                list.add(BasicDBObject.parse("{\"$group\": {_id:{\"SID\":\"$SID\",\"NAME\":\"$NAME\"},avg: {$avg: \"$COURSES.SCORE\"}}}"));
//                list.add(BasicDBObject.parse("{\"$sort\": {\"avg\": -1}}"));
//                list.add(BasicDBObject.parse("{\"$limit\": 10}"));
//                list.add(BasicDBObject.parse("{\"$project\":{\"_id\":\"$_id.SID\",\"NAME\":\"$_id.NAME\",\"AVG\":\"$avg\"}}"));
//                return  MongoDAO.Aggregate(ColName.student,list).iterator();
                return  SeventhTest.two().iterator();
            }
        };
        Answers[2] = new Answer() {
            @Override
            public Iterator<DBObject>answer() {
//                List<Bson>list = new LinkedList();
//                list.add(BasicDBObject.parse("{\"$group\": {\"_id\": {\"SID\": \"$SID\",\"NAME\":\"$NAME\",\"COURSES\": \"$COURSES\"}}}"));
//                list.add(BasicDBObject.parse("{\"$project\": {\"_id\": \"$_id.SID\",\"item\": 1,\"NAME\":\"$_id.NAME\",\"size\": {\"$size\": \"$_id.COURSES\"}}}"));
//                list.add(BasicDBObject.parse("{\"$sort\": {\"size\": -1}}"));
//                return  MongoDAO.Aggregate(ColName.student,list).iterator();
                return SeventhTest.three().iterator();
            }
        };
        Answers[3] = new Answer() {
            @Override
            public Iterator<DBObject> answer() {
                return SeventhTest.four().iterator();
            }
        };
        Answers[4] = new Answer() {
            @Override
            public Iterator<DBObject>answer() {
                return SeventhTest.five().iterator();
            }
        };
        Answers[5] = new Answer() {
            @Override
            public Iterator<DBObject> answer() {
//                List<Bson>list = new LinkedList();
//                list.add(BasicDBObject.parse("{\"$group\": {_id: {\"CID\": \"$CID\",\"NAME\": \"$NAME\",\"STUDENTS\": \"$STUDENTS\"}}}"));
//                list.add(BasicDBObject.parse("{\"$project\": {\"CID\": \"$_id.CID\",\"NAME\": \"$_id.NAME\",\"STUDENTS\":\"$_id.STUDENTS\",\"item\": 1,\"size\": {\"$size\": \"$_id.STUDENTS\"}}}"));
//                list.add(BasicDBObject.parse("{\"$unwind\": \"$STUDENTS\"}"));
//                list.add(BasicDBObject.parse("{\"$group\": {_id: {\"CID\": \"$CID\",\"size\":\"$size\",\"NAME\": \"$_id.NAME\",},avg: {$avg: \"$STUDENTS.SCORE\"}}}"));
//                list.add(BasicDBObject.parse("{\"$project\": {\"_id\": \"$_id.CID\",\"NAME\": \"$_id.NAME\",\"AVG\": \"$avg\",\"SIZE\":\"$_id.size\"}}"));
//                return  MongoDAO.Aggregate(ColName.course,list).iterator();
                return SeventhTest.six().iterator();
            }
        };
        Answers[6] = new Answer() {
            @Override
            public Iterator<DBObject> answer() {
                return SeventhTest.seven().iterator();
            }
        };
        Answers[7] = new Answer() {
            @Override
            public Iterator<DBObject> answer() {
//                List<Bson>list = new LinkedList();
////                list.add(BasicDBObject.parse("{\"$unwind\": \"$STUDENTS\"}"));
////                list.add(BasicDBObject.parse("{\"$group\": {_id: {\"CID\":\"$CID\",\"NAME\":\"$NAME\"},avg: {$avg: \"$STUDENTS.SCORE\"}}}"));
////                list.add(BasicDBObject.parse("{\"$sort\": {\"avg\": -1}}"));
////                list.add(BasicDBObject.parse("{\"$limit\": 10}"));
////                list.add(BasicDBObject.parse("{\"$project\": {\"_id\": \"$_id.CID\",\"NAME\": \"$_id.NAME\",\"AVG\": \"$avg\"}}"));
////                return  MongoDAO.Aggregate(ColName.course,list).iterator();

                return SeventhTest.eight().iterator();
            }
        };
        Answers[8] = new Answer() {
            @Override
            public Iterator<DBObject>answer() {
//                List<Bson>list = new LinkedList();
//                list.add(BasicDBObject.parse("{\"$group\": {\"_id\": {\"CID\": \"$CID\",\"NAME\": \"$NAME\",\"STUDENTS\": \"$STUDENTS\",}}}"));
//                list.add(BasicDBObject.parse("{\"$project\": {\"_id\": \"$_id.CID\",\"NAME\": \"$_id.NAME\",\"item\": 1,\"size\": {\"$size\": \"$_id.STUDENTS\"}}}"));
//                list.add(BasicDBObject.parse("{\"$sort\": {\"size\": -1}}"));
//                list.add(BasicDBObject.parse("{\"$limit\": 10}"));
//                return  MongoDAO.Aggregate(ColName.course,list).iterator();
                return SeventhTest.nine().iterator();
            }
        };



    }

    public static int  next(){
        return num++%9;
    }


    private static SevenTest firstTest =null ;

    public static SevenTest Initial(){
        if(firstTest==null){
            firstTest =new SevenTest();
        }
        return firstTest;
    }
}
