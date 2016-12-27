/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.Graphic;

import inventario.Entity.Producto;
import inventario.Logic.CONFIG_FORMS;
import inventario.Logic.LogicController;
import inventario.Logic.MyTableModel;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author David
 */
public class FrmMovimientos extends javax.swing.JFrame {

    private ArrayList<String> listProductos_Codigo;
    
    /**
     * Creates new form FrmMovimientos
     */
    public FrmMovimientos() {
        initComponents();
        
        initForm();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbRetiro_Nombre = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtRetiro_Codigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtRetiro_Cantidad = new javax.swing.JTextField();
        btInsertRetiro = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProductos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Movimientos de Inventario");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Nuevo retiro"));

        jLabel1.setText("Producto:");

        cbRetiro_Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRetiro_NombreActionPerformed(evt);
            }
        });

        jLabel2.setText("Código:");

        txtRetiro_Codigo.setEditable(false);

        jLabel3.setText("Cantidad:");

        btInsertRetiro.setText("Retirar");
        btInsertRetiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInsertRetiroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbRetiro_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRetiro_Codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRetiro_Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btInsertRetiro, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbRetiro_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtRetiro_Codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtRetiro_Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btInsertRetiro))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Situación actual"));

        tbProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbProductos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(768, 423));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        CONFIG_FORMS.frmMain.show();
        this.hide();
    }//GEN-LAST:event_formWindowClosing

    private void cbRetiro_NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRetiro_NombreActionPerformed
        // TODO add your handling code here:
        if(cbRetiro_Nombre.getSelectedIndex() > 0)
        {
            Producto producto = LogicController.getProductoByCodigo(this.listProductos_Codigo.get(cbRetiro_Nombre.getSelectedIndex() -1));
            if(producto != null)
            {
                txtRetiro_Codigo.setText(producto.getCodigo());
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error al consultar el producto", "Retirar producto", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            txtRetiro_Codigo.setText("");
        }
    }//GEN-LAST:event_cbRetiro_NombreActionPerformed

    private void btInsertRetiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInsertRetiroActionPerformed
        // TODO add your handling code here:
        if(validateFormRetiro())
        {
            int cantidad = LogicController.getProducto_Cantidad(txtRetiro_Codigo.getText());
            if(cantidad >= Integer.parseInt(txtRetiro_Cantidad.getText()))
            {
                int confirm = JOptionPane.showConfirmDialog(this, "¿Realmente deseas registrar el retiro de productos?", "Retirar producto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(confirm == JOptionPane.YES_OPTION)
                {
                    if(LogicController.updateProducto_Cantidad(txtRetiro_Codigo.getText(), txtRetiro_Cantidad.getText(), false))
                    {
                        JOptionPane.showMessageDialog(this, "Se han retirado exitosamente los productos", "Retirar producto", JOptionPane.INFORMATION_MESSAGE);
                       
                        clearFormRetiro();
                        loadProductos_SituacionActual();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Ha ocurrido un error al retirar los productos", "Retirar producto", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "La cantidad a retirar debe ser igual o inferior al stock disponible (Stock: " + cantidad + ")", "Retirar producto", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btInsertRetiroActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmMovimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMovimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMovimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMovimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMovimientos().setVisible(true);
            }
        });
    }
    
    
    /*
    *
    *   C O D E
    *
    */
    
    private void initForm()
    {
        this.getContentPane().setBackground(Color.WHITE);
    }

    private void loadProductos_Retiro()
    {
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        
        MyTableModel dbMtm = LogicController.getProductos();
        
        if(dbMtm != null)
        {
            this.listProductos_Codigo = new ArrayList<String>();
            dcbm.addElement("Seleccione..");

            for(int i=0; i<dbMtm.getRowCount(); i++)
            {
                this.listProductos_Codigo.add(dbMtm.getValueAt(i, 0).toString());
                dcbm.addElement(dbMtm.getValueAt(i, 1).toString());
            }
        }
        else
        {
            
        }
        
        cbRetiro_Nombre.setModel(dcbm);
    }
    
    public void loadProductos_SituacionActual()
    {
        displayProductos(LogicController.getProductos());
    }
    
    private boolean displayProductos(MyTableModel dbMtm)
    {
        if(dbMtm == null)
        {
            return false;
        }
        
        boolean result = false;
        
        MyTableModel mtm = new MyTableModel();
        mtm.addColumn("Código");
        mtm.addColumn("Producto");
        mtm.addColumn("Clasificación");
        mtm.addColumn("Fecha caducidad");
        mtm.addColumn("Cantidad");

        Object[] data = null;

        for(int i=0; i<dbMtm.getRowCount(); i++)
        {
            data = new Object[5];
            data[0] = dbMtm.getValueAt(i, 0).toString();
            data[1] = dbMtm.getValueAt(i, 1).toString();
            data[2] = dbMtm.getValueAt(i, 2).toString();
            data[3] = dbMtm.getValueAt(i, 3).toString();
            data[4] = dbMtm.getValueAt(i, 4).toString();

            mtm.addRow(data);
        }
        
        tbProductos.setModel(mtm);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        
        tbProductos.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbProductos.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbProductos.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbProductos.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbProductos.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        return result;
    }
    
    private boolean validateNumber(String s)
    {
        try
        {
            int i = Integer.parseInt(s);
            if(i > 0)
            {
                return true;
            }
            
            return false;
        }
        catch(Exception ex)
        {
            return false;
        }
    }
    
    private boolean validateFormRetiro()
    {
        boolean response = true;
        
        if(cbRetiro_Nombre.getSelectedIndex() < 1)
        {
            JOptionPane.showMessageDialog(this, "Debes seleccionar el producto", "Returar producto", JOptionPane.WARNING_MESSAGE);
            
            response = false;
        }
        else if(txtRetiro_Codigo.getText().length() < 1)
        {
            JOptionPane.showMessageDialog(this, "Debes seleccionar el producto", "Returar producto", JOptionPane.WARNING_MESSAGE);
            
            response = false;
        }
        else if(txtRetiro_Cantidad.getText().length() < 1)
        {
            JOptionPane.showMessageDialog(this, "Debes ingresar la cantidad a retirar", "Returar producto", JOptionPane.WARNING_MESSAGE);
            
            response = false;
        }
        else if(validateNumber(txtRetiro_Cantidad.getText()) == false)
        {
            JOptionPane.showMessageDialog(this, "La cantidad a retirar debe ser numérica positiva", "Returar producto", JOptionPane.WARNING_MESSAGE);
            
            response = false;
        }
        
        return response;
    }
    
    public void clearFormRetiro()
    {
        txtRetiro_Codigo.setText("");
        txtRetiro_Cantidad.setText("");
        
        loadProductos_Retiro();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btInsertRetiro;
    private javax.swing.JComboBox<String> cbRetiro_Nombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbProductos;
    private javax.swing.JTextField txtRetiro_Cantidad;
    private javax.swing.JTextField txtRetiro_Codigo;
    // End of variables declaration//GEN-END:variables
}