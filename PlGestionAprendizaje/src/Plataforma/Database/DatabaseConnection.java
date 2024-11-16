package Plataforma.Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:PlGestionAprendizaje/src/Plataforma/Database/database.db";

    public static Connection connect(){
        Connection conne = null;
        try {
            conne = DriverManager.getConnection(URL);
            System.out.println("\t\nConexion a SQlite establecida.");
        } catch (Exception e) {
            System.out.println("\t\nError al conectar con la base de datos: " + e.getMessage());
        }
        return conne;
    }
    
}
