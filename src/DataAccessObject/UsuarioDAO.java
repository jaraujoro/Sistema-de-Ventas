
package DataAccessObject;

import ConexionJDBC.GestorJDBC;
import Modelo.Cargo;
import Modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    GestorJDBC gestorJDBC;
    Usuario user;
    Cargo cargo;
    public UsuarioDAO( GestorJDBC gestorJDBC){
        this.gestorJDBC = gestorJDBC;
    }
    
    public int registrarUsuario(Usuario usuario) throws SQLException{
        String sql = "insert into usuario (nombre, apellido, dni, usuario, contraseña, id_cargo) values(?,?,?,?,?,?)";
        PreparedStatement sentencia = gestorJDBC.prepararSentencia(sql);
        sentencia.setString(1, usuario.getNombre());
        sentencia.setString(2, usuario.getApellido());
        sentencia.setString(3, usuario.getDni());
        sentencia.setString(4, usuario.getUsuario());
        sentencia.setString(5, usuario.getContraseña());
        sentencia.setInt(6, usuario.getCargo().getId_cargo());
        return sentencia.executeUpdate();
    }
    
    public Usuario validarUsuario(String usuario) throws SQLException{
        String sql = "SELECT * FROM  usuario where usuario=?";
        PreparedStatement sentencia  = gestorJDBC.prepararSentencia(sql);
        sentencia.setString(1,usuario);
        ResultSet result = sentencia.executeQuery();
        if(result.next()){
            user = new Usuario(
                    result.getInt("id_usuario"),
                    result.getString("nombre"),
                    result.getString("apellido"),
                    result.getString("dni"),
                    result.getString("usuario"), 
                    result.getString("contraseña"),
                    cargo = new Cargo(result.getInt("id_cargo")));
        }
        return user;
    }
}
