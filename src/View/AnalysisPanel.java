package View;

import Control.DataSelector;
import Model.ColName;
import Model.MongoDAO;
import Test.SevenTest;
import Test.SeventhTest;
import Utils.Values;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

        LinkedList<Object[]> data = DataSelector.Select(SeventhTest.seven().iterator());
        Object[] colNames = DataSelector.GetFieldNames();
        table.setModel(new MyTableModel(data, colNames));


        JComboBox comboBox = new JComboBox(SevenTest.Initial().Tilte);
        comboBox.setEditable(true);
        comboBox.setFont(new Font("Menu.font", Font.PLAIN, 12));
        comboBox.setBounds(Values.FRAM_WIDTH - 200, 0, 150, 30);

        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){


                }
            }
        });
        this.add(comboBox);
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
