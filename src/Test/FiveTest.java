package Test;

import java.util.LinkedList;

import Model.ColName;
import Model.MongoDAO;

public class FiveTest {
    public static String[]  Tilte= new String[5];

    public static Answer[] Answers = new Answer[5];

    private static int num=0;
    public static int  next(){
        return num++%10;
    }
    public FiveTest(){
        Tilte[0]="将学号为201600301079的学生的年龄改成21";
        Tilte[1]="将非关系型数据库的学分改为2分";
        Tilte[2]="将";


        Answers[0]=new Answer() {
            @Override
            public LinkedList answer() {
                MongoDAO.Update(ColName.student,"{SID:\"201600301079\"}","{$set:{AGE:21}}");
                return null;
            }
        };
        Answers[1]=new Answer() {
            @Override
            public LinkedList answer() {
                MongoDAO.Update(ColName.course,"{NAME:\"非关系型数据库\"}","{$set:{CREDIT:\"2\"}}");
                return null;
            }
        };
        Answers[2]=new Answer() {
            @Override
            public LinkedList answer() {

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
