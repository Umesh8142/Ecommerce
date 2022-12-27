package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class HeaderController {

    @FXML
    Button logoutButton;
    @FXML
    TextField SearchText;
    @FXML
    Label email;
    @FXML
    Button loginbutton;
    @FXML
    public void login(MouseEvent e) throws IOException{
        AnchorPane loginpage= FXMLLoader.load(getClass().getResource("loginpage.fxml"));
        HelloApplication.root.getChildren().add(loginpage);
    }
    @FXML
    public void search(MouseEvent e) throws IOException, SQLException {
        productPage productPage = new productPage();
        Header header= new Header();
        AnchorPane productpane = new AnchorPane();
        productpane.setLayoutX(125);
        productpane.setLayoutY(100);
        productpane.getChildren().add(productPage.productsbySearch(SearchText.getText()));
        HelloApplication.root.getChildren().clear();
        HelloApplication.root.getChildren().addAll(header.root,productpane);
    }
    @FXML
    public void logoutButton(MouseEvent e) throws IOException {
        HelloApplication.emailId="";
        logoutButton.setOpacity(0);
        Header header= new Header();
        HelloApplication.root.getChildren().add(header.root);

    }
    @FXML
    public void initialize(){
        if(!HelloApplication.emailId.equals("")){
            loginbutton.setOpacity(0);
            email.setText(HelloApplication.emailId);
        }
    }
    @FXML
    public void logoutAppear(MouseEvent e){
        if(logoutButton.getOpacity()==0){
            logoutButton.setOpacity(1);
        }
        else{
            logoutButton.setOpacity(0);
        }
    }
    @FXML
    public void logout(MouseEvent e) throws IOException {
        if (logoutButton.getOpacity() == 1) {
            HelloApplication.emailId = "";
            logoutButton.setOpacity(0.000);
            Header header = new Header();
            HelloApplication.root.getChildren().add(header.root);
        }
    }
}
