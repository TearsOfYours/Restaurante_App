package ies.sequeros.com.dam.pmdm.commons.infraestructura;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseConnection {
    private String config_path;
    private String connection_string;
    private Connection conexion;

    public DataBaseConnection() {
    }
    public void open() throws Exception {
        FileReader fr = null;
        File f = new File(System.getProperty("user.dir") +
                this.getConfig_path());
        fr = new FileReader(f);
        Properties props = new Properties();
        try {
            props.load(fr);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        String url = props.getProperty("database.path");
        String user = props.getProperty("database.user");
        String password = props.getProperty("database.password");
        // + ";user=" + user + ";password=" + password;
        //this.conexion =
        // DriverManager.getConnection(this.connection_string);

        this.connection_string = url;
        this.conexion = DriverManager.getConnection(connection_string, user, password);
    }

    public Connection getConnection() {
        return this.conexion;
    }
    public void close() throws SQLException {
            if (conexion != null) {
                conexion.close();
                conexion = null;
            }
        }


    public String getConfig_path() {
        return config_path;
    }
    public void setConfig_path(String config_path) {
        this.config_path = config_path;
    }
}
