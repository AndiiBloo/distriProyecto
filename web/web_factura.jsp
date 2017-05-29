<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="pck_pdist_fact_conta.entidades.CiudadEntrega"%>
<%@page import="pck_pdist_fact_conta.negocio_ciudad"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="pck_pdist_fact_conta.entidades.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="pck_pdist_fact_conta.negocio_cliente"%>
<%@ page import="java.io.PrintWriter" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    pck_pdist_fact_conta.entidades.Usuarios us;
    
    HttpSession ses = request.getSession(false);
    if(ses != null){ 
        ses = request.getSession();
        us = (pck_pdist_fact_conta.entidades.Usuarios)ses.getAttribute("usuario");
    }
    else{
        us = null;
    }
    
    if(us != null && (us.getUsRol().intValueExact() == 1 || us.getUsRol().intValueExact() == 2)){
%>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Factura</title>
            <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
        </head>
        <body>
            <script>
                function contador()
                {
                    var i=0;
                     $('#aTabla tr').each(function() {
                         $(this).find(".aNo").html(i);
                         i++;
                      });
                }
                <%
                    Cliente c = new Cliente();
                    CiudadEntrega ce = new CiudadEntrega();
                    java.util.Date d = new java.util.Date();
                    String fd="";
                    String nFc="";
                    if(request.getAttribute("rucAtr")!= null && 
                            request.getAttribute("cAtr")!=null && 
                            request.getAttribute("fAtr") !=null &&
                            request.getAttribute("nFac") != null){
                        
                        c = (Cliente)request.getAttribute("rucAtr");
                        ce = (CiudadEntrega)request.getAttribute("cAtr");
                        nFc = (String)request.getAttribute("nFac");
                        DateFormat dFor = new SimpleDateFormat("yyyy-MM-dd");
                        d = (java.util.Date)request.getAttribute("fAtr");
                        fd = dFor.format(d);
                    }
                    else{
                        d = Calendar.getInstance().getTime();
                        DateFormat dFor = new SimpleDateFormat("yyyy-MM-dd");
                        fd = dFor.format(d);
                    }
                    
                %>
                $(document).ready(function(){
                    $('#cbCliente option[value ="'+<%=c.getCliRuc()%>+'"]').attr("selected","selected");
                    $('#cbCiudad option[value ="'+<%=ce.getCiuCodigo()%>+'"]').attr("selected","selected");
                    $('#txtFecha').val('<%=fd%>');
                    $('#numFac').val('<%=nFc%>');
                    $('#agregarFila').click(function(){
                        var anum=$('#aTabla tr').length;
                        trow=  "<tr><td class='aNo'>"+anum+"</td>"+
                            "<td><input name='aNombre' type='text'></td>"+
                            "<td><input name='aCantidad' type='text'></td>"+
                            "<td><input name='aPrecio' type='text'></td>"+
                           "<td><button type='button' class='quitar'>Quitar</button></td></tr>";
                        $('#aTabla').append(trow);
                        $('#num').show();
                        $('#art').show();
                        $('#cant').show();
                        $('#prec').show();
                        $('#elim').show();
                    });
                });

                $(document).on('click', 'button.quitar', function () {
                       $(this).closest('tr').remove();
                       contador();
                     return false;
                 });
            </script>

            <%
                negocio_cliente ncli = new negocio_cliente();
                negocio_ciudad nciu = new negocio_ciudad();
                List<Cliente> listCli = ncli.mostrarClientes();
                List<CiudadEntrega> listCiu = nciu.mostrarCiudades();
            %>

            <h1> Tabla Compleja - Factura </h1>
            <form action="servlet_factura" method="post">
                <h2>Cabecera</h2>
                <table>
                    <tr>
                        <td style="font-weight: bold;">
                            Número:
                        </td>
                        <td>
                            <input type="text" id="numFac" name="numFac">
                        </td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold;">
                            Ruc:
                        </td>
                        <td>
                            <select id="cbCliente" name="cbCliente">
                                <%
                                    for(int i=0; i<listCli.size();i++){
                                %>
                                <option value="<%=listCli.get(i).getCliRuc()%>">
                                    <%=listCli.get(i).getCliNombre()%>
                                <%
                                    }
                                %>
                                </option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold;">
                            Fecha:
                        </td>
                        <td>
                        <% 
                            DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
                            java.util.Date hoy = Calendar.getInstance().getTime();
                            String fecha = dFormat.format(hoy);
                        %>
                        <input type="date" id="txtFecha" name="txtFecha" value="<%= fecha%>"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold;">
                            Ciudad: 
                        </td>
                        <td>
                            <select id="cbCiudad" name="cbCiudad">
                                <%
                                    for(int i=0; i<listCiu.size();i++){
                                %>
                                <option value="<%=listCiu.get(i).getCiuCodigo()%>">
                                    <%=listCiu.get(i).getCiuNombre()%>
                                <%
                                    }
                                %>
                                </option>
                            </select>
                        </td>
                    </tr>
                </table>

                <h2>Artículos</h2>
                <input id="agregarFila" type="button" value="Agregar Fila">
                <table id="aTabla">
                    <tr>
                        <td hidden="true" id="num">N°</td>
                        <td hidden="true" id="art">Artículo</td>
                        <td hidden="true" id="cant">Cantidad</td>
                        <td hidden="true" id="prec">Precio</td>
                        <td hidden="true" id="elim">Eliminar</td>
                    </tr>
                </table>
                <br>
                <input type='submit' value='Insertar' name='boton'>
                <input type='submit' value='Eliminar Factura' name='boton'>
                <input type='submit' value='Modificar Factura' name='boton'>
                <input type='submit' value='Buscar Factura' name='boton'>
                <input type="submit" name="boton" value="Regresar">
                <br><br>
                <input type='submit' value='Modificar Articulos' name='boton'>
                <% String msj= String.valueOf(request.getAttribute("msj"));
                System.out.println("1. "+msj);
                if(msj != "null"){
                %>
                    <script>alert('<%=msj%>');</script>
                <%}%>
            </form>
        </body>
    </html>
<%  } 
    else{
%>
<html>
    <body>
        <script> alert('Sesión incorrecta');</script>
    </body>
</html>
<%  }
%>