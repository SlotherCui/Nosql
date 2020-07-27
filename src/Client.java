import Control.DataSelector;
import Model.ColName;
import Model.MongoDAO;
import Model.MongoDBJDBC;
import Model.MongoDataRead;
import Test.AdvanceTest;
import Test.SeventhTest;
import View.MainFrame;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.bson.Document;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.io.IOException;


/**
 * 程序入口
 */
public class Client {

    public static void main(String[]args){
        //数据插入
//       try {
//            MongoDataRead.readData(ColName.student,"C:/Users/Sloth/Desktop/任务/Nosql/实验/student.xlsx");
//            MongoDataRead.readData(ColName.course,"C:/Users/Sloth/Desktop/任务/Nosql/实验/course.xlsx");
//            MongoDataRead.readData(ColName.student_course,"C:/Users/Sloth/Desktop/任务/Nosql/实验/student_course.xlsx");
//            MongoDataRead.readData(ColName.teacher,"C:/Users/Sloth/Desktop/任务/Nosql/实验/teacher.xlsx");
//            MongoDataRead.readData(ColName.teacher_course,"C:/Users/Sloth/Desktop/任务/Nosql/实验/teacher_course.xlsx");
//            System.out.println("插入成功");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InvalidFormatException e) {
//            e.printStackTrace();
//        }
//        //合并关系表
//        MongoDataRead.mergeTeacher();
//        MongoDataRead.mergeCourse();
//        MongoDataRead.mergeStudent();



        try
        {
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
            BeautyEyeLNFHelper.translucencyAtFrameInactive = true;
        }
        catch(Exception e)
        {
            //TODO exception
        }


        new MainFrame();



//        Mongo mongo=new Mongo("localhost",27017);
//        DB db = mongo.getDB("user201600301079");
//        DBCollection dbCollection = db.getCollection("course");
//        DBCollection outCollection = db.getCollection("posts_total");
//        String map = "function(){emit(this.FCID,this.NAME);}";
//        String reduce="function(key,value){return {value:2};}";
//        MapReduceCommand cmd = new MapReduceCommand(dbCollection,map ,reduce, "posts_total" , MapReduceCommand.OutputType.REPLACE,null);
//
//        MapReduceOutput out = dbCollection.mapReduce(cmd);
//
//        for(DBObject o : out.results()){
//            System.out.println(o.toString());
//        }

//        AdvanceTest.AvgScoreDistribution();
    }
}
