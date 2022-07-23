
package Controlador;

import Modelo.Categoria;
import Utilidades.ModelTabel.ModelTable;
import Vista.FormMenu;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class ContCategoria extends MouseAdapter implements ActionListener, KeyListener{
    FormMenu formMenu;
    Categoria categoria = new Categoria();
    ImageIcon icon = new ImageIcon("src/imagenes/borrar.png");
    JButton editar = new JButton("Editar");
    JButton eliminar = new JButton("Eliminar");
    private String buscar="";
    
    public ContCategoria(FormMenu formMenu) {
        this.formMenu = formMenu;
        formMenu.btnEditarCategoría.addActionListener(this);
        formMenu.btnGuardarCategoria.addActionListener(this);
        formMenu.tablaCategoria.addMouseListener(this);
        formMenu.btnLimpiarBuscador.addActionListener(this);
        formMenu.txtBuscarCategoria.addKeyListener(this);
        //diseño de la tabla categoria.------------------------------------------------------
        editar.setBackground(new Color(23, 162, 184));
        editar.setForeground(Color.white);
        eliminar.setBackground(new Color(220, 53, 69));
        eliminar.setForeground(Color.white);
        eliminar.setBorder(new LineBorder(Color.BLACK));
        editar.setBorder(new LineBorder(Color.BLACK));
        //editar.setBorder(null);
        listarCategoria();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (formMenu.btnGuardarCategoria == e.getSource()) {
            registrarCategoria();
        }
        if (formMenu.btnEditarCategoría == e.getSource()) {
            editarCategoria();
        }
        if (formMenu.btnLimpiarBuscador == e.getSource()) {
            //limpiar caja de texto del buscador
            formMenu.txtBuscarCategoria.setText("");
            buscar = "";
            formMenu.btnLimpiarBuscador.setEnabled(false);
            listarCategoria();
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //evento para la tabla : boton editar o eliminar
        int fila = formMenu.tablaCategoria.rowAtPoint(e.getPoint());
        int columna = formMenu.tablaCategoria.columnAtPoint(e.getPoint());
        String id_categoria = String.valueOf(formMenu.tablaCategoria.getValueAt(fila, 0));
        id_categoria = id_categoria.replace("Cod#", "");
        if (fila > -1 && columna == 2) {
            verCategoria(id_categoria);
        } else if (fila > -1 && columna == 3) {
            eliminarCategoria(id_categoria);
        }
    }
    
    
    
    /*Metodos*/
    public void mensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void editarCategoria() {
        String nombreCategoria = formMenu.txtEditarCategoria.getText();
        if (nombreCategoria.isEmpty()) {
            mensaje("Complete el campo");
        } else {
            int id_categoria = categoria.getId_categoria();
            categoria = new Categoria(id_categoria, nombreCategoria);
            try {
                int editar = categoria.editarCategoria();
                if (editar > 0) {
                    mensaje("Categoria actualizada");
                    formMenu.PanelModalCategoriaMode.setVisible(false);
                    listarCategoria();
                }
            } catch (Exception e) {
                mensaje("error: " + e);
            }
        }
    }
    
    public void registrarCategoria() {
        String txtcategoria = formMenu.txtCategoria.getText();
        if (txtcategoria.isEmpty()) {
            mensaje("Complete el campo");
        } else {
            try {
                int registrarCategoria = this.categoria.registrarCategoria(txtcategoria);
                if (registrarCategoria > 0) {
                    mensaje("Categoria registrada");
                    listarCategoria();
                    formMenu.txtCategoria.setText("");
                }
            } catch (Exception ex) {
                System.out.println("error: " + ex);
            }
        }
    }

    public void listarCategoria() {
        String[] encab = {"Codigo", "Categoria", "Editar", "Eliminar"};
        DefaultTableModel model = new DefaultTableModel(null, encab) {
            @Override
            public boolean isCellEditable(int i, int j) {
                return false;
            }
        };
        formMenu.tablaCategoria.setDefaultRenderer(Object.class, new ModelTable());
        try {
            Object[] datos = new Object[4];

            for (int i = 0; i < categoria.listarCategoria(buscar).size(); i++) {
                datos[0] = "Cod#" + categoria.listarCategoria(buscar).get(i).getId_categoria();
                datos[1] = categoria.listarCategoria(buscar).get(i).getCategoria();
                datos[2] = editar;
                datos[3] = eliminar;
                model.addRow(datos);
            }
            formMenu.tablaCategoria.setModel(model);
            formMenu.tablaCategoria.getColumnModel().getColumn(2).setPreferredWidth(10);
            formMenu.tablaCategoria.getColumnModel().getColumn(3).setPreferredWidth(10);
        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }

    //metodo para eliminar categoria 
    public void eliminarCategoria(String id_categoria) {
        int resultado = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar la categoria", "Eliminar Categoria", JOptionPane.YES_NO_OPTION, 0, icon);
        System.out.println(resultado); //[0 es si : 1 es no]
        if (resultado == 0) {
            try {
                int eliminar = categoria.eliminarCategoria(id_categoria);
                if (eliminar == 1) {
                    mensaje("Categoria Eliminada");
                    listarCategoria();
                }
            } catch (Exception e) {
                mensaje("Error: " + e);
            }
        }
    }

    //metodo para abrir modal y ver el dato selecionado
    public void verCategoria(String id_categoria) {
        formMenu.PanelModalCategoriaMode.setVisible(true);
        formMenu.txtBuscarCategoria.setEnabled(false);
        formMenu.txtCategoria.setEnabled(false);
        try {
            categoria = categoria.verCategoria(id_categoria);
            if (categoria != null) {
                formMenu.txtEditarCategoria.setText(categoria.getCategoria());
            }
        } catch (Exception ex) {
            mensaje("error: " + ex);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        buscar = formMenu.txtBuscarCategoria.getText();
        listarCategoria();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!formMenu.txtBuscarCategoria.getText().isEmpty()) {
            formMenu.btnLimpiarBuscador.setEnabled(true);
        } else {
            formMenu.btnLimpiarBuscador.setEnabled(false);
        }
    }
}
/*formMenu.tablaCategoria.getColumnModel().getColumn(2).setPreferredWidth(10);
   formMenu.tablaCategoria.getColumnModel().getColumn(3).setPreferredWidth(10);*/