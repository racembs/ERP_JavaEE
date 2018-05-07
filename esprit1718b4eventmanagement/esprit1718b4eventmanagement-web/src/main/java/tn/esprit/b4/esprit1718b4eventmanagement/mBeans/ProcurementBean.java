package tn.esprit.b4.esprit1718b4eventmanagement.mBeans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.persistence.Column;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.InputStream;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.MvtApprov;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.SpareParts;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.MvtApprovService;

@FacesComponent("procurementBean")
@ManagedBean(name="procurementBean")
@SessionScoped
public class ProcurementBean implements Serializable {
	private Integer id ; 

	private Article article;
	private SpareParts spareparts;
	private int quantity;
	private Date alarmDate;
	private Date requestDate;
	private Date receptionDate;
	private int minQuantity;
	private int maxQuantity;
	private List<Article> articles;
	private String articleCode;
	private int dailyConsumption;
	private int deliveryTime;
	private List<MvtApprov> orders;
	public Paragraph ordersParagraph;
    private LineChartModel areaModel;
    private static List<MvtApprov> panierOrdres=new ArrayList<>();


	
	 
	@EJB
	ArticleService articleService;
	@EJB
	MvtApprovService approvService;
	private static final long serialVersionUID = 3350653785168926842L;
	 private static final int DEFAULT_BUFFER_SIZE = 10240; 

	 

		@PostConstruct
	    public void init() throws NamingException {
	    	AutoOrderGenerateByMinimumQuantity();
	    	createAreaModel();
	    	
			 }
	 
	public void	addToBasket(MvtApprov order) {
		panierOrdres.add(order);
	}
	public void removeFromBasket(MvtApprov order) {
		panierOrdres.remove(order);
	}
	public String getBasketSize() {
		return "Basket ("+panierOrdres.size()+")";
	}
	 
 public int getRemainingTime(MvtApprov order) {
	 java.util.Date Date=new java.util.Date();
	 int day=(order.getRequestDate().getDate()+order.getArticle().getDeliveryTime()-Date.getDate());
		if(day>0) {
			return day*24*3600;
		}
		else {
			return 1;
		}
	 }
 
 public boolean getAlarmVisibility(MvtApprov order) {
	 java.util.Date Date=new java.util.Date();
	 int day=(order.getRequestDate().getDate()+order.getArticle().getDeliveryTime()-Date.getDate());
		if(day<=0&&order.getReceptionDate()==null) {
			return true;
		}
		else {
			return false;
		}
 }
 public long getAlarmRaminingTime(MvtApprov order) {
	 java.util.Date Date=new java.util.Date();
	 long second;
	 
	 if(Date.getHours()>12) {
		 Date.setHours(Date.getHours()-12);
		 second=12*3600+((12-Date.getHours()-1)*3600)+(60-Date.getMinutes())*60+(60-Date.getSeconds());
	 }else {
		 second=((12-Date.getHours()-1)*3600)+(60-Date.getMinutes())*60+(60-Date.getSeconds());
	 }
	
			return second;
	
 }
 public String getDiplayOption(MvtApprov order) {
	 java.util.Date Date=new java.util.Date();
	 int day=(order.getRequestDate().getDate()+order.getArticle().getDeliveryTime()-Date.getDate());
	 if(order.getReceptionDate()==null) {
		
		 return "display:none";
		 
	 }
	 else return "";
	 
 }
 public String getDiplayOptionNotDelivered(MvtApprov order) {
	 java.util.Date Date=new java.util.Date();
 if(order.getReceptionDate()!=null||(Date.getDate()<order.getAlarmDate().getDate()&&Date.getHours()<12)) {
		 
		 return "display:none";
		 
	 }
	 else return "";
 }
 public boolean getVisibility(MvtApprov order) {
	 java.util.Date Date=new java.util.Date();
	 int day=(order.getRequestDate().getDate()+order.getArticle().getDeliveryTime()-Date.getDate());
		
	 if(order.getReceptionDate()!=null) {
		
			 return false;
		 }
		 
	 
	 else if(day<=0) {
		 return false;
	 }else {
		 return true; 
	 }
}
	
	 
 

 
	  public void downloadPDF() throws IOException {

	        // Prepare.
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        ExternalContext externalContext = facesContext.getExternalContext();
	        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

	        File file = new File("C:\\Users\\RBS\\Desktop\\","test.pdf");
	        BufferedInputStream input = null;
	        BufferedOutputStream output = null;

	        try {
	            // Open file.
	            input = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);

	            // Init servlet response.
	            response.reset();
	            response.setHeader("Content-Type", "application/pdf");
	            response.setHeader("Content-Length", String.valueOf(file.length()));
	            response.setHeader("Content-Disposition", "inline; filename=\"" + "test.pdf" + "\"");
	            output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

	            // Write file contents to response.
	            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
	            int length;
	            while ((length = input.read(buffer)) > 0) {
	                output.write(buffer, 0, length);
	            }

	            // Finalize task.
	            output.flush();
	        } finally {
	            // Gently close streams.
	            close(output);
	            close(input);
	        }

