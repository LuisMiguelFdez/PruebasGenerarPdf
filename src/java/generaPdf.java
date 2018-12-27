/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfSmartCopy;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import misClases.Articulo;
import misClases.Cesta;
import misClases.Tienda;

/**
 *
 * @author lmfde
 */
public class generaPdf extends HttpServlet {

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
        Tienda tiendaDaw=(Tienda)request.getServletContext().getAttribute("contextoTienda");
        HashMap<String,Articulo> articulosTienda = tiendaDaw.getListaArticulos();
        
        HttpSession miSession = request.getSession();
        Cesta miCarro = (Cesta) miSession.getAttribute("miCarro");
        HashMap<String,Integer> articulos = miCarro.getContenido();
        
        /*Tabla*/
        PdfPTable tablaPDF = new PdfPTable(3);
        tablaPDF.addCell("Articulos");
        tablaPDF.addCell("Cantidad");
        tablaPDF.addCell("Precio");
        
        for(Map.Entry<String,Integer> cursor : articulos.entrySet()) {	
            String nombreArt = cursor.getKey();
            Integer cantidad = cursor.getValue();
            Double precioLinea = cantidad*articulosTienda.get(nombreArt).getPrecio();
            tablaPDF.addCell(nombreArt);
            tablaPDF.addCell(cantidad.toString());
            tablaPDF.addCell(precioLinea.toString());
        }
        
        /*Paragraphs*/
        Paragraph parrafo = new Paragraph("Factura --- nª 3322 ---");
        parrafo.setAlignment(2);
        Paragraph parrafo2 = new Paragraph("Factura --- nª 3323 ---");
        parrafo2.setAlignment(1);
        
        Document documentoPdf =new Document();
        documentoPdf.addAuthor("@Luismi");
        documentoPdf.addHeader("cabecera", "esta es la cabecera del pdf generado");

        try {
            PdfWriter.getInstance(documentoPdf, new FileOutputStream("C:/documento.pdf"));
            documentoPdf.open();
                documentoPdf.add(new Paragraph("Esta es una linea del pdf"));
                documentoPdf.add(parrafo);
                documentoPdf.add(parrafo2);
                documentoPdf.add(new LineSeparator());
                documentoPdf.add(tablaPDF);
            documentoPdf.close();
        } catch (DocumentException ex) {
            Logger.getLogger(generaPdf.class.getName()).log(Level.SEVERE, null, ex);
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
