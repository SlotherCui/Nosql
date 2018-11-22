package Test;

import java.util.LinkedList;

import Model.ColName;
import Model.MongoDAO;

public class FourTest {

    public static String[]  Tilte= new String[5];

    public static Answer[] Answers = new Answer[5];

    private static int num=0;
    public static int  next(){
        return num++%10;
    }
    public FourTest(){
        Tilte[0]="增加一个学生 201600301079 崔玉峰 男 20 1998-11-05 软件学院 2016";
        Tilte[1]="增加一个课程 300141 非关系型数据库 3";
        Tilte[2]="增加一位老师 100201 闫老师 女 软件学院";


        Answers[0]=new Answer() {
            @Override
            public LinkedList answer() {
                MongoDAO.InsertOne(ColName.student,"{SID:\"201600301079\",NAME:\"崔玉峰\",SEX:\"男\",AGE:\"20\",BIRTHDAY:\"1998-11-05\",DNAME:\"软件学院\",CLASS:\"2016\"}");
                return null;
            }
        };
        Answers[1]=new Answer() {
            @Override
            public LinkedList answer() {
                MongoDAO.InsertOne(ColName.course,"{CID:\"300141\",NAME:\"非关系型数据库\",CREDIT:\"3\"}");
                return null;
            }
        };
        Answers[2]=new Answer() {
            @Override
            public LinkedList answer() {
                MongoDAO.InsertOne(ColName.teacher,"{TID:\"100201\",NAME:\"闫中敏\",SEX:\"女\"},DNAME:\"软件学院\"");
                return null;
            }
        };





    }
    private static FourTest firstTest =null ;

    public static FourTest Initial(){
        if(firstTest==null){
            firstTest =new FourTest();
        }
        return firstTest;
    }
}
