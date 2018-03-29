package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CalendarController implements Initializable {

    private Label label;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private VBox colorRootPane;
    @FXML
    private JFXColorPicker fallSemCP;
    @FXML
    private JFXCheckBox fallSemCheckBox;
    @FXML
    private JFXColorPicker springSemCP;
    @FXML
    private JFXCheckBox springSemCheckBox;
    @FXML
    private JFXColorPicker summerSemCP;
    @FXML
    private JFXCheckBox summerSemCheckBox;
    @FXML
    private JFXColorPicker allQtrCP;
    @FXML
    private JFXCheckBox allQtrCheckBox;
    @FXML
    private JFXColorPicker allMbaCP;
    @FXML
    private JFXCheckBox allMbaCheckBox;
    @FXML
    private JFXColorPicker allHalfCP;
    @FXML
    private JFXCheckBox allHalfCheckBox;
    @FXML
    private JFXColorPicker allCampusCP;
    @FXML
    private JFXCheckBox allCampusCheckBox;
    @FXML
    private JFXColorPicker allHolidayCP;
    @FXML
    private JFXCheckBox allHolidayCheckBox;
    @FXML
    private JFXColorPicker traTrbCP;
    @FXML
    private JFXCheckBox allTraTrbCheckBox;
    @FXML
    private JFXCheckBox selectAllCheckBox;
    @FXML
    private VBox toolsRootPane;
    @FXML
    private VBox centerArea;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private HBox weekdayHeader;
    @FXML
    private GridPane calendarGrid;
    @FXML
    private Label calendarNameLbl;
    @FXML
    private Label monthLabel;
    @FXML
    private JFXComboBox<?> selectedYear;
    @FXML
    private JFXListView<?> monthSelect;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void newCalendar(MouseEvent event) {
    }

    @FXML
    private void manageCalendars(MouseEvent event) {
    }

    @FXML
    private void pdfBtn(MouseEvent event) {
    }

    @FXML
    private void excelBtn(MouseEvent event) {
    }

    @FXML
    private void handleCheckBoxAction(ActionEvent event) {
    }

    @FXML
    private void selectAllCheckBoxes(ActionEvent event) {
    }

    @FXML
    private void updateColors(MouseEvent event) {
    }

    @FXML
    private void newRule(MouseEvent event) {
    }

    @FXML
    private void manageRules(MouseEvent event) {
    }

    @FXML
    private void manageTermDates(MouseEvent event) {
    }

    @FXML
    private void deleteAllEvents(MouseEvent event) {
    }
    
}
