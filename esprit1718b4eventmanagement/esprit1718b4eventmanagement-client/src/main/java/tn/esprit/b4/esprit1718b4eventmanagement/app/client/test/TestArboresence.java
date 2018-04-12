package tn.esprit.b4.esprit1718b4eventmanagement.app.client.test;


import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.naming.Context;
import javax.naming.InitialContext;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Arboresence;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote;

public class TestArboresence extends Application {

	/*public static void main(String[] args) throws NamingException {
		String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
		Context context = new InitialContext();
		ArticleServiceRemote ArticleProxy = (ArticleServiceRemote) context.lookup(ArticlejndiName);
		
		
		Article article1 = new Article("1","article1","20","MatiérePrimére",50,100);
		Article article2 = new Article("2","article2","21","MatiérePrimére",30,150);
		System.out.println(ArticleProxy.addArticle(article1));
		ArticleProxy.addArticle(article2);
		article2.setId(1);
		ArticleProxy.updateArticle(article2);
		ArticleProxy.incrementArticleQuantity(1,20);
		//ArticleProxy.addNomenclature(1,2,5);
		
		System.out.println(ArticleProxy.findArticle(1).getDescription());
		ArticleProxy.updateNomeclature(1,2,20);
		
		System.out.println(ArticleProxy.getFilsArticles(1).get(0).getArticleFils());
		
	
			
	}*/
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		  CalendarView calendarView = new CalendarView();

	        Calendar katja = new Calendar("Katja");
	        Calendar dirk = new Calendar("Dirk");
	        Calendar philip = new Calendar("Philip");
	        Calendar jule = new Calendar("Jule");
	        Calendar armin = new Calendar("Armin");
	        Calendar birthdays = new Calendar("Birthdays");
	        Calendar holidays = new Calendar("Holidays");

	        katja.setShortName("K");
	        dirk.setShortName("D");
	        philip.setShortName("P");
	        jule.setShortName("J");
	        armin.setShortName("A");
	        birthdays.setShortName("B");
	        holidays.setShortName("H");

	        katja.setStyle(Style.STYLE1);
	        dirk.setStyle(Style.STYLE2);
	        philip.setStyle(Style.STYLE3);
	        jule.setStyle(Style.STYLE4);
	        armin.setStyle(Style.STYLE5);
	        birthdays.setStyle(Style.STYLE6);
	        holidays.setStyle(Style.STYLE7);

	        CalendarSource familyCalendarSource = new CalendarSource("Family");
	        familyCalendarSource.getCalendars().addAll(birthdays, holidays, katja, dirk, philip, jule, armin);

	        calendarView.getCalendarSources().setAll(familyCalendarSource);
	        calendarView.setRequestedTime(LocalTime.now());

	        StackPane stackPane = new StackPane();
	        stackPane.getChildren().addAll(calendarView); // introPane);

	        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
	            @Override
	            public void run() {
	                while (true) {
	                    Platform.runLater(() -> {
	                        calendarView.setToday(LocalDate.now());
	                        calendarView.setTime(LocalTime.now());
	                    });

	                    try {
	                        // update every 10 seconds
	                        sleep(10000);
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }

	                }
	            }
	        };

	        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
	        updateTimeThread.setDaemon(true);
	        updateTimeThread.start();

	        Scene scene = new Scene(stackPane);
	        primaryStage.setTitle("Calendar");
	        primaryStage.setScene(scene);
	        primaryStage.setWidth(800);
	        primaryStage.setHeight(600);
	        primaryStage.centerOnScreen();
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

