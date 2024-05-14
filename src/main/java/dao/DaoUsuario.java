package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Usuario;

public class DaoUsuario {

public static Connection con = null;
	
	public DaoUsuario() throws SQLException {
		
		this.con = ConexionDB.getConexion();
	}
	
	public void insertar(Usuario x) throws SQLException {
		
		String sql = "INSERT INTO usuarios (nombreUsuario, mailUsuario, permisos) VALUES (?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, x.getNombre());
		ps.setString(2, x.getMail());
		ps.setInt(3, x.getPermiso());
		
		int filas = ps.executeUpdate();
		ps.close();
		
	}
	
	public void insertarPass(Usuario y, String pass) throws SQLException {
		
		String sql = "UPDATE usuarios SET passUsuario = '" + pass + "' WHERE nombreUsuario = '" + y.getNombre() + "' AND mailUsuario = '" + y.getMail() + "'";
		PreparedStatement ps = con.prepareStatement(sql);
		
		int filas = ps.executeUpdate();
		ps.close();
		
	}
	
	public Usuario loging(Usuario f, String pass) throws SQLException {
		
		String sql = "SELECT * FROM usuarios WHERE mailUsuario=? AND passUsuario=?";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, f.getMail());
		ps.setString(2, pass);
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		Usuario aux = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(5));
				
		return aux;		
		
	}
	
	
	
	
	
	
	
	
	
	
}