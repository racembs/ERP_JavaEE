package tn.esprit.b4.esprit1718b4eventmanagement.mBeans;


import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.FacesComponent;
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
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleService;

@FacesComponent("PdfBean")
@ManagedBean(name="PdfBean")
@SessionScoped
public class PdfBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	public ArticleService ArticleServices;
	
    @PostConstruct
    public void init() throws NamingException {
    	
    }
    public Paragraph paragraph1;	
	public void Pdf(){
		try {
	    	   try {
	    		   try {
	    			   try {
        String FILE = "C:\\Users\\ons\\Documents\\FirstPdf.pdf";
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
                  
            		
                  Paragraph preface = new Paragraph("Devis Articles", catFont);
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
                                  
                                  
                                  java.util.List<Article> liste = new ArrayList<>();
                                  
                            
                                  java.util.List<Article> articles = ArticleServices.DisplayArticle();
                                 
                                  for (Article ress : articles) 
                                  {
                                	 paragraph1 = new Paragraph("Devis N°"+ress.getArticleCode()+ress.getArticleCode() , catFont);
                                	 Float x= ress.getQuantity()*ress.getPmp();
                                	 String p=String.valueOf(x);
                                	 String q=String.valueOf(ress.getPricipalQuantity()-ress.getQuantity());
                                	 String pmp=String.valueOf(ress.getPmp());
                                      table_cell=new PdfPCell(new Phrase((ress.getArticleCode()),smallBold));
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

}
  
		

}


