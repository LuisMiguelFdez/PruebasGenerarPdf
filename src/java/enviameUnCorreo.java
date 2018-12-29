/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lmfde
 */
public class enviameUnCorreo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {                 
        String email = "tuEmailAqui@gmail.com";
        String passwd = "********";
        String destinatario = "destinatario@gmail.com" ; //no se si funciona a un destinatario @hotmail, @outlook...
        
        String asunto = " (No haga caso al mensaje, se trata de una serie de pruebas) Compra realizada correctamente";
        String contenidoMensaje = "Gracias por realizar su pedido, le llegerá a su casa en menos de 72 Horas.";
        
        //Properties
        Properties propiedadesConexion = new Properties();
        propiedadesConexion.setProperty("mail.smtp.host","smtp.gmail.com");//Nombre del servidor de correo
        propiedadesConexion.setProperty("mail.smtp.starttls.enable","true");// TLS si está activo (Transport Layer Security)
        propiedadesConexion.setProperty("mail.smtp.port", "587");
        propiedadesConexion.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
        propiedadesConexion.setProperty("mail.smtp.user", email);
        propiedadesConexion.setProperty("mail.smtp.auth", "true");
        
        //Session
        Session sesion = Session.getDefaultInstance(propiedadesConexion);
        sesion.setDebug(true);
        
        //MimeMessage
        MimeMessage mensaje = new MimeMessage(sesion);
        try {
            //De quien
            mensaje.setFrom(new InternetAddress(email));
            //Para quien
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress("emailgenericolm@gmail.com"));
            //Ahora falta el asunto y el mensaje
            mensaje.setSubject(asunto);
            mensaje.setText(contenidoMensaje);
        } catch (AddressException ex) {
            System.out.println("Error Direccion --> "+ex.getMessage());
        } catch (MessagingException ex) {
            System.out.println("Error Message --> "+ex.getMessage());
        }
        
        //Ahora enviamos el mensaje 
        
        try {
            Transport envio = sesion.getTransport("smtp");
            envio.connect(email, passwd);
            mensaje.saveChanges();
            envio.sendMessage(mensaje, mensaje.getAllRecipients());
            envio.close();
            
        } catch (NoSuchProviderException ex) {
            System.out.println("Error Provider --> "+ex.getMessage());
        } catch (MessagingException ex) {
            System.out.println("Error Message Envio (posiblemente no esté validado el correo o haga cosas raras) --> "+ex.getMessage());
        }
        
        
        
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
