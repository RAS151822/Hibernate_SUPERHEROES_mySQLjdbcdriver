/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import MODEL.Marcas;
import MODEL.Superheroes;
import MODEL.Superpoderes;
import MODEL.TestsClass;
import MODEL.Villanos;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ras
 */
public class UnityTests {
    
    public UnityTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void TestAñadirSuperheroeArray(){
        
        TestsClass t = new TestsClass();
       
        t.CrearNuevoSuperHeroe("Spiderman", "Telarañas", 'H', false);
        
        String nombre = "Spiderman";
        
        int pos = t.getDimSuperHeroes()-1;
        
        Superheroes aux = t.getSuperHeroe(pos);
        
        assertEquals(nombre,aux.getNomSuperheroe());
        
        
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void TestAñadirSuperPoderArray(){
        
        TestsClass t = new TestsClass();
        
     
        t.CrearNuevoSuperPoder(1875421,"Super Salto", 200, 400);
        
        Integer id = 1875421;
        
        Integer pos = t.getDimSuperPoder()-1;
        
        Superpoderes aux = t.getSuperPoder(pos);
        
        assertEquals(id,aux.getIdSuperpoder());
        
        
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void TestAñadirVillanoArray(){
        
        TestsClass t = new TestsClass();
       
        t.CrearNuevoVillano(0, "Joker", "Payaso Loco", true, 'H');
        char genero = 'H';
        
        int pos = t.getDimVillanos()-1;
        
        Villanos aux = t.getVillano(pos);
        
        assertEquals(genero,aux.getGenero());
        
        
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void TestAñadirMarcaArray(){
        
        TestsClass t = new TestsClass();
        
        t.CrearNuevaMarca(3, "Marvel", 0, true);
        
        String nombre = "Marvel";
        
        int pos = t.getDimMarcas()-1;
        
        Marcas aux = t.getMarca(pos);
        
        assertEquals(nombre,aux.getNomMarca());
        
        
    }
}
