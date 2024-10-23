package com.example.project;

import com.example.project.Cart.CartEntry;
import com.example.project.Cart.ShoppingCart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class CartController {

    @FXML
    VBox cartPane;


    @FXML
    Label cartStatusLabel;

    private Label totalpriceLabel;



    @FXML
    public void initialize() throws FileNotFoundException {
        List<CartEntry> entries = ShoppingCart.getInstance().getEntries();


        if (entries.isEmpty()){
            Label emptyLabel = new Label("Shopping Cart is Empty");
            emptyLabel.setFont(Font.font("calibri", FontWeight.BOLD, FontPosture.REGULAR,20));
            cartPane.getChildren().add(emptyLabel);
        }else {

            for(CartEntry cartEntry:entries){
                HBox productView = cartEntryView(cartEntry);
                cartPane.getChildren().add(productView);

            }


            HBox totalView = totalView(ShoppingCart.getInstance().getTotalAmount());
            cartPane.getChildren().add(totalView);
        }

    }

// Total Price
    private HBox totalView(double totalPrice){
        HBox layout = new HBox();

        Label totalLabel = new Label("Total:");
        totalLabel.setFont(Font.font("calibri", FontWeight.BOLD, FontPosture.REGULAR,15));

        this.totalpriceLabel = new Label(String.valueOf(totalPrice));
        this.totalpriceLabel.setFont(Font.font("calibri", FontWeight.BOLD, FontPosture.REGULAR,15));

        layout.getChildren().addAll(totalLabel,this.totalpriceLabel);
        return layout;
    }

    private HBox cartEntryView(CartEntry cartEntry) throws FileNotFoundException {
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER_LEFT);

        FileInputStream input = new FileInputStream(
                Paths.get("src", "main", "resources", "Images",
                        cartEntry.getItem().getImageFile()).toString()
        );
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);

        Label itemName = new Label(cartEntry.getItem().name());
        itemName.setPrefWidth(100);
        itemName.setFont(Font.font("calibri", FontWeight.BOLD, FontPosture.REGULAR,15));

        Label quantity = new Label(String.valueOf(cartEntry.getQuantity()));
        quantity.setStyle("-fx-padding:5px");




        Button minusButton = new Button("-");
        minusButton.setStyle("-fx-padding:5px");
        minusButton.setUserData(cartEntry.getItem().name());
        minusButton.setOnAction(e -> {
            String name = (String) ((Node) e.getSource()).getUserData();
            ShoppingCart.getInstance().removeItem(name);
            int quantityValue = ShoppingCart.getInstance().getQuantity(name);

            quantity.setText(String.valueOf(quantityValue));
            this.totalpriceLabel.setText(String.valueOf(ShoppingCart.getInstance().getTotalAmount()));


            if (quantityValue <= 0) {
                minusButton.setDisable(true);
            }
        });

        Button plusButton = new Button("+");
        plusButton.setStyle("-fx-padding:5px");
        plusButton.setUserData(cartEntry.getItem().name());
        plusButton.setOnAction(e -> {
            String name = (String) ((Node) e.getSource()).getUserData();
            ShoppingCart.getInstance().addItem(name);

            // Get the updated quantity
            int quantityValue = ShoppingCart.getInstance().getQuantity(name);
            quantity.setText(String.valueOf(quantityValue));
            this.totalpriceLabel.setText(String.valueOf(ShoppingCart.getInstance().getTotalAmount()));

            // Enable the minus button if quantity is greater than 0
            if (quantityValue > 0) {
                minusButton.setDisable(false);
            }
        });



        Label price = new Label (String.valueOf("$ "+ cartEntry.getItem().getPrice()));
        price.setFont(Font.font("calibri", FontWeight.BOLD, FontPosture.REGULAR,15));
        price.setStyle("-fx-padding:5px");

        layout.getChildren().addAll(imageView,itemName,quantity,plusButton,minusButton,price);

        return layout;
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchtoHomepage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Shirt.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
