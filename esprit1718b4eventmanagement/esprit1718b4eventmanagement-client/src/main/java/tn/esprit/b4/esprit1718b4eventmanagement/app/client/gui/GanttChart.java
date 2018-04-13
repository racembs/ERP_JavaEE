package tn.esprit.b4.esprit1718b4eventmanagement.app.client.gui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.PreventiveWork;
import tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.WorkPrevServiceRemote;

public class GanttChart extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     * @throws NamingException 
     */
    public GanttChart(final String title) throws NamingException {

        super(title);

        final IntervalCategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        
        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * [url=http://www.object-refinery.com/jfreechart/guide.html]Object Refinery Limited - JFreeChart[/url]                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
    /**
     * Creates a sample dataset for a Gantt chart.
     *
     * @return The dataset.
     * @throws NamingException 
     */
    public static IntervalCategoryDataset createDataset() throws NamingException {
    	Context contextkk;
		 contextkk = new InitialContext();
	 //String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
	 WorkPrevServiceRemote proxypp=(WorkPrevServiceRemote) contextkk.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorkPrevService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorkPrevServiceRemote");
	
	 List<PreventiveWork> list2 = proxypp.DisplayPWorks();
    		 ArrayList<String> machines1 = new ArrayList<String>();
        	 for (PreventiveWork u: list2){
        		machines1.add(u.getObjet()+u.getTechnology()) ;
        		 }
        	 int i=0;
        	 final TaskSeriesCollection collection = new TaskSeriesCollection();
        	 String[] machines = machines1.toArray(new String[0]);
        	 for (PreventiveWork u: list2){
        		 
        		 Calendar cal = Calendar.getInstance();
        		 cal.setTime(u.getStartDate());
        		 int year = cal.get(Calendar.YEAR);
        		 int month = cal.get(Calendar.MONTH);
        		 int day = cal.get(Calendar.DAY_OF_MONTH);
        		 Calendar cale = Calendar.getInstance();
        		 cale.setTime(u.getEndDate());
        		 int yeare= cale.get(Calendar.YEAR);
        		 int monthe = cale.get(Calendar.MONTH);
        		 int daye = cale.get(Calendar.DAY_OF_MONTH);
        final TaskSeries s1 = new TaskSeries("Scheduled");
        s1.add(new Task(u.getObjet(),
               new SimpleTimePeriod(date(day, month, year),
                                    date(daye, monthe, yeare))));
       
        collection.add(s1);}
   

        return collection;
    }

    /**
     * Utility method for creating <code>Date</code> objects.
     *
     * @param day  the date.
     * @param month  the month.
     * @param year  the year.
     *
     * @return a date.
     */
    private static Date date(final int day, final int month, final int year) {

        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        final Date result = calendar.getTime();
        return result;

    }
        
    /**
     * Creates a chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return The chart.
     */
    private JFreeChart createChart(final IntervalCategoryDataset dataset) {
        final JFreeChart chart = ChartFactory.createGanttChart(
            "Gantt Chart Preventive works",  // chart title
            "Preventive works",              // domain axis label
            "Date",              // range axis label
            dataset,             // data
            true,                // include legend
            true,                // tooltips
            false                // urls
        );    
        return chart;    
    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     * @throws NamingException 
     */
 

}