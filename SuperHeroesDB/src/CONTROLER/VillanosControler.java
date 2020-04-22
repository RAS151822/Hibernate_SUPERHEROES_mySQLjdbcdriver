/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLER;

import MODEL.Marcas;
import MODEL.NewHibernateUtil;
import MODEL.Villanos;
import MODEL.Superheroes;
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
public class VillanosControler {
    
    public static Transaction tx;
    public static Session sesion;
    public static Integer Id_Villano;
    
    
    public static ArrayList <Marcas> marcas;
     

    public static Integer getId_Villano() {
        return Id_Villano;
    }

    public static void setId_Villano(Integer Id_Villano) {
        VillanosControler.Id_Villano = Id_Villano;
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
    
    public static ArrayList <Villanos>  CreateVillano(ArrayList<Villanos> Villanos ,String nombreVillano, String habilidad, boolean capa, char genero, Superheroes SuperHeroe){
        
        
        
        IniciarConexion();
        
            //Inserto los datos en el array
            
        Villanos nuevo = new Villanos();
        nuevo.setNomVillano(nombreVillano);
        nuevo.setHabilidad(habilidad);
        nuevo.setCapa(capa);
        nuevo.setGenero(genero);
        nuevo.setSuperheroes(SuperHeroe);
        
        
       

        Villanos.add(nuevo);
        System.out.println("llega");

        try{
            
            Id_Villano = (Integer)sesion.save(nuevo);
            tx.commit();

            
            
        }catch(HibernateException he){
            
            manejaExcepcion(he);
            throw he; 
            
        }finally{
        
            sesion.close();
        };
        
        return Villanos;
        
    }

   //MOSTRAR LISTA DE VILLANOS
    
    public static ArrayList <Villanos> MostrarVillanos() {
        
        Session sesion = NewHibernateUtil.getSessionFactory().openSession();
        ArrayList <Villanos> villanos = (ArrayList <Villanos>) sesion.createCriteria(Villanos.class).list();
        
        
        /*
        for (int i=0; i<villanos.size(); i++){
            
            System.out.println("Villanos base de datos = " + villanos.get(i).getNomVillano() + " " +  villanos.get(i).getIdVillano());
            
        }
        */
        
        return villanos;
    }
    
    
    //MODIFICAR VILLANOS
    
    public static void ModificarVillano(Villanos villanos) throws HibernateException {
        
        System.out.println("Nombre + Id_Villano =" + villanos.getNomVillano()+ " " + villanos.getIdVillano());
        
        
        try 
        { 
            IniciarConexion(); 
            sesion.update(villanos); 
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
    
    //BUSCAR VILLANO
    
    public static Villanos BuscarVillano(int idvillano) throws HibernateException{ 
        
       Villanos villano = null;
        //System.out.println("Id_Villano dentro del controler Buscar  " + idvillano);

        try 
        { 
            IniciarConexion();
            
            villano = (Villanos) sesion.get(Villanos.class, idvillano); 
            
        } finally 
        { 
            sesion.close(); 
        }  
        return villano; 
    }
    
    //BUSCAR MARCA EN VILLANO
    
    public static boolean BuscarMarcaVillano(Villanos villano, String nombremarca){
    
        boolean encontrado = false;
        
       
       marcas =  new ArrayList(villano.getMarcases());
       
        for (int i = 0; i < marcas.size() && encontrado == false; i++) {
            
            if(nombremarca.equals(marcas.get(i).getNomMarca())){
                
                System.out.println("Nombre Marca introducido por el usuario = " + nombremarca);
                System.out.println("Nombre del Villano encontrado = " + marcas.get(i).getNomMarca());
                encontrado = true;
            }
        }
       
           // System.out.println("superheroes.getSuperpodereses().toArray() " + superheroes.getSuperpodereses().toArray());
        
       
        
        return encontrado;
    }
    
    
        //ELIMINAR VILLANOS
    public static void EliminarVillano(Villanos villano) throws HibernateException 
    { 
        
       
                //VillanosControler.EliminarVillano(VillanosControler.BuscarVillano(this.villanos.get(seleccion).getIdVillano()));
        
        
        try 
        { 
             
            IniciarConexion();
            sesion.delete(villano); 
            
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

    
    
    
     public static void EliminarrMarcaVillano(Villanos villano){
    
        IniciarConexion();
        boolean encontrado = false;
        
        //System.out.println("Dimension al entrar = " + villano.getMarcases().size() + " Vector Marcas de el Villano = " + villano.getNomVillano());
       
        //System.out.println("villanos Nombre Villano " + villano.getNomVillano());
        marcas =  new ArrayList(villano.getMarcases());
        
        
        
        if(villano.getMarcases().size()>=0){
            
            for(int i = 0; i < marcas.size(); i++) {
            
                    MarcasControler.EliminarMarca(MarcasControler.BuscarMarca(marcas.get(i).getIdMarca())); 
            }      
       
        
        }
             
               
    }
    
}
