package Test;

import Model.ColName;
import Model.MongoDAO;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.*;

public class AdvanceTest {

    /**
     * 课程选课人数的分布
     * @return
     */
    public static DefaultCategoryDataset StudentNumDistribution(){

      Iterator<DBObject> itero = SevenTest.Answers[2].answer();

        //统计结果
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

        HashMap<String, Integer> counter = new HashMap();
        counter.put("<20",0);
        counter.put("<40",0);
        counter.put("<60",0);
        counter.put("<80",0);
        counter.put("<100",0);
        counter.put(">100",0);

        while(itero.hasNext()){
            DBObject course = itero.next();


            int size = (int) course.get("size");


            if(size<20){
                counter.replace("<20",counter.get("<20")+1);
            }else if(size<40){
                counter.replace("<40",counter.get("<40")+1);
            }else if(size<60){
                counter.replace("<60",counter.get("<60")+1);
            }else if(size<80){
                counter.replace("<80",counter.get("<80")+1);
            }else if(size<100) {
                counter.replace("<100",counter.get("<100")+1);
            }else {
                counter.replace(">100",counter.get(">100")+1);
            }


        }



        dataset.addValue( counter.get("<20"),"Size","<20");
        dataset.addValue( counter.get("<40"),"Size","<40");
        dataset.addValue( counter.get("<60"),"Size","<60");
        dataset.addValue( counter.get("<80"),"Size","<80");
        dataset.addValue( counter.get("<100"),"Size","<100");
        dataset.addValue( counter.get(">100"),"Size",">100");

        return dataset;
    }

    /**
     * 平均成绩分布
     */
    public  static DefaultCategoryDataset AvgScoreDistribution(){

        ListIterator<DBObject> liter = SeventhTest.six().listIterator();



        //统计结果
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );


        HashMap<String, Integer> counter = new HashMap();
        counter.put("0~60",0);
        counter.put("60~80",0);
        counter.put("80~100",0);
        counter.put("100~",0);

        while(liter.hasNext()){
            DBObject course = liter.next();
            int AvgScore = (int) course.get("AvgScore");
            if(AvgScore<60){
                counter.replace("0~60",counter.get("0~60")+1);
            }else if(AvgScore<80) {
                counter.replace("60~80",counter.get("60~80")+1);
            }else if(AvgScore<100){
                counter.replace("80~100",counter.get("80~100")+1);
            }else{
                counter.replace("100~",counter.get("100~")+1);
            }
        }





        dataset.addValue( counter.get("0~60"),"AVG","0~60");
        dataset.addValue( counter.get("60~80"),"AVG","60~80");
        dataset.addValue( counter.get("80~100"),"AVG","80~100");
        dataset.addValue( counter.get("100~"),"AVG","100~");
        return dataset;

    }

    public static DefaultCategoryDataset StudentAvgScoreDistribution(){

        ListIterator<DBObject> liter = SeventhTest.getStudentAvg().listIterator();



        //统计结果
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

        HashMap<String, Integer> counter = new HashMap();
        counter.put("0~60",0);
        counter.put("60~80",0);
        counter.put("80~100",0);
        counter.put("100~",0);

        while(liter.hasNext()){
            DBObject course = liter.next();
            int AvgScore = (int) course.get("AvgScore");
            if(AvgScore<60){
                counter.replace("0~60",counter.get("0~60")+1);
            }else if(AvgScore<80) {
                counter.replace("60~80",counter.get("60~80")+1);
            }else if(AvgScore<100){
                counter.replace("80~100",counter.get("80~100")+1);
            }else{
                counter.replace("100~",counter.get("100~")+1);
            }
        }





        dataset.addValue( counter.get("0~60"),"AVG","0~60");
        dataset.addValue( counter.get("60~80"),"AVG","60~80");
        dataset.addValue( counter.get("80~100"),"AVG","80~100");
        dataset.addValue( counter.get("100~"),"AVG","100~");
        return dataset;
    }






}
