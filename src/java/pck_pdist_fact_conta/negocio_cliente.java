package pck_pdist_fact_conta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.ArrayList;
import pck_pdist_fact_conta.entidades.Cliente;

public class negocio_cliente {
    int ok;
    public int insertar(String ruc, String nombre, String direccion){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pdist_fact_contaPU");
        EntityManager em1 = factory.createEntityManager();
        pck_pdist_fact_conta.entidades.Cliente c1 = new pck_pdist_fact_conta.entidades.Cliente(ruc);                  
        c1.setCliRuc(ruc);
        c1.setCliNombre(nombre);
        c1.setCliDireccion(direccion);
        
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
    
    public int eliminar(String ruc){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pdist_fact_contaPU");
        EntityManager em1 = factory.createEntityManager();           
        pck_pdist_fact_conta.entidades.Cliente c1 = new pck_pdist_fact_conta.entidades.Cliente();                  
        c1.setCliRuc(ruc);            
        try{
            c1=em1.find(Cliente.class,ruc);
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
    
     public int modificar(String ruc, String nombre, String direccion){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pdist_fact_contaPU");
        EntityManager em1 = factory.createEntityManager();             
        pck_pdist_fact_conta.entidades.Cliente c1 = new pck_pdist_fact_conta.entidades.Cliente();                  

        try{
            c1 = em1.find(Cliente.class,ruc);
            em1.getTransaction().begin();
            c1.setCliNombre(nombre);
            c1.setCliDireccion(direccion);
            
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
     
    public List<String> buscar(String ruc){
        List<String> datos = new ArrayList<>();
        String nombre;
        String direccion;
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pdist_fact_contaPU");
        EntityManager em1 = factory.createEntityManager();        
        pck_pdist_fact_conta.entidades.Cliente c1 = new pck_pdist_fact_conta.entidades.Cliente();                  
        
        try{
            c1 = em1.find(Cliente.class,ruc);
            nombre = c1.getCliNombre();
            direccion = c1.getCliDireccion();
            datos.add(nombre);
            datos.add(direccion);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            nombre = null;
            direccion = null;
            datos.add(nombre);
            datos.add(direccion);
        } 
        em1.close();
        factory.close();
        return datos;
     }
    
     public void procesar()
     {
	// programar el código de la regla de negocio         
     }    
}
