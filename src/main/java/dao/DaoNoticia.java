package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.Noticia;

public class DaoNoticia {

	public static Connection con = null;

	public DaoNoticia() throws SQLException {

		this.con = ConexionDB.getConexion();

	}
	
	
	public void insertarnoti(Noticia n) throws SQLException {
		
		String sql= "INSERT INTO noticias (titulo, texto) VALUES (?,?)";
 		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,n.getTitulo());
		ps.setString(2,n.getTexto());
		
		int filas = ps.executeUpdate();
		ps.close();
		
	}
	
	
	
	
	
	
}