	        // Inform JSF that it doesn't need to handle response.
	        // This is very important, otherwise you will get the following exception in the logs:
	        // java.lang.IllegalStateException: Cannot forward after response has been committed.
	        facesContext.responseComplete();
	    }

	
	    private static void close(Closeable resource) {
	        if (resource != null) {
	            try {
	                resource.close();
	            } catch (IOException e) {
	                // Do your thing with the exception. Print it, log it or mail it. It may be useful to 
	                // know that this will generally only be thrown when the client aborted the download.
	                e.printStackTrace();
	            }
	        }
	    }


     

    public List<MvtApprov> sortOrderList() {
    	
    	List <MvtApprov> ordersList=approvService.getAllOrders();
    	
    	for(int i=0;i<ordersList.size()-1;i++) {

      			for(int j=0;j<ordersList.size();j++) {
      				
    				if(i!=j&&ordersList.get(i).getArticle().getArticleCode()
    				.equals(ordersList.get(j).getArticle().getArticleCode())) {
    					
    					ordersList.remove(ordersList.get(j));
    					
    				}
    			}
    			
    		
    					
    	}
    	
    	return ordersList;
		
    }
    
  
    
    private void createAreaModel() {
    	List<MvtApprov> list=sortOrderList();
        areaModel = new LineChartModel();

        LineChartSeries minChart = new LineChartSeries();
        minChart.setFill(true);
        minChart.setLabel("Min");
 for(int i=0 ;i<list.size();i++) {
	 minChart.set(list.get(i).getArticle().getArticleCode()+
			 " ("+String.valueOf(approvService.calculerArticleOrders(list.get(i).getArticle().getId()))+") "
			 , list.get(i).getArticle().getPricipalQuantity());
 }
       
        
 
        LineChartSeries maxChart = new LineChartSeries();
        maxChart.setFill(true);
        maxChart.setLabel("Normal");
        for(int i=0 ;i<list.size();i++) {
        	maxChart.set(list.get(i).getArticle().getArticleCode()+
       			 " ("+String.valueOf(approvService.calculerArticleOrders(list.get(i).getArticle().getId()))+") "
       			 , list.get(i).getArticle().getMinQuantity());
        }
        areaModel.addSeries(maxChart);
        areaModel.addSeries(minChart);
        
 
       // areaModel.setTitle("Orders Chart");
      //  areaModel.setLegendPosition("ne");
        areaModel.setStacked(true);
        areaModel.setShowPointLabels(true);
 
        Axis xAxis = new CategoryAxis("Article(Nbre Of oders)");
        areaModel.getAxes().put(AxisType.X, xAxis);
        Axis yAxis = areaModel.getAxis(AxisType.Y);
        yAxis.setLabel("Quantity");
        yAxis.setMin(0);
        yAxis.setMax(600);
    }
    
    public LineChartModel getAreaModel() {
        return areaModel;
    }
public void updateMinMaxQuantity() {
	Article article=articleService.getArticleListByCode(articleCode).get(0);
	article.setPricipalQuantity(maxQuantity);
	article.setMinQuantity(minQuantity);
	articleService.updateArticle(article);
	createAreaModel();
}

public void updateMinMaxQuantityForAlArticles() {
	List<Article> list =articleService.getAllArticles();
	 for(int i=0;i<list.size();i++) {
		 list.get(i).setMinQuantity(minQuantity);
		 list.get(i).setPricipalQuantity(maxQuantity);
		 articleService.updateArticle(list.get(i));
		}
	 createAreaModel();
}

public List<String> findArticles(String query){
	 List<Article> list =articleService.getArticlesByType("Matiére-Premiére");
	 List<String> stringList=new ArrayList<>();
	 for(int i=0;i<list.size();i++) {
		 if(list.get(i).getArticleCode().toUpperCase().contains(query)||list.get(i).getArticleCode().toLowerCase().contains(query)) {
			 stringList.add(list.get(i).getArticleCode());
		 }
		
		}
	 return stringList;
}
public void addOrder() {
	article=articleService.findArticleByCode(articleCode).get(0);
	approvService.addMvtApprov(new MvtApprov(article, null, quantity,null,requestDate,null));
}

public void updateArticleParamaters() {
	article.setDailyConsumption(dailyConsumption);
	article.setDeliveryTime(deliveryTime);
	articleService.updateArticle(article);
}
public void reset() {
	this.quantity=0;
	this.receptionDate=null;
}
public void refresh() {
	createAreaModel();
}

