package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Esta clase se encarga de facilitarnos la vida, aquí escribimos la conexion a la base de datos y en las otras clases DAO
 * unicamente tenemos que llamar a esta clase y nos ahorramos tener que escribir esto cada vez que nos queramos conectar.
 * @author Oscar Fuertes Munoz
 * @version 0.7
 */

public class ConexionDB {

	public static final String JDBC_URL = "jdbc:mysql://localhost:3306/pokemon";
	public static Connection instance = null;

	public static Connection getConexion() throws SQLException {
		
		if (instance == null) {
			
			Properties props = new Properties();
			props.put("user", "root");
			props.put("password", "");
			props.put("charset", "UTF-8");


			instance = DriverManager.getConnection(JDBC_URL, props);
		} 
		
		return instance;

	}
}
