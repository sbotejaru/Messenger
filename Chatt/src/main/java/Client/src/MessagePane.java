package Client.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessagePane extends JPanel implements MessageListener {

    private final ChatClient client;
    private final String login;
    private String user;

    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> messageList = new JList<>(listModel);
    private JTextField inputField = new JTextField();

    public MessagePane(ChatClient client, String login) {
        this.client = client;
        this.login = login;

        messageList.setFont(new Font("Verdana", Font.PLAIN, 12));
        ImageIcon icon = new ImageIcon("src\\main\\resources\\com\\example\\chatt\\chat.jpg");
        Image image = icon.getImage();
        image = image.getScaledInstance(30,30, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        client.addMessageListener(this);
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new BorderLayout());
        JLabel messageReceiver = new JLabel();
        messageReceiver.setForeground(Color.WHITE);
        messageReceiver.setFont(new Font("Verdana", Font.PLAIN, 15));
        messageReceiver.setPreferredSize(new Dimension(20, 60));
        messageReceiver.setText(" You are chatting with " + login); // + from login
        messageReceiver.setIcon(icon);
        add(messageReceiver, BorderLayout.NORTH);
        add(new JScrollPane(messageList), BorderLayout.CENTER);
        add(inputField, BorderLayout.SOUTH);
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        listModel.addElement("                  "  + "                   "
                + "            "  +  formatter1.format(date));
        listModel.addElement("\n");

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Date date1 = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                    String text = inputField.getText();

                    client.msg(login, text);
                    listModel.addElement('['+ formatter.format(date1) + "] " + user + ": " + text);
                    inputField.setText("");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public void setUser(String username)
    {
        this.user = username;
    }

    @Override
    public void onMessage(String fromLogin, String msgBody) {
        if (login.equalsIgnoreCase(fromLogin)) {
            Date date2 = new Date();
            SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
            String line = '['+ formatter2.format(date2) + "] " + fromLogin + ": " + msgBody;
            listModel.addElement(line);
        }
    }
}
