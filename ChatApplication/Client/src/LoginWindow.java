import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginWindow extends JFrame {
    private final ChatClient client;
    JTextField loginField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Login");
    JButton registerButton = new JButton("Register");

    public LoginWindow() {
        super("Login");
        this.setSize(200,200);

        this.client = new ChatClient("localhost", 8817);
        client.connect();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p = new JPanel();

        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setSize(300, 300);
        p.add(loginField);
        p.add(passwordField);
        p.add(loginButton);
        p.add(registerButton);
        p.setSize(300, 300);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doLogin();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doRegister();
            }
        });

        getContentPane().add(p);

        pack();


        setVisible(true);
    }

    private void doLogin() {
        String login = loginField.getText();
        String password = passwordField.getText();

        try {
            int result = client.login(login, password);
            if (result == 1) {
                // bring up the user list window
                UserListPane userListPane = new UserListPane(client);
                JFrame frame = new JFrame("Hello, " + login + "!");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 600);

                frame.getContentPane().add(userListPane, BorderLayout.CENTER);
                frame.setVisible(true);

                setVisible(false);
            } else if (result == 2){
                // show error message
                JOptionPane.showMessageDialog(this, "Invalid username/password.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doRegister()
    {
        String login = loginField.getText();
        String password = passwordField.getText();

        try {
            int result = client.register(login, password);
            if (result == 1) {
                JOptionPane.showMessageDialog(this, "Registered succesfully.");
            } else if (result == 2){
                // show error message
                JOptionPane.showMessageDialog(this, "Username already in use.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LoginWindow loginWin = new LoginWindow();
        loginWin.setVisible(true);
    }
}
