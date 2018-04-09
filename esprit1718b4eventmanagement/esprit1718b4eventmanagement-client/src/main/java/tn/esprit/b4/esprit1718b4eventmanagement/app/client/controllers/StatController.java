
package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;

/**
 * FXML Controller class
 *
 * @author ons
 */
public class  StatController implements Initializable {

    @FXML
    private Label txtCurrentWindow;
    @FXML
    private Separator id1;
    @FXML
    private Label id2;
    @FXML
    private Label OptRStat;
    @FXML
    private PieChart idPc;
    @FXML
    private BarChart<?, ?> idBC;
    @FXML
    private NumberAxis idBCy;
    @FXML
    private CategoryAxis idBCx;
    @FXML
    private AreaChart<?, ?> idAC;
    @FXML
    private NumberAxis idACy;
    @FXML
    private CategoryAxis idACx;
    @FXML
    private ScatterChart<?, ?> idSC;
    @FXML
    private NumberAxis idSCy;
    @FXML
    private CategoryAxis idSCx;
    @FXML
    private Label idA;
    @FXML
    private Label idE;
    @FXML
    private Label idCS;
    @FXML
    private Label idO;
    @FXML
    private Label idMP;
    
    @FXML
    private ComboBox<String> idComboStat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	 
        OptRStat.setText("20");
        idA.setText("1");
        idE.setText("1");
        idCS.setText("5");
        idO.setText("11");
        idMP.setText("2");
        
    
        idComboStat.getItems().addAll("Consecutive","Overlap","With staking delay","Parallel");
        idComboStat.getSelectionModel().selectLast();
        
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("0",20));
        series.getData().add(new XYChart.Data("1",21));
        series.getData().add(new XYChart.Data("2",22));
        series.getData().add(new XYChart.Data("3",23));
        series.getData().add(new XYChart.Data("4",24));
        series.getData().add(new XYChart.Data("5",25));
        
        XYChart.Series series1 = new XYChart.Series();
        series1.getData().add(new XYChart.Data("0",30));
        series1.getData().add(new XYChart.Data("1",25));
        series1.getData().add(new XYChart.Data("2",17));
        series1.getData().add(new XYChart.Data("3",31));
        series1.getData().add(new XYChart.Data("4",15));
        series1.getData().add(new XYChart.Data("5",33));
        
        XYChart.Series series2 = new XYChart.Series<>();
        series2.getData().add(new XYChart.Data("A",3000));
        series2.getData().add(new XYChart.Data("B",2500));
        series2.getData().add(new XYChart.Data("C",1700));
        series2.getData().add(new XYChart.Data("D",3100));
        series2.getData().add(new XYChart.Data("E",1500));
        series2.getData().add(new XYChart.Data("F",3300));
         
         
        
        ObservableList<PieChart.Data> pieChartData= FXCollections.observableArrayList(
                new PieChart.Data("A",30),
                new PieChart.Data("B",50),
                new PieChart.Data("C",20)
        );
        
        idPc.setData(pieChartData);
        idPc.setTitle("Pie Chart");
        
        idAC.getData().addAll(series, series1);
        idAC.setTitle("Area Chart");
        
        idSC.getData().addAll(series, series1);
        idSC.setTitle("Scatter Chart");
         
        idBC.getData().add(series2);
        idBC.setTitle("Bar Chart");
    }    
    
}
