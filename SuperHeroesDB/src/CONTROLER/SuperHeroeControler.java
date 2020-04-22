/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLER;


import MODEL.Marcas;
import MODEL.NewHibernateUtil;
import MODEL.Superheroes;
import MODEL.Superpoderes;
import MODEL.Villanos;

import org.hibernate.HibernateException;

import java.util.ArrayList;
import org.hibernate.Session;

import org.hibernate.Transaction;


/**
 *
 * @author Ras
 */
public class SuperHeroeControler {
    
    public static Transaction tx = null;
    public static Session sesion;
    public static Integer Id_SuperHeroe;
    
    //ARRAY DE OBJETOS PERTENECIENTE A LA FOREIGN KEY
    public static ArrayList <Superpoderes> superpoderes;
    public static ArrayList <Villanos> villanos;
    
   
    public static long getId_SuperHeroe() {
        
        return Id_SuperHeroe;
    }

    public static void setId_SuperHeroe(Integer Id_SuperHeroe) {
        
        SuperHeroeControler.Id_SuperHeroe = Id_SuperHeroe;
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
    
    public static ArrayList <Superheroes>  CreateUsuario(ArrayList<Superheroes> superheroes,String nombre, String habilidad, boolean capa, char genero){
        
        
        
        IniciarConexion();
        
            //Inserto los datos en el array
            
        Superheroes nuevo = new Superheroes();
        nuevo.setNomSuperheroe(nombre);
        nuevo.setHabilidad(habilidad);
        nuevo.setGenero(genero);
        nuevo.setCapa(capa);
        
       
        superheroes.add(nuevo);

        try{
            
            Id_SuperHeroe = (Integer)sesion.save(nuevo); // Guardo en la base de datos y a su vez guardo el id de ese superheroe
            tx.commit();

            
            
        }catch(HibernateException he){
            
            manejaExcepcion(he);
            throw he; 
            
        }finally{
        
            sesion.close();
        };
        
        return superheroes;
        
    }

   //MOSTRAR LISTA DE SUPERHEROES
    
    public static ArrayList <Superheroes> MostrarSuperHeroes() {
        
        Session sesion = NewHibernateUtil.getSessionFactory().openSession();
        ArrayList <Superheroes> superheroes = (ArrayList <Superheroes>) sesion.createCriteria(Superheroes.class).list();
        
        
        /*
        for (int i=0; i<superheroes.size(); i++)    
            System.out.println("Superheroes base de datos = " + superheroes.get(i).getNomSuperheroe() + superheroes.get(i).getIdSuperheroe()); 
        */    
       
        return superheroes;
    }
    
    
    //MOSTRAR LISTA DE SUPERPODERES DE UN DETERMINADO SUPERHEROE
    
    public static ArrayList <Superpoderes> MostrarSuperpoderes(Superheroes superheroe){
    
        superpoderes =  new ArrayList(superheroe.getSuperpodereses());
       
        /*
        for (int i = 0; i < superpoderes.size(); i++) {
            
            System.out.println(" Mostrar superpoderes (Controler)= " + superpoderes.get(i).getNomPoder() );
        }
        */ 
        
        return superpoderes;
    }
    
    
   
     //BUSCAR SUPERHEROE
    public static Superheroes BuscarSuperHeroe(int idSuperHeroe) throws HibernateException{ 
        
       Superheroes superheroe = null;
        System.out.println("Id_SuperHeroe dentro de Buscar  " + idSuperHeroe);

        try 
        { 
            IniciarConexion();
            
            superheroe = (Superheroes) sesion.get(Superheroes.class, idSuperHeroe); 
            
        } finally 
        { 
            sesion.close(); 
        }  
        return superheroe; 
    }
    
    
    //BUSCAR SUPERPODER EN SUPERHEROE
    
    public static boolean BuscarPoderSuperHeroe(Superheroes superheroe, String nombresuperpoder){
    
        boolean encontrado = false;
        
        superpoderes =  new ArrayList(superheroe.getSuperpodereses());
       
        for (int i = 0; i < superpoderes.size() && encontrado == false; i++) {
            
            if(nombresuperpoder.equals(superpoderes.get(i).getNomPoder())){
                
                //System.out.println("Nombre Superpoder introducido por el usuario = " + nombresuperpoder);
                //System.out.println("Nombre del Superpoder encontrado = " + superpoderes.get(i).getNomPoder());
                encontrado = true;
            }
        }
       
           // System.out.println("superheroes.getSuperpodereses().toArray() " + superheroes.getSuperpodereses().toArray());
        
       
        
        return encontrado;
    }
    
    //BUSCAR VILLANO EN SUPERHEROE
    
    public static boolean BuscarVillanoSuperHeroe(Superheroes superheroe, String nombrevillano){
    
        boolean encontrado = false;
        
        villanos =  new ArrayList(superheroe.getVillanoses());
       
        for (int i = 0; i < villanos.size() && encontrado == false; i++) {
            
            if(nombrevillano.equals(villanos.get(i).getNomVillano())){
                
              
                encontrado = true;
            }
        }
       
           // System.out.println("superheroes.getSuperpodereses().toArray() " + superheroes.getSuperpodereses().toArray());
        
       
        
        return encontrado;
    }
   
    //ELIMINAR SUPERHEROES
    public static void EliminarSuperHeroe(Superheroes r)
    { 
      
        //System.out.println("Nombre Superheroe = " + superheroes.getNomSuperheroe() + "+ ID = " + superheroe.getIdSuperheroe());
        
       try 
        { 
            IniciarConexion(); 
            //System.out.println("llega1");
            if(r !=null) {
                sesion.delete(r);
            }
            //System.out.println("llega2");
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
    
    //ELIMINAR VILLANOS SUPERHEROE
       public static void EliminarVillanosSuperHeroe(Superheroes superheroe){
    
        IniciarConexion();
        boolean encontrado = false;
        
        //System.out.println("Dimension al entrar = " + villano.getMarcases().size() + " Vector Marcas de el Villano = " + villano.getNomVillano());
       
        //System.out.println("villanos Nombre Villano " + villano.getNomVillano());
        villanos =  new ArrayList(superheroe.getVillanoses());
       
        
        
        if(villanos.size()>=1){
            
            for(int i = 0; i < villanos.size(); i++) {
            
                    //1 leeo el array de villanos que contiene el objeto superheroe
                    
                    //2  Compruebo si ese objeto villano a su vez tiene un array de marcas en su interior
                    if(villanos.get(i).getMarcases().size()>=0){
                        
                        //Creo Un array list para cada objeto marca ya que la dimension del vector marcas del objeto villano es >= 0;
                            ArrayList <Marcas>  marcas = new  ArrayList(villanos.get(i).getMarcases());
                        
                        for(int j=0; j < marcas.size(); j++){
                            
                            MarcasControler.EliminarMarca(MarcasControler.BuscarMarca(marcas.get(j).getIdMarca())); 
                        }
                        
                    }
                    
                        //3ºELIMINO EL Array De Villanos uno a uno
                        VillanosControler.EliminarVillano(VillanosControler.BuscarVillano(villanos.get(i).getIdVillano()));
                        //MarcasControler.EliminarMarca(MarcasControler.BuscarMarca(marcas.get(i).getIdMarca())); 
            }      
       
        
        }
             
               
    }

       
    //ELIMINAR SUPERPODERES SUPERHEROE
    public static void EliminarSuperPoderesSuperHeroe(Superheroes superheroe){
    
        
        
        
        IniciarConexion();
       
        //System.out.println("Dimension al entrar = " + villano.getMarcases().size() + " Vector Marcas de el Villano = " + villano.getNomVillano());
       
        //System.out.println("villanos Nombre Villano " + villano.getNomVillano());
        superpoderes =  new ArrayList(superheroe.getSuperpodereses());
       
        
        
        if(superpoderes.size()>=1){
            
            for(int i = 0; i < superpoderes.size(); i++) {
                
                SuperPoderesControler.EliminarSuperPoder(SuperPoderesControler.BuscarSuperPoder(superpoderes.get(i).getIdSuperpoder()));
            
                
            }
        

        }
    }
    
    
     //MODIFICAR SUPERHEROES
    
    public static void ModificarSuperheroe(Superheroes superheroe) throws HibernateException {
        
        //System.out.println("Nombre + Id_SuperHeroe  dentro del Modificar = " + superheroe.getNomSuperheroe() + " " + superheroe.getIdSuperheroe());
        
        
        try 
        { 
            IniciarConexion(); 
            sesion.update(superheroe); 
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
    
    
}
