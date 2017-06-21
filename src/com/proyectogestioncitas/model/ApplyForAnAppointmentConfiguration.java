package com.proyectogestioncitas.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class ApplyForAnAppointmentConfiguration {
	private String name;
	private String surname;
	private String birthDate;
	private String id;
	private String associatedCenter;
	private String email;
	
	private String day;
	private String hour;
	private String associatedCenter2;
	
	private File file;
	
	public ApplyForAnAppointmentConfiguration(String name, String surname, String birthDate, String id,
			String associatedCenter, String email, String day, String hour, String associatedCenter2) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.id = id;
		this.associatedCenter = associatedCenter;
		this.email = email;
		this.day = day;
		this.hour = hour;
		this.associatedCenter2 = associatedCenter2;
	}
	
	public void downloadPdfToDesktop(){
		Document document = new Document();
		String userHomeFolder = System.getProperty("user.home");
		file = new File(userHomeFolder + "/Desktop/AppointmentInformation.pdf");

		try {
			PdfWriter.getInstance(document, new FileOutputStream(file));
			System.out.println(userHomeFolder + "/Desktop/file.pdf");
			document.open();
			setPdfInformation(document);
			document.close();
			//System.out.println("File to string: " + file.toString());
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			System.out.println("Fichero no encontrado");
		}
		System.out.println("Pdf creado.");
		JOptionPane.showMessageDialog(null, "The file was created on your desktop(" + file + ")", "File created", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void sendEmailToClient(){
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
	            	javax.mail.PasswordAuthentication password = new javax.mail.PasswordAuthentication("procitas.noreplay@gmail.com", "ProyectoProgramacion");
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
	        msg.setFrom( new InternetAddress( "procitas.noreplay@gmail.com" ) );
	        msg.setRecipients( Message.RecipientType.TO,InternetAddress.parse(email) );
	        msg.setDataHandler(new DataHandler(new FileDataSource(file.toString())));
	        msg.setFileName("AppointmentInformation.pdf");
	        msg.setSentDate( new Date());
	        msg.setSubject( "PROCITAS - Appointment information." );
	        
	        //msg.setContent(new Object(), "Hi Dear " + surname + ", " + name + "\n" + "    Here is your PDF with all the information about your last " + 
	        //"appointment that you requested with the app PROCITAS. Hope this will be usefull." + "\n Bye ;)");
	
	        //--[ Ask the Transport class to send our mail message
	        Transport.send( msg );
	        JOptionPane.showMessageDialog(null, "An e-mail was sended to your e-mail address: " + email + "\n Please check it, because " + 
	        		"it has all the information about your appointment." , "E-mail sended", JOptionPane.INFORMATION_MESSAGE);
	
	    } catch(Exception E){
	        System.out.println( "Oops something has gone pearshaped!");
	        System.out.println( E );
	    }
	}
	
	public void setPdfInformation(Document document){
		try {
			
			Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
			Paragraph title = new Paragraph("PROCITAS - Appointment information", boldFont);
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);
			
			Paragraph content = new Paragraph();
			content.add("\n");
			content.add("Dear " + name + ", \n");
			content.add("    Here is the PDF with all the information about the appointment that you requested recently by our app 'PROCITAS'.\n"
					+ "    Thank you for your attention and for use our application, then here is all the information about your appointment:\n" +
					"\nName: " + name +
					"\nSurname/s: " + surname +
					"\nID: " + id +
					"\nBirth date: " + birthDate +
					"\nAssociated center: " + associatedCenter +
					"\nDate: Day " + day + " at " + hour);
			
			document.add(content);

			Image image1;
			try {
				image1 = Image.getInstance("img/proCitas.jpg");
				image1.setAbsolutePosition(0, 0);
				image1.scalePercent(40f);
				document.add(image1);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		} catch (DocumentException e) {
			System.out.println("Error en el setPdfInformation()");
		}
	}
	
/**
	public static void main(String[] args) {
		ApplyForAnAppointmentConfiguration apply = new ApplyForAnAppointmentConfiguration("Nombre", "Apellidos", "13/10/1998", "77644840Z", "1", "oscarlifeplays@gmail.com", "21", "18:00", "1");
		apply.downloadPdfToDesktop();
		apply.sendEmailToClient();
	}
*/
	
	
	
}