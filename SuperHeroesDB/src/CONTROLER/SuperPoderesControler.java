/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLER;

import MODEL.NewHibernateUtil;
import MODEL.Superpoderes;
import java.awt.List;
import java.util.ArrayList;
import net.sf.ehcache.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import MODEL.Superheroes;
/**
 *
 * @author Ras
 */
public class SuperPoderesControler {
    
    public static Transaction tx;
    public static Session sesion;
    public static Integer Id_SuperPoder;
    
   
    public static long getI_SuperPoder() {
        
        return Id_SuperPoder;
    }

    public static void setId_SuperPoder(Integer Id_SuperHeroe) {
        
        SuperPoderesControler.Id_SuperPoder = Id_SuperHeroe;
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
    
    public static ArrayList <Superpoderes>  CreateUsuario(ArrayList<Superpoderes> superpoderes,String nombrePoder, int danio, int potencia, Superheroes superheroe){
        
        
        
        IniciarConexion();
        
            //Inserto los datos en el array
            
        Superpoderes nuevo = new Superpoderes();
        
        nuevo.setNomPoder(nombrePoder);
        nuevo.setDanio(danio);
        nuevo.setPotencia(potencia);
        nuevo.setSuperheroes(superheroe);
        
        
        

        superpoderes.add(nuevo);

        try{
            
            Id_SuperPoder = (Integer)sesion.save(nuevo);
            tx.commit();

            
            
        }catch(HibernateException he){
            
            manejaExcepcion(he);
            throw he; 
            
        }finally{
        
            sesion.close();
        };
        
        return superpoderes;
        
    }

   //MOSTRAR LISTA DE SUPERHEROES
    
    public static ArrayList <Superpoderes> MostrarSuperPoderes() {
        
        Session sesion = NewHibernateUtil.getSessionFactory().openSession();
        ArrayList <Superpoderes> superpoderes = null;
        superpoderes = (ArrayList <Superpoderes>) sesion.createCriteria(Superpoderes.class).list();
        
        
        /*
        for (int i=0; i<superpoderes.size(); i++)
            System.out.println("SuperPoderes base de datos = " + superpoderes.get(i).getNomPoder() + superpoderes.get(i).getIdSuperpoder()); 
        */  
       
        return superpoderes;
    }
    
    
    //MODIFICAR SUPERHEROES
    
    public static void ModificarSuperPoder(Superpoderes superpoderes) throws HibernateException {
        
       
        
        try 
        { 
            IniciarConexion(); 
            sesion.update(superpoderes); 
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
    
    public static Superpoderes BuscarSuperPoder(int idSuperPoder) throws HibernateException{ 
        
       Superpoderes superpoder = null;
       System.out.println("Id_SuperPoder dentro del controler Buscar  " + idSuperPoder);

        try 
        { 
            IniciarConexion();
            
            superpoder = (Superpoderes) sesion.get(Superpoderes.class, idSuperPoder); 
            
        } finally 
        { 
            sesion.close(); 
        }  
        return superpoder; 
    }
    
    //ELIMINAR SUPERHEROES
    public static void EliminarSuperPoder(Superpoderes superpoder) throws HibernateException 
    { 
        
        //System.out.println("superpoder.getIdSuperHeroe() " + superpoder.getIdSuperpoder());
        
        try 
        { 
            IniciarConexion(); 
            sesion.delete(superpoder); 
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
