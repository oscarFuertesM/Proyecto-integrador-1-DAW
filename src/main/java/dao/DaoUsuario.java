package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Usuario;

/**
 * Esta es la clase DAO que trabaja con los objetos Usuario, contiene todos los metodos necesarios para llevar a cabo los
 * procedimientos requeridos.
 * @author Oscar Fuertes Munoz
 * @version 0.7
 */

public class DaoUsuario {
	/**
	 * Metodo para conectar a la base de datos
	 */
public static Connection con = null;
	
	public DaoUsuario() throws SQLException {
		
		this.con = ConexionDB.getConexion();
	}
	
	/**
	 * Metodo para insertar objetos Usuario en la base de datos
	 * @param x Objeto Usuario que queremos insertar en la base de datos
	 */
	public void insertar(Usuario x) throws SQLException {
		
		String sql = "INSERT INTO usuarios (nombreUsuario, mailUsuario, permisos) VALUES (?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, x.getNombre());
		ps.setString(2, x.getMail());
		ps.setInt(3, x.getPermiso());
		
		int filas = ps.executeUpdate();
		ps.close();
		
	}
	
	/**
	 * A este metodo le pasamos un usuario que queremos actualizar para insertar la contrasena, que tambien le pasamos, en la base de datos
	 * @param y Usuario que queremos actualizar en la base de datos
	 * @param pass Contrasena que queremos insertar en la base de datos
	 */
	public void insertarPass(Usuario y, String pass) throws SQLException {
		
		String sql = "UPDATE usuarios SET passUsuario = '" + pass + "' WHERE nombreUsuario = '" + y.getNombre() + "' AND mailUsuario = '" + y.getMail() + "'";
		PreparedStatement ps = con.prepareStatement(sql);
		
		int filas = ps.executeUpdate();
		ps.close();
		
	}
	
	/**
	 * Este metodo recibe un objeto Usuario únicamente con el mail y la contrasena a traves de un String, mediante una consulta
	 * SQL comprobamos si el mail y contrasena son correctos y nos devuelve toda la informacion del usuario
	 * @param f Usuario con el mail que queremos comprobar que sea correcto
	 * @param pass Contrasena que queremos comprobar que sea correcta
	 * @return Nos devuelve un objeto Usuario con toda la informacion (ID, nombre, mail, permiso)
	 */
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
