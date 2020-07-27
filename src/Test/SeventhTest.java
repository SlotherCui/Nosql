package Test;

import Model.ColName;
import Model.MongoDAO;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;

public class SeventhTest {

    public static LinkedList<DBObject> one() {
        MongoCursor<DBObject> itero = MongoDAO.FindAll(ColName.course);

        LinkedList<DBObject> result = new LinkedList<>();
        while (itero.hasNext()) {
            DBObject course = itero.next();

            Object obj = course.get("STUDENTS");
            if (obj != null) {

                JSONArray jsonArray = JSONArray.fromObject(obj);
                if (jsonArray.size() != 0) {

                    course.put("size", jsonArray.size());
                    course.removeField("STUDENTS");
                    course.removeField("TEACHER");
                    result.add(course);
                }

            }

        }
        return result;
    }


    public static LinkedList<DBObject> two() {
        MongoCursor<DBObject> itero = MongoDAO.FindAll(ColName.student);
        LinkedList<DBObject> result = new LinkedList<>();


        while (itero.hasNext()) {
            DBObject student = itero.next();

            //遍历所有选课并求出平均成绩
            JSONArray jsonArray = JSONArray.fromObject(student.get("COURSES"));
            Iterator<JSONObject> jsonIterator = jsonArray.iterator();

            //求平均成绩
            int SumScore = 0;
            int Count = 0;
            int AvgScore = 0;
            while (jsonIterator.hasNext()) {

                JSONObject jsonObject = jsonIterator.next();

//                 jsonObject.get("SCORE");
                SumScore += (Integer) jsonObject.get("SCORE");
                Count++;
            }
            if (Count != 0)
                AvgScore = SumScore / Count;

            student.put("AvgScore", AvgScore);
            student.removeField("COURSES");

            //选出TOP 10
            for (int i = 0; i < result.size(); i++) {
                DBObject dbObject = result.get(i);
                if (Integer.parseInt(String.valueOf(dbObject.get("AvgScore"))) < AvgScore) {
                    result.add(i, student);
                    break;
                }
            }
            if (result.size() > 10) {
                result.removeLast();
            }
            if (result.size() == 0) {
                result.add(student);
            }


        }

        return result;

    }


    public static LinkedList<DBObject> three() {
        MongoCursor<DBObject> itero = MongoDAO.FindAll(ColName.student);
        LinkedList<DBObject> result = new LinkedList<>();

        while (itero.hasNext()) {
            DBObject student = itero.next();

            //遍历所有选课并求出平均成绩
            JSONArray jsonArray = JSONArray.fromObject(student.get("COURSES"));
            int size = jsonArray.size();



            student.put("size", size);
            student.removeField("COURSES");

            //选出TOP 10
            for (int i = 0; i < result.size(); i++) {
                DBObject dbObject = result.get(i);
                if (Integer.parseInt(String.valueOf(dbObject.get("size"))) < size) {
                    result.add(i, student);
                    break;
                }
            }
            if (result.size() > 10) {
                result.removeLast();
            }
            if (result.size() == 0) {
                result.add(student);
            }
        }

        return result;
    }

    public static LinkedList<DBObject> four() {
        MongoCursor<DBObject> itero = MongoDAO.FindByBosn(ColName.student,"{}",new String[]{"SID","NAME","COURSES"});

        LinkedList<DBObject> result = new LinkedList<>();
        while (itero.hasNext()) {
            DBObject student = itero.next();

            //遍历所有选课并求出最高成绩
            JSONArray jsonArray = JSONArray.fromObject(student.get("COURSES"));
            Iterator<JSONObject> jsonIterator = jsonArray.iterator();
            //选出最高成绩
            int MaxScore = 0;
            String Cid = "";
            while (jsonIterator.hasNext()) {

                JSONObject jsonObject = jsonIterator.next();
                int score = (Integer)jsonObject.get("SCORE");
                if (score > MaxScore) {
                    MaxScore = score;
                    Cid = (String) jsonObject.get("CID");
                }
            }


            //返回结果
            student.removeField("COURSES");
            student.put("Cid", Cid);
            DBObject Cname = MongoDAO.FindByBosn(ColName.course,"{\"CID\":\""+Cid+"\"}",new String[]{"NAME"}).tryNext();
            if(Cname==null){
                student.put("Cname", "");
            }else{
                student.put("Cname", Cname.get("NAME"));
            }
            student.put("MaxScore", MaxScore);
            result.add(student);
        }

        return result;
    }

