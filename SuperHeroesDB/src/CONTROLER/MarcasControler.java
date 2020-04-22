/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLER;

import MODEL.NewHibernateUtil;
import MODEL.Marcas;
import MODEL.Villanos;
import java.awt.List;
import java.util.ArrayList;
import net.sf.ehcache.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Ras
 */
public class MarcasControler {
    
    public static Transaction tx;
    public static Session sesion;
    public static Integer Id_Marca;
    
   
    public static long getId_SuperHeroe() {
        
        return Id_Marca;
    }

    public static void setId_SuperHeroe(Integer Id_SuperHeroe) {
        
        MarcasControler.Id_Marca = Id_SuperHeroe;
    }
    
    //CONEXION Y CONTROL DE EXCEPCION BASE DE DATOS 
    
    public static void IniciarConexion() throws HibernateException{
        
        sesion = NewHibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }
    
    public static void manejaExcepcion(HibernateException he) throws HibernateException{
        
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
        
    }
    
   //INSERTAR SUPERHEROE
    
    public static ArrayList <Marcas>  CreateMarca(ArrayList<Marcas> marcas,String nombreMarca, int anoCreacion, boolean pelicula, Villanos Villano){
        
        
        
        IniciarConexion();
        
            //Inserto los datos en el array
            
        Marcas nuevo = new Marcas();
        nuevo.setNomMarca(nombreMarca);
        nuevo.setAnioCreacion(anoCreacion);
        nuevo.setPelicula(pelicula);
        nuevo.setVillanos(Villano);
        
        
       

        marcas.add(nuevo);

        try{
            
            Id_Marca = (Integer)sesion.save(nuevo);
            tx.commit();

            
            
        }catch(HibernateException he){
            
            manejaExcepcion(he);
            throw he; 
            
        }finally{
        
            sesion.close();
        };
        
        return marcas;
        
    }

   //MOSTRAR LISTA DE SUPERHEROES
    
    public static ArrayList <Marcas> MostrarMarcas() {
        
        Session sesion = NewHibernateUtil.getSessionFactory().openSession();
        ArrayList <Marcas> superheroes = (ArrayList <Marcas>) sesion.createCriteria(Marcas.class).list();
        
        
        
       
        
        return superheroes;
    }
    
    
    //MODIFICAR SUPERHEROES
    
    public static void ModificarMarca(Marcas marcas) throws HibernateException {
        
        System.out.println("Nombre + Id_SuperHeroe =" + marcas.getNomMarca() + " " + marcas.getIdMarca());
        
        
        try 
        { 
            IniciarConexion(); 
            sesion.update(marcas); 
            tx.commit(); 
            
        }catch (HibernateException he) 
        { 
            manejaExcepcion(he); 
            throw he; 
            
        }finally 
        { 
            sesion.close(); 
        } 
    }
    
    
    
    public static Marcas BuscarMarca(int idmarcas) throws HibernateException{ 
        
       Marcas marca = null;
        System.out.println("Id_Marca dentro del controler Buscar  " + idmarcas);

        try 
        { 
            IniciarConexion();
            
            marca = (Marcas) sesion.get(Marcas.class, idmarcas); 
            
        } finally 
        { 
            sesion.close(); 
        }  
        return marca; 
    }
    
    //ELIMINAR SUPERHEROES
    public static void EliminarMarca(Marcas marcas) throws HibernateException 
    { 
        
        //System.out.println("marcas.getIdmarcas() " + marcas.getIdMarca());
        
        try 
        { 
            IniciarConexion(); 
            sesion.delete(marcas); 
            tx.commit(); 
        } catch (HibernateException he) 
        { 
            manejaExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        } 
    }  

}
