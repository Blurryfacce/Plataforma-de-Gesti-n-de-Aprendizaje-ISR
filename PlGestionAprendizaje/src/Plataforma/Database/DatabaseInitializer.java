package Plataforma.Database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initializeDatabase(){
        String scriptPath = "PlGestionAprendizaje/src/Plataforma/Database/schema.sql";
        try (Connection conne = DatabaseConnection.connect();
            BufferedReader reader = new BufferedReader(new FileReader(scriptPath));
            Statement stmt = conne.createStatement()){
            
            StringBuilder sql = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sql.append(line).append("\n");
            }
            stmt.executeUpdate(sql.toString());
            System.out.println("\t\nBase de datos inicializada correctamente!");
        } catch (Exception e) {
            System.out.println("\n\tError al inicializar la base de datos: "+ e.getMessage());
        }
    }
}
