package dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoNoticia {

	public static Connection con = null;

	public DaoNoticia() throws SQLException {

		this.con = ConexionDB.getConexion();

	}
	
	
	
	
	
	
	
	
	
}


