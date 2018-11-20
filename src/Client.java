import View.MainFrame;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;


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


//        SeventhTest.seven();

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

//        MongoCursor<DBObject> it =MongoDAO.FindByBosn(ColName.student,);
//        while(it.hasNext()){
//              System.out.println(it.next());
//        }





    }
}
