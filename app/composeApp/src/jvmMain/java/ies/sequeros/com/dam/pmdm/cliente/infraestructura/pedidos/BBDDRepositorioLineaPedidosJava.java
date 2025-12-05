package ies.sequeros.com.dam.pmdm.cliente.infraestructura.pedidos;

import ies.sequeros.com.dam.pmdm.administrador.modelo.LineaPedido;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.DataBaseConnection;

import java.sql.SQLException;
import java.util.List;

public class BBDDRepositorioLineaPedidosJava {
    private final DataBaseConnection db;
    private final LineaPedidoDao dao;

    public BBDDRepositorioLineaPedidosJava(DataBaseConnection connection) {
        this.db = connection;
        dao = new LineaPedidoDao();
        dao.setConn(this.db);
    }

    public void add(LineaPedido lp){
        dao.insert(lp);
    }

    public boolean remove(LineaPedido lp){
        dao.delete(lp);
        return true;
    }

    public boolean remove(String id){
        LineaPedido lp = dao.getById(id);
        if(lp != null){
            dao.delete(lp);
            return true;
        }
        return false;
    }

    public boolean update(LineaPedido lp){
        dao.update(lp);
        return true;
    }

    public List<LineaPedido> getAll(){
        return dao.getAll();
    }

    public LineaPedido getById(String id){
        return dao.getById(id);
    }

    public List<LineaPedido> getByPedido(String idPedido){
        return dao.getByPedido(idPedido);
    }

    public void close(){
        try {
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
