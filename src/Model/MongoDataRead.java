package Model;

import Utils.ExcelTool;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MongoDataRead {


    /**
     * 从文件读入数据，并插入到对应集合中去
     * @param colName
     * @param fileName
     */
    public static void readData(ColName colName,String fileName) throws IOException, InvalidFormatException {

        //xlsx文件数据转化为json字符串
        File file = new File(fileName);
        JSONArray jsonArray = ExcelTool.readExcel(file);


        //连接mongoDB数据库，获取collection
        MongoDatabase database = MongoDBJDBC.Initial();
        MongoCollection<DBObject> collection = database.getCollection(colName.toString(), DBObject.class);


        //遍历JsonArray将数据插入collection
        Iterator jsonArrayIterator =jsonArray.iterator();
        while(jsonArrayIterator.hasNext()){
            String json = jsonArrayIterator.next().toString();
            DBObject dbObject =(DBObject) JSON.parse(json);
            collection.insertOne(dbObject);
        }


        System.out.println("插入成功");
    }

    /**
     * 合并student表和student_course表
     */
    public static void mergeStudent() {

        //对于学生表每条选课记录追加选课记录
        MongoCursor<DBObject> cursorStudent =  MongoDAO.FindAll(ColName.student);
        while(cursorStudent.hasNext()){

            //查找每个学生的选课记录
            DBObject student =cursorStudent.next();
            Map map = student.toMap();
            String Sid = (String) map.get("SID");

            MongoCursor<DBObject> iterator = MongoDAO.FindByBosn(ColName.student_course,"{\"SID\":\""+Sid+"\"}");


            //构建每个学生的选课数组：
            JSONArray jsonArray = new JSONArray();
            while(iterator.hasNext()){
                DBObject sc =  iterator.next();
                Map ClassMap = sc.toMap();

                //课程号和成绩
                JSONObject obj = new JSONObject();
                obj.put("CID",ClassMap.get("CID"));
                obj.put("SCORE",ClassMap.get("SCORE"));

                jsonArray.add(obj);
            }

            //更新学生集合
            MongoDAO.Update(ColName.student,"{\"SID\":\""+Sid+"\"}","{\"$set\":{COURSES:"+jsonArray+"}}");

        }
    }

    /**
     * 合并course表和student_course表
     */

    public static void mergeCourse() {

        //每个课程增加选课学生的数组
        MongoCursor<DBObject> cursorStudent =  MongoDAO.FindAll(ColName.course);
        while(cursorStudent.hasNext()){

            //查找选择这堂课的学生
            DBObject student =cursorStudent.next();
            Map map = student.toMap();
            String Cid = (String) map.get("CID");

            MongoCursor<DBObject> iteratorSC = MongoDAO.FindByBosn(ColName.student_course,"{\"CID\":\""+Cid+"\"}");
            MongoCursor<DBObject> iteratorTC = MongoDAO.FindByBosn(ColName.teacher_course,"{\"CID\":\""+Cid+"\"}");


            //构建选课学生数组：
            JSONArray jsonArray = new JSONArray();
            while(iteratorSC.hasNext()){
                DBObject sc =  iteratorSC.next();
                Map ClassMap = sc.toMap();

                //构建每个学生json
                JSONObject obj = new JSONObject();
                obj.put("SID",ClassMap.get("SID"));
                obj.put("SCORE",ClassMap.get("SCORE"));

                jsonArray.add(obj);
            }

            //更新课程集合
            MongoDAO.Update(ColName.course,"{\"CID\":\""+Cid+"\"}","{\"$set\":{STUDENTS:"+jsonArray+"}}");

            //为每个课程添加老师
            if(iteratorTC.hasNext()) {

                DBObject tc =  iteratorTC.next();
                Map ClassMap = tc.toMap();
                JSONObject obj = new JSONObject();
                obj.put("TID",ClassMap.get("TID"));
                MongoDAO.Update(ColName.course, "{\"CID\":\"" + Cid + "\"}", "{\"$set\":{TEACHER:" + obj + "}}");
            }


        }
    }


    public static void mergeTeacher() {

        //每个老师增加上的课列表
        MongoCursor<DBObject> cursorStudent =  MongoDAO.FindAll(ColName.teacher);
        while(cursorStudent.hasNext()){

            //查找这位老师上的所有课
            DBObject student =cursorStudent.next();
            Map map = student.toMap();
            String Tid = (String) map.get("TID");

            MongoCursor<DBObject> iteratorTC = MongoDAO.FindByBosn(ColName.teacher_course,"{\"TID\":\""+Tid+"\"}");


            //构建每堂课
            JSONArray jsonArray = new JSONArray();
            while(iteratorTC.hasNext()){
                DBObject sc =  iteratorTC.next();
                Map ClassMap = sc.toMap();

                //构建每个学生json
                JSONObject obj = new JSONObject();
                obj.put("CID",ClassMap.get("CID"));

                jsonArray.add(obj);
            }

            //更新教师集合
            MongoDAO.Update(ColName.teacher,"{\"TID\":\""+Tid+"\"}","{\"$set\":{COURSES:"+jsonArray+"}}");


        }
    }







}
