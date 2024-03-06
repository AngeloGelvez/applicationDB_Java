/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MiCrud implements Crud {

    Constantes config = new Constantes();
    private PreparedStatement consultas;
    private String consultaSQL;

    @Override
    public ArrayList<Producto> consultarTodo() {
        ArrayList<Producto> misProductos = new ArrayList<>();

        try {
            consultaSQL = "SELECT * FROM producto;";

            consultas = config.getMiConexion().prepareStatement(consultaSQL);
            ResultSet fila = consultas.executeQuery();

            while(fila.next()) {
                Producto miProducto = new Producto();
                miProducto.setId(Integer.valueOf(fila.getObject(1).toString()));
                miProducto.setNombre(fila.getObject(2).toString());
                miProducto.setCantidad(Integer.valueOf(fila.getObject(3).toString()));
                miProducto.setMarca(fila.getObject(4).toString());
                miProducto.setPrecio(Double.valueOf(fila.getObject(5).toString()));
                
                misProductos.add(miProducto);
            }
            
            config.getMiConexion().close();
        } catch (SQLException ex) {
            Logger.getLogger(MiCrud.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en consultar: " + ex);
        }

        return misProductos;
    }

    @Override
    public Producto consultarPorId(Producto p) {
        Producto miProducto = new Producto();

        try {
            consultaSQL = "SELECT * FROM producto WHERE id_producto = ?;";
            consultas = config.getMiConexion().prepareStatement(consultaSQL);
            consultas.setInt(1, p.getId());
            ResultSet producto = consultas.executeQuery();
            
            while (producto.next()) {                
                miProducto.setId(Integer.valueOf(producto.getObject(1).toString()));
                miProducto.setNombre(producto.getObject(2).toString());
                miProducto.setCantidad(Integer.valueOf(producto.getObject(3).toString()));
                miProducto.setMarca(producto.getObject(4).toString());
                miProducto.setPrecio(Double.valueOf(producto.getObject(5).toString()));
            }

            config.getMiConexion().close();
        } catch (SQLException ex) {
            Logger.getLogger(MiCrud.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en consultar Id: " + ex);
        }

        return miProducto;
    }

    @Override
    public String insertar(Producto p) {
        String mensaje = "";

        try {
            consultaSQL = "INSERT INTO producto(nombre_producto,cantidad_producto,marca_producto,precio_producto) VALUES(?,?,?,?);";
            consultas = config.getMiConexion().prepareStatement(consultaSQL);
            consultas.setString(1, p.getNombre());
            consultas.setInt(2, p.getCantidad());
            consultas.setString(3, p.getMarca());
            consultas.setDouble(4, p.getPrecio());
            consultas.execute();
            
            config.getMiConexion().close();
            mensaje = "El producto se ha creado con exito";
        } catch (SQLException ex) {
            Logger.getLogger(MiCrud.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en insercion: " + ex);
            mensaje = "El producto no se ha creado";
        }

        return mensaje;
    }

    @Override
    public String actualizar(Producto p) {
        String mensaje = "";
        
        try {
            consultaSQL = "UPDATE producto SET nombre_producto = ?,cantidad_producto = ?,marca_producto = ?,precio_producto =? WHERE id_producto = ?";
            consultas = config.getMiConexion().prepareStatement(consultaSQL);
            consultas.setString(1, p.getNombre());
            consultas.setInt(2, p.getCantidad());
            consultas.setString(3, p.getMarca());
            consultas.setDouble(4, p.getPrecio());
            consultas.setInt(5, p.getId());
            consultas.execute();

            config.getMiConexion().close();
            mensaje = "El producto se ha actualizado con exito";
        } catch (SQLException ex) {
            Logger.getLogger(MiCrud.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en actualziar: " + ex);
            mensaje = "El producto no se ha actualizado";
        }
        
        return mensaje;
    }

    @Override
    public String eliminar(Producto p) {
        String mensaje = "";
        
        try {
            consultaSQL = "DELETE FROM producto WHERE id_producto = ?;";
            consultas = config.getMiConexion().prepareStatement(consultaSQL);
            consultas.setInt(1, p.getId());
            consultas.execute();

            config.getMiConexion().close();
            mensaje = "El producto se ha eliminado con exito";
        } catch (SQLException ex) {
            Logger.getLogger(MiCrud.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en eliminar: " + ex);
            mensaje = "El producto no se ha eliminado";
        }
        
        return mensaje;
    }

}
