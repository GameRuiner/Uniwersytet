/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget2;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author MarkG
 */
public class GUIController implements Initializable {
    
    @FXML
    private Button Addbtn;
    @FXML
    private TextField Expensename;
    @FXML
    private RadioButton constant;
    @FXML
    private RadioButton variable;
    @FXML
    private DatePicker dateE;
    @FXML
    private TextField amount;   
    
    @FXML
    private Button en;
    @FXML
    private Button pl;
    @FXML
    private Button ru;
    @FXML
    private Label sumC;
    @FXML
    private Label sumV;
    @FXML
    private Label vAmount;
    @FXML
    private Label cAmount;
    @FXML
    private ListView<Expense> ListExpense;
    
    ObservableList<Expense> list = FXCollections.observableArrayList();

    private String constan;
    private String variab;
    private String currency;
    
    @FXML
    private void AddButtonAction(ActionEvent event) {
        String amountString = amount.getText();
        double amountDouble = Double.parseDouble(amountString);
        
        String type;
        if (constant.isSelected()) {
            type = constan;
            cAmount.setText((Double.parseDouble(cAmount.getText())+amountDouble)+"");
        }
        else {
            type = variab;
            vAmount.setText((Double.parseDouble(vAmount.getText())+amountDouble)+"");
        }
       
       DateTimeFormatter formatters = DateTimeFormatter.ofPattern(pattern); 
       
       list.add(new Expense(Expensename.getText(), dateE.getValue().format(formatters), type, amountDouble, currency));
       ListExpense.setItems(list);
       
       amount.setText("");
       Expensename.setText("");
       dateE.setValue(LocalDate.now());
    }
    
    @FXML
    private void CheckC(ActionEvent event) {
        if (constant.isSelected())
            variable.setSelected(false);
        else
            variable.setSelected(true);
    }
    
    @FXML
    private void CheckV(ActionEvent event) {
        if (variable.isSelected())
            constant.setSelected(false);
        else
            constant.setSelected(true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       vAmount.setText("0");
       cAmount.setText("0");
       constant.setSelected(true);
       setLocale("en");
       
    }    
    
    @FXML
    private void SetEn(ActionEvent event) {
        setLocale("en");
        setStageTitle("en");
    }
    
    @FXML
    private void SetPl(ActionEvent event) {
        setLocale("pl");
        setStageTitle("pl");
        
    }
    
    @FXML
    private void SetRu(ActionEvent event) {
        setLocale("ru");
        setStageTitle("ru");
    }
    
    private void setStageTitle(String lang) {
        Locale locale = new Locale(lang);
        ResourceBundle labels = ResourceBundle.getBundle("budget2.langprop", locale);
        Stage s = Budget2.getStage();
        s.setTitle(labels.getString("header"));
    }
    
    String pattern;
    
    public void setLocale(String lang) {
            
        Locale locale = new Locale(lang);
        ResourceBundle labels = ResourceBundle.getBundle("budget2.langprop", locale);
        currency = labels.getString("currency");
        Addbtn.setText(labels.getString("add"));
        amount.setPromptText(labels.getString("amount"));
        
        pattern = labels.getString("dayFormat");
    
        dateE.setValue(LocalDate.now());
        variable.setText(labels.getString("variable"));
        variab = labels.getString("variable");
        constant.setText(labels.getString("constant"));
        constan = labels.getString("constant");
        Expensename.setPromptText(labels.getString("Expensename"));
        sumC.setText(constan);
        sumV.setText(variab);
             
    }
    
}
