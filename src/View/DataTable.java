package View;

import Model.ColName;
import Model.MongoDAO;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.LinkedList;
import java.util.Vector;

public class DataTable extends JTable{

    public DataTable(LinkedList<Object[]> data, Object[] colName){
        super(new MyTableModel(data,colName));
    }

    public DataTable(){
    }
}
class MyTableModel extends AbstractTableModel {


    private LinkedList<Object[]> data =null;
    private Object [] colName = null;

    public void setTableChageAction(TableChageAction tableChageAction) {
        this.tableChageAction = tableChageAction;
    }

    private  TableChageAction tableChageAction;


    public MyTableModel(LinkedList<Object[]> data, Object[] colName) {
        this.data = data;
        this.colName = colName;
    }


    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int column) {
        return String.valueOf(colName[column]);
    }

    @Override
    public int getColumnCount() {
        return colName.length;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex);
        this.tableChageAction.datachange((String) aValue, rowIndex, columnIndex);

    }
}
interface TableChageAction{
   void  datachange(String aValue, int rowIndex, int columnIndex);
}