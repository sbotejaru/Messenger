package com.example.chatt;

import Client.src.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Register {

    public ChatClient client;
    @FXML
    private Button registerButton;
    @FXML
    private TextField usernameTextEdit;
    @FXML
    private PasswordField password;
    @FXML
    private Label errorLabel;

    public void userRegister() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        stage.setTitle("Register");
        stage.setResizable(false);
    }

    /*public void Register() {
        client = new ChatClient("localhost", 8817);
    }*/

    public void userRegister(ActionEvent event) throws IOException{
        client = new ChatClient("localhost", 8817);
        client.connect();
        checkRegister();
    }

    public void setClient(ChatClient client)
    {
        this.client = client;
        //client.connect();
    }

    private void checkRegister() throws IOException{

        String user = usernameTextEdit.getText();
        String pass = password.getText();

        try {
            int result = client.register(user, pass);
            if (result == 1) {
                errorLabel.setText("Registered succesfully.");
            } else if (result == 2) {
                // show error message
                errorLabel.setText("Username already in use.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

