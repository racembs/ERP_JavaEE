package tn.esprit.b4.esprit1718b4eventmanagement.mBeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.MeterGaugeChartModel;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.PreventiveWork;
import tn.esprit.b4.esprit1718b4eventmanagement.services.WorkPrevService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsService;


@ManagedBean
public class AnimateTraderBean implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LineChartModel animatedModel1;

	    private BarChartModel animatedModel2;
	@EJB
	WorksUsService ws=new WorksUsService();
	@EJB
	WorkPrevService wp=new WorkPrevService();
	private List<Object> l;

	    private MeterGaugeChartModel meterGaugeModel2;
	 
	  
	    @PostConstruct
	    public void init() {
	        createAnimatedModels();
	        createMeterGaugeModels();
	    }
	 
	    public LineChartModel getAnimatedModel1() {
	        return animatedModel1;
	    }
	 
	    public BarChartModel getAnimatedModel2() {
	        return animatedModel2;
	    }
	    public 	List<Object> getL()
		{return ws.Top5ProdTech();
		}
	    @SuppressWarnings("static-access")
		private void createAnimatedModels() {
	    	
	        animatedModel1 = initLinearModel();
	        animatedModel1.setTitle("Last 3 month PMP indicator");
	        animatedModel1.getAxes().put(AxisType.X, new CategoryAxis("Months"));
	        animatedModel1.setShowPointLabels(true);
	        animatedModel1.setAnimate(true);
	        animatedModel1.setSeriesColors("FF0000");
	        animatedModel1.setLegendPosition("se");
	        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
	        yAxis.setMin(0);
	        yAxis.setMax(100);
	        yAxis.setLabel("%"); 
	        animatedModel2 = initBarModel();
	        animatedModel2.setTitle("Last 4 weeks PMC indicator");
	        animatedModel2.setAnimate(true);
	        animatedModel2.setLegendPosition("ne");
	        animatedModel2.setShowDatatip(true);
	     
	        yAxis = animatedModel2.getAxis(AxisType.Y);
	        
	        yAxis.setMin(0);
	        yAxis.setMax(100);
	        yAxis.setLabel("%");
	        
	    }
	     
	    private BarChartModel initBarModel() {
	        BarChartModel model = new BarChartModel();
	        DecimalFormat df=new DecimalFormat("#%");
	        ChartSeries week1 = new ChartSeries();
	        week1.setLabel("Last 4 weeks PMC indicator");
	        week1.set("week1",ws.calculPMCw1()*100 );
	   
	        week1.set("week2",ws.calculPMCw2()*100);
	        
	       
	        week1.set("week3",ws.calculPMCw3()*100);
	        
	       
	        week1.set("week4",ws.calculPMCw4()*100);
	        
	        model.addSeries(week1);
	       
	      
	        return model;
	    }
	     
	    private LineChartModel initLinearModel() {
	        LineChartModel model = new LineChartModel();
	        Date today = new Date(); // Fri Jun 17 14:54:28 PDT 2016 
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			 LocalDate currentDate = LocalDate.now();
			 Month m = currentDate.getMonth();
			
			 Month month2= m.plus(1);
			//int month1 = cal.get(Calendar.MONTH)+1;
			//int month2 = cal.get(Calendar.MONTH);
			Month month3 = m.plus(2);
	        
	       
	 
	       ChartSeries series2 = new ChartSeries();
	        series2.setLabel("Series 2");
	        
	        series2.set(m.toString()+"-"+currentDate.getYear(), ws.calculPMP().get(0));
	        series2.set(month2.toString()+"-"+currentDate.getYear(), ws.calculPMP().get(1));
	        series2.set(month3.toString()+"-"+currentDate.getYear(), ws.calculPMP().get(2));
	 System.out.println(ws.calculPMP().get(0));
	     
	        model.addSeries(series2);


	    
	        return model;
	        }

	 
	 
	   
	    public MeterGaugeChartModel getMeterGaugeModel2() {
	        return meterGaugeModel2;
	    }
	 
	    private MeterGaugeChartModel initMeterGaugeModel() {
	        List<Number> intervals = new ArrayList<Number>(){{
	            add(10);
	            add(25);
	            add(50);
	            add(75);
	            add(100);
	        }};
	         
	        return new MeterGaugeChartModel(140, intervals);
	    }
	 
	    private void createMeterGaugeModels() {
	      
	         
	        meterGaugeModel2 = initMeterGaugeModel();
	        meterGaugeModel2.setTitle("Preventive strategy efficiency");
	        meterGaugeModel2.setSeriesColors("66cc66,93b75f,E7E658,cc6666");
	        meterGaugeModel2.setGaugeLabel("%");
	        meterGaugeModel2.setValue(ws.WorkOntime());
	        meterGaugeModel2.setGaugeLabelPosition("bottom");
	        meterGaugeModel2.setShowTickLabels(true);
	        meterGaugeModel2.setLabelHeightAdjust(50);
	        meterGaugeModel2.setIntervalOuterRadius(100);
	        meterGaugeModel2.setValue(wp.calculPefficiency()*100);
	    }
	    
	 
}
