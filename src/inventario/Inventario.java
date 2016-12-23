/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario;

import inventario.Graphic.FrmLogin;
import inventario.Graphic.FrmMain;
import inventario.Logic.CONFIG_FORMS;

/**
 *
 * @author David
 */
public class Inventario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        FrmLogin frmLogin = new FrmLogin();
        
        //frmLogin.show();
        
        //REMOVE IT!
        {
            FrmMain frmMain = new FrmMain();
            CONFIG_FORMS.frmMain = frmMain;
            
            CONFIG_FORMS.frmMain.show();
        }
    }
    
}
