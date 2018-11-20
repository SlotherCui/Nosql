package Test;

import Model.ColName;

public class ThirdTest {

    public static String[]  Tilte= new String[10];

    public static SelectBean[] Answer = new SelectBean[10];

    private static int num=0;
    public static int  next(){
        return num++%10;
    }
    public ThirdTest(){
        Tilte[0]="找出年龄小于20岁的所有学生";
        Tilte[1]="找出年龄小于20岁且是软件学院的学生";
        Tilte[2]="找出学生关系中的所有学生";
        Tilte[3]="求所有学生的姓名、年龄";
        Tilte[4]="找出年龄小于20岁的学生的姓名、性别";
        Tilte[5]="检索所有课程情况";
        Tilte[6]="检索先行课号为“300001”的课程名";
        Tilte[7]="找出年龄大于50岁的老师";
        Tilte[8]="找出所有的男老师";
        Tilte[9]="找出所有在CS学院的老师";

        Answer[0]=new SelectBean(ColName.student,"{\"AGE\":{$lt:\"20\"}}");
        Answer[1]=new SelectBean(ColName.student,"{\"AGE\":{$lt:\"20\"},\"DNAME\":\"软件学院\"}");
        Answer[2]=new SelectBean(ColName.student,"{}");
        Answer[3]=new SelectBean(ColName.student,"{}",new String[]{"NAME","AGE"});
        Answer[4]=new SelectBean(ColName.student,"{\"AGE\":{$lt:\"20\"}}",new String[]{"NAME","SEX"});
        Answer[5]=new SelectBean(ColName.course,"{}",new String[]{"CID","COURSE","NAME","FCID","CREDIT"});
        Answer[6]=new SelectBean(ColName.course,"{\"FCID\":\"300001\"}",new String[]{"CID","COURSE","NAME","FCID","CREDIT"});
        Answer[7]=new SelectBean(ColName.teacher,"{\"AGE\":{$gt:\"50\"}}");
        Answer[8]=new SelectBean(ColName.teacher,"{\"SEX\":\"男\"}");
        Answer[9]=new SelectBean(ColName.teacher,"{\"DNAME\":\"计算机科学与技术学院\"}");



    }
    private static ThirdTest firstTest =null ;

    public static ThirdTest Initial(){
        if(firstTest==null){
            firstTest =new ThirdTest();
        }
        return firstTest;
    }
}
