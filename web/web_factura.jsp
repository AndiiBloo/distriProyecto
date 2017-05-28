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

            $(document).ready(function(){
                $('#agregarFila').click(function(){
                    var anum=$('#aTabla tr').length;
                    trow=  "<tr><td class='aNo'>"+anum+"</td>"+
                        "<td><input name='aNombre' type='text'></td>"+
                        "<td><input name='aCantidad' type='text'></td>"+
                        "<td><input name='aPrecio' type='text'></td>"+
                       "<td><button type='button' class='quitar'>Quitar</button></td></tr>";
                    $('#aTabla').append(trow);
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
        <form method="post" action="servlet_factura">
            <table>
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
                        <input type="date" value="<%= fecha%>"/>
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
            
            <h3>Artículos</h3>
            <input style="text-align: right;" id="agregarFila" type="button" value="Agregar Fila">
            <table id="aTabla">
                <tr>
                    <td>N°</td>
                    <td>Artículo</td>
                    <td>Cantidad</td>
                    <td>Precio</td>
                    <td>Eliminar</td>
                </tr>
            </table>
            <input type="Submit" value="Enviar">
            <input type="Reset" value="Cancelar">
        </form>
    </body>
</html>
