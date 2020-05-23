/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.util.ArrayList;


/**
 *
 * @author Ras
 */
public class TestsClass {
    
    ArrayList <Superheroes> superheroestest = new ArrayList();
    ArrayList <Superpoderes> superpoderstest = new ArrayList();
    ArrayList <Villanos> villanostest = new ArrayList();
    ArrayList <Marcas> marcasstest = new ArrayList();
    
    
 
    //METODOS TESTSCLASS PARA SUPERHEROES
    public void CrearNuevoSuperHeroe(String nomSuperheroe, String habilidad, char genero, Boolean capa){
    
        Superheroes n = new Superheroes(nomSuperheroe,habilidad, genero,capa);   
        superheroestest.add(n);
        
    }
    
    public Superheroes getSuperHeroe(int posicion){
    
        
        Superheroes aux = superheroestest.get(posicion);
        
        return aux;
    }
    
    public int getDimSuperHeroes(){
        int dim;
    
        dim = superheroestest.size();
        
        
        return dim;
    }
    
     //METODOS TESTSCLASS PARA SUPERPODERES
    public void CrearNuevoSuperPoder(Integer idSuperpoder, String nomPoder, int danio, int potencia){
    
        Superpoderes n = new Superpoderes (idSuperpoder,nomPoder,danio,potencia);   
        superpoderstest.add(n);
        
    }
    
    public Superpoderes getSuperPoder(int posicion){
    
        
        Superpoderes aux = superpoderstest.get(posicion);
        
        return aux;
    }
    
    public int getDimSuperPoder(){
        int dim;
    
        dim = superpoderstest.size();
        
        
        return dim;
    }
     //METODOS TESTSCLASS PARA VILLANOS
    public void CrearNuevoVillano(Integer idVillano, String nomVillano, String habilidad, Boolean capa, char genero){
    
        Villanos n = new Villanos ( idVillano,nomVillano,habilidad,capa,genero);   
        villanostest.add(n);
        
    }
    
    public Villanos getVillano(int posicion){
    
        
        Villanos aux = villanostest.get(posicion);
        
        return aux;
    }
    
    public int getDimVillanos(){
        int dim;
    
        dim = villanostest.size();
        
        
        return dim;
    }
     //METODOS TESTSCLASS PARA MARCAS
    public void CrearNuevaMarca(Integer idMarca, String nomMarca, int anioCreacion, Boolean pelicula){
    
        Marcas n = new Marcas(idMarca,nomMarca,anioCreacion,pelicula);   
        marcasstest.add(n);
        
    }
    
    public Marcas getMarca(int posicion){
    
        
        Marcas aux = marcasstest.get(posicion);
        
        return aux;
    }
    
    public int getDimMarcas(){
        int dim;
    
        dim = marcasstest.size();
        
        
        return dim;
    }
    
}
