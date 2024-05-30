package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Equipo;

/**
 * Esta es la clase DAO que trabaja con los objetos Equipo, contiene todos los metodos necesarios para llevar a cabo los
 * procedimientos requeridos.
 * @author Oscar Fuertes Munoz
 * @version 0.7
 */

public class DaoEquipo {
	
/**
 * Metodo para conectar a la base de datos
 */
public static Connection con = null;
	
	public DaoEquipo() throws SQLException {
		
		this.con = ConexionDB.getConexion();
	}
	
	/**
	 * Metodo para insertar un equipo en la base de datos a traves de una sentencia SQL
	 * @param e Equipo que queremos insertar en la base de datos
	 * @return Nos devuelve el ID que ha generado la base de datos al insertar el equipo que le hemos pasado
	 */
	public int insertarEquipo(Equipo e) throws SQLException {
		//System.out.println("hola"+e);
		String sql = "INSERT INTO equipos(datosEquipo) VALUES (?)";
		PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, e.getDatosEquipo());
		
		int filas = ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		return rs.getInt(1);
	}
	
	/**
	 * Metodo para insertar en la tabla que relaciona usuarios con equipos en la base de datos, insertamos ID del usuario e ID del equipo, la base de datos nos genera un ID para esta relacion
	 * @param idUsuario ID del usuario que queremos relacionar con el equipo
	 * @param idEquipo ID del equipo que queremos relacionar con el usuario
	 */
	public void relacionUsuarioEquipo(int idUsuario, int idEquipo) throws SQLException {
		
		String sql = "INSERT INTO usuarioequipos (idUsuario, idEquipo) VALUES (?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idUsuario);
		ps.setInt(2, idEquipo);
		
		ps.executeUpdate();
		
	}
	
	//Variable de instancia para almacenar y listar los equipos que tiene un usuario
	public ArrayList<Equipo> listarEquiposUsuario(int idUsuario) throws SQLException {
		
        ArrayList<Equipo> e7 = new ArrayList<>();
        
        String sql = "SELECT equipos.idEquipo, equipos.datosEquipo FROM equipos " +
                     "JOIN usuarioequipos ON equipos.idEquipo = usuarioequipos.idEquipo " +
                     "WHERE usuarioequipos.idUsuario = ?";
        
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
        	
            Equipo equipo = new Equipo();
            equipo.setIdEquipo(rs.getInt("idEquipo"));
            equipo.setDatosEquipo(rs.getString("datosEquipo"));
            e7.add(equipo);
            
        }
        return e7;
    }
	
	
	
	
	
}
