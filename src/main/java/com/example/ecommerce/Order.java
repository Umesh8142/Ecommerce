package com.example.ecommerce;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

public class Order {
    void placeOrder(String productID) throws SQLException {
        ResultSet res = HelloApplication.connection.executeQuery("select max(OrderID) as orderID from orders");
        int orderID = 1;
        if (res.next()) {
            orderID = res.getInt("orderID") + 1;
        }
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        Timestamp ts = new Timestamp(Calendar.getInstance().getTime().getTime());
        System.out.println(date.toString());
        String query = String.format("INSERT INTO ORDERS VALUES (%S,%s,'%s','%s')", orderID, productID, HelloApplication.emailId, ts);
        int responce = HelloApplication.connection.executeUpdate(query);
        if (responce > 0) {

            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Order");
            ButtonType type = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add((type));
            dialog.setContentText("Your order is placed");
            dialog.showAndWait();
        }
        else {
            Dialog<String> dialog1 = new Dialog<>();
            dialog1.setTitle("Order");
            ButtonType type = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            dialog1.getDialogPane().getButtonTypes().add((type));
            dialog1.setContentText("Your order not placed");
            dialog1.showAndWait();

        }
    }
    }


