import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.PasswordAuthentication;
import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.log.SysoCounter;
import com.itextpdf.text.pdf.PdfWriter;

public class PruebasPDF {
	
	public static void main(String[] args) {	
		
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream("FILE/file"));
			document.open();
			document.add(new Paragraph("PDF para Ángel"));
			document.close();
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			System.out.println("Fichero no encontrado");
		}
		System.out.println("Pdf creado.");
		
		//enviarCorreo();
		intento2();
		
	}
	
	public void intento2(){
		try{

	        Properties props = new Properties();
	        props.put("mail.smtp.host", "smtp.gmail.com"); // for gmail use smtp.gmail.com
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.debug", "true"); 
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.port", "465");
	        props.put("mail.smtp.socketFactory.port", "465");
	        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        props.put("mail.smtp.socketFactory.fallback", "false");
	  
	        Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {

	            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
	            	javax.mail.PasswordAuthentication password = new javax.mail.PasswordAuthentication("oscarlifeplays4@gmail.com", "contraseña");
	                return password;
	            }
	        });

	        mailSession.setDebug(true); // Enable the debug mode

	        Message msg = new MimeMessage( mailSession );

	        /**
	         * Nos da error al tener que activar la seguridad para aplicaciones menos seguras.
	         * tendrás que crear el FILE/file para que funcione el programa.
	         */
	        //Security.addProvider(new JSSEProvider());
	        //--[ Set the FROM, TO, DATE and SUBJECT fields
	        msg.setFrom( new InternetAddress( "oscarlifeplays4@gmail.com" ) );
	        msg.setRecipients( Message.RecipientType.TO,InternetAddress.parse("elbodegondeljazz@gmail.com") );
	        msg.setDataHandler(new DataHandler(new FileDataSource("FILE/file")));
	        msg.setFileName("FILE/file.pdf");
	        msg.setSentDate( new Date());
	        msg.setSubject( "Funciona!" );

	        //--[ Ask the Transport class to send our mail message
	        Transport.send( msg );
	        System.out.println("Correo enviado");

	    }catch(Exception E){
	        System.out.println( "Oops something has gone pearshaped!");
	        System.out.println( E );
	    }
	}
}

