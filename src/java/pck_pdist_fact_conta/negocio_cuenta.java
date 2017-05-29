package pck_pdist_fact_conta;

import java.math.BigDecimal;
import pck_pdist_fact_conta.entidades.Cuenta;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.ArrayList;
import pck_pdist_fact_conta.entidades.Tipocuenta;

public class negocio_cuenta {
    int validar;
    public int insertar(String nombre, Tipocuenta tipo)
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pdist_fact_contaPU");
        EntityManager em1 = factory.createEntityManager();
        pck_pdist_fact_conta.entidades.Cuenta c1 = new pck_pdist_fact_conta.entidades.Cuenta();                  
        c1.setCueCodigo(BigDecimal.ZERO);
        c1.setCueNombre(nombre);
        c1.setTdcCodigo(tipo);
        
        try{   
            em1.getTransaction().begin();
            em1.persist(c1);
            em1.getTransaction().commit();
            validar = 1;
            
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            validar = 0;
        }
        em1.close();
        factory.close();
        return validar;
    }
    
    public int eliminar(BigDecimal codigo){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pdist_fact_contaPU");
        EntityManager em1 = factory.createEntityManager();           
        pck_pdist_fact_conta.entidades.Cuenta c1 = new pck_pdist_fact_conta.entidades.Cuenta();                  
        c1.setCueCodigo(codigo);            
        try{
            c1=em1.find(Cuenta.class,codigo);
            em1.getTransaction().begin();
            em1.remove(c1);
            em1.getTransaction().commit();
            validar = 1;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            validar = 0;
        }
        em1.close();
        factory.close();
        return validar;
     }
    
     public int modificar(BigDecimal codigo, String nombre, Tipocuenta tipo)
     {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pdist_fact_contaPU");
        EntityManager em1 = factory.createEntityManager();             
        pck_pdist_fact_conta.entidades.Cuenta c1 = new pck_pdist_fact_conta.entidades.Cuenta();                  

        try{
            c1 = em1.find(Cuenta.class,codigo);
            em1.getTransaction().begin();
            c1.setCueNombre(nombre);
            c1.setTdcCodigo(tipo);
            
            em1.persist(c1);
            em1.getTransaction().commit();
            validar = 1;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            validar = 0;
        } 
        em1.close();
        factory.close();
        return validar;
     }
     
    public List<String> buscar(BigDecimal codigo)
    {
        List<String> datos = new ArrayList<>();
        String nombre;
        String tipo;
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pdist_fact_contaPU");
        EntityManager em1 = factory.createEntityManager();        
        pck_pdist_fact_conta.entidades.Cuenta c1 = new pck_pdist_fact_conta.entidades.Cuenta();                  
        
        try{
            c1 = em1.find(Cuenta.class,codigo);
            nombre=c1.getCueNombre();
            tipo=c1.getTdcCodigo().getTdcCodigo().toString();
            datos.add(nombre);
            datos.add(tipo);
        }catch 
                (Exception ex)
        {
            System.out.println(ex.getMessage()); 
            nombre=null;
            tipo=null;
            datos.add(nombre);
            datos.add(tipo);
        } 
        em1.close();
        factory.close();
        return datos;
     }
    public int max()
    {
        int max=0;
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pdist_fact_contaPU");
        EntityManager em1 = factory.createEntityManager();   
        max= (Integer)em1.createQuery("SELECT MAX(c.cueCodigo) FROM Cuenta c").getSingleResult();
        return max;
    }  
}
