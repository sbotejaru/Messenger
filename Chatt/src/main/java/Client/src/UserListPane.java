package Client.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class UserListPane extends JPanel implements UserStatusListener {

    private final ChatClient client;
    private JList<String> userListUI;
    private DefaultListModel<String> userListModel;
    private Button disconnect = new Button("Disconnect");

    public UserListPane(ChatClient client) {
        this.client = client;
        this.client.addUserStatusListener(this);

        userListModel = new DefaultListModel<>();
        //userListModel.addElement("\n");
        userListUI = new JList<>(userListModel);
        userListUI.setFont(new Font("Verdana", Font.PLAIN, 15));
        setBackground(Color.BLACK);
        setLayout(null);

        ImageIcon icon = new ImageIcon("src\\main\\resources\\com\\example\\chatt\\something2.png");
        Image image = icon.getImage();
        image = image.getScaledInstance(40,40, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        JLabel currentUser = new JLabel(client.getUsername());
        JLabel onlineUsers = new JLabel("Online users:");
        JLabel online = new JLabel("ONLINE");

        currentUser.setForeground(Color.WHITE);
        currentUser.setFont(new Font("Verdana", Font.PLAIN, 20));
        currentUser.setText(client.getUsername());
        currentUser.setIcon(icon);
        currentUser.setBounds(10, 15, 300, 50);

        //online.setForeground(Color.GREEN);
        //online.setBounds(70, 40, 50, 50);

        onlineUsers.setBounds(150, 60, 100, 30);
        onlineUsers.setForeground(Color.white);

        disconnect.setForeground(Color.WHITE);
        disconnect.setBackground(Color.BLACK);
        disconnect.setFocusable(false);
        disconnect.setBounds(0, 530, 400, 30);

        JScrollPane scrollPane = new JScrollPane(userListUI);
        scrollPane.setBounds(0, 90, 400, 460);

        add(currentUser);
        add(disconnect);
        add(scrollPane);
        add(onlineUsers);
        add(online);

        disconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    client.logoff();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });

        userListUI.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 1) {
                    String login = userListUI.getSelectedValue();
                    MessagePane messagePane = new MessagePane(client, login);
                    messagePane.setUser(client.getUsername());

                    JFrame f = new JFrame("Chat Window");
                    ImageIcon img = new ImageIcon("src\\main\\resources\\com\\example\\chatt\\chat2.jpg");
                    f.setIconImage(img.getImage());
                    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    f.setSize(500, 500);
                    f.getContentPane().add(messagePane, BorderLayout.CENTER);
                    f.setResizable(false);
                    f.setVisible(true);
                }
            }
        });
    }

    /*public static void main(String[] args) {
        ChatClient client = new ChatClient("localhost", 8817);

        UserListPane userListPane = new UserListPane(client);
        JFrame frame = new JFrame("User List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);

        frame.getContentPane().add(userListPane, BorderLayout.CENTER);
        frame.setVisible(true);

        *//*if (client.connect()) {
            try {
                client.login("guest", "guest");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*//*
    }*/

    @Override
    public void online(String login) {
        if (userListModel == null)
        {
            userListModel = new DefaultListModel<>();
            userListUI = new JList<>(userListModel);
            userListUI.setFont(new Font("Verdana", Font.PLAIN, 15));
        }

        userListModel.addElement(login);
    }

    @Override
    public void offline(String login) {
        userListModel.removeElement(login);
    }
}
