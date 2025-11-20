package ies.sequeros.com.dam.pmdm.administrador.infraestructura.dependientes;


import ies.sequeros.com.dam.pmdm.administrador.modelo.Dependiente;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.DataBaseConnection;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.IDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DependienteDao implements IDao<Dependiente> {
    private DataBaseConnection conn;
    private final String table_name = "DEPENDIENTE";
    private final String selectall = "select * from " + table_name;
    private final String selectbyid = "select * from " + table_name + " where id=?";
    private final String findbyname = "select * from " + table_name + " where name=?";

    private final String deletebyid = "delete from " + table_name + " where id='?'";
    private final String insert = "INSERT INTO " + table_name + " (id, name, email, password, image_path, enabled, is_admin) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String update =
            "UPDATE " + table_name + " SET name = ?, email = ?, password = ?, image_path = ?, enabled = ?, is_admin = ? " +
                    "WHERE id = ?";
    public DependienteDao() {
    }

    public DataBaseConnection getConn() {
        return this.conn;
    }

    public void setConn(final DataBaseConnection conn) {
        this.conn = conn;
    }

    @Override
    public Dependiente getById(final String id) {
        Dependiente sp = null;// = new Dependiente();
        try {
            final PreparedStatement pst = conn.getConnection().prepareStatement(selectbyid);
            pst.setString(1, id);
            final ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                sp = registerToObject(rs);
            }
            pst.close();
            Logger logger = Logger.getLogger(DependienteDao.class.getName());
            logger.info("Ejecutando SQL: " + selectbyid + " | Parametros: [id=" + id + "]");
            return sp;
        } catch (final SQLException ex) {
            Logger.getLogger(DependienteDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return sp;
    }

    public Dependiente findByName(final String name) {
        Dependiente sp = null;// = new Dependiente();
        try {
            final PreparedStatement pst = conn.getConnection().prepareStatement(findbyname);
            pst.setString(1, name);
            final ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                sp = registerToObject(rs);
            }
            pst.close();
            Logger logger = Logger.getLogger(DependienteDao.class.getName());
            logger.info("Ejecutando SQL: " + findbyname + " | Parametros: [name=" + name + "]");

            return sp;
        } catch (final SQLException ex) {
            Logger.getLogger(DependienteDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return sp;
    }
    @Override
    public List<Dependiente> getAll() {
        final ArrayList<Dependiente> scl = new ArrayList<>();
        Dependiente tempo;
        PreparedStatement pst = null;
        try {
            try {
                pst = conn.getConnection().prepareStatement(selectall);
            } catch (final SQLException ex) {
                Logger.getLogger(DependienteDao.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
            final ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tempo = registerToObject(rs);
                scl.add(tempo);
            }

            pst.close();
            Logger logger = Logger.getLogger(DependienteDao.class.getName());
            logger.info("Ejecutando SQL: " + selectall+ " | Parametros: ");

        } catch (final SQLException ex) {
            Logger.getLogger(DependienteDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

        return scl;
    }

    @Override
    public void update(final Dependiente item) {

        try {
            final PreparedStatement pst =
                    conn.getConnection().prepareStatement(update);
            pst.setString(1, item.getName());
            pst.setString(2, item.getEmail());
            pst.setString(3, item.getPassword());
            pst.setString(4, item.getImagePath());
            pst.setBoolean(5, item.getEnabled());
            pst.setBoolean(6, item.isAdmin());
            pst.setString(7, item.getId());
            pst.executeUpdate();
            pst.close();
            Logger logger = Logger.getLogger(DependienteDao.class.getName());
            logger.info(() ->
                    "Ejecutando SQL: " + update +
                            " | Params: [1]=" + item.getName() +
                            ", [2]=" + item.getEmail() +
                            ", [3]=" + item.getPassword() +
                            ", [4]=" + item.getImagePath() +
                            ", [5]=" + item.getEnabled() +
                            ", [6]=" + item.isAdmin() +
                            ", [7]=" + item.getId() +
                            "]"
            );
        } catch (final SQLException ex) {
            Logger.getLogger(DependienteDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

    }

    @Override
    public void delete(final Dependiente item) {
        try {
            final PreparedStatement pst =
                    conn.getConnection().prepareStatement(deletebyid);
            pst.setString(1, item.getId());
            pst.executeUpdate();
            pst.close();
            Logger logger = Logger.getLogger(DependienteDao.class.getName());
            logger.info("Ejecutando SQL: " + deletebyid + " | Parametros: [id=" + item.getId() + "]");

        } catch (final SQLException ex) {
            Logger.getLogger(DependienteDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    @Override
    public void insert(final Dependiente item) {

        final PreparedStatement pst;
        try {
           pst = conn.getConnection().prepareStatement(insert,
                    Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, item.getId());
            pst.setString(2, item.getName());
            pst.setString(3, item.getEmail());
            pst.setString(4, item.getPassword());
            pst.setString(5,item.getImagePath());
            pst.setBoolean(6, item.getEnabled());
            pst.setBoolean(7, item.isAdmin());

            pst.executeUpdate();
            pst.close();
            Logger logger = Logger.getLogger(DependienteDao.class.getName());
            logger.info(() ->
                    "Ejecutando SQL: " + insert +
                            " | Params: [1]=" + item.getId() +
                            ", [2]="+ item.getName() +
                            ", [3]=" + item.getEmail() +
                            ", [4]=" + item.getPassword() +
                            ", [5]=" + item.getImagePath() +
                            ", [6]=" + item.getEnabled() +
                            ", [7]=" + item.isAdmin() +

                            "]"
            );

        } catch (final SQLException ex) {
            Logger.getLogger(DependienteDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    //pasar de registro a objeeto
    private Dependiente registerToObject(final ResultSet r) {
         Dependiente sc =null;

        try {

            sc=new Dependiente(
                    r.getString("ID"),
                    r.getString("NAME"),
                    r.getString("EMAIL"),
                    r.getString("PASSWORD"),
                    r.getString("IMAGE_PATH"),
                    r.getBoolean("ENABLED"),
                    r.getBoolean("IS_ADMIN"));
            return sc;
        } catch (final SQLException ex) {
            Logger.getLogger(DependienteDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return sc;
    }
}
