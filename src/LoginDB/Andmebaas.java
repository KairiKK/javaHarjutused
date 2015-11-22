package LoginDB;

import java.sql.*;

/**
 * Created by kairkall on 22.11.15.
 */
public class Andmebaas {
    Connection conn;

    public Andmebaas() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:login2.db");
            System.out.println("Andmebaas on ühendatud");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLClientInfoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void sulgeYhendus() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean kasKasutajaOlemas(String kasutajaNimi ) {
        String sql = String.format("SELECT USERNAME FROM USER " + "WHERE USERNAME = '%s'", kasutajaNimi);
        boolean olemas = kaivitaKasutajaOlemasToiming(sql);
        return false;
    }


    private boolean kaivitaKasutajaOlemasToiming(String sql) {
        try {
            Statement stat = conn.createStatement();
            ResultSet tulemus = stat.executeQuery(sql);

            String username = tulemus.getString("USERNAME");

            tulemus.close();
            stat.close();


            return true;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void looTabel() {
        try {
            Statement stat = conn.createStatement();
            String sql = "CREATE TABLE USER (ID INT AUTO_INCREMENT);" +
                    "USERNAME TEXT, PASSWORD TEXT, FULLNAME TEXT," +
                    "ADDRESS TEXT);";
            stat.executeUpdate(sql);
            stat.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registreeri(String kasutajaNimi, String parool) {
        try {
            Statement stat = conn.createStatement();
            String sql = String.format("INSERT INTO USER " +
                    "(USERNAME, PASSWORD) VALUES ('%s', '%s')", kasutajaNimi, parool);
            stat.executeUpdate(sql);
            stat.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}