    public static LinkedList<DBObject> five() {
        MongoCursor<DBObject> itero = MongoDAO.FindAll(ColName.student);
        LinkedList<DBObject> result = new LinkedList<>();

        while (itero.hasNext()) {
            DBObject student = itero.next();

            //遍历每个同学的选课记录 并选出优秀，良好，及格，不及格人数
            JSONArray jsonArray = JSONArray.fromObject(student.get("COURSES"));
            Iterator<JSONObject> jsonIterator = jsonArray.iterator();

            int Anum = 0;
            int Bnum = 0;
            int Cnum = 0;
            int Dnum = 0;
            while (jsonIterator.hasNext()) {

                JSONObject jsonObject = jsonIterator.next();
                int score = (Integer)jsonObject.get("SCORE");
                if (score > 100) {
                    Anum++;
                } else if (score > 80) {
                    Bnum++;

                } else if (score > 60) {
                    Cnum++;
                } else {
                    Dnum++;
                }
            }
            //选出结果
            student.removeField("COURSES");
            student.put("Anum", Anum);
            student.put("Bnum", Bnum);
            student.put("Cnum", Cnum);
            student.put("Dnum", Dnum);
            result.add(student);

        }

        return result;
    }

    public static LinkedList<DBObject> six() {
        MongoCursor<DBObject> itero = MongoDAO.FindAll(ColName.course);
        LinkedList<DBObject> result = new LinkedList<>();
        while (itero.hasNext()) {
            DBObject course = itero.next();

            JSONArray jsonArray = new JSONArray();
            if (course.get("STUDENTS") != null) {
                jsonArray = JSONArray.fromObject(course.get("STUDENTS"));
            }


            int SumScore = 0;
            int Count = 0;
            int AvgScore = 0;
            Iterator<JSONObject> jsonIterator = jsonArray.iterator();


            while (jsonIterator.hasNext()) {

                JSONObject jsonObject = jsonIterator.next();
                SumScore += (Integer)jsonObject.get("SCORE");
                Count++;

            }
            if (Count != 0)
                AvgScore = SumScore / Count;

            course.removeField("STUDENTS");
            course.removeField("TEACHER");
            course.put("size", jsonArray.size());
            course.put("AvgScore", AvgScore);
            result.add(course);
        }
        return result;

    }

    public static LinkedList<DBObject> seven() {
        MongoCursor<DBObject> itero = MongoDAO.FindByBosn(ColName.course,"{}",new String[]{"CID","NAME","STUDENTS"});
        LinkedList<DBObject> result = new LinkedList<>();

        while (itero.hasNext()) {
            DBObject course = itero.next();

            //遍历所有选课并求出最高成绩

            if (course.get("STUDENTS") != null) {

                //选课学生
                JSONArray students = JSONArray.fromObject(course.get("STUDENTS"));


                Iterator<JSONObject> jsonIterator = students.iterator();

                int MaxScore = 0;
                String Sid = "";


                while (jsonIterator.hasNext()) {

                    JSONObject jsonObject = jsonIterator.next();
                    int score = (Integer)jsonObject.get("SCORE");
                    if (score > MaxScore) {
                        MaxScore = score;
                        Sid = (String) jsonObject.get("SID");
                    }
                }

                //选出结果
                course.removeField("STUDENTS");
                course.removeField("TEACHER");
                course.put("Sid", Sid);
                DBObject Sname = MongoDAO.FindByBosn(ColName.student,"{\"SID\":\""+Sid+"\"}",new String[]{"NAME"}).tryNext();
                if(Sname==null){
                    course.put("Sname", "");
                }else{
                    course.put("Sname", Sname.get("NAME"));
                }



                course.put("MaxScore", MaxScore);
                result.add(course);
            }

        }
        return result;
    }


    public static LinkedList<DBObject> eight() {
        MongoCursor<DBObject> itero = MongoDAO.FindAll(ColName.course);

        LinkedList<DBObject> result = new LinkedList();
        while (itero.hasNext()) {
            DBObject course = itero.next();

            //选课学生数组
            JSONArray jsonArray = new JSONArray();
            if (course.get("STUDENTS") != null)
                jsonArray = JSONArray.fromObject(course.get("STUDENTS"));


            //求学生平均值
            int SumScore = 0;
            int Count = 0;
            int AvgScore = 0;
            Iterator<JSONObject> jsonIterator = jsonArray.iterator();

            while (jsonIterator.hasNext()) {

                JSONObject jsonObject = jsonIterator.next();
                SumScore += (Integer)jsonObject.get("SCORE");
                Count++;

            }
            if (Count != 0)
                AvgScore = SumScore / Count;

            course.removeField("STUDENTS");
            course.removeField("TEACHER");
            course.put("AvgScore", AvgScore);

            //选出TOP 10
            for (int i = 0; i < result.size(); i++) {
                DBObject dbObject = result.get(i);
                if (Integer.parseInt(String.valueOf(dbObject.get("AvgScore"))) < AvgScore) {
                    result.add(i, course);
                    break;
                }
            }
            if (result.size() > 10) {
                result.removeLast();
            }
            if (result.size() == 0) {
                result.add(course);
            }


        }
        return result;
    }

    public static LinkedList<DBObject> nine() {
        MongoCursor<DBObject> itero = MongoDAO.FindAll(ColName.course);

        //top n 列表
        LinkedList<DBObject> list = new LinkedList();
        while (itero.hasNext()) {

            DBObject course = itero.next();
            JSONArray jsonArray = JSONArray.fromObject(course.get("STUDENTS"));


            course.put("size", jsonArray.size());
            course.removeField("STUDENTS");
            course.removeField("TEACHER");
            Iterator<DBObject> it = list.iterator();


            //选出TOP 10
            for (int i = 0; i < list.size(); i++) {
                DBObject dbObject = list.get(i);
                if (Integer.parseInt(String.valueOf(dbObject.get("size"))) < jsonArray.size()) {
                    list.add(i, course);
                    break;
                }
            }
            if (list.size() > 10) {
                list.removeLast();
            }
            if (list.size() == 0) {
                list.add(course);
            }


        }
        return list;

    }



    public static LinkedList<DBObject> getStudentAvg() {
        MongoCursor<DBObject> itero = MongoDAO.FindAll(ColName.student);
        LinkedList<DBObject> result = new LinkedList<>();


        while (itero.hasNext()) {
            DBObject student = itero.next();

            //遍历所有选课并求出平均成绩
            JSONArray jsonArray = JSONArray.fromObject(student.get("COURSES"));
            Iterator<JSONObject> jsonIterator = jsonArray.iterator();

            //求平均成绩
            int SumScore = 0;
            int Count = 0;
            int AvgScore = 0;
            while (jsonIterator.hasNext()) {

                JSONObject jsonObject = jsonIterator.next();

//                Object o= jsonObject.get("SCORE");
                SumScore += (Integer)jsonObject.get("SCORE");
                Count++;
            }
            if (Count != 0)
                AvgScore = SumScore / Count;

            student.put("AvgScore", AvgScore);
            student.removeField("COURSES");

            result.add(student);


        }

        return result;

    }


}
