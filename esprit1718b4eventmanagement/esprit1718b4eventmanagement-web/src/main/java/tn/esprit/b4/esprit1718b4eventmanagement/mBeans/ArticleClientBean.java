package tn.esprit.b4.esprit1718b4eventmanagement.mBeans;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

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

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Orders;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrderItemService;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrdersService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleService;

@FacesComponent("ArticleClientBean")
@ManagedBean(name="ArticleClientBean")
@SessionScoped
public class ArticleClientBean implements Serializable {
	
	private static final long serialVersionUID = 3350653785168926842L;
	private String articleCode;
	private String description;
	
    private static int idA;
    private static int idO;
    private static int idAUpdate;
    private static int idOUpdate;
    private static int id;
    private static int idUserCo;
	private float pmp;
	private int quantity;
	private int qte =0;
	private List<Article> Articles;
	
	private List<OrdredItem>OrdredItems;


	@EJB
	ArticleService ArticleServices;
	
	@EJB
	OrderItemService OrderItemServices;
	
	
	@EJB
	OrdersService OrdersService;
	 
	    @PostConstruct
	    public void init() throws NamingException {
	    	idUserCo= Identity.user.getId();
	    }






		public List<OrdredItem> getOrdredItems() {
			List<OrdredItem> OrdredItems = null;
			List<Orders> ListOrd =OrdersService.findOrdersByClient(Identity.user.getId());
			for (int i = 0; i < ListOrd.size(); i++) {
				OrdredItems =OrderItemServices.findItemsOfAnOrder(ListOrd.get(i).getId());
				
			}
			
			return OrdredItems;
		}



		public void setOrdredItems(List<OrdredItem> ordredItems) {
			OrdredItems = ordredItems;
		}









	public int getQte() {
		return qte;
	}


	public void setQte(int qte) {
		this.qte = qte;
	}


	
	


	
		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public List<Article> getArticles() {
			List<Article>listA =ArticleServices.getArticlesByType("Produit-Fini");
			return listA;
		}

		public void setArticles(List<Article> Articles) {
			this.Articles = Articles;
		}


		public String getArticleCode() {
			return articleCode;
		}


		public void setArticleCode(String articleCode) {
			this.articleCode = articleCode;
		}


		public float getPmp() {
			return pmp;
		}


		public void setPmp(float pmp) {
			this.pmp = pmp;
		}
	
		public static int getIdA() {
			return idA;
		}



		public static void setIdA(int idA) {
			ArticleClientBean.idA = idA;
		}



		public static int getIdO() {
			return idO;
		}



		public static void setIdO(int idO) {
			ArticleClientBean.idO = idO;
		}



		public static int getId() {
			return id;
		}

		public static void setId(int id) {
			ArticleClientBean.id = id;
		}


	    public List<String> findArticle(String query){
	        List<Article> list=ArticleServices.getArticlesByType("Produit-Fini");
	        List<String> ListArt =new ArrayList<>();
	        for(int i=0;i<list.size();i++){
	        	if(list.get(i).getArticleCode().toUpperCase().contains(query)||list.get(i).getArticleCode().toLowerCase().contains(query)){
	        		ListArt.add(list.get(i).getArticleCode());
	        	}
	        
	        }
	        return ListArt;
	        }
	    
	    
		public void AddClt()
		{
			
			//ArticleServices.DeleteArticle(41);
			
			Article article = new Article();
			article.setArticleCode("azertyu");
			 article =ArticleServices.findArticle(19);
			 
			 Orders order = new Orders();
	
			 order.setId(50);
			OrdredItem newOrdredItem = new OrdredItem();
			newOrdredItem.setQuantity(qte);
			Float x = (float) 3.5;
			List<Article> listA =ArticleServices.findArticleByCode(articleCode);
			for (int i = 0; i < listA.size(); i++) {
				idA=listA.get(i).getId();
				if (listA.get(i).getQuantity()<qte)		
				{
					FacesContext context = FacesContext.getCurrentInstance();
				    context.addMessage(null, new FacesMessage("Insufficient quantity of this item"+listA.get(i).getArticleCode() ) );
				}
				else{
					OrderItemServices.addOrdredItem(1, idA, newOrdredItem);
					FacesContext context = FacesContext.getCurrentInstance();
					
				    context.addMessage(null, new FacesMessage("Order accepted, Valid quantity" ) );
				}
					
			}
			

		}

		public void Delete( OrdredItem ordredItems)
		{
			OrderItemServices.delete(ordredItems);
		}
		
		public void Update( OrdredItem ordredItems)
		{
			this.setArticleCode(ordredItems.getArticle().getArticleCode());
			this.setQte(ordredItems.getQuantity());
			idAUpdate=ordredItems.getOrdredItemPk().getId_Article();
			idOUpdate=ordredItems.getOrdredItemPk().getId_Order();
			
			
		}
		
