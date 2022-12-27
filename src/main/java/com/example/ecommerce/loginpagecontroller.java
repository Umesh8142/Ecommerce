package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginpagecontroller {
    @FXML
    TextField email;
    @FXML
    PasswordField password;
    @FXML
    public void login(MouseEvent e) throws SQLException, IOException {
        String query = String.format("Select * from user where emailID ='%s' and pass='%s'", email.getText(), password.getText());
        ResultSet res = HelloApplication.connection.executeQuery(query);

        if (res.next()) {
            HelloApplication.emailId=res.getString("emailId");
            String userType = res.getString("userType");
            if (userType.equals("seller")) {
                AnchorPane sellerPage = FXMLLoader.load(getClass().getResource("sellerpage.fxml"));
                HelloApplication.root.getChildren().add(sellerPage);



            }
            else{
                System.out.println("buyer page");
                productPage productPage = new productPage();
                Header header= new Header();
                AnchorPane productpane = new AnchorPane();
                productpane.setLayoutX(125);
                productpane.setLayoutY(100);
                productpane.getChildren().add(productPage.products());
                HelloApplication.root.getChildren().clear();
                HelloApplication.root.getChildren().addAll(header.root,productpane);
            }


        }
        else{

            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Login ");
            ButtonType type = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add((type));
            dialog.setContentText("Login failed ,please check username/Password and try again");
            dialog.showAndWait();
        }

    }
}
