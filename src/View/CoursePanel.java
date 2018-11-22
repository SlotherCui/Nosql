package View;

import Control.CourseService;
import Control.DataSelector;
import Model.ColName;
import Model.MongoDAO;
import Utils.Values;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;

public class CoursePanel extends JPanel  implements  TableChageAction,ShowTableAble{

    private  String SID = "";
    private DataTable table;
    private  JTextField TF;
    private  JLabel Label;

    private JButton addCourse;
    private  JTextField CTF;
    public CoursePanel() {
        this.setBounds(0, 0, Values.FRAM_WIDTH, Values.FRAM_HEIGHT);
        this.setLayout(null);

        /**
         * 数据表
         */
        table = new DataTable();
        JScrollPane scroll = new JScrollPane(table);
        scroll.setSize(Values.FRAM_WIDTH, Values.FRAM_HEIGHT / 2);
        this.add(scroll);

        /**
         * SID输入框
         */
        TF =new JTextField();
        TF.setBounds(Values.FRAM_WIDTH / 2 - 125, Values.FRAM_HEIGHT * 3 / 4-50, 150, 40);
        TF.setVisible(true);
        TF.setFont(new Font("Menu.font", Font.PLAIN, 15));
        this.add(TF);


        Label = new JLabel();
        Label.setText("根据SID查询选课记录");
        Label.setBounds(Values.FRAM_WIDTH / 2 - 125, Values.FRAM_HEIGHT * 3 / 4, 500, 40);
        Label.setVisible(true);
        Label.setFont(new Font("Menu.font", Font.PLAIN, 20));
        this.add(Label);


        /**
         * CID输入框
         */
        CTF =new JTextField();
        CTF.setBounds(Values.FRAM_WIDTH / 2+125, Values.FRAM_HEIGHT * 3 / 4-50, 150, 40);
        CTF.setVisible(false);

        CTF.setFont(new Font("Menu.font", Font.PLAIN, 15));
        this.add(CTF);

        /**
         * 添加选课按钮
         */
        addCourse = new JButton("添加选课");
        addCourse.setBounds(Values.FRAM_WIDTH / 2 +275, Values.FRAM_HEIGHT * 3 / 4-50, 70, 40);
        this.add(addCourse);
        addCourse.setVisible(false);
        addCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CourseService.RequiredOneCourse(SID,CTF.getText());
                LoadData();
            }
        });


        /**
         * 查询按钮
         */
        JButton button = new JButton("查询选课");
        button.setBounds(Values.FRAM_WIDTH / 2 +25, Values.FRAM_HEIGHT * 3 / 4-50, 70, 40);
        this.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SID = TF.getText();

                LoadData();

                Label.setText(SID+"的选修记录");
                CTF.setVisible(true);
                addCourse.setVisible(true);
            }
        });

    }

    private void LoadData() {

        try {

            LinkedList<Object[]> data = DataSelector.SelectScore(CourseService.FindCourseBySID(SID),SID);
            Object[] colName = DataSelector.GetFieldNames();

            MyTableModel tableModel =new MyTableModel(data, colName);
            tableModel.setTableChageAction(CoursePanel.this);
            table.setModel(tableModel);
        }catch (Exception el){
            table.setModel(new MyTableModel(new LinkedList<>(), new Object[]{"暂无选课"}));
        }
    }


    @Override
    public void datachange(String aValue, int rowIndex, int columnIndex) {
        System.out.println(rowIndex);
        System.out.println(columnIndex);

        String cid = (String) this.table.getModel().getValueAt(rowIndex,1);

        switch (columnIndex){
            //修改SCORE
            case 5:
                CourseService.UpdateCourseScore(SID, cid,aValue);
                break;

            //修改课程号
            case 1:
                CourseService.UpdateCourse(SID,cid,aValue);
                break;
        }




        LoadData();
    }

    @Override
    public void ShowTable(ColName colName) {
        MongoCursor<DBObject> iterable = MongoDAO.FindAll(colName);

        //获得数据
        LinkedList<Object[]> data = DataSelector.Select(iterable);
        Object[] colNames = DataSelector.GetFieldNames();

        //表格显示数据
        table.setModel(new MyTableModel(data, colNames));
    }
}
