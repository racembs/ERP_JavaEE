package tn.esprit.b4.esprit1718b4eventmanagement.app.client.test;

import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NomenclaturePk;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.MvtApprovServiceRemote;

public class TestArticle extends Application {


	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
		Context context = new InitialContext();
		ArticleServiceRemote ArticleProxy = (ArticleServiceRemote) context.lookup(ArticlejndiName);
		//Article article =ArticleProxy.findArticleByCode("MatierePremiere");
		//System.out.println(article.getId());
		
		String jndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/MvtApprovService!tn.esprit.b4.esprit1718b4eventmanagement.services.MvtApprovServiceRemote";
    	Context context1 = new InitialContext();
    	MvtApprovServiceRemote OrdreProxy = (MvtApprovServiceRemote) context1.lookup(jndiName);
    //	System.out.println(OrdreProxy.getOrderByArticle(article.getId()).getQuantity());
	
		Parent parent =FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
		Scene scene=new Scene(parent);
		scene.getStylesheets().add("/src/main/resouces/views/tab.css");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}

