/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.itson.gestionproductospaquetescui;

import GoOrderDTO.CategoriaDTO;
import GoOrderDTO.NuevoPaqueteDTO;
import GoOrderDTO.NuevoProductoDTO;
import GoOrderDTO.PaqueteDTO;
import GoOrderDTO.ProductoActualizadoDTO;
import GoOrderDTO.ProductoDTO;
import GoOrderDTO.ProductoDTOCom;
import GoOrderDTO.ReportePaquetesResumenDTO;
import GoOrderDTO.VentaDTO;
import Interfaces.ICatalogoCategoriasBO;
import Interfaces.IPaquetesBO;
import Interfaces.IProductoBO;
import Interfaces.IVentaBO;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import org.example.CatalogoCategoriasBO;
import org.example.NegocioException;
import org.example.PaquetesBO;
import org.example.ProductoBO;
import org.example.VentaBO;
import org.itson.infraestructura.GeneradorReportesPDF;

/**
 *
 * @author juanl
 */
public class GestionProductosPaquetesCUI implements IGestionProductosPaquetesCUI {

    private IProductoBO productoBO;
    private ICatalogoCategoriasBO categoriasBO;
    private IPaquetesBO paquetesBO;
    private IVentaBO ventasBO;

    public GestionProductosPaquetesCUI() {
        this.productoBO = new ProductoBO();
        this.categoriasBO = new CatalogoCategoriasBO();
        this.paquetesBO = new PaquetesBO();
        this.ventasBO = new VentaBO();
    }
            
    
    
    @Override
    public ProductoDTO RegistrarProducto(NuevoProductoDTO nuevoProducto) throws NegocioException {
       
        try {
            return productoBO.registrarProducto(nuevoProducto);
        } catch (NegocioException e) {
            e.printStackTrace(); 
            throw new NegocioException("Falló el BO: " + e.getMessage()); 
        }
    } 

    @Override
    public List<CategoriaDTO> listarCategorias() throws NegocioException {
           return categoriasBO.listaCategorias();
    }

    @Override
    public List<ProductoDTOCom> buscarProductosDinamico(String nombre, String idCategoria, Double precioMin, Double precioMax) throws NegocioException {
           return productoBO.buscarProductosDinamico(nombre, idCategoria, precioMin, precioMax);
    }

    @Override
    public ProductoDTO actualizarProducto(ProductoActualizadoDTO productoActualizado) throws NegocioException {
            return productoBO.actualizarProducto(productoActualizado);
    }

    @Override
    public ProductoDTO eliminarProducto(String id) throws NegocioException {
            return productoBO.eliminarProducto(id);
    }

    @Override
    public PaqueteDTO registrarPaquete(NuevoPaqueteDTO nuevopaquete) throws NegocioException {
            return paquetesBO.registrarPaquete(nuevopaquete);
    }

    @Override
    public List<PaqueteDTO> listarPaquetes() throws NegocioException {
            return paquetesBO.listarPaquetes();
    }

    @Override
    public List<PaqueteDTO> buscarPaquetesDinamico(String nombre, Double precioMax) throws NegocioException {
            return paquetesBO.buscarPaquetesDinamico(nombre, precioMax);
    }

    @Override
    public PaqueteDTO actualizarPaquete(PaqueteDTO paqueteActualizado) throws NegocioException {
            return paquetesBO.actualizarPaquete(paqueteActualizado);
    }

    @Override
    public PaqueteDTO eliminarPaquete(String id) throws NegocioException {
            return paquetesBO.eliminarPaquete(id);
    }

    @Override
    public List<VentaDTO> obtenerVentasPorRango(LocalDate inicio, LocalDate fin) throws NegocioException {
            return ventasBO.obtenerVentasPorRango(inicio, fin);
    }

    @Override
    public void generarReportePDF() throws NegocioException {
        try {

        ReportePaquetesResumenDTO reporte =ventasBO.generarDatosReporte();

        GeneradorReportesPDF.generarReportePaquetes(
                reporte
        );

        } catch (Exception e) {

        JOptionPane.showMessageDialog(
                null,
                "Error al generar reporte: "
                + e.getMessage()
        );
    }
    }    

    

    
}
 