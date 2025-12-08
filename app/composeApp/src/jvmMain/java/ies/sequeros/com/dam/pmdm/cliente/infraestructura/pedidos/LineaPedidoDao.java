package ies.sequeros.com.dam.pmdm.cliente.infraestructura.pedidos;

import ies.sequeros.com.dam.pmdm.administrador.modelo.LineaPedido;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.DataBaseConnection;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.IDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LineaPedidoDao implements IDao<LineaPedido> {
    private DataBaseConnection conn;
    private final String table_name = "LINEAPEDIDO";

    private final String selectAll = "SELECT * FROM " + table_name;
    private final String selectById = "SELECT * FROM " + table_name + " WHERE id=?";
    private final String selectByPedido = "SELECT * FROM " + table_name + " WHERE idPedido=?";
    private final String insert = "INSERT INTO " + table_name + " (id, idPedido, idProducto, precio, cantidad, estado) VALUES (?, ?, ?, ?, ?, ?)";
    private final String update = "UPDATE " + table_name + " SET idPedido=?, idProducto=?, precio=?, cantidad=?, estado=? WHERE id=?";
    private final String deleteById = "DELETE FROM " + table_name + " WHERE id=?";

    public void setConn(DataBaseConnection conn) {
        this.conn = conn;
    }

    @Override
    public LineaPedido getById(String id) {
        LineaPedido lp = null;
        try {
            PreparedStatement pst = conn.getConnection().prepareStatement(selectById);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                lp = registerToObject(rs);
            }
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(LineaPedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lp;
    }

    public List<LineaPedido> getByPedido(String idPedido) {
        List<LineaPedido> lista = new ArrayList<>();
        try {
            PreparedStatement pst = conn.getConnection().prepareStatement(selectByPedido);
            pst.setString(1, idPedido);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                lista.add(registerToObject(rs));
            }
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(LineaPedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public List<LineaPedido> getAll() {
        List<LineaPedido> lista = new ArrayList<>();
        try {
            PreparedStatement pst = conn.getConnection().prepareStatement(selectAll);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                lista.add(registerToObject(rs));
            }
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(LineaPedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public void insert(LineaPedido lp) {
        try {
            PreparedStatement pst = conn.getConnection().prepareStatement(insert);
            pst.setString(1, lp.getId());
            pst.setString(2, lp.getIdPedido());
            pst.setString(3, lp.getIdProducto());
            pst.setDouble(4, lp.getPrecioUnitario());
            pst.setInt(5, lp.getCantidad());
            pst.setString(6, lp.getEstado());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(LineaPedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(LineaPedido lp) {
        try {
            PreparedStatement pst = conn.getConnection().prepareStatement(update);
            pst.setString(1, lp.getIdPedido());
            pst.setString(2, lp.getIdProducto());
            pst.setDouble(3, lp.getPrecioUnitario());
            pst.setInt(4, lp.getCantidad());
            pst.setString(5, lp.getEstado());
            pst.setString(6, lp.getId());

            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(LineaPedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(LineaPedido lp) {
        try {
            PreparedStatement pst = conn.getConnection().prepareStatement(deleteById);
            pst.setString(1, lp.getId());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(LineaPedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private LineaPedido registerToObject(ResultSet rs) throws SQLException {
        return new LineaPedido(
                rs.getString("id"),
                rs.getString("idPedido"),
                rs.getString("idProducto"),
                rs.getInt("cantidad"),
                rs.getDouble("precio"),
                rs.getString("estado"));

    }
}

