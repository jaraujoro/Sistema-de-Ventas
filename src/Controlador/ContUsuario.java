
package Controlador;

import Modelo.Cargo;
import Modelo.Usuario;
import Utilidades.BcryptPassword.Bcrypt;
import Vista.FormMenu;
import Vista.Login.FormLogin;
import Vista.Login.FormRegistro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ContUsuario  implements ActionListener{
    
    private FormLogin login = new FormLogin();
    private FormRegistro registro = new FormRegistro();
    private FormMenu fromMenu = new FormMenu();
    private Usuario user = new Usuario();
    private Cargo cargo = new Cargo();
    private Bcrypt bcrypt = new Bcrypt();
    ContDashBoard  controladorDash;
    public ContUsuario(FormLogin login, FormRegistro registro){
        this.login     = login;
        this.registro = registro;
        login.btnIngresar.addActionListener(this);
        login.btnCrearCuenta.addActionListener(this);
        registro.btnRegistrar.addActionListener(this);
        registro.btnRegresar.addActionListener(this);
    }
    //eventos de los botones
    public void actionPerformed(ActionEvent e){
        if(login.btnIngresar == e.getSource()){ //login -> ingresar al sistema
             validarUsuario();
        }
        if(registro.btnRegistrar == e.getSource()){ //registrar usuario en la base de datos
             registrarUsuario();
        }
        if(login.btnCrearCuenta == e.getSource()){
             login.dispose();
             registro.setVisible(true);
        }
        if(registro.btnRegresar == e.getSource()){
             registro.dispose();
             login.setVisible(true);
        }
    }
    //métodos 
    public void mensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    
    public void validarUsuario() {
        String usuario = login.txtUsuario.getText();
        String contraseña = login.txtContraseña.getText();
        if (usuario.isEmpty() || contraseña.isEmpty()) {
            mensaje("Complete todos los campos");
        } else {
            try {
                user = new Usuario();
                user = user.validarUsuario(usuario);
                if (user != null) {
                    if (bcrypt.desencriptionPassword(contraseña, user.getContraseña())) {
                        mensaje("bienvenido");
                        login.dispose();
                        controladorDash = new ContDashBoard(fromMenu, user);
                    } else {
                        mensaje("contraseña incorrecta");
                    }
                } else {
                    mensaje("El usuario ingresado no existe");
                }
            } catch (Exception e) {
                System.out.println("error " + e);
            }
        }
    }

    public void limpiarCampos() {
        registro.txtNombre.setText("");
        registro.txtApellido.setText("");
        registro.txtDni.setText("");
        registro.txtUsuario.setText("");
        registro.txtContraseña.setText("");
        registro.cbnCargo.setSelectedIndex(0);
    }
    //metodo para registrar en la base de datos;
    public void registrarUsuario() {
        String nombre = registro.txtNombre.getText();
        String apellido = registro.txtApellido.getText();
        String dni = registro.txtDni.getText();
        String correo = registro.txtUsuario.getText();
        String contraseña = registro.txtContraseña.getText();
        String passwordBcrypt = bcrypt.EncryptionPassword(contraseña); //encriptamos la contraseña y lo insertamos en la base de datos
        int opcion = registro.cbnCargo.getSelectedIndex();
        cargo.setId_cargo(opcion);
        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || correo.isEmpty() || contraseña.isEmpty() || opcion == 0) {
            mensaje("Complete todos los campos");
        } else {
            user = new Usuario(nombre, apellido, dni, correo, passwordBcrypt, cargo);
            try {
                int registrar = user.registrarUsuario();
                  if (registrar > 0) {
                     JOptionPane.showMessageDialog(null, "Usuario registrado");
                     limpiarCampos();
                     registro.dispose();
                     login.setVisible(true);
                  }
            } catch (Exception e) {
                System.out.println("Error");
            }
        }
    }
    
    
    
}
