
package Modelo;

import ConexionJDBC.GestorJDBC;
import ConexionJDBC.JDBCMySql;
import DataAccessObject.CategoriaDAO;

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
    

}
