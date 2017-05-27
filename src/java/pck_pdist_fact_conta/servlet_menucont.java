/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck_pdist_fact_conta;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Marco Rodriguez
 */
@WebServlet(name = "servlet_menucont", urlPatterns = {"/servlet_menucont"})
public class servlet_menucont extends HttpServlet {

        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String pantalla = "";
        String boton = "";
        
        boton = request.getParameter("boton");        
        
        if (boton==null || boton=="")
            pantalla=mostrar_pantalla("","");

        if (boton!=null && boton!=""){            
            if(boton.equals("Cuentas")){
                response.sendRedirect("servlet_cuenta");
            }
            if(boton.equals("Tipo de Cuentas")){
                response.sendRedirect("servlet_tipocuenta");
            }
            if(boton.equals("Cerrar Sesion")){
                response.sendRedirect("servlet_usuario");
            }
        }
        out.println(pantalla);
            
    }
    
    public String mostrar_pantalla(String nombre, String password){       
        String pantalla="";
        pantalla+="<html>";
        pantalla+="<head>";
        pantalla+="</head>";
        pantalla+="<body>";
        pantalla+="<h2>Menu Contabilidad</h2>";
        pantalla+="<form action='servlet_menu' method='post'>";
        pantalla+="<input type='submit' value='Cuentas' name='boton' ></input>";
        pantalla+="<br><br>";
        pantalla+="<input type='submit' value='Tipo de Cuentas' name='boton' ></input>";
        pantalla+="<br><br>";
        pantalla+="<input type='submit' value='Cerrar Sesion' name='boton' ></input>";
        pantalla+="</form>";            
        pantalla+="</body>";
        pantalla+="</html>";
        
        return pantalla;
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
