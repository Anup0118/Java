import javax.swing.*;
import java.awt.event.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailSender {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Email Sender");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel senderLabel = new JLabel("Sender Email:");
        senderLabel.setBounds(20, 20, 100, 25);
        JTextField senderField = new JTextField();
        senderField.setBounds(150, 20, 200, 25);

        JLabel receiverLabel = new JLabel("Receiver Email:");
        receiverLabel.setBounds(20, 60, 100, 25);
        JTextField receiverField = new JTextField();
        receiverField.setBounds(150, 60, 200, 25);

        JLabel subjectLabel = new JLabel("Subject:");
        subjectLabel.setBounds(20, 100, 100, 25);
        JTextField subjectField = new JTextField();
        subjectField.setBounds(150, 100, 200, 25);

        JLabel messageLabel = new JLabel("Message:");
        messageLabel.setBounds(20, 140, 100, 25);
        JTextArea messageArea = new JTextArea();
        messageArea.setBounds(150, 140, 200, 100);

        JButton sendButton = new JButton("Send");
        sendButton.setBounds(150, 260, 100, 30);

        frame.add(senderLabel);
        frame.add(senderField);
        frame.add(receiverLabel);
        frame.add(receiverField);
        frame.add(subjectLabel);
        frame.add(subjectField);
        frame.add(messageLabel);
        frame.add(messageArea);
        frame.add(sendButton);

        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sender = senderField.getText();
                String receiver = receiverField.getText();
                String subject = subjectField.getText();
                String message = messageArea.getText();

                // SMTP config (assume available)
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.example.com");
                props.put("mail.smtp.port", "25"); // change if needed

                Session session = Session.getInstance(props, null);
                try {
                    MimeMessage msg = new MimeMessage(session);
                    msg.setFrom(new InternetAddress(sender));
                    msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
                    msg.setSubject(subject);
                    msg.setText(message);
                    Transport.send(msg);
                    JOptionPane.showMessageDialog(frame, "Email Sent Successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error Sending Email: " + ex.getMessage());
                }
            }
        });

        frame.setVisible(true);
    }
}
