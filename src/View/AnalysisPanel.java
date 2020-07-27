package View;

import Control.DataSelector;
import Model.ColName;
import Model.MongoDAO;
import Test.AdvanceTest;
import Test.SevenTest;
import Test.SeventhTest;
import Utils.Values;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class AnalysisPanel extends JPanel implements  ShowTableAble{

    private DataTable table;
    public AnalysisPanel() {
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
         * 选择
         */
        JComboBox comboBox = new JComboBox(SevenTest.Initial().Tilte);
        comboBox.setEditable(true);
        comboBox.setFont(new Font("Menu.font", Font.PLAIN, 12));
        comboBox.setBounds(Values.FRAM_WIDTH - 350, Values.FRAM_HEIGHT * 3 / 4-50, 300, 30);

        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){

                    int index  = comboBox.getSelectedIndex();

                    Date datebfore = new Date();
                    LinkedList<Object[]> data = DataSelector.Select(SevenTest.Answers[index].answer());
                    Date datebafter = new Date();
                    System.out.println(datebafter.getTime()-datebfore.getTime());

                    Object[] colNames = DataSelector.GetFieldNames();
                    table.setModel(new MyTableModel(data, colNames));
                }
            }
        });
        this.add(comboBox);

        this.add(ChartHelper.drawBarChart(AdvanceTest.StudentNumDistribution()));


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
