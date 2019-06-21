/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarycalculator2;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;

/**
 *
 * @author MarkG
 */
public class BinaryCalculator2 extends Application {
    boolean signSet = false;
    
    void ChangeSign() {
        signSet = !signSet; 
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void init() throws Exception {
         
        System.out.println("Application inits");
        super.init();
    }
    
    
    @Override
    public void start(Stage stage) {
        CalculatorController controller = new CalculatorController();

        Button button0  = new Button("0");
        button0.setId("btn0");
        button0.setLayoutX(275);
        button0.setLayoutY(250);
        button0.setMinSize(50, 50);
        
        Button button1  = new Button("1");
        button1.setLayoutX(225);
        button1.setLayoutY(250);
        button1.setMinSize(50, 50);
        
        Button buttonp  = new Button("+");
        buttonp.setLayoutX(175);
        buttonp.setLayoutY(200);
        buttonp.setMinSize(50, 50);
        
        Button buttonrd  = new Button("%");
        buttonrd.setLayoutX(250);
        buttonrd.setLayoutY(175);
        buttonrd.setMinSize(50, 50);
        
        Button buttonmult  = new Button("*");
        buttonmult.setLayoutX(325);
        buttonmult.setLayoutY(200);
        buttonmult.setMinSize(50, 50);
        
        Button buttonmin  = new Button("-");       
        buttonmin.setLayoutX(300);
        buttonmin.setLayoutY(325);
        buttonmin.setMinSize(50, 50);
        
        Button buttond  = new Button("/");
        buttond.setLayoutX(200);
        buttond.setLayoutY(325);
        buttond.setMinSize(50, 50);
        
        Button buttoneq  = new Button("=");
        buttoneq.setLayoutX(250);
        buttoneq.setLayoutY(110);
        buttoneq.setMinSize(50, 50);
        
        Button buttonClear = new Button("C");
        buttonClear.setLayoutX(495);
        buttonClear.setLayoutY(75);
        buttonClear.setMinSize(50, 50);
        
        TextField leftoperand = new TextField();
        leftoperand.setLayoutY(75);
        leftoperand.setLayoutX(75);
        
        TextField rightoperand = new TextField();
        rightoperand.setLayoutY(75);
        rightoperand.setLayoutX(325);
        
        Label sign = new Label();
        sign.setLayoutY(80);
        sign.setLayoutX(270);
        
        Label result = new Label();
        result.setLayoutY(30);
        result.setLayoutX(270);
        
        Label resultDecimal = new Label();
        resultDecimal.setLayoutY(0);
        resultDecimal.setLayoutX(270);
        
        Label leftDecimal = new Label();
        leftDecimal.setLayoutY(105);
        leftDecimal.setLayoutX(75);
        
        Label rightDecimal = new Label();
        rightDecimal.setLayoutY(105);
        rightDecimal.setLayoutX(325);
        
        
        Group group = new Group(button0,button1,buttonp,buttonrd,buttonmult,
                                buttonmin,buttond, leftoperand, rightoperand,
                                sign, buttoneq, result, resultDecimal,
                                leftDecimal, rightDecimal, buttonClear);
        
       
         EventHandler<ActionEvent> eventHandler01 = (ActionEvent e) -> {
            String text = (((Button) e.getSource()).getText());
            if (!signSet) {
                leftoperand.appendText(text);
            } else {
                rightoperand.appendText(text);
            }
        };
        
        button0.addEventFilter(ActionEvent.ACTION, eventHandler01);
        button1.addEventFilter(ActionEvent.ACTION, eventHandler01);
        
        EventHandler<ActionEvent> eventHandlerSign = (ActionEvent e) -> { 
            String signBtn = (((Button) e.getSource()).getText());
            ChangeSign();
            sign.setText(signBtn);
        };
        
        buttonp.addEventFilter(ActionEvent.ACTION, eventHandlerSign);
        buttonrd.addEventFilter(ActionEvent.ACTION, eventHandlerSign);
        buttonmult.addEventFilter(ActionEvent.ACTION, eventHandlerSign);
        buttonmin.addEventFilter(ActionEvent.ACTION, eventHandlerSign);
        buttond.addEventFilter(ActionEvent.ACTION, eventHandlerSign);
        
        
         EventHandler<ActionEvent> eventHandlerEqual = (ActionEvent e) -> {
            String left = leftoperand.getText() ;
            String right = rightoperand.getText() ;
            String signstr = sign.getText() ;
            try {
                result.setText(controller.MakeExpression(left, right, signstr));
                resultDecimal.setText(controller.decimal);
                leftDecimal.setText(controller.astr);
                rightDecimal.setText(controller.bstr);
            } catch (Exception ex) {
                result.setText(ex.getMessage());
            }
        };
        
        buttoneq.addEventFilter(ActionEvent.ACTION, eventHandlerEqual);
        
        
        EventHandler<ActionEvent> eventHandlerClear = (ActionEvent e) -> {
            result.setText("");
            resultDecimal.setText("");
            leftDecimal.setText("");
            rightDecimal.setText("");
            leftoperand.setText("");
            rightoperand.setText("");
            sign.setText("");
            ChangeSign();
        };
        
        buttonClear.addEventFilter(ActionEvent.ACTION, eventHandlerClear);
        
        Scene scene = new Scene(group);
        stage.getIcons().add(new Image("https://cdn3.iconfinder.com/data/icons/education-133/500/366-512.png"));
        stage.setScene(scene);
        stage.setWidth(585);
        stage.setHeight(500);
        stage.setTitle("Binary Calculator");
        stage.show();
    }
    
}
