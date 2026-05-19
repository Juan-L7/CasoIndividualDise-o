/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

import Entidades.Categoria;
import Entidades.Producto;
import Fachada.FachadaPersistencia;
import Fachada.IFachadaPersistencia;
import GoOrderDTO.CategoriaDTO;
import Interfaces.ICatalogoCategoriasBO;
import Mappers.CategoriasMapper;
import goorderpersistencia.PersistenciaException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juanl
 */
public class CatalogoCategoriasBO implements ICatalogoCategoriasBO{
    
    private IFachadaPersistencia fachada;
    
    public CatalogoCategoriasBO(){
        this.fachada = new FachadaPersistencia();
    }

    @Override
    public List<CategoriaDTO> listaCategorias() throws NegocioException {
        try {
            List<Categoria> listaEntidad = fachada.listarCategorias();
            
            List<CategoriaDTO> listaNegocio = new ArrayList<>();
            for (Categoria c : listaEntidad) {
                listaNegocio.add(CategoriasMapper.toNegocio(c));
            }
            return listaNegocio;
        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible consultar las categorias.");
        }
    }

    @Override
    public CategoriaDTO buscarCategoriaPorId(String idCategoria) throws NegocioException {
        try {
            Categoria categoria = fachada.buscarCategoriaPorId(idCategoria);
            CategoriaDTO catDTO = CategoriasMapper.toNegocio(categoria);
            return catDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible consultar la categoria.");
        }
    }
    
    
}
