/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package goorderpersistencia;

import Entidades.ImagenSerializada;
import Entidades.Producto;
import static Enumeradores.Disponibilidad.DISPONIBLE;
import Interfaces.IProductosDAO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author juanl
 */
public class ProductoDAOTest {
    
    private IProductosDAO dao;
    
    public ProductoDAOTest() {
        dao = new ProductoDAO();
    }

    @Test
    public void registrarProductoOK() throws PersistenciaException{
        Producto nuevoProducto = new Producto(null,null,"Latte", "Un tipo de cafe", 50.00,DISPONIBLE,null,100 );
        Producto productoRegistrado = dao.registrarProducto(nuevoProducto);
        assertEquals(productoRegistrado.getNombre(),"Latte");
        System.out.println(productoRegistrado);
        
    }
    
}
