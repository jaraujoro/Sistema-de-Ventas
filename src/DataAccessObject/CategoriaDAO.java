
package DataAccessObject;

import ConexionJDBC.GestorJDBC;
import Modelo.Categoria;
import java.sql.PreparedStatement;


public class CategoriaDAO {
    
    GestorJDBC gestorJDBC;
    public CategoriaDAO(GestorJDBC gestorJDBC){
        this.gestorJDBC = gestorJDBC;
    }
    
    
    public int  registrarCategoria(String categoria) throws Exception{
        String sql = "insert into categoria(categoria) values(?)";
        PreparedStatement prepare = gestorJDBC.prepararSentencia(sql);
        prepare.setString(1, categoria);
        return prepare.executeUpdate();
    }
}
