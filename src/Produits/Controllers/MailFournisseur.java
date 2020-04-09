package Produits.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class MailFournisseur implements Initializable {
    @FXML private TextField mail;
    @FXML private TextField objet;
    @FXML private TextField contenu;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void EnvoiMail(javafx.event.ActionEvent event) {
        String email=mail.getText();
        String object=objet.getText();
        String content=contenu.getText();

        //Envoi du mail
        final String username = "huntkingdom216@gmail.com";
        final String password = "esprit2020";
        String fromEmail ="huntkingdom216@gmail.com";
        String toEmail = email;

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        // start our mail message


        Message message = new MimeMessage(session);
        try{
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("[HuntKingdom] " + object );

            //   message.setText("Email Body Text!");  utilisé dans le cas d'un simple email

            /***** With attachements ********/
            Multipart emailContent = new MimeMultipart();
            //Text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(content);


            //More Body part .. more attachments!
            //Attach body parts
            emailContent.addBodyPart(textBodyPart);


            //Attach multiparts to message
            message.setContent(emailContent);

            /**************************************************************/

            Transport.send(message);
            System.out.println("Message sent =) !");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Succès");
            alert.setContentText("Mail Envoyé Avec Succès!");
            alert.showAndWait();

        } catch(MessagingException e){
            e.printStackTrace();
        }
    }

    public void vide(ActionEvent event) {
        mail.clear();
        objet.clear();
        contenu.clear();

    }
}
