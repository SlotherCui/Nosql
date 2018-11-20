package View;

import Control.DataSelector;
import Model.*;
import Test.FiveTest;
import Test.FourTest;
import Test.ThirdTest;
import Test.SelectBean;
import Utils.Values;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * 数据表面板
 */
public class MainPanel extends JPanel  implements ActionListener{

    private DataTable table;
    private  JLabel label;

    private  String function;
    public MainPanel(String labelText,String function) {


        this.setBounds(0, 0, Values.FRAM_WIDTH, Values.FRAM_HEIGHT);
        this.setLayout(null);

        this.function = function;
        /**
         * 数据表
         */
        table = new DataTable();
        JScrollPane scroll = new JScrollPane(table);
        scroll.setSize(Values.FRAM_WIDTH, Values.FRAM_HEIGHT / 2);
        this.add(scroll);



        /**
         * 显示问题的标签
         */
        label =new JLabel();
        label.setBounds(Values.FRAM_WIDTH / 2 - 125, Values.FRAM_HEIGHT * 3 / 4-100, 500, 50);
        label.setVisible(true);
        label.setText(labelText);
        label.setFont(new Font("Menu.font", Font.PLAIN, 20));
        this.add(label);
        /**
         * 查询按钮
         */
        JButton button = new JButton("下一个"+function);
        button.setBounds(Values.FRAM_WIDTH / 2 - 75, Values.FRAM_HEIGHT * 3 / 4-50, 150, 50);
        button.addActionListener(this);
        this.add(button);

//        ShowTable(ColName.student);

    }

    /**
     * 点击按钮事件
     * @param e
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if(function.equals("查询")) {
            select();
        }else if(function.equals("插入")){
            insert();
        }else if(function.equals("更新")){
            update();
        }
//        select();
    }


    public void update(){
        int num  = FiveTest.Initial().next();
        label.setText(FiveTest.Initial().Tilte[num]);
       FiveTest.Initial().Answers[num].answer();

        ShowTable(ColName.student);
    }

    public void insert(){
        int num  = FourTest.Initial().next();
        label.setText(FourTest.Initial().Tilte[num]);
        FourTest.Initial().Answers[num].answer();

        ShowTable(ColName.student);
    }


    public void select(){
        int num  = ThirdTest.Initial().next();

        label.setText(ThirdTest.Initial().Tilte[num]);

        //获得查找体
        SelectBean selectBean = ThirdTest.Initial().Answer[num];


        //数据库查找结果
        MongoCursor<DBObject> iterable = null;


        //选择显示字段 若为null则显示全部字段
        Object[] colName = selectBean.projections;
        if (colName!= null) {
            iterable = MongoDAO.FindByBosn(selectBean.colname, selectBean.query,colName);
        }else{
            iterable = MongoDAO.FindByBosn(selectBean.colname, selectBean.query);
        }


        //获得数据
        LinkedList<Object[]> data = DataSelector.Select(iterable);
        colName = DataSelector.GetFieldNames();

        //表格显示数据
        table.setModel(new MyTableModel(data, colName));

    }

    /**
     * 展示整个集合
     * @param colName
     */
    public void ShowTable(ColName colName){
        MongoCursor<DBObject> iterable = MongoDAO.FindAll(colName);

        //获得数据
        LinkedList<Object[]> data = DataSelector.Select(iterable);
        Object[] colNames = DataSelector.GetFieldNames();

        //表格显示数据
        table.setModel(new MyTableModel(data, colNames));

    }



}


