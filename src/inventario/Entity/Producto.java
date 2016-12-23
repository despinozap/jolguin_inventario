/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.Entity;

/**
 *
 * @author David
 */
public class Producto {
    
    private final String codigo;
    private final String nombre;
    private final String clasificacion;
    private final String fechaCaducidad;
    private final int cantidad;
    
    public Producto(String codigo, String nombre, String clasificacion, String fechaCaducidad, int cantidad)
    {
        this.codigo = codigo;
        this.nombre = nombre;
        this.clasificacion = clasificacion;
        this.fechaCaducidad = fechaCaducidad;
        this.cantidad = cantidad;
    }
    
    public String getCodigo()
    {
        return this.codigo;
    }
    
    public String getNombre()
    {
        return this.nombre;
    }
    
    public String getClasificacion()
    {
        return this.clasificacion;
    }
    
    public String getFechaCaducidad()
    {
        return this.fechaCaducidad;
    }
    
    public int getCantidad()
    {
        return this.cantidad;
    }
}
