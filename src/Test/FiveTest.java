package Test;

import java.util.Iterator;
import java.util.LinkedList;

import Model.ColName;
import Model.MongoDAO;
import com.mongodb.DBObject;

public class FiveTest {
    public static String[]  Tilte= new String[6];

    public static Answer[] Answers = new Answer[6];

    private static int num=0;
    public static int  next(){
        return num++%6;
    }
    public FiveTest(){
        Tilte[0]="将学号为201600301079的学生的年龄改成21";
        Tilte[1]="将非关系型数据库的学分改为2分";
        Tilte[2]="将教师号为100200的老师的学院改成软件学院";

        Tilte[3]="将所有教师的年龄加一";
        Tilte[0]="将所有没有性别数据的学生都改为女";
        Tilte[5]="为教师号为100201的老师增加一个选课300141";

        Answers[0]=new Answer() {
            @Override
            public Iterator<DBObject> answer() {
                MongoDAO.Update(ColName.student,"{SID:\"201600301079\"}","{$set:{AGE:21}}");
                return null;
            }
        };
        Answers[1]=new Answer() {
            @Override
            public Iterator<DBObject> answer() {
                MongoDAO.Update(ColName.course,"{NAME:\"非关系型数据库\"}","{$set:{CREDIT:\"2\"}}");
                return null;
            }
        };
        Answers[2]=new Answer() {
            @Override
            public Iterator<DBObject> answer() {
                MongoDAO.Update(ColName.teacher,"{TID:\"100200\"}","{$set:{DNAME:\"软件学院\"}}");
                return null;
            }
        };

        Answers[3]=new Answer() {
            @Override
            public Iterator<DBObject> answer() {
                MongoDAO.Update(ColName.teacher,"{AGE:{$exists:true,$type:16}}","{$inc:{AGE:1}}");
                return null;
            }
        };
        Answers[0]=new Answer() {
            @Override
            public Iterator<DBObject> answer() {
                MongoDAO.Update(ColName.student,"{SEX:\"\"}","{$set:{SEX:\"女\"}}");
                return null;
            }
        };


        Answers[5]=new Answer() {
            @Override
            public Iterator<DBObject> answer() {
                MongoDAO.Update(ColName.teacher,"{TID:\"100201\"}","{$addToSet:{COURSES:{CID:\"300141\"}}}");
                MongoDAO.Update(ColName.course,"{CID:\"300141\"}","{$set:{TEACHER:{TID:\"100201\"}}}");
                return null;
            }
        };

    }
    private static FiveTest firstTest =null ;

    public static FiveTest Initial(){
        if(firstTest==null){
            firstTest =new FiveTest();
        }
        return firstTest;
    }
}
