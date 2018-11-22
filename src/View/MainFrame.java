package View;

import Model.ColName;
import Model.MongoDBJDBC;
import Utils.Values;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * 主界面
 */
public class MainFrame extends JFrame {

    public MainFrame(){

        super("Nosql学生管理系统");

        this.setLayout(null);

        this.setBounds(Values.FRAM_X, Values.FRAM_Y, Values.FRAM_WIDTH+50, Values.FRAM_HEIGHT);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this. setResizable(false);


        //主panel
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, Values.FRAM_WIDTH, Values.FRAM_HEIGHT);
        panel.setLayout(null);
        this.setContentPane(panel);

        //标签转换
        final JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(0, 0, Values.FRAM_WIDTH, Values.FRAM_HEIGHT);

        /**
         * 集合选择
         */

        String []  collections =new String[]{"student","course","teacher"};
        JComboBox comboBox = new JComboBox(collections);
        comboBox.setEditable(true);
        comboBox.setFont(new Font("Menu.font", Font.PLAIN, 12));
        comboBox.setBounds(Values.FRAM_WIDTH - 200, 0, 150, 30);

        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){
                   int  tableIndex = tabbedPane.getSelectedIndex();
                   ShowTableAble showTableAble = (ShowTableAble) tabbedPane.getComponentAt(tableIndex);

                    showTableAble.ShowTable(ColName.valueOf(collections[comboBox.getSelectedIndex()]));
                }
            }
        });
        this.add(comboBox);



        tabbedPane.addTab("实验三",new MainPanel("实验三 使用Java做简单查询","查询"));
        tabbedPane.addTab("实验四",new MainPanel("实验三 使用Java做简单数据插入","插入"));
        tabbedPane.addTab("实验五",new MainPanel("实验三 使用Java做数据更新","更新"));
        tabbedPane.addTab("实验六",new CoursePanel());
        tabbedPane.addTab("实验七",new AnalysisPanel());
        panel.add(tabbedPane);

        tabbedPane.setVisible(true);
        this.setVisible(true);
    }


}
