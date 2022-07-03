/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionJDBC;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class JDBCSQLServer extends GestorJDBC {
    @Override
    public void abrirConexion() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");   
            String url = "jdbc:sqlserver://localhost:1433;databaseName=sistema_ventas";
            System.out.println("conectado");
            conexion = DriverManager.getConnection(url, "sa", "123");
        } catch (SQLException e) {
            throw new Exception("Error en la conexion con la base de datos, consulte con el administrador." + e);
        }
    }
}
