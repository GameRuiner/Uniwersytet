/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp4server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author MarkG
 */
public class TCP4Server  extends Application{

    /**
     * @param args the command line arguments
     */
    
    @Override
    public void start(Stage stage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
       stage.setTitle("Server");
       Scene scene = new Scene(root);
       //Scene scene = new Scene(new Pane());
       stage.setScene(scene);
       stage.show();
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
