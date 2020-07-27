package View;

import Utils.Values;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.util.List;

public class ChartHelper {

    public static ChartPanel drawPieChart(DefaultPieDataset dataset){



        JFreeChart chart = ChartFactory.createPieChart("Mobile Sales",dataset,true,true,false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(50, Values.FRAM_HEIGHT * 1 / 2, 300, 270);

        return chartPanel;
    }

    public static  ChartPanel drawBarChart(DefaultCategoryDataset dataset){


        JFreeChart barChart = ChartFactory.createBarChart(
                "Courses Size Distribution",
                "Size",
                "Number",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);



        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setBounds(50, Values.FRAM_HEIGHT * 1 / 2, 300, 270);

        return chartPanel;
    }



    private DefaultPieDataset convert(DefaultCategoryDataset dataset){
        List<Comparable>columnKeys=dataset.getColumnKeys();
        List<Comparable> rowKeys = dataset.getRowKeys();


        DefaultPieDataset piedataset = new DefaultPieDataset();

            for(int j=0;j<rowKeys.size();j++){
                for(int i=0;i<columnKeys.size();i++) {
                    dataset.getValue(rowKeys.get(j),columnKeys.get(i));
                }
            }






        return piedataset;
    }



}
