package mail;

import domain.MailDetails;
import domain.Product;
import selenium.QueryController;
import xml.Utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailController {
    private static Session session;
    private static MailDetails mailDetails;

    public static void sendNew() {
        mailDetails = Utils.fetchMailDetails();
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(mailDetails.getUsername(), mailDetails.getPassword());
                    }
                });
        try {
            Transport.send(setMessage());
            System.out.println("Email sent with no errors...");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private static Message setMessage() throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(mailDetails.getFrom()));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(mailDetails.getTo()));
        message.setSubject("Amazon products");
        message.setContent(getEmailContent(), "text/html; charset=utf-8");
        return message;
    }

    private static String getEmailContent() {
        QueryController.products.sort((o1, o2) -> {
            if (o1.getPrice() < o2.getPrice()) {
                return -1;
            } else {
                return 1;
            }
        });
        String top = "<html>\n" +
                "\n" +
                "<head>\n" +
                "\n" +
                "    <style type=\"text/css\">\n" +
                "        table {\n" +
                "            table-layout: fixed;\n" +
                "            width: 100%;\n" +
                "        }\n" +
                "\n" +
                "        .thead-dark {\n" +
                "            border-right: 1px solid #ddd;\n" +
                "            border-bottom: 1px solid #ddd;\n" +
                "            background-color: #43474f;\n" +
                "            color: white;\n" +
                "        }\n" +
                "\n" +
                "        table td {\n" +
                "            border-right: 1px solid #ddd;\n" +
                "            border-bottom: 1px solid #ddd;\n" +
                "            word-wrap: break-word;\n" +
                "            /* All browsers since IE 5.5+ */\n" +
                "            overflow-wrap: break-word;\n" +
                "            /* Renamed property in CSS3 draft spec */\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "    <h2>Listado de productos</h2>\n" +
                "\n" +
                "    <p>Ordenados de menor a mayor precio.</p>\n" +
                "\n" +
                "    <table class=\"table\">\n" +
                "        <thead class=\"thead-dark\">\n" +
                "            <tr>\n" +
                "                <th scope=\"col\">Precio</th>\n" +
                "                <th scope=\"col\">Nombre</th>\n" +
                "                <th scope=\"col\">Descripción</th>\n" +
                "                <th scope=\"col\">URL</th>\n" +
                "            </tr>\n" +
                "        </thead>\n" +
                "        <tbody>\n";
        String products = "";
        for (Product product : QueryController.products
                ) {

            products +=
                    "            <tr>\n" +
                            "                <th scope=\"row\">$"+product.getPrice()+"</th>\n" +
                            "                <td>" + product.getName() + "</td>\n" +
                            "                <td>\n" +
                            "                    <p>" + product.getDescription() + "</p>\n" +
                            "                </td>\n" +
                            "                <td><a href=\"" + product.getUrl() + "\">Ver en Amazon</a></td>\n" +
                            "            </tr>\n";//Todo... Revisar la estructura de la tabla, valorar ctrl+z
        }
        String bottom =
                "        </tbody>\n" +
                        "    </table>\n" +
                        "</body>\n" +
                        "\n" +
                        "</html>";
        return top + products + bottom;
    }
}