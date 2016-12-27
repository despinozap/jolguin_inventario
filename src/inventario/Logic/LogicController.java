/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.Logic;

import inventario.Entity.Producto;
import inventario.Entity.Proveedor;
import inventario.Persistent.SQLite;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.regex.Pattern;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author David
 */
public class LogicController 
{
    
    /*
    *
    *   R E P O R T E S
    *
    */
    
    public static MyTableModel getProveedoresByProducto(String codigo)
    {        
        String query = "SELECT prov.rut AS proveedor_rut, prov.nombre AS proveedor_nombre, c.preciocompra AS precio, c.cantidad AS cantidad, c.numerofactura AS numerofactura, c.fechacompra AS fechacompra FROM Compra c, Producto prod, Proveedor prov WHERE (c.producto_codigo=?) AND (prod.codigo=c.producto_codigo) AND (prov.rut=c.proveedor_rut) ORDER BY c.fechacompra DESC;";
        ArrayList<String> values = new ArrayList<String>();
        values.add(codigo);
        
        MyTableModel mtm = SQLite.getDB(query, values);
        
        return mtm;
    }
    
    public static BufferedImage getChart_Top10Productos(String month, int width, int height)
    {              
        if(month == null)
        {
            return null;
        }
        else if(month.length() != 7)
        {
            return null;
        }
        
        if(Pattern.matches("[0-9]{4}-[0-9]{2}", month) == false)
        {
            return null;
        }
        
        MyTableModel mtm = LogicController.getData_Top10Productos(month);

        if(mtm != null)
        {
            DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

            for(int i=0; i<mtm.getRowCount(); i++)
            {
                dataSet.addValue(
                                    Integer.parseInt(mtm.getValueAt(i, 1).toString()), 
                                    "Cantidad de productos", 
                                    mtm.getValueAt(i, 0).toString()
                );
            }

            JFreeChart barChart = ChartFactory.createBarChart3D(
                                                                "Productos más utilizados en " + month,
                                                                "Producto",
                                                                "Cantidad",
                                                                dataSet,
                                                                PlotOrientation.HORIZONTAL,
                                                                true,
                                                                true,
                                                                false
            );

            CategoryPlot plot = barChart.getCategoryPlot();
            NumberAxis numberAxis = (NumberAxis)plot.getRangeAxis();
            numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

            return barChart.createBufferedImage(width, height);
        }
        else
        {
            return null;
        }
          
    }
    
    private static MyTableModel getData_Top10Productos(String month)
    {        
        String query = "SELECT p.nombre AS nombre, SUM(r.cantidad) AS cantidad FROM Retiro r, Producto p WHERE (r.fecha LIKE ?) AND (p.codigo=r.producto_codigo) GROUP BY (r.producto_codigo) ORDER BY SUM(r.cantidad) DESC LIMIT 10;";
        ArrayList<String> values = new ArrayList<String>();
        values.add(month);

        return SQLite.getDB_LIKE(query, values);
    }
    
    public static BufferedImage getChart_InformeCostos(String year, int width, int height)
    {       
        String[] months = {
                            "Enero",
                            "Febrero",
                            "Marzo",
                            "Abril",
                            "Mayo",
                            "Junio",
                            "Julio",
                            "Agosto",
                            "Septiembre",
                            "Octubre",
                            "Noviembre",
                            "Diciembre"
        };
        
        int[] data = LogicController.getData_InformeCostos(year);

        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

        for(int i=0; i<months.length; i++)
        {
            dataSet.addValue(
                                data[i], 
                                "Costos", 
                                months[i]
            );
        }

        JFreeChart lineChart = ChartFactory.createLineChart3D(
                                                            "Informe de Costos año " + year,
                                                            "Mes",
                                                            "Monto",
                                                            dataSet,
                                                            PlotOrientation.VERTICAL,
                                                            true,
                                                            true,
                                                            false
        );
        
        CategoryAxis axis = lineChart.getCategoryPlot().getDomainAxis();
        axis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        return lineChart.createBufferedImage(width, height);  
    }
    
    private static int[] getData_InformeCostos(String year)
    {
        int[] data = {
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0
        };
        
        String query = "SELECT substr(fechaCompra, 6, 2) AS mes, SUM(cantidad*preciocompra) AS cantidad FROM Compra WHERE(substr(fechaCompra, 0, 5) = ?) GROUP BY substr(fechaCompra, 0, 8);";
        ArrayList<String> values = new ArrayList<String>();
        values.add(year);
        
        MyTableModel mtm = SQLite.getDB(query, values);
        
        if(mtm != null)
        {
            int month = -1;
            for(int i=0; i<mtm.getRowCount(); i++)
            {
                month = Integer.parseInt(mtm.getValueAt(i, 0).toString());
                data[month -1] = Integer.parseInt(mtm.getValueAt(i, 1).toString());
            }
        }
        
        return data;
    }
    
    
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
            
            if(executed == true)
            {
                LogicController.updateProducto_Cantidad(producto_codigo, cantidad, true);
            }
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
        
        String query = "SELECT id FROM Compra WHERE (proveedor_rut=?);";
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
        
        String query = "DELETE FROM Proveedor WHERE (rut=?);";
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
    
    public static boolean updateProducto_Cantidad
    (
        String codigo,
        String sCantidad,
        boolean add
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
        
        int cantidad = 0;
        try
        {
            cantidad = Integer.parseInt(sCantidad);
        }
        catch(Exception ex)
        {
            return false;
        }
        
        if(add == false)
        {
            cantidad = cantidad * -1;
        }
        
        boolean executed = true;
        
        int newCantidad = LogicController.getProducto_Cantidad(codigo);
        if(newCantidad >= 0)
        {
            newCantidad += cantidad;
            if(newCantidad >= 0)
            {
                String query = "UPDATE Producto SET cantidad=? WHERE (codigo=?);";
                ArrayList<String> values = new ArrayList<String>();
                values.add(Integer.toString(newCantidad));
                values.add(codigo);
        
                executed = SQLite.exec(query, values);
                
                if((executed == true) && (add == false))
                {
                    query = "INSERT INTO Retiro(producto_codigo, cantidad, fecha) VALUES (?, ?, strftime('%Y-%m-%d', 'now'));";
                    values = new ArrayList<String>();
                    values.add(codigo);
                    values.add(sCantidad);
                    
                    executed = SQLite.exec(query, values);
                }
            }
            else
            {
                executed = false;
            }    
        }
        else
        {
            executed = false;
        }
        
  
        return executed;
    }
    
    public static int getProducto_Cantidad(String codigo)
    {
        if(codigo == null)
        {
            return -1;
        }
        else if(codigo.length() < 1)
        {
            return -1;
        }
        
        String query = "SELECT cantidad FROM Producto WHERE (codigo=?);";
        ArrayList<String> values = new ArrayList<String>();
        values.add(codigo);
        
        MyTableModel mtm = SQLite.getDB(query, values);
        
        int response = 0;
        if(mtm.getRowCount() > 0)
        {
            try
            {
                response = Integer.parseInt(mtm.getValueAt(0, 0).toString());
            }
            catch(Exception ex)
            {
                response = -1;
            }
        }
        
        return response;
    }
    
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
        
        String query = "SELECT id FROM Compra WHERE (producto_codigo=?);";
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
