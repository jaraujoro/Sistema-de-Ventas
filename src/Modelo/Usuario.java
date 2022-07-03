package Modelo;

import ConexionJDBC.GestorJDBC;
import ConexionJDBC.JDBCMySql;
import DataAccessObject.UsuarioDAO;
import java.sql.SQLException;

public class Usuario{
    private int id_usuario;
    private String nombre;
    private String apellido;
    private String dni;
    private String usuario;
    private String contraseña;
    private Cargo cargo;
    GestorJDBC gestor = new JDBCMySql();
    UsuarioDAO usarioDAO = new UsuarioDAO(gestor);
    
    public Usuario(String nombre, String apellido, String dni, String usuario, String contraseña, Cargo cargo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.cargo = cargo;
    }

    public Usuario(int id_usuario, String nombre, String apellido, String dni, String usuario, String contraseña, Cargo cargo) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.cargo = cargo;
    }
    
    public Usuario(){
        
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Cargo getCargo() {
        return cargo;
    }
    
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
 
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
    public int registrarUsuario() throws Exception{
        gestor.abrirConexion();
        try {
            gestor.iniciarTransaccion();
            int registrar = usarioDAO.registrarUsuario(this);
            gestor.terminarTransaccion();
            return registrar;
        } catch (Exception e) {
            gestor.cancelarTransaccion();
            throw e;
        }
    }
    
    public Usuario validarUsuario(String usuario) throws Exception{
            gestor.abrirConexion();
            Usuario user = usarioDAO.validarUsuario(usuario);
            gestor.cerrarConexion();
            return user;
    }
}
