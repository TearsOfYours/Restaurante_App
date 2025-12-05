package ies.sequeros.com.dam.pmdm.administrador.infraestructura.pedidos;

import ies.sequeros.com.dam.pmdm.administrador.modelo.LineaPedido;
import ies.sequeros.com.dam.pmdm.administrador.modelo.Pedido;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.DataBaseConnection;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.IDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PedidoDao implements IDao<Pedido> {

    private DataBaseConnection conn;
    private final String table_name = "PEDIDO";

    private final String selectAll = "SELECT * FROM " + table_name;
    private final String selectById = "SELECT * FROM " + table_name + " WHERE id=?";
    private final String findByName = "SELECT * FROM " + table_name + " WHERE nombre=?";

    private final String deleteById = "DELETE FROM " + table_name + " WHERE id=?";
    private final String insert =
            "INSERT INTO " + table_name +
                    " (id, nombre, fecha, estado, idCliente, precioTotal) VALUES (?, ?, ?, ?, ?, ?)";

    private final String update =
            "UPDATE " + table_name +
                    " SET nombre=?, fecha=?, estado=?, idCliente=?, precioTotal=? WHERE id=?";

    public PedidoDao() {}

    public DataBaseConnection getConn() {
        return conn;
    }

    public void setConn(final DataBaseConnection conn) {
        this.conn = conn;
    }

    @Override
    public Pedido getById(final String id) {
        Pedido p = null;
        try {
            PreparedStatement pst = conn.getConnection().prepareStatement(selectById);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                p = registerToObject(rs);
            }

            pst.close();

            Logger logger = Logger.getLogger(PedidoDao.class.getName());
            logger.info("Ejecutando SQL: " + selectById + " | Parametros: [id=" + id + "]");

            return p;

        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public Pedido findByName(final String nombre) {
        Pedido p = null;
        try {
            PreparedStatement pst = conn.getConnection().prepareStatement(findByName);
            pst.setString(1, nombre);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                p = registerToObject(rs);
            }

            pst.close();

            Logger logger = Logger.getLogger(PedidoDao.class.getName());
            logger.info("Ejecutando SQL: " + findByName + " | Parametros: [nombre=" + nombre + "]");

            return p;

        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public List<Pedido> getAll() {
        ArrayList<Pedido> lista = new ArrayList<>();
        Pedido temp;
        try {
            PreparedStatement pst = conn.getConnection().prepareStatement(selectAll);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                temp = registerToObject(rs);
                lista.add(temp);
            }

            pst.close();

            Logger logger = Logger.getLogger(PedidoDao.class.getName());
            logger.info("Ejecutando SQL: " + selectAll + " | Parametros: ");

        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public void update(final Pedido p) {
        try {
            PreparedStatement pst = conn.getConnection().prepareStatement(update);

            pst.setString(1, p.getId());
            pst.setString(2, p.getName());
            pst.setDate(3, Date.valueOf(p.getFecha()));
            pst.setString(4, p.getEstado());
            pst.setDouble(5, p.getPrecioTotal());

            pst.executeUpdate();
            pst.close();

            Logger logger = Logger.getLogger(PedidoDao.class.getName());
            logger.info(() ->
                    "Ejecutando SQL: " + update +
                            " | Params: nombre=" + p.getName() +
                            ", fecha=" + p.getFecha() +
                            ", estado=" + p.getEstado() +
                            ", precioTotal=" + p.getPrecioTotal() +
                            ", id=" + p.getId()
            );

        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(final Pedido p) {
        try {
            PreparedStatement pst = conn.getConnection().prepareStatement(deleteById);
            pst.setString(1, p.getId());

            Logger logger = Logger.getLogger(PedidoDao.class.getName());
            logger.info("Ejecutando SQL: " + deleteById + " | Parametros: [id=" + p.getId() + "]");

            pst.executeUpdate();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void insert(final Pedido p) {
        try {
            PreparedStatement pst = conn.getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, p.getId());
            pst.setString(2, p.getName());
            pst.setDate(3, Date.valueOf(p.getFecha()));
            pst.setString(4, p.getEstado());
            pst.setString(5, p.getIdCliente());
            pst.setDouble(6, p.getPrecioTotal());

            pst.executeUpdate();
            pst.close();

            Logger logger = Logger.getLogger(PedidoDao.class.getName());
            logger.info(() ->
                    "Ejecutando SQL: " + insert +
                            " | Params: id=" + p.getId() +
                            ", nombre=" + p.getName() +
                            ", fecha=" + p.getFecha() +
                            ", estado=" + p.getEstado() +
                            ", idCliente=" + p.getId() +
                            ", precioTotal=" + p.getPrecioTotal()
            );

        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Pedido registerToObject(final ResultSet r) {
        Pedido p = null;
        try {
            String pedidoId = r.getString("ID");

            PreparedStatement pstLineas = conn.getConnection().prepareStatement("SELECT * FROM LINEAPEDIDO WHERE idPedido=?");
            pstLineas.setString(1, pedidoId);
            ResultSet rsLineas = pstLineas.executeQuery();

            List<LineaPedido> lineas = new ArrayList<>();
            while (rsLineas.next()) {
                lineas.add(new LineaPedido(
                        rsLineas.getString("idProducto"),
                        rsLineas.getInt("cantidad"),
                        rsLineas.getDouble("precio")
                ));
            }
            rsLineas.close();
            pstLineas.close();

            p = new Pedido(
                    pedidoId,
                    r.getString("NOMBRE"),
                    r.getString("FECHA"),
                    lineas,
                    r.getString("ESTADO"),
                    r.getString("idCliente")
            );
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    public List<Pedido> getByCliente(final String clienteId) {
        List<Pedido> lista = new ArrayList<>();
        try {
            PreparedStatement pst = conn.getConnection().prepareStatement(
                    "SELECT * FROM PEDIDO WHERE idCliente=?"
            );
            pst.setString(1, clienteId);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                lista.add(registerToObject(rs));
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
