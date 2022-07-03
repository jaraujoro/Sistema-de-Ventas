package Main;

import ConexionJDBC.GestorJDBC;
import ConexionJDBC.JDBCMySql;
import ConexionJDBC.JDBCSQLServer;
import Controlador.ContUsuario;
import Vista.Login.FormLogin;
import Vista.Login.FormRegistro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    
    public static ContUsuario controlador;
    public static FormLogin login;
    public static FormRegistro registro;
    
    public static void main(String[] args) throws Exception {
        login = new FormLogin();
        registro = new FormRegistro();
        controlador = new ContUsuario(login, registro);
        login.setVisible(true);
    }

}
