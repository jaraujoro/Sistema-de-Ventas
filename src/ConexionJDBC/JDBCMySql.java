package ConexionJDBC;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCMySql extends GestorJDBC {

    @Override
    public void abrirConexion() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/sistema_ventas?useSSL=false";
            conexion = DriverManager.getConnection(url, "root", "");
        }catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
    }
}