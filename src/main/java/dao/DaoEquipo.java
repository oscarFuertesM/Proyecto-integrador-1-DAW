package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Equipo;

public class DaoEquipo {

public static Connection con = null;
	
	public DaoEquipo() throws SQLException {
		
		this.con = ConexionDB.getConexion();
	}
	
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
	
	
	public void relacionUsuarioEquipo(int idUsuario, int idEquipo) throws SQLException {
		
		String sql = "INSERT INTO usuarioequipos (idUsuario, idEquipo) VALUES (?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idUsuario);
		ps.setInt(2, idEquipo);
		
		ps.executeUpdate();
		
	}
	
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
