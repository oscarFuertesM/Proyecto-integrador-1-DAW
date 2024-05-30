package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Noticia;

/**
 * Esta es la clase DAO que trabaja con los objetos Noticia, contiene todos los
 * metodos necesarios para llevar a cabo los procedimientos requeridos.
 * 
 * @author Oscar Fuertes Munoz
 * @version 0.7
 */

public class DaoNoticia {

	/**
	 * Metodo para conectar a la base de datos
	 */
	public static Connection con = null;

	public DaoNoticia() throws SQLException {

		this.con = ConexionDB.getConexion();

	}

	/**
	 * Metodo para insertar objetos noticia en la base de datos a traves de una
	 * sentencia SQL
	 * 
	 * @param n Noticia que queremos insertar en la base de datos
	 */
	public void insertarnoti(Noticia n) throws SQLException {

		String sql = "INSERT INTO noticias (titulo, texto) VALUES (?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, n.getTitulo());
		ps.setString(2, n.getTexto());

		int filas = ps.executeUpdate();
		ps.close();

	}

	// Variable de instancia para recoger y almacenar todas las noticias de la base de datos
	private ArrayList<Noticia> listanoti() throws SQLException {

		String sql = "SELECT * FROM noticias";

		PreparedStatement ps = con.prepareStatement(sql);

		ResultSet result = ps.executeQuery();

		ArrayList<Noticia> noticias = null;

		while (result.next()) {

			if (noticias == null) {
				noticias = new ArrayList<Noticia>();
			}

			noticias.add(
					new Noticia(result.getInt("idnoticias"), result.getString("titulo"), result.getString("texto")));
			// System.out.println(noticias);
		}

		return noticias;
	}

	/**
	 * Metodo para pasar todos los objetos Noticia a formato JSON
	 * @return Nos devuelve el String formado por todos los objetos Noticia en formato JSON
	 */
	public String listarJson() throws SQLException {

		String txtJSON = "";

		Gson gson = new Gson();

		txtJSON = gson.toJson(this.listanoti());

		return txtJSON;

	}
	
	/**
	 * A este metodo le pasamos la id de una noticia y nos devuelve toda su informacion de la base de datos
	 * @param id ID de la noticia que queremos recoger toda su informacion de la base de datos
	 * @return Nos devuelve un objeto Noticia con toda su informacion: ID, titulo y texto
	 */
	public Noticia obtenerPorId(int id) throws SQLException {

		String sql = "SELECT * FROM noticias WHERE idnoticias=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		rs.next();

		Noticia p = new Noticia(rs.getInt(1), rs.getString(2), rs.getString(3));

		return p;
	}
	
	/**
	 * Este metodo recibe un objeto noticia y actualiza la informacion en la base de datos de la noticia con el mismo ID
	 * @param n Objeto Noticia que vamos a editar
	 */
	public void editar(Noticia n) throws SQLException {
		String sql = "UPDATE noticias SET titulo = ?, texto = ? WHERE idnoticias = ?";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, n.getTitulo());
		ps.setString(2, n.getTexto());
		ps.setInt(3, n.getIdNoti());
		int filas = ps.executeUpdate();
		ps.close();
	}

	/**
	 * Este metodo recibe un ID de la noticia que deseamos borrar de la base de datos
	 * @param id ID de la noticia que queremos borrar
	 */
	public void borrar(int id) throws SQLException {
		String sql = "DELETE FROM noticias WHERE idnoticias=?";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, id);
		int filas = ps.executeUpdate();
		ps.close();
	}

}
