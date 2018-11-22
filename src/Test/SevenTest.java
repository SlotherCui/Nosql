package Test;

import Model.ColName;
import Model.MongoDAO;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
            public void answer() {
                SeventhTest.one();
            }
        };
        Answers[1] = new Answer() {
            @Override
            public void answer() {
                SeventhTest.two();
            }
        };
        Answers[2] = new Answer() {
            @Override
            public void answer() {
                SeventhTest.three();
            }
        };
        Answers[3] = new Answer() {
            @Override
            public void answer() {
                SeventhTest.four();
            }
        };
        Answers[4] = new Answer() {
            @Override
            public void answer() {
                SeventhTest.five();
            }
        };
        Answers[5] = new Answer() {
            @Override
            public void answer() {
                SeventhTest.six();
            }
        };
        Answers[6] = new Answer() {
            @Override
            public void answer() {
                SeventhTest.seven();
            }
        };
        Answers[7] = new Answer() {
            @Override
            public void answer() {
                SeventhTest.eight();
            }
        };
        Answers[8] = new Answer() {
            @Override
            public void answer() {
                SeventhTest.nine();
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
