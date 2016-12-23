/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.Logic;

import inventario.Entity.Producto;
import inventario.Persistent.SQLite;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    
    public static MyTableModel getProductos()
    {
        String query = "SELECT codigo, nombre, clasificacion, fechacaducidad, cantidad FROM Producto;";
        ArrayList<String> values = new ArrayList<String>();
        
        MyTableModel mtm = SQLite.getDB(query, values);
        
        return mtm;
    }
    
    public static boolean insertProducto(String codigo, String nombre, String clasificacion, String fechaCaducidad)
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
