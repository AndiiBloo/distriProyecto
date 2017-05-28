/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck_pdist_fact_conta;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pck_pdist_fact_conta.entidades.Cliente;

/**
 *
 * @author Andrés López
 */
@WebServlet(name = "servlet_factura", urlPatterns = {"/servlet_factura"})
public class servlet_factura extends HttpServlet {

    negocio_cliente ncli = new negocio_cliente();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String f = request.getParameter("txtFecha");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        String ruc = request.getParameter("cbCliente");
        Date fecha = new Date();
        try {
            fecha = format.parse(f);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        String ciudad = request.getParameter("cbCiudad");
        
        String[] nomArticulo = request.getParameterValues("aNombre");
        String[] cantArticulo = request.getParameterValues("aCantidad");
        String[] precArticulo = request.getParameterValues("aPrecio");
        int [] cArticulo = new int[1024];
        float[] pArticulo = new float[1024];
        for(int i=0;i<nomArticulo.length;i++){
            cArticulo[i] = Integer.parseInt(cantArticulo[i]);
            pArticulo[i] = Float.parseFloat(precArticulo[i]);
        }
        System.out.println(ruc+" "+fecha.toString()+" "+ciudad+"<br>");
        out.print(ruc+" "+fecha.toString()+" "+ciudad+"<br>");
        for(int i=0;i<nomArticulo.length;i++){
            out.println(nomArticulo[i]+" "+cArticulo[i]+" "+pArticulo[i]+"<br>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
