package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Noticia;

public class DaoNoticia {

	public static Connection con = null;

	public DaoNoticia() throws SQLException {

		this.con = ConexionDB.getConexion();

	}
	
	//insertar noticias
	public void insertarnoti(Noticia n) throws SQLException {
		
		String sql= "INSERT INTO noticias (titulo, texto) VALUES (?,?)";
 		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,n.getTitulo());
		ps.setString(2,n.getTexto());
		
		int filas = ps.executeUpdate();
		ps.close();
		
	}
	
	private ArrayList<Noticia> listanoti() throws SQLException{
		
		String sql = "SELECT * FROM noticias";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet result = ps.executeQuery();
		
		ArrayList<Noticia> noticias = null;
		
		
		while(result.next()) {
			
			if(noticias == null) {
				noticias = new ArrayList<Noticia>();
			}
			
			noticias.add(new Noticia(result.getInt("idnoticias"), result.getString("titulo"), result.getString("texto")));
			//System.out.println(noticias);
		}
		
		return noticias;
	}
	
		public String listarJson() throws SQLException {
		
		String txtJSON = "";
		
		
		Gson gson = new Gson();
		
		txtJSON = gson.toJson(this.listanoti());
		
		return txtJSON;
		
	}
	
		
		public Noticia obtenerPorId(int id) throws SQLException {
			
			String sql = "SELECT * FROM noticias WHERE idnoticias=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			Noticia p = new Noticia(rs.getInt(1),rs.getString(2),rs.getString(3));
			
			return p;
		}
	
	
		public void editar(Noticia n) throws SQLException {
			String sql = "UPDATE noticias SET titulo = ?, texto = ? WHERE idnoticias = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, n.getTitulo());
			ps.setString(2, n.getTexto());
			ps.setInt(3, n.getIdNoti());
			int filas = ps.executeUpdate();
			ps.close();
			
		}
	
	
}


