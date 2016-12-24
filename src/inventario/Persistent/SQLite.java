/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.Persistent;

/**
 *
 * @author daid
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import inventario.Logic.MyTableModel;

public class SQLite 
{
    private static final String DB_NAME = "database.db";
    
    public static boolean exec(String sentence, ArrayList<String> values)
    { 
        
        boolean executed = false;

        try 
        {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + SQLite.DB_NAME);
            PreparedStatement stat = conn.prepareStatement(sentence);
            
            for(int i=0; i<values.size(); i++)
            {
                stat.setString(i+1, values.get(i));
            }
            
            stat.execute();
            executed = true;
        } 
        catch (SQLException ex) 
        {
            System.err.println("MANAGED EXCEPTION: " + ex.toString());
        } 
        catch (ClassNotFoundException ex) 
        {
            System.err.println("MANAGED EXCEPTION: " + ex.toString());
        }
        catch (Exception ex)
        {
            System.err.println("MANAGED EXCEPTION: " + ex.toString());
        }

        return executed;
    }

    public static MyTableModel getDB_LIKE(String query, ArrayList<String> values)
    {
        MyTableModel mtm = null;
        Connection conn = null;

        try 
        {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + SQLite.DB_NAME);
            
            PreparedStatement stat = conn.prepareStatement(query);
            
            for(int i=0; i<values.size(); i++)
            {
                stat.setString(i+1, "%" + values.get(i) + "%");
            }
            
            ResultSet rs = stat.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
                    
            Object[] labels = new Object[rsmd.getColumnCount()];
                    
            mtm = new MyTableModel();
                    
            for(int i=0; i<labels.length; i++)
            {
                mtm.addColumn(rsmd.getColumnLabel(i+1));
            }
                    
            while(rs.next())
            {
                Object[] data = new Object[rsmd.getColumnCount()];
                for(int i=0; i<data.length; i++)
                {
                    data[i] = rs.getString(i+1);
                }
                
                mtm.addRow(data);
            }       
        } 
        catch (ClassNotFoundException ex) 
        {
            System.err.println("MANAGED EXCEPTION: " + ex.toString());
            mtm = null;
        } 
        catch (SQLException ex) 
        {
            System.err.println("MANAGED EXCEPTION: " + ex.toString());
            mtm = null;
        }
        catch( Exception ex)
        {
            System.err.println("MANAGED EXCEPTION: " + ex.toString());
            mtm = null;
        }
        finally
        {
            if(conn != null)
            {
                try 
                {
                    conn.close();
                } 
                catch (SQLException ex) 
                {
                    System.err.println("MANAGED EXCEPTION: " + ex.toString());
                }
            }
        }
                
        return mtm;
    }
    
    public static MyTableModel getDB(String query, ArrayList<String> values)
    {
        MyTableModel mtm = null;
        Connection conn = null;

        try 
        {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + SQLite.DB_NAME);
            
            PreparedStatement stat = conn.prepareStatement(query);
            
            for(int i=0; i<values.size(); i++)
            {
                stat.setString(i+1, values.get(i));
            }
            
            ResultSet rs = stat.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
                    
            Object[] labels = new Object[rsmd.getColumnCount()];
                    
            mtm = new MyTableModel();
                    
            for(int i=0; i<labels.length; i++)
            {
                mtm.addColumn(rsmd.getColumnLabel(i+1));
            }
                    
            while(rs.next())
            {
                Object[] data = new Object[rsmd.getColumnCount()];
                for(int i=0; i<data.length; i++)
                {
                    data[i] = rs.getString(i+1);
                }
                
                mtm.addRow(data);
            }       
        } 
        catch (ClassNotFoundException ex) 
        {
            System.err.println("MANAGED EXCEPTION: " + ex.toString());
            mtm = null;
        } 
        catch (SQLException ex) 
        {
            System.err.println("MANAGED EXCEPTION: " + ex.toString());
            mtm = null;
        }
        catch( Exception ex)
        {
            System.err.println("MANAGED EXCEPTION: " + ex.toString());
            mtm = null;
        }
        finally
        {
            if(conn != null)
            {
                try 
                {
                    conn.close();
                } 
                catch (SQLException ex) 
                {
                    System.err.println("MANAGED EXCEPTION: " + ex.toString());
                }
            }
        }
                
        return mtm;
    }

}

