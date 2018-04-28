package tn.esprit.b4.esprit1718b4eventmanagement.mBeans;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Column;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javafx.scene.control.TreeItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote;
@FacesComponent("treeBean")
@ManagedBean(name="treeBean")
@SessionScoped
public class ArticleBean implements Serializable {
	private static int idArticle;
	private String articleCode;
	private String description;
	private String unitCode;
	private String type;
	private float pmp;
	private int quantity;
	private List<Article> articles;
	 @EJB
	ArticleService articleService;
	private static final long serialVersionUID = 3350653785168926842L;
	private TreeNode root;
    
    @PostConstruct
    public void init() throws NamingException {
    	fill3();
    	//fill();
//    	Article article=new Article();
//    	article.setArticleCode("code");
//    	article.setQuantity(1);
//    	root = new DefaultTreeNode(article);
//    	
//    	TreeNode node0 = new DefaultTreeNode(article,root);
//    	node0.setExpanded(true);
//    	root.getChildren().add(new DefaultTreeNode(article));
		 }
public void addArticle() {
	articleService.addArticle(new Article(articleCode, description, unitCode, type, pmp, quantity));
}
public void deleteArticle(Integer idArticle) {
	articleService.DeleteArticle(idArticle);
	//this.quantity=idArticle;
}


public void reset() {
	this.articleCode="";
	this.description="";
	this.quantity=0;
	this.pmp=0;
	this.unitCode="";
}
public void setArticle(Article article){
	this.articleCode=article.getArticleCode();
	this.description=article.getDescription();
	this.quantity=article.getQuantity();
	this.pmp=article.getPmp();
	this.unitCode=article.getUnitCode();
	this.type=article.getType();
	this.idArticle=article.getId();
	
}
public void updteArticle() {
	Article article=new Article(articleCode, description, unitCode, type, pmp, quantity);
	article.setId(this.getIdArticle());
	articleService.updateArticle(article);
}
    
    public void fill() {
root = new DefaultTreeNode("Root", null);

              
        
        List<Nomenclature> listNomenclature;
		 List<Nomenclature> produitFini =  new ArrayList<Nomenclature>();
		 listNomenclature=articleService.getAllFinalArticleNomenclature();
			for(int i=0;i<listNomenclature.size();i++) {
			if(listNomenclature.get(i).getArticlePere().getType().equals("Produit-Pere")&&listNomenclature.get(i).getArticleFils().getType().equals("Produit-Fini")) {
				produitFini.add(listNomenclature.get(i));
				
			}
		}
		 for(int i=0;i<produitFini.size();i++) {
			 TreeNode node0 = new DefaultTreeNode(produitFini.get(i).getArticleFils());
			 root.getChildren().add(node0);
  
		 
		 ArrayDeque <TreeNode> queue=new ArrayDeque<>();
		 queue.add(node0);
		 
		
		 while(!queue.isEmpty()) {
			 
		
			//Nomenclature TreeItemHead=queue.getFirst();
			TreeNode TreeItemHead = queue.getFirst();
			 queue.removeFirst();
			//Article article= articleService.findArticleByCode(TreeItemHead.toString()).get(0);
//			 List<Nomenclature> listNomenclatureFils=articleService.getFilsArticles(TreeItemHead.get);
//					 //getArticleFils().getId());
//			 for(int j=0;j<listNomenclatureFils.size();j++) {
//				// newItemarticleFils=new TreeItem<>(listNomenclatureFils.get(j));
//				 TreeNode newItemarticleFils = new DefaultTreeNode(listNomenclatureFils.get(j));
//				 TreeItemHead.getChildren().add(newItemarticleFils);
//				 queue.addLast(newItemarticleFils);
//				 
//		
//		
//			 }
		 }
		  }
		 
    }
    
    
    public void fillTree() {
    	root = new DefaultTreeNode("Root", null);

    	              
    	        
    	        List<Nomenclature> listNomenclature;
    			 List<Nomenclature> produitFini =  new ArrayList<Nomenclature>();
    			 listNomenclature=articleService.getAllFinalArticleNomenclature();
    				for(int i=0;i<listNomenclature.size();i++) {
    				if(listNomenclature.get(i).getArticlePere().getType().equals("Produit-Pere")&&listNomenclature.get(i).getArticleFils().getType().equals("Produit-Fini")) {
    					produitFini.add(listNomenclature.get(i));
    					
    				}
    			}
    			 for(int i=0;i<produitFini.size();i++) {
    				 TreeNode node0 = new DefaultTreeNode(produitFini.get(i));
    				 root.getChildren().add(node0);
    	  
    			 
    			 ArrayDeque <Nomenclature> queue=new ArrayDeque<>();
    			 queue.add(produitFini.get(i));
    			 
    			
    			 while(!queue.isEmpty()) {
    				 
    			
    				Nomenclature TreeItemHead=queue.getFirst();
    				TreeNode newTreeNode = new DefaultTreeNode(TreeItemHead);
    				//TreeNode TreeItemHead = queue.getFirst();
    				 queue.removeFirst();
    				//Article article= articleService.findArticleByCode(TreeItemHead.getArticleFils().getId());
    				 List<Nomenclature> listNomenclatureFils=articleService.getFilsArticles(TreeItemHead.getArticleFils().getId());
    						 //getArticleFils().getId());
    				 for(int j=0;j<listNomenclatureFils.size();j++) {
    					
    					 //TreeNode newItemarticleFils = new DefaultTreeNode(listNomenclatureFils.get(j).getArticleFils().getArticleCode());
    					 newTreeNode.getChildren().add(new DefaultTreeNode(listNomenclatureFils.get(j)));
    					 queue.addLast(listNomenclatureFils.get(j));
    					 
    			
    			
    				 }
    			 }
    			  }
    			 
    	    }
    	    
    
    
