package Client.src;

import javax.swing.*;
import com.example.chatt.*;

public class LoginWindow extends JFrame {
    HelloApplication hello = new HelloApplication();

    public LoginWindow() {
        hello.launchApp();
    }
    public static void main(String[] args) {
        LoginWindow loginWin = new LoginWindow();
    }
}
