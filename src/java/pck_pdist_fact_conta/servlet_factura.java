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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pck_pdist_fact_conta.entidades.Articulos;
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
        String pantalla="";
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
        System.out.println(f);
        String ruc = "";
        ruc = request.getParameter("cbCliente");
        Date fecha = new Date();
        if(f != null){
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                fecha = format.parse(f);
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        String ciudad = "";
        ciudad = request.getParameter("cbCiudad");
        pck_pdist_fact_conta.entidades.CiudadEntrega ciu;
        if(ciudad!=null){
            ciu = new pck_pdist_fact_conta.entidades.CiudadEntrega(BigDecimal.valueOf(Double.valueOf(ciudad)));
        }
        else{
            ciu = new pck_pdist_fact_conta.entidades.CiudadEntrega();
        }
        pck_pdist_fact_conta.entidades.Cliente cli;
        if(ruc!=null){
            cli = new pck_pdist_fact_conta.entidades.Cliente(ruc);
        }
        else{
            cli = new pck_pdist_fact_conta.entidades.Cliente();
        }
        
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
        
        Factura numFInsertar = nfac.obtenerNum();
        
        if(boton!=null && boton!=""){
            if(boton.equals("Insertar")){
                nfac.insertar(ciu,cli,fecha);
                for(int i=0;i<nomArticulo.length;i++){
                    nart.insertar(numFInsertar, nomArticulo[i], pArticulo[i], new BigInteger(cantArticulo[i]));
                }
                response.sendRedirect("servlet_menu");
            }
            
            if(boton.equals("Eliminar")){
                if(nfac.eliminar(new BigDecimal(numFac)) == 1){
                    for(pck_pdist_fact_conta.entidades.Articulos a:nart.mostrarArticulos(numFact)){
                        nart.eliminar(a.getArtCodigo());
                    }                
                    out.println("<script> alert('Factura eliminada'); </script>");
                    request.getRequestDispatcher("web_factura.jsp").forward(request, response);
                }
                else{
                    request.getRequestDispatcher("web_factura.jsp").forward(request, response);
                    out.println("<script> alert('No existe la factura'); </script>");
                }
            }
                
             /*   if(boton.equals("Modificar Factura")){
                    if (ncli.modificar(ruc, nombre,direccion)==1)
                        msj = "Se modificó";
                    else
                        msj = "No se pudo modificar";
                    pantalla = mostrar_pantalla("","","");
                    pantalla+=msj;
                }
            */
            if(boton.equals("Modificar Articulos")){
                pantalla = desplegar_pantalla(numFac,nart.mostrarArticulos(numFact));
            }
             /*
                if(boton.equals("Buscar")){  
                    nombre = ncli.buscar(ruc).get(0);
                    direccion = ncli.buscar(ruc).get(1);
                    if(nombre!=null && direccion!=null)
                        msj="Se encontró";
                    else
                        msj="No se encontró";               
                    pantalla = mostrar_pantalla(ruc,nombre,direccion);                  
                    pantalla+=msj;
                }*/
            if(boton.equals("Regresar")){
                response.sendRedirect("servlet_menu");
            }
            if(boton.equals("Regresar Factura")){
                response.sendRedirect("web_factura.jsp");
            }
            out.println(pantalla);
        }
    }
    
    public String desplegar_pantalla(String as_factura, List<Articulos> as_articulos){
        String pantalla="";
        pantalla+="<html>";
        pantalla+="<head>";
        pantalla+="</head>";
        pantalla+="<body>";
        pantalla+="<h2>Tablas Simples - Cliente</h2>";
        pantalla+="<form action='servlet_factura' method='post'>";
        pantalla+="Factura: "+as_factura;
        pantalla+="<br><br>";
        pantalla+="<h2>Artículos</h2>";
        pantalla+="<table>";
        pantalla+="<table id='aTabla'>";
        pantalla+="<tr>";
        pantalla+="<td>N°</td>";
        pantalla+="<td>Artículo</td>";
        pantalla+="<td>Cantidad</td>";
        pantalla+="<td>Precio</td>";
        pantalla+="<td>Eliminar</td>";
        pantalla+="</tr>";
        int i=1;
        for(Articulos a:as_articulos){
            pantalla+="<tr>";
            pantalla+="<td>"+ i++ +"</td>";
            pantalla+="<td><input name='aNombre' value='"+a.getArtNombre()+"' type='text'></td>";
            pantalla+="<td><input name='aCantidad' value='"+a.getArtCantidad()+"' type='text'></td>";
            pantalla+="<td><input name='aPrecio' value='"+a.getArtPrecio()+"' type='text'></td>";
            pantalla+="<td><button type='button' class='quitar'>Quitar</button></td>";
            pantalla+="</tr>";
        }
        pantalla+="</table>";
        pantalla+="<br>";
        pantalla+="<input type='submit' value='Modificar Articulo' name='boton'></input> ";
        pantalla+="<input type='submit' value='Regresar Factura' name='boton'></input>";
        pantalla+="</form>";            
        pantalla+="</body>";
        pantalla+="</html>";
        return pantalla;
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
