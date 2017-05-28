package pck_pdist_fact_conta;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pck_pdist_fact_conta.entidades.CiudadEntrega;
import pck_pdist_fact_conta.entidades.Cliente;
import pck_pdist_fact_conta.entidades.Factura;

public class negocio_factura {
    int ok;
    public int insertar(CiudadEntrega codigoC, Cliente rucCl, Date fecha){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pdist_fact_contaPU");
        EntityManager em1 = factory.createEntityManager();
        pck_pdist_fact_conta.entidades.Factura c1 = new pck_pdist_fact_conta.entidades.Factura();                  
        c1.setFacNumero(BigDecimal.ZERO);
        c1.setCiuCodigo(codigoC);
        c1.setCliRuc(rucCl);
        c1.setFacFecha(fecha);
        
        try{   
            em1.getTransaction().begin();
            em1.persist(c1);
            em1.getTransaction().commit();
            ok = 1;
            
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            ok = 0;
        }
        em1.close();
        factory.close();
        return ok;
    }
    
    public int eliminar(BigDecimal codigo){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pdist_fact_contaPU");
        EntityManager em1 = factory.createEntityManager();           
        pck_pdist_fact_conta.entidades.Factura c1 = new pck_pdist_fact_conta.entidades.Factura();                  
        c1.setFacNumero(codigo);            
        try{
            c1=em1.find(Factura.class,codigo);
            em1.getTransaction().begin();
            em1.remove(c1);
            em1.getTransaction().commit();
            ok = 1;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            ok = 0;
        }
        em1.close();
        factory.close();
        return ok;
    }
    
    public int modificar(BigDecimal codigo, CiudadEntrega codigoC, Cliente rucCl, Date fecha){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pdist_fact_contaPU");
        EntityManager em1 = factory.createEntityManager();             
        pck_pdist_fact_conta.entidades.Factura c1 = new pck_pdist_fact_conta.entidades.Factura();                  

        try{
            c1 = em1.find(Factura.class,codigo);
            em1.getTransaction().begin();
            
            c1.setCiuCodigo(codigoC);
            c1.setCliRuc(rucCl);
            c1.setFacFecha(fecha);
            
            em1.persist(c1);
            em1.getTransaction().commit();
            ok = 1;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            ok = 0;
        } 
        em1.close();
        factory.close();
        return ok;
    }
     
    public List<String> buscar(BigDecimal codigo){
        List<String> datos = new ArrayList<>();
        CiudadEntrega codigoC;
        Cliente rucCl;
        Date fecha;
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pdist_fact_contaPU");
        EntityManager em1 = factory.createEntityManager();        
        pck_pdist_fact_conta.entidades.Factura c1 = new pck_pdist_fact_conta.entidades.Factura();                  
        
        try{
            c1 = em1.find(Factura.class,codigo);
            codigoC = c1.getCiuCodigo();
            rucCl = c1.getCliRuc();
            fecha = c1.getFacFecha();
            datos.add(codigoC.getCiuNombre());
            datos.add(rucCl.getCliNombre());
            datos.add(fecha.toString());
        }catch (Exception ex){
            System.out.println(ex.getMessage());

            datos = new ArrayList<>();
        }
        em1.close();
        factory.close();
        return datos;
    }
}
