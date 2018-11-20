package Test;

import Model.ColName;

import java.util.Arrays;

public class SelectBean {


    public ColName colname;//查询集合名
    public String query;  //查询体
    public String [] projections;//返回字段


    public SelectBean( ColName colname, String query) {
        this.colname = colname;
        this.query = query;
    }

    public SelectBean(ColName colname, String query, String[] projections) {
        this.colname = colname;
        this.query = query;
        this.projections = projections;
    }


    @Override
    public String toString() {
        return "SelectBean{" +
                "colname=" + colname +
                ", query='" + query + '\'' +
                '}';
    }
}
