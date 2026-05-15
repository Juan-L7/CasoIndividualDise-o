/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import Enumeradores.EstadoCodigoDesc;

/**
 *
 * @author juanl
 */
public class CodigoDescuento {
    
    private Long id;
    private String codigo;
    private Double monto;
    private EstadoCodigoDesc estado;

    public CodigoDescuento() {
    }

    public CodigoDescuento(Long id, String codigo, Double monto, EstadoCodigoDesc estado) {
        this.id = id;
        this.codigo = codigo;
        this.monto = monto;
        this.estado = estado;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public EstadoCodigoDesc getEstado() {
        return estado;
    }

    public void setEstado(EstadoCodigoDesc estado) {
        this.estado = estado;
    }
    
    
    
}
