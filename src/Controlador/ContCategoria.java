
package Controlador;

import Modelo.Categoria;
import Vista.FormMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ContCategoria implements ActionListener{
    FormMenu formMenu;
    Categoria categoria = new Categoria();
    public ContCategoria(FormMenu formMenu){
            this.formMenu=formMenu;
            formMenu.btnGuardarCategoria.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(formMenu.btnGuardarCategoria == e.getSource()){
            registrarCategoria();
        }
    }
    
    
    /*Metodos*/
    public void mensaje(String mensaje){
        JOptionPane.showMessageDialog(null,mensaje);
    }
    public void registrarCategoria() {
        String txtcategoria = formMenu.txtCategoria.getText();
        if (txtcategoria.isEmpty()) {
            mensaje("Complete el campo");
        } else {
            try {
                int registrarCategoria = this.categoria.registrarCategoria(txtcategoria);
                if (registrarCategoria > 0) {
                    mensaje("Categoria registrada");
                    formMenu.txtCategoria.setText("");
                }
            } catch (Exception ex) {
                System.out.println("error: " + ex);
            }
        }
    }
}