		   public void Confirm(){
			   Article art = ArticleServices.findArticle(idAUpdate);
			   OrdredItem ordredItems=OrderItemServices.findOrdredItemById(idOUpdate, idAUpdate);
			   ordredItems.setQuantity(qte);
			   OrderItemServices.mergeOrdredItem(idOUpdate, idAUpdate, ordredItems);
		   }
		   
		   
		   public Paragraph paragraph1;	
		   public void Payer()
		   {
			   
			 
				
					try {
				    	   try {
				    		   try {
				    			   try {
			        String FILE = "C:\\Users\\ons\\Documents\\BillPdf.pdf";
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
			                  
			            		
			                  Paragraph preface = new Paragraph("Bill", catFont);
			                  preface.setAlignment(Element.ALIGN_CENTER);

			                  
			                  
			            
			            	  

			              		
			              		
			              		
			            	  
			                  PdfPTable my_report_table = new PdfPTable(4);
			                 
			                  PdfPCell table_cell;
			                  
			                                  Phrase Code = new Phrase("Article Code", subFont);
			                                  Phrase Quantity = new Phrase("Quantity", subFont);
			                                  Phrase Price = new Phrase("Price (DT)", subFont);
			                                  Phrase Total = new Phrase("Total", subFont);
			                               
			                                  
			                                  table_cell=new PdfPCell(Code);
			                                  my_report_table.addCell(table_cell);
			                                  table_cell=new PdfPCell(Quantity);
			                                  my_report_table.addCell(table_cell);
			                                  table_cell=new PdfPCell(Price);
			                                  my_report_table.addCell(table_cell);
			                                  table_cell=new PdfPCell(Total);
			                                  my_report_table.addCell(table_cell);
			                                  
			                                  List<OrdredItem> OrdredItems = null;
			                      			List<Orders> ListOrd =OrdersService.findOrdersByClient(Identity.user.getId());
			                      			for (int i = 0; i < ListOrd.size(); i++) {
			                      				OrdredItems =OrderItemServices.findItemsOfAnOrder(ListOrd.get(i).getId());
			                      				
			                      			}
			                      			
			                      			
			                                  
			                                  java.util.List<Article> liste = new ArrayList<>();
			                                  
			                            
			                                  java.util.List<Article> articles = ArticleServices.DisplayArticle();
			                                 
			                                  for (OrdredItem ress : OrdredItems) 
			                                  {
			                                	 paragraph1 = new Paragraph("Devis N°"+ress.getCode()+ress.getCode() , catFont);
			                                	 Float x= ress.getQuantity()*ress.getArticle().getPmp();
			                                	 String p=String.valueOf(x);
			                                	 
			                                	 
			                                	 String q=String.valueOf(ress.getQuantity());
			                                	 
			                                	 
			                                	 String pmp=String.valueOf(ress.getArticle().getPmp());
			                                	 
			                                	 
			                                      table_cell=new PdfPCell(new Phrase((ress.getArticle().getArticleCode()),smallBold));
			                                      my_report_table.addCell(table_cell);
			                                      table_cell=new PdfPCell(new Phrase(q,smallBold));
			                                      my_report_table.addCell(table_cell);
			                                      table_cell=new PdfPCell(new Phrase(pmp,smallBold));
			                                      my_report_table.addCell(table_cell);
			                                      table_cell=new PdfPCell(new Phrase(p,smallBold));
			                                      my_report_table.addCell(table_cell);
			                                     

			                                  
			                                  }
			                                  
			                                  
			                  my_pdf_report.add(preface);
			                  my_pdf_report.add(paragraph1);
			                  paragraph1.setAlignment(Element.ALIGN_LEFT);        	  
			                  
			                  Calendar c = Calendar.getInstance();
			          		my_pdf_report.add(new Paragraph("Nom Société : Esprit PDEV JEE"));
			          		my_pdf_report.add(new Paragraph("Nom Client : GPAO - GMAO"));
			          		my_pdf_report.add(new Paragraph("Date : "+c.getTime()));
			          		my_pdf_report.add(new Paragraph("Adresse : Esprit"));
			          		my_pdf_report.add(new Paragraph("Num Tel : 216123654"));
			          	    my_pdf_report.add(new Paragraph("   ")); 
			          	    my_pdf_report.add(new Paragraph("   ")); 
			          	    my_pdf_report.add(new Paragraph("   "));  
			                my_pdf_report.add(my_report_table); 

			                Image img2 = Image.getInstance("C:\\Users\\ons\\Documents\\tampon1.jpg");
			                img2.setAlignment(2);
			                img2.setBorderWidth(2);
			                img2.setCompressionLevel(5);
			                
			                my_pdf_report.add(img2);
			                my_pdf_report.close();              
			             
			             //     Desktop.getDesktop().open(new File("C:\\Users\\ons\\Documents\\FirstPdf.pdf"));
			                
			                
			                
			  }catch (FileNotFoundException e) {
			  e.printStackTrace();
			  }
				 }catch (DocumentException e) {
					  e.printStackTrace();
					  }
				 }catch (MalformedURLException e) {
					  e.printStackTrace();
					  }
				 }catch (IOException e) {
					  e.printStackTrace();
					  }

			   
			   
			   
			   
			   FacesContext context = FacesContext.getCurrentInstance();
			    context.addMessage(null, new FacesMessage("Your bill has been successfully uploaded" ) );
			    
			    
			    
		   }
		   
		   public String Reclamation()
		   {
			   String navigateTo = "";
			   navigateTo = "/Operation/ReclamOp?faces-redirect=true";
				return navigateTo;
		   }

}
