/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck_pdist_fact_conta;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pck_pdist_fact_conta.entidades.Factura;


/**
 *
 * @author Andrés López
 */
@WebServlet(name = "servlet_factura", urlPatterns = {"/servlet_factura"})
public class servlet_factura extends HttpServlet {

    negocio_factura nfac = new negocio_factura();
    negocio_articulo nart = new negocio_articulo();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String numFac = "";
        numFac = request.getParameter("numFac");
        Factura numFact = new Factura();
        try{
            numFact = new Factura(new BigDecimal(numFac));
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        String boton = "";
        boton=request.getParameter("boton");
        String f = "";
        f = request.getParameter("txtFecha");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String ruc = "";
        ruc = request.getParameter("cbCliente");
        Date fecha = new Date();
        try {
            fecha = format.parse(f);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        String ciudad = "";
        ciudad = request.getParameter("cbCiudad");
        
        pck_pdist_fact_conta.entidades.CiudadEntrega ciu = 
                new pck_pdist_fact_conta.entidades.CiudadEntrega(BigDecimal.valueOf(Double.valueOf(ciudad)));
        
        pck_pdist_fact_conta.entidades.Cliente cli = 
                new pck_pdist_fact_conta.entidades.Cliente(ruc);
        
        String[] nomArticulo;
        nomArticulo = request.getParameterValues("aNombre");
        String[] cantArticulo;
        cantArticulo = request.getParameterValues("aCantidad");
        String[] precArticulo;
        precArticulo = request.getParameterValues("aPrecio");
        float[] pArticulo = new float[1024];
        try{
            for(int i=0;i<nomArticulo.length;i++){
                pArticulo[i] = Float.parseFloat(precArticulo[i]);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        Factura numF = nfac.obtenerNum();
        
        if(boton!=null && boton!=""){
            if(boton.equals("Insertar")){
                nfac.insertar(ciu,cli,fecha);
                for(int i=0;i<nomArticulo.length;i++){
                    nart.insertar(numF, nomArticulo[i], pArticulo[i], new BigInteger(cantArticulo[i]));
                }
            }
            
            if(boton.equals("Eliminar")){
                nfac.eliminar(new BigDecimal(numFac));
                for(pck_pdist_fact_conta.entidades.Articulos a:nart.mostrarArticulos(numFact)){
                    nart.eliminar(a.getArtCodigo());
                }
                out.println("<script> alert('Factura eliminada'); </script>");
            }
                
             /*   if(boton.equals("Modificar")){
                    if (ncli.modificar(ruc, nombre,direccion)==1)
                        msj = "Se modificó";
                    else
                        msj = "No se pudo modificar";
                    pantalla = mostrar_pantalla("","","");
                    pantalla+=msj;
                }

                if(boton.equals("Buscar")){  
                    nombre = ncli.buscar(ruc).get(0);
                    direccion = ncli.buscar(ruc).get(1);
                    if(nombre!=null && direccion!=null)
                        msj="Se encontró";
                    else
                        msj="No se encontró";               
                    pantalla = mostrar_pantalla(ruc,nombre,direccion);                  
                    pantalla+=msj;
                }
                if(boton.equals("Regresar")){
                    response.sendRedirect("servlet_menu");
                }*/
        }
        
        //request.getRequestDispatcher("web_factura.jsp").forward(request, response);
        
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
