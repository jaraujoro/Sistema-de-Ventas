
package Controlador;
import Modelo.Usuario;
import Vista.FormMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ContDashBoard extends MouseAdapter implements ActionListener{
        private String cargoUsuario;
        private FormMenu formMenu;
        ContCategoria formCategoria;
    public ContDashBoard (FormMenu formMenu ,Usuario usuario){
        this.formMenu = formMenu;
        formCategoria = new ContCategoria(formMenu);
        formMenu.setVisible(true);
        formMenu.labelNombreUsuario.setText(usuario.getNombre()+" "+usuario.getApellido());
        cargoUsuario = (usuario.getCargo().getId_cargo()==1) ? "Administrador" :"Vendedor";
        formMenu.labelCargoUsuario.setText(cargoUsuario);
        formMenu.btnLimpiarBuscador.setEnabled(false);
        //eventos a los botones
        formMenu.btnGestionarCategoria.addActionListener(this);
        formMenu.btnGestionarProducto.addActionListener(this);
        formMenu.btnGestionarCliente.addActionListener(this);
        formMenu.btnGestionarFactura.addActionListener(this);
        formMenu.btnReporte.addActionListener(this);
        formMenu.btnHistorialVenta.addActionListener(this);
        formMenu.btnCerrarSesion.addActionListener(this);
        formMenu.btnCerrarModal.addActionListener(this);
        //effect's
        formMenu.PanelModalProductoMode.setVisible(false);
        formMenu.PanelModalCategoriaMode.setVisible(false);
        }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(formMenu.btnCerrarModal == e.getSource()){
            formMenu.PanelModalCategoriaMode.setVisible(false);
            formMenu.txtBuscarCategoria.setEnabled(true);
            formMenu.txtCategoria.setEnabled(true);
        }
        if(formMenu.btnGestionarCategoria == e.getSource()){
            formMenu.PanelPrincipal.setVisible(false);
            //formMenu.PanelProducto.setVisible(false);
            formMenu.PanelCategoria.setVisible(true);
        }
        if(formMenu.btnGestionarProducto == e.getSource()){
            formMenu.PanelPrincipal.setVisible(false);
            formMenu.PanelCategoria.setVisible(false);
            //formMenu.PanelProducto.setVisible(true);
        }
        
    }
    //////////////////////////////////////////////////////////////
     @Override
    public void mouseClicked(MouseEvent e) {
        
    }
    
}
