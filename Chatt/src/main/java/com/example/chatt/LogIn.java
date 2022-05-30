package com.example.chatt;

import Client.src.ChatClient;
import Client.src.UserListPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LogIn {

    @FXML
    private Button button;
    @FXML
    private Label wrongLogin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label labelRegister;

    private final ChatClient client;
    //private Register register = new Register(client);

    public void userLogIn(ActionEvent event) throws IOException{
        checkLogin();
    }

    public LogIn() {
        client = new ChatClient("localhost", 8817);
        client.connect();
    }

    private void checkLogin() throws IOException{
       // client.connect();

        String user = username.getText();
        String pass = password.getText();

        try {
            int result = client.login(user, pass);
            if (result == 1) {
                // bring up the user list window
                UserListPane userListPane = new UserListPane(client);
                JFrame frame = new JFrame("Hello, " + user + "!");
                ImageIcon img = new ImageIcon("src\\main\\resources\\com\\example\\chatt\\chat2.jpg");
                frame.setIconImage(img.getImage());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 600);
                frame.getContentPane().add(userListPane, BorderLayout.CENTER);

                Stage stage = (Stage) button.getScene().getWindow();
                stage.hide();

                frame.setResizable(false);
                frame.setVisible(true);

            } else if (result == 2){
                // show error message
                wrongLogin.setText("Invalid username/password.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void userRegister() throws IOException {
       // client.logoff();
        Register register = new Register();
        register.userRegister();
    }
}




