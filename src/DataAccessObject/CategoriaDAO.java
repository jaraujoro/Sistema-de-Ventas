
package DataAccessObject;

import ConexionJDBC.GestorJDBC;
import Modelo.Categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


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
    
    public List<Categoria> listarCategoria(String buscar) throws Exception{
        ArrayList<Categoria> listarCategoria = new ArrayList<>();
        String sql = "select id_categoria, categoria from categoria where categoria like ?";
        PreparedStatement  prepare = gestorJDBC.prepararSentencia(sql);
        prepare.setString(1, "%" + buscar + "%");
        ResultSet result = prepare.executeQuery();
        while(result.next()){
            Categoria categoria = new Categoria();
            categoria.setId_categoria(result.getInt("id_categoria"));
            categoria.setCategoria(result.getString("categoria"));
            listarCategoria.add(categoria);
        }
        return listarCategoria;
    }
    
    public int eliminarCategoria(String id_categoria) throws Exception {
        String sql = "delete from categoria where id_categoria = ?";
        PreparedStatement prepare = gestorJDBC.prepararSentencia(sql);
        prepare.setString(1, id_categoria);
        return prepare.executeUpdate();
    }
    
    public Categoria verCategoria(String id_categoria) throws Exception{
        Categoria categoria = null;
        String sql = "select  id_categoria, categoria from categoria where id_categoria = ?";
        PreparedStatement prepare = gestorJDBC.prepararSentencia(sql);
        prepare.setString(1, id_categoria);
        ResultSet result = prepare.executeQuery();
        if(result.next()){
            categoria = new Categoria();
            categoria.setId_categoria(result.getInt("id_categoria"));
            categoria.setCategoria(result.getString("categoria"));
        }
        result.close();
        return categoria;
    }
    
    public int editarCategoria(Categoria categoria) throws Exception{
        String sql = "update categoria set categoria=? where id_categoria=?";
        PreparedStatement prepare = gestorJDBC.prepararSentencia(sql);
        prepare.setString(1, categoria.getCategoria());
        prepare.setInt(2, categoria.getId_categoria());
        return prepare.executeUpdate();
    }
    
}
