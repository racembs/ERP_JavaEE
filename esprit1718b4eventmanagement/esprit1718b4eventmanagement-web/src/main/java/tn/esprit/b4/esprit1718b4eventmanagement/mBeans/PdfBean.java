package tn.esprit.b4.esprit1718b4eventmanagement.mBeans;


import java.awt.Desktop;
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
import java.util.ArrayList;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.FacesComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;

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
	private static final int DEFAULT_BUFFER_SIZE = 10240;
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
          		my_pdf_report.add(new Paragraph("Nom Client : "+Identity.user.getFirstname()+""+Identity.user.getLastname()));
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
	    	   downloadPDF();
	 }catch (IOException e) {
		  e.printStackTrace();
		  }
		
		  
		    	  
		 
}
  
	  public void downloadPDF() throws IOException {

	        // Prepare.
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        ExternalContext externalContext = facesContext.getExternalContext();
	        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

	        File file = new File("C:\\Users\\ons\\Documents\\","FirstPdf.pdf");
	        BufferedInputStream input = null;
	        BufferedOutputStream output = null;

	        try {
	            // Open file.
	            input = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);

	            // Init servlet response.
	            response.reset();
	            response.setHeader("Content-Type", "application/pdf");
	            response.setHeader("Content-Length", String.valueOf(file.length()));
	            response.setHeader("Content-Disposition", "inline; filename=\"" + "FirstPdf.pdf" + "\"");
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
		

}


