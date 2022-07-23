
package Modelo;

import ConexionJDBC.GestorJDBC;
import ConexionJDBC.JDBCMySql;
import DataAccessObject.CategoriaDAO;
import java.util.List;

public class Categoria {
    
    private int id_categoria;
    private String categoria;
    private GestorJDBC gestorJDBC = new JDBCMySql();
    private CategoriaDAO categoriaDAO = new CategoriaDAO(gestorJDBC);
    
    public Categoria(){
        
    }
    
    public Categoria(int id_categoria, String categoria) {
        this.id_categoria = id_categoria;
        this.categoria = categoria;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public int registrarCategoria(String categoria) throws Exception {
        gestorJDBC.abrirConexion();
        try {
            gestorJDBC.iniciarTransaccion();
            int registrarCategoria = categoriaDAO.registrarCategoria(categoria);
            gestorJDBC.terminarTransaccion();
            return registrarCategoria;
        } catch (Exception e) {
            gestorJDBC.cancelarTransaccion();
            throw e;
        }
    }
    
    public List<Categoria> listarCategoria(String buscar) throws Exception{
        gestorJDBC.abrirConexion();
        List listarCategoria = categoriaDAO.listarCategoria(buscar);
        gestorJDBC.cerrarConexion();
        return listarCategoria;
    }
    
    public int eliminarCategoria(String id_categoria) throws Exception {
        gestorJDBC.abrirConexion();
        try {
            gestorJDBC.iniciarTransaccion();
            int eliminar = categoriaDAO.eliminarCategoria(id_categoria);
            gestorJDBC.terminarTransaccion();
            return eliminar;
        } catch (Exception e) {
            gestorJDBC.cancelarTransaccion();
            throw e;
        }
    }
    
    public Categoria verCategoria(String id_cagetoria) throws Exception{
        gestorJDBC.abrirConexion();
        Categoria categoria = categoriaDAO.verCategoria(id_cagetoria);
        gestorJDBC.cerrarConexion();
        return categoria;
    }
    
    public int editarCategoria() throws Exception{
        gestorJDBC.abrirConexion();
        try {
            gestorJDBC.iniciarTransaccion();
            int editar = categoriaDAO.editarCategoria(this);
            gestorJDBC.terminarTransaccion();
            return editar;
        } catch (Exception e) {
            gestorJDBC.cancelarTransaccion();
            throw e;
        }
      
    }
}
