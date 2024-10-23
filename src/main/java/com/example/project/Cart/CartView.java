package com.example.project.Cart;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

public class CartView {

    private Parent view;

    public CartView() throws IOException {
        URL uiFile = CartView.class.getClassLoader().getResource("com/example/project/cart.fxml");
        Parent root = FXMLLoader.load(uiFile);
    }

    public Parent getView(){
        return this.view;
    }
}
