/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.Logic;

import inventario.Entity.Producto;
import inventario.Entity.Proveedor;
import inventario.Persistent.SQLite;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class LogicController 
{
    /*
    public static MyTableModel selectSorteo()
    {
        String query = "SELECT id, date, premio FROM Sorteo;";
        ArrayList<String> values = new ArrayList<String>();
        
        MyTableModel mtm = SQLite.getDB(query, values);
        
        return mtm;
    }
    
    public static boolean deleteSorteos()
    {        
        String query = "DELETE FROM Sorteo;";
        ArrayList<String> values = new ArrayList<String>();
        
        boolean executed = SQLite.exec(query, values);
        
        return executed;
    }
    
    public static boolean insertSorteo(String premio)
    {
        if(premio == null)
        {
            return false;
        }
        else if(premio.length() < 1)
        {
            return false;
        }
        
        int id = -1;
        {
            String query = "SELECT value FROM Config WHERE (name=?);";
            ArrayList<String> values = new ArrayList<String>();
            values.add("last_id_sorteo");
            
            MyTableModel mtm = SQLite.getDB(query, values);
            if(mtm != null)
            {
                if(mtm.getRowCount() > 0)
                {
                    try
                    {
                        id = Integer.parseInt(mtm.getValueAt(0, 0).toString());
                        id++;
                    }
                    catch(Exception ex)
                    {
                        
                    }
                }
            }
        }
        
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date dt = new Date();
        String date = df.format(dt);
        
        boolean executed = false;
        
        if(id >= 0)
        {
            String query = "INSERT INTO Sorteo(id, date, premio) VALUES (?, ?, ?);";
            ArrayList<String> values = new ArrayList<String>();
            values.add(Integer.toString(id));
            values.add(date);
            values.add(premio);
            
            executed = SQLite.exec(query, values);
            
            if(executed)
            {
                query = "UPDATE Config SET value=? WHERE (name=?);";
                values = new ArrayList<String>();
                values.add(Integer.toString(id));
                values.add("last_id_sorteo");
                
                SQLite.exec(query, values);
            }
        }
        else
        {
            executed = false;
        }

        return executed;
    }
    */
    
    
    /*
    *
    *   C O M P R A
    *
    */
    
    public static boolean insertCompra
    (
        String producto_codigo,
        String proveedor_rut, 
        String proveedor_nombre, 
        String proveedor_direccion, 
        String proveedor_telefono, 
        String proveedor_celular, 
        String proveedor_email,
        String precioCompra,
        String cantidad,
        String numeroFactura,
        String fechaCompra
    )
    {
        if(producto_codigo == null)
        {
            return false;
        }
        else if(producto_codigo.length() < 1)
        {
            return false;
        }
        else if(proveedor_rut == null)
        {
            return false;
        }
        else if(proveedor_rut.length() < 1)
        {
            return false;
        }
        else if(cantidad == null)
        {
            return false;
        }
        else if(cantidad.length() < 1)
        {
            return false;
        }
        else if(numeroFactura == null)
        {
            return false;
        }
        else if(numeroFactura.length() < 1)
        {
            return false;
        }
        else if(precioCompra == null)
        {
            return false;
        }
        else if(precioCompra.length() < 1)
        {
            return false;
        }
        else if(LogicController.getProductoByCodigo(producto_codigo) == null)
        {
            return false;
        }
    
        boolean executed = false;
        Proveedor proveedor = LogicController.getProveedorByRUT(proveedor_rut);
        if(proveedor == null)
        {
            executed = LogicController.insertProveedor(
                                                        proveedor_rut,
                                                        proveedor_nombre,
                                                        proveedor_direccion,
                                                        proveedor_telefono,
                                                        proveedor_celular,
                                                        proveedor_email
            );
        }
        else
        {
            executed = LogicController.updateProveedor(
                                                        proveedor_rut,
                                                        proveedor_nombre,
                                                        proveedor_direccion,
                                                        proveedor_telefono,
                                                        proveedor_celular,
                                                        proveedor_email
            );
        }
        
        if(executed == true)
        {
            String query = "INSERT INTO Compra(producto_codigo, proveedor_rut, preciocompra, cantidad, numerofactura, fechacompra) VALUES (?, ?, ?, ?, ?, ?);";
            ArrayList<String> values = new ArrayList<String>();
            values.add(producto_codigo);
            values.add(proveedor_rut);
            values.add(precioCompra);
            values.add(cantidad);
            values.add(numeroFactura);
            values.add(fechaCompra);
        
            executed = SQLite.exec(query, values);
        }

        return executed;
    }
    
    
    /*
    *
    *   P R O V E E D O R
    *
    */
    
    public static int isProveedorInOrden(String rut)
    {
        if(rut == null)
        {
            return -2;
        }
        else if(rut.length() < 1)
        {
            return -2;
        }
        
        if(LogicController.getProveedorByRUT(rut) == null)
        {
            return -1;
        }
        
        int response = 0;
        
        String query = "SELECT orden_id FROM Orden_Producto WHERE (proveedor_rut=?);";
        ArrayList<String> values = new ArrayList<String>();
        values.add(rut);
        
        MyTableModel mtm = SQLite.getDB(query, values);

        if(mtm.getRowCount() > 0)
        {
            response = 1;
        }
        
        return response;
    }
    
    public static Proveedor getProveedorByRUT(String rut)
    {
        if(rut == null)
        {
            return null;
        }
        else if(rut.length() < 1)
        {
            return null;
        }
        
        String query = "SELECT rut, nombre, direccion, telefono, celular, email FROM Proveedor WHERE (rut=?);";
        ArrayList<String> values = new ArrayList<String>();
        values.add(rut);
        
        MyTableModel mtm = SQLite.getDB(query, values);
        
        Proveedor proveedor = null;
        if(mtm.getRowCount() > 0)
        {
            proveedor = new Proveedor(
                                        mtm.getValueAt(0, 0).toString(),
                                        mtm.getValueAt(0, 1).toString(),
                                        mtm.getValueAt(0, 2).toString(),
                                        mtm.getValueAt(0, 3).toString(),
                                        mtm.getValueAt(0, 4).toString(),
                                        mtm.getValueAt(0, 5).toString()
            );
        }
        
        return proveedor;
    }
    
    public static boolean updateProveedor
    (
        String rut, 
        String nombre, 
        String direccion, 
        String telefono, 
        String celular, 
        String email
    )
    {
        if(rut == null)
        {
            return false;
        }
        else if(rut.length() < 1)
        {
            return false;
        }
        else if(nombre == null)
        {
            return false;
        }
        else if(nombre.length() < 1)
        {
            return false;
        }
        else if(direccion == null)
        {
            return false;
        }
        else if(direccion.length() < 1)
        {
            return false;
        }
        else if(telefono == null)
        {
            return false;
        }
        else if(telefono.length() < 1)
        {
            return false;
        }
        else if(celular == null)
        {
            return false;
        }
        else if(celular.length() < 1)
        {
            return false;
        }
        else if(email == null)
        {
            return false;
        }
        else if(email.length() < 1)
        {
            return false;
        }
        
        String query = "UPDATE Proveedor SET nombre=?, direccion=?, telefono=?, celular=?, email=? WHERE (rut=?);";
        ArrayList<String> values = new ArrayList<String>();
        values.add(nombre);
        values.add(direccion);
        values.add(telefono);
        values.add(celular);
        values.add(email);
        values.add(rut);
        
        boolean executed = SQLite.exec(query, values);
        
        return executed;
    }
    
    public static boolean insertProveedor
    (
        String rut, 
        String nombre, 
        String direccion, 
        String telefono, 
        String celular, 
        String email
    )
    {
        if(rut == null)
        {
            return false;
        }
        else if(rut.length() < 1)
        {
            return false;
        }
        else if(nombre == null)
        {
            return false;
        }
        else if(nombre.length() < 1)
        {
            return false;
        }
        else if(direccion == null)
        {
            return false;
        }
        else if(direccion.length() < 1)
        {
            return false;
        }
        else if(telefono == null)
        {
            return false;
        }
        else if(telefono.length() < 1)
        {
            return false;
        }
        else if(celular == null)
        {
            return false;
        }
        else if(celular.length() < 1)
        {
            return false;
        }
        else if(email == null)
        {
            return false;
        }
        else if(email.length() < 1)
        {
            return false;
        }
        
        String query = "INSERT INTO Proveedor(rut, nombre, direccion, telefono, celular, email) VALUES (?, ?, ?, ?, ?, ?);";
        ArrayList<String> values = new ArrayList<String>();
        values.add(rut);
        values.add(nombre);
        values.add(direccion);
        values.add(telefono);
        values.add(celular);
        values.add(email);
        
        boolean executed = SQLite.exec(query, values);
        
        return executed;
    }
    
    public static boolean deleteProveedor(String rut)
    {
        if(rut == null)
        {
            return false;
        }
        else if(rut.length() < 1)
        {
            return false;
        }
        
        String query = "DELETE FROM Proveedor WHERE (ruto=?);";
        ArrayList<String> values = new ArrayList<String>();
        values.add(rut);
        
        boolean executed = SQLite.exec(query, values);
        
        return executed;
    }
    
    
    /*
    *
    *   P R O D U C T O
    *
    */
    
    public static int isProductoInOrden(String codigo)
    {
        if(codigo == null)
        {
            return -2;
        }
        else if(codigo.length() < 1)
        {
            return -2;
        }
        
        if(LogicController.getProductoByCodigo(codigo) == null)
        {
            return -1;
        }
        
        int response = 0;
        
        String query = "SELECT orden_id FROM Orden_Producto WHERE (producto_codigo=?);";
        ArrayList<String> values = new ArrayList<String>();
        values.add(codigo);
        
        MyTableModel mtm = SQLite.getDB(query, values);

        if(mtm.getRowCount() > 0)
        {
            response = 1;
        }
        
        return response;
    }
    
    public static Producto getProductoByNombre(String nombre)
    {
        if(nombre == null)
        {
            return null;
        }
        else if(nombre.length() < 1)
        {
            return null;
        }
        
        String query = "SELECT codigo, nombre, clasificacion, fechacaducidad, cantidad FROM Producto WHERE (UPPER(nombre)=?);";
        ArrayList<String> values = new ArrayList<String>();
        values.add(nombre.toUpperCase());
        
        MyTableModel mtm = SQLite.getDB(query, values);
        
        Producto producto = null;
        if(mtm.getRowCount() > 0)
        {
            producto = new Producto(
                                        mtm.getValueAt(0, 0).toString(),
                                        mtm.getValueAt(0, 1).toString(),
                                        mtm.getValueAt(0, 2).toString(),
                                        mtm.getValueAt(0, 3).toString(),
                                        Integer.parseInt(mtm.getValueAt(0, 4).toString())
            );
        }
        
        return producto;
    }
    
    public static Producto getProductoByCodigo(String codigo)
    {
        if(codigo == null)
        {
            return null;
        }
        else if(codigo.length() < 1)
        {
            return null;
        }
        
        String query = "SELECT codigo, nombre, clasificacion, fechacaducidad, cantidad FROM Producto WHERE (UPPER(codigo)=?);";
        ArrayList<String> values = new ArrayList<String>();
        values.add(codigo.toUpperCase());
        
        MyTableModel mtm = SQLite.getDB(query, values);
        
        Producto producto = null;
        if(mtm.getRowCount() > 0)
        {
            producto = new Producto(
                                        mtm.getValueAt(0, 0).toString(),
                                        mtm.getValueAt(0, 1).toString(),
                                        mtm.getValueAt(0, 2).toString(),
                                        mtm.getValueAt(0, 3).toString(),
                                        Integer.parseInt(mtm.getValueAt(0, 4).toString())
            );
        }
        
        return producto;
    }
    
    public static MyTableModel getProductos(String nombre)
    {
        String query = "SELECT codigo, nombre, clasificacion, fechacaducidad, cantidad FROM Producto WHERE nombre LIKE ?;";
        ArrayList<String> values = new ArrayList<String>();
        values.add(nombre);
        
        MyTableModel mtm = SQLite.getDB_LIKE(query, values);
        
        return mtm;
    }
    
    public static MyTableModel getProductos()
    {
        String query = "SELECT codigo, nombre, clasificacion, fechacaducidad, cantidad FROM Producto;";
        ArrayList<String> values = new ArrayList<String>();
        
        MyTableModel mtm = SQLite.getDB(query, values);
        
        return mtm;
    }
    
    public static boolean insertProducto
    (
        String codigo, 
        String nombre, 
        String clasificacion, 
        String fechaCaducidad
    )
    {
        if(codigo == null)
        {
            return false;
        }
        else if(codigo.length() < 1)
        {
            return false;
        }
        else if(nombre == null)
        {
            return false;
        }
        else if(nombre.length() < 1)
        {
            return false;
        }
        else if(clasificacion == null)
        {
            return false;
        }
        else if(clasificacion.length() < 1)
        {
            return false;
        }
        else if(fechaCaducidad == null)
        {
            return false;
        }
        else if(fechaCaducidad.length() < 1)
        {
            return false;
        }
        
        String query = "INSERT INTO Producto(codigo, nombre, clasificacion, fechacaducidad, cantidad) VALUES (?, ?, ?, ?, 0);";
        ArrayList<String> values = new ArrayList<String>();
        values.add(codigo);
        values.add(nombre);
        values.add(clasificacion);
        values.add(fechaCaducidad);
        
        boolean executed = SQLite.exec(query, values);
        
        return executed;
    }
    
    public static boolean deleteProducto(String codigo)
    {
        if(codigo == null)
        {
            return false;
        }
        else if(codigo.length() < 1)
        {
            return false;
        }
        
        String query = "DELETE FROM Producto WHERE (codigo=?);";
        ArrayList<String> values = new ArrayList<String>();
        values.add(codigo);
        
        boolean executed = SQLite.exec(query, values);
        
        return executed;
    }
    
}