public void confirmReception(int orderId) {
	java.util.Date Date=new java.util.Date();
	MvtApprov order=approvService.findMvtApprovById(orderId);
	order.setReceptionDate(Date);
	order.getArticle().setQuantity(order.getArticle().getQuantity()+order.getQuantity());
	order.getArticle().setEtatOrdre(0);
	articleService.updateArticle(order.getArticle());
	approvService.updateMvtApprov(order);
}
	public ArticleService getArticleService() {
		return articleService;
	}


	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Article getArticle() {
		return article;
	}


	public void setArticle(Article article) {
		this.article = article;
	}


	public SpareParts getSpareparts() {
		return spareparts;
	}


	public void setSpareparts(SpareParts spareparts) {
		this.spareparts = spareparts;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Date getAlarmDate() {
		return alarmDate;
	}


	public void setAlarmDate(Date alarmDate) {
		this.alarmDate = alarmDate;
	}


	public Date getRequestDate() {
		return requestDate;
	}


	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}


	public Date getReceptionDate() {
		return receptionDate;
	}


	public void setReceptionDate(Date receptionDate) {
		this.receptionDate = receptionDate;
	}


	public MvtApprovService getApprovService() {
		return approvService;
	}


	public void setApprovService(MvtApprovService approvService) {
		this.approvService = approvService;
	}
	
	public List<Article> getArticles() {
		 //List<Article>list =articleService.getArticlesByType("Matiére-Premiére");
		 List<Article> list =articleService.findArticleByCode(articleCode);	
			return list;
	}


	public List<MvtApprov> getOrders() {
		List <MvtApprov> list =approvService.getAllOrders();
		return list;
	}

	public void setOrders(List<MvtApprov> orders) {
		this.orders = orders;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}



	public String getArticleCode() {
		return articleCode;
	}


	public void setArticleCode(String articleCode) {
		this.articleCode = articleCode;
	}

	public int getDailyConsumption() {
		return dailyConsumption;
	}

	public void setDailyConsumption(int dailyConsumption) {
		this.dailyConsumption = dailyConsumption;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	
	



public List<MvtApprov> getPanierOrdres() {
		return panierOrdres;
	}



	public void setPanierOrdres(List<MvtApprov> panierOrdres) {
		this.panierOrdres = panierOrdres;
	}



public int getMinQuantity() {
		return minQuantity;
	}

	public void setMinQuantity(int minQuantity) {
		this.minQuantity = minQuantity;
	}

	public int getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxQuantity(int maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	
	
public void AutoOrderGenerateByMinimumQuantity() {
		
		List<Article> list = articleService.getArticlesByType("Matiére-Premiére");

		for(int i=0;i<list.size();i++) {
			
			if(list.get(i).getQuantity()<=list.get(i).getMinQuantity()&&list.get(i).getEtatOrdre()==0) {
				
				java.util.Date Date=new java.util.Date();
				java.util.Date alarmDate=new java.util.Date();
				
				alarmDate.setDate(alarmDate.getDate()+list.get(i).getDeliveryTime()+1);
				
				int needed=list.get(i).getPricipalQuantity()-list.get(i).getQuantity();

				approvService.addMvtApprov(new MvtApprov(list.get(i),null,needed,alarmDate,Date,null));
				list.get(i).setEtatOrdre(1);
				articleService.updateArticle(list.get(i));
			}
		}
	}
	
public void generatePdf(MvtApprov order) throws IOException{
	try {
    	   try {
    	
    String FILE = "C:\\Users\\RBS\\Desktop\\test.pdf";
Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
      Font.BOLD, BaseColor.DARK_GRAY);
Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
      Font.NORMAL, BaseColor.RED);
Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
      Font.BOLD, BaseColor.GRAY);
Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 9,
      Font.BOLD);
  
  
              Document my_pdf_report = new Document();
              PdfWriter.getInstance(my_pdf_report, new FileOutputStream(FILE));
              my_pdf_report.open();  
              
        		
              Paragraph preface = new Paragraph("", catFont);
              preface.setAlignment(Element.ALIGN_CENTER);
              ordersParagraph = new Paragraph("Facture");
                  
              my_pdf_report.add(preface);
              my_pdf_report.add(ordersParagraph);
              ordersParagraph.setAlignment(Element.ALIGN_LEFT);        	  
              
              Calendar c = Calendar.getInstance();
            my_pdf_report.add(new Paragraph("Date : "+c.getTime()));
      		my_pdf_report.add(new Paragraph("Aticle UnitCode : "+order.getArticle().getUnitCode()));
      	    my_pdf_report.add(new Paragraph("Quantity : "+order.getQuantity())); 
      	    my_pdf_report.add(new Paragraph("   ")); 
      	    my_pdf_report.add(new Paragraph("   "));  
         

            my_pdf_report.close();              

            
            
            
}catch (FileNotFoundException e) {
e.printStackTrace();
}
 }catch (DocumentException e) {
	  e.printStackTrace();
	  }
	downloadPDF();
    	   

}
	
}