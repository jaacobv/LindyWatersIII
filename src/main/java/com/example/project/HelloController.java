package com.example.project;

import com.example.project.Cart.Item2;
import com.example.project.Cart.ShoppingCart;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

public class HelloController implements Initializable{


    int clicks = 0;

    //GridPane
    @FXML
    private GridPane productGridPane;







    @FXML
    private Label count;
    int number = 0;




    //Switch to Cart
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchtoCart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("cart.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




    @FXML
    private TextField searchBar;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        VBox itemView1= null;
        VBox itemView2=null;
        VBox itemView3 = null;
        try {
            itemView1 = itemView(Item2.BLACKSHIRT);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            itemView2 = itemView(Item2.BLUESHIRT);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            itemView3 = itemView(Item2.REDSHIRT);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        productGridPane.add(itemView1,0,0);
        productGridPane.add(itemView2,1,0);
        productGridPane.add(itemView3,2,0);




    }


    //Distribute into VBOX
    private VBox itemView (Item2 item) throws FileNotFoundException {
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);

        FileInputStream input = new FileInputStream(Paths.get("src", "main", "resources", "Images", item.getImageFile()).toString());

        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);

        Label itemName = new Label(item.getName());
        Label price = new Label(item.getPrice()+ " $");


        Button addButton = new Button ("Add to Cart");
        addButton.setStyle("-fx-background-radius: 100");
        addButton.setUserData(item.name());
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //add to cart button
                Node sourceComponent = (Node)actionEvent.getSource();
               String itemName= (String)sourceComponent.getUserData();
               ShoppingCart shoppingCart = ShoppingCart.getInstance();
               shoppingCart.addItem(itemName);
               //Increment Label
                number++;
                count.setText(""+number);

            }
        });


        layout.getChildren().addAll(imageView,itemName,price,addButton);

        return layout;
    }








}