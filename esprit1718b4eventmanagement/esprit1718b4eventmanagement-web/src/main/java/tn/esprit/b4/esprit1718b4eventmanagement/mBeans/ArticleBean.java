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

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javafx.scene.control.TreeItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote;
@FacesComponent("treeBean")
@ManagedBean(name="treeBean")
@ViewScoped
public class ArticleBean implements Serializable {
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