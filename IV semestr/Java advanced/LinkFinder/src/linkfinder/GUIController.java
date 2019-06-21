/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkfinder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.application.HostServices;
import javafx.application.Application;

/**
 *
 * @author MarkG
 */
public class GUIController implements Initializable {
    
        
        
    @FXML
    private Button StartBtn;
    
    @FXML
    private TextField URLAdress;
    
    @FXML
    private TextArea TextView;
    
    @FXML
    private ListView URLList;
    
    @FXML
    private ListView hostList;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         //To change body of generated methods, choose Tools | Templates.
    }
    
    @FXML
    private void StartBtnAction(ActionEvent event) throws Exception {
       URLList.getItems().clear();
       hostList.getItems().clear();
       String res = URLConnectionReader(URLAdress.getText());
       TextView.setText(res);
       LinkedList<URL> items = LinkExtractor(res);
       LinkedList<Hyperlink> hyperlinks = URLtoHyperlink(items);
       HashMap<String,Integer> hosts = URLtoHost(items);
       LinkedList<String> strHost = HashtoList(hosts);
       //URLList.setItems(FXCollections.observableArrayList(items));
       URLList.getItems().addAll(hyperlinks);
       hostList.getItems().addAll(strHost);
    }
    
    private String URLConnectionReader(String urlString) throws Exception {
        //jsoup
        URL url = new URL(urlString);
        URLConnection urlc = url.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(urlc.getInputStream()));
        
        String inputLine;
        String result = "";
        while((inputLine = in.readLine()) != null) {
            result+=inputLine + '\n';
        }
        return result;
    }
    
    private LinkedList<URL> LinkExtractor(String html) {
        String htmlPattern = "(?i)<a([^>]+)>(.+?)</a>";
        String hrefPattern = "\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'(^')*'|((^'\">\\s)+))";
        Pattern patternTag, patternLink;
        Matcher matcherTag, matcherLink;
        
        LinkedList<URL> result = new LinkedList<>();
        
        patternTag = Pattern.compile(htmlPattern);
        patternLink = Pattern.compile(hrefPattern);
        
        matcherTag = patternTag.matcher(html);
        
        while(matcherTag.find()) {
            String href = matcherTag.group(1);
            String linkText = matcherTag.group(2);
            
            matcherLink = patternLink.matcher(href);
            
            while (matcherLink.find()) {
                String link = matcherLink.group(1);
                String linkT = matcherLink.group(2);
                //System.out.println(link + " " + linkT);
                try {
                    link = link.substring(1, link.length()-1);
                    URL url = new URL(link);
                    //System.out.println(link + " " + url);
                    result.add(url);
                } catch (Exception e) {
                     System.out.println("Error link " + link);
                }
            }
        }
        return result;
    }
    
    private LinkedList<Hyperlink> URLtoHyperlink(LinkedList<URL> urlList ) throws Exception {
        LinkedList<Hyperlink> result = new LinkedList<>();
        for (URL url:urlList) {
            Hyperlink link =  new Hyperlink(url.toString());
            result.add(link);
             link.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    try {
                        java.awt.Desktop.getDesktop().browse(new URI(url.toString()));
                    } catch(Exception e) {
                        
                    }
                }});}
       
        return result;
    }
    
    private HashMap<String ,Integer> URLtoHost(LinkedList<URL> urlList ) throws Exception {
        HashMap<String,Integer> result = new HashMap<>();
        urlList.forEach((url) -> {
            String link =  url.getHost();
            if (result.containsKey(link)) {
                result.put(link, 1 + result.get(link));
            } else{
                result.put(link, 1);
            }
        });
       
        return result;
    }
    
    private LinkedList<String> HashtoList(HashMap<String,Integer> hosts){
        LinkedList<String> result = new LinkedList<>();

        HashMap<String, HashMap> selects = new HashMap<String, HashMap>();

    for(Map.Entry<String, Integer> entry : hosts.entrySet()) {
        String key = entry.getKey();
        int value = entry.getValue();
        if (key.equals("")) continue;
        try {
            InetAddress address = InetAddress.getByName(key);
            result.add(key + " " + value + " " + address.getHostAddress());
        } catch (Exception e ) {
            System.out.println(key);
        }
    }
        return result;
    }
}
