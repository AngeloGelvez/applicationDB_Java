/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logic;

import java.util.List;

/**
 *
 * @author angel
 */
public interface Crud {
    
    //metodos crud que se van a implementar
    public List<Producto> consultarTodo();
    public Producto consultarPorId(Producto p);
    public String insertar(Producto p);
    public String actualizar(Producto p);
    public String eliminar(Producto p);
}
