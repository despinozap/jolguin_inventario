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
public class Proveedor {
    
    private final String rut;
    private final String nombre;
    private final String direccion;
    private final String telefono;
    private final String celular;
    private final String email;
    
    public Proveedor(String rut, String nombre, String direccion, String telefono, String celular, String email)
    {
        this.rut = rut;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.celular = celular;
        this.email = email;
    }
    
    public String getRUT()
    {
        return this.rut;
    }
    
    public String getNombre()
    {
        return this.nombre;
    }
    
    public String getDireccion()
    {
        return this.direccion;
    }
    
    public String getTelefono()
    {
        return this.telefono;
    }
    public String getCelular()
    {
        return this.celular;
    }
    public String getEmail()
    {
        return this.email;
    }
    
}