 public void fillTreeNode() throws NamingException {
	
			root = new DefaultTreeNode("Root", null);	
		 List<Nomenclature> listNomenclature;
		 List<Nomenclature> produitFini =  new ArrayList<Nomenclature>();
		 listNomenclature=articleService.getAllFinalArticleNomenclature();

		 
		 
		
		 
	for(int i=0;i<listNomenclature.size();i++) {
		if(listNomenclature.get(i).getArticlePere().getType().equals("Produit-Pere")&&listNomenclature.get(i).getArticleFils().getType().equals("Produit-Fini")) {
			produitFini.add(listNomenclature.get(i));
			
		}
	}
	
	
	
	
	
 
 for(int i=0;i<listNomenclature.size();i++) {
	 List<Nomenclature> articlePere=produitFini;
	 
	 //newItemarticlePere=new TreeItem<>(articlePere.get(i));
	 TreeNode newItemarticlePere = new DefaultTreeNode(articlePere.get(i).getArticleFils().getArticleCode());
	 root.getChildren().add(newItemarticlePere);
	 
	
	
	 
	 ArrayDeque <Nomenclature> queue=new ArrayDeque<>();
	 queue.add(articlePere.get(i));
	 
	
	 while(!queue.isEmpty()) {
		 
	
		//Nomenclature TreeItemHead=queue.getFirst();
		TreeNode TreeItemHead = new DefaultTreeNode(queue.getFirst().getArticleFils().getArticleCode());
		 queue.removeFirst();
		
		 List<Nomenclature> listNomenclatureFils=articleService.getFilsArticles(queue.getFirst().getArticleFils().getId());
		 for(int j=0;j<listNomenclatureFils.size();j++) {
			// newItemarticleFils=new TreeItem<>(listNomenclatureFils.get(j));
			 TreeNode newItemarticleFils = new DefaultTreeNode(listNomenclatureFils.get(j).getArticleFils().getArticleCode());
			 TreeItemHead.getChildren().add(newItemarticleFils);
			 queue.addLast(listNomenclatureFils.get(i));
			 
	
	
		 }
	 }
	 
	 

 }

	
	 
 }
 
 
 public void fill3() {
	 
	 root = new DefaultTreeNode("Root", null);
		 List<Nomenclature> listNomenclature;
		 List<Nomenclature> produitFini =  new ArrayList<Nomenclature>();
		 listNomenclature=articleService.getAllFinalArticleNomenclature();

		
		 
	for(int i=0;i<listNomenclature.size();i++) {
		if(listNomenclature.get(i).getArticlePere().getType().equals("Produit-Pere")&&listNomenclature.get(i).getArticleFils().getType().equals("Produit-Fini")) {
			produitFini.add(listNomenclature.get(i));
			
		}
	}

	 
	 
	 	TreeItem<Nomenclature> newItemarticlePere;
	 	TreeItem<Nomenclature> newItemarticleFils=null;
	 for(int i=0;i<produitFini.size();i++) {
		 List<Nomenclature> articlePere=produitFini;
		 
		// newItemarticlePere=new TreeItem<>(articlePere.get(i));
		 TreeNode newNode = new DefaultTreeNode(articlePere.get(i));
		// root = new DefaultTreeNode(newNode);
		 root.getChildren().add(newNode);
		 
		
		
		 
		 ArrayDeque <Nomenclature> queue=new ArrayDeque<>();
		 queue.add(articlePere.get(i));
		 
		
		 while(!queue.isEmpty()) {
			 
		
			Nomenclature TreeItemHead=queue.getFirst();
			 queue.removeFirst();
			
			 List<Nomenclature> listNomenclatureFils=articleService.getFilsArticles(TreeItemHead.getArticleFils().getId());
			 for(int j=0;j<listNomenclatureFils.size();j++) {
				// newItemarticleFils=new TreeItem<>(listNomenclatureFils.get(j));
				 TreeNode newNodeFils = new DefaultTreeNode(listNomenclatureFils.get(j));
				 newNode.getChildren().add(newNodeFils);
				 queue.addLast(listNomenclatureFils.get(j));
				 
		
		
			 }
		 }
		 
		 
		
	 }
 }
 public ArticleService getArticleService() {
	return articleService;
}
 
 
 
 
 
 
 
 



public List<Article> getArticles() {
	List<Article>list =articleService.getAllArticles();
	for(int i=0;i<list.size()-1;i++) {
		for(int j=0;j<list.size();j++) {
			if(i!=j&&list.get(i).getArticleCode().equals(list.get(j).getArticleCode())) {
				list.remove(list.get(j));
			}
		}
	}
	return list;
}


public void setArticles(List<Article> articles) {
	this.articles = articles;
}

public static int getIdArticle() {
	return idArticle;
}
public static void setIdArticle(int idArticle) {
	ArticleBean.idArticle = idArticle;
}
public String getArticleCode() {
	return articleCode;
}
public void setArticleCode(String articleCode) {
	this.articleCode = articleCode;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getUnitCode() {
	return unitCode;
}
public void setUnitCode(String unitCode) {
	this.unitCode = unitCode;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public float getPmp() {
	return pmp;
}
public void setPmp(float pmp) {
	this.pmp = pmp;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public void setArticleService(ArticleService articleService) {
	this.articleService = articleService;
}

public int getQuantity(Nomenclature n) {
	return n.getQuantity();
}

public String getArticleFilsCode(Nomenclature n) {
	return n.getArticleFils().getArticleCode();
}
public Article getArticleFils(Nomenclature n) {
	return n.getArticleFils();
}
	public TreeNode getRoot() {
        return root;
    }
}