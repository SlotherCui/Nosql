package Control;

import Model.ColName;
import Model.MongoDAO;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.internal.MongoBatchCursorAdapter;

public class CourseService {

    /**
     * 通过SID 查找选修的课程
     * @param SID
     * @return
     */
    public static MongoCursor<DBObject> FindCourseBySID(String SID) throws  NullPointerException{

        MongoCursor<DBObject> it = MongoDAO.FindByBosn(ColName.student,"{SID:\""+SID+"\"}",new String[]{"COURSES.CID"});
        if(it.hasNext()){
            DBObject dbObject = it.next();
            System.out.println(dbObject);
            if(dbObject.get("COURSES")==null){
                throw new NullPointerException();
            }

            String course = dbObject.get("COURSES").toString();
            MongoCursor<DBObject> courses =MongoDAO.FindByBosn(ColName.course,"{$or:"+course+"}");
            System.out.println(courses);
            return courses;
        }
        throw new NullPointerException();
    }

    /**
     * 选课
     * @param SID
     * @param CID
     */
    public static void RequiredOneCourse(String SID,String CID){

        MongoDAO.Update(ColName.student,"{SID:\""+SID+"\"}","{$addToSet:{COURSES:{CID:\""+CID+"\"}}}");
        MongoDAO.Update(ColName.course,"{CID:\""+CID+"\"}","{$addToSet:{STUDENTS:{SID:\""+SID+"\"}}}");


    }

    /**
     * 添加成绩
     * @param SID
     * @param CID
     * @param Score
     */
    public static void UpdateCourseScore(String SID, String CID, String Score){

        MongoDAO.Update(ColName.student,"{SID:\""+SID+"\",\"COURSES.CID\":\""+CID+"\"}","{$set:{\"COURSES.$.SCORE\":\""+Score+"\"}}");
        MongoDAO.Update(ColName.course,"{CID:\""+CID+"\",\"STUDENTS.SID\":\""+SID+"\"}","{$set:{\"STUDENTS.$.SCORE\":\""+Score+"\"}}");
    }

    /**
     * 修改选课
     * @param SID
     * @param OldCID
     * @param NewCID
     */
    public static void UpdateCourse(String SID, String OldCID, String NewCID){

        MongoDAO.Update(ColName.student,"{SID:\""+SID+"\",\"COURSES.CID\":\""+OldCID+"\"}","{$set:{\"COURSES.$.CID\":\""+NewCID+"\"}}");

        MongoDAO.Update(ColName.course,"{CID:\""+OldCID+"\"}","{$pull:{STUDENTS:{SID:\""+SID+"\"}}}");

        MongoDAO.Update(ColName.course,"{CID:\""+NewCID+"\"}","{$addToSet:{STUDENTS:{SID:\""+SID+"\"}}}");

    }
}
