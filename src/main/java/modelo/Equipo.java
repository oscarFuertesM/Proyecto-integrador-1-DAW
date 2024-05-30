package modelo;

import java.sql.SQLException;

import dao.DaoEquipo;

/**
 * Esta clase define objetos Equipo, contiene un entero (idEquipo) y un String (datosEquipo). 
 * @author Oscar Fuertes Munoz
 * @version 0.7
 */

public class Equipo {

	private int idEquipo;
	private String datosEquipo;
	
	public Equipo() {
		
	}
	
	/**
	 * Constructor con 2 parametros
	 * @param idEquipo ID del equipo
	 * @param datosEquipo Datos del equipo pokemon (pokemons y movimientos)
	 */
	public Equipo(int idEquipo, String datosEquipo) {
		super();
		this.idEquipo = idEquipo;
		this.datosEquipo = datosEquipo;
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getDatosEquipo() {
		return datosEquipo;
	}

	public void setDatosEquipo(String datosEquipo) {
		this.datosEquipo = datosEquipo;
	}

	@Override
	public String toString() {
		return "Equipo [idEquipo=" + idEquipo + ", datosEquipo=" + datosEquipo + "]";
	}
	
	/**
	 * Metodo que llama a la clase DaoEquipo y su metodo "insertarEquipo" nos devuelve el ID que le ha generado la base de datos.
	 * @return El metodo "dao.insertarEquipo" nos devuelve el ID que le genera en la base de datos, desde aqui lo devolvemos ese ID
	 */
	public int insertarEquipo() throws SQLException {
		DaoEquipo dao = new DaoEquipo();
		return dao.insertarEquipo(this);
		
	}
	
	/**
	 * Metodo que llama a la clase DaoEquipo y su metodo "relacionUsuarioEquipo"
	 * @param idUsuario ID del usuario que ha guardado el equipo
	 * @param idEquipo ID del equipo que ha guardado el usuario
	 */
	public void relacionUsuarioEquipo(int idUsuario, int idEquipo) throws SQLException {
		DaoEquipo dao = new DaoEquipo();
		dao.relacionUsuarioEquipo(idUsuario, idEquipo);
	}
	
	/**
	 * Metodo que llama a la clase DaoEquipo y su metodo "listarEquiposUsuario"
	 * @param idUsuario ID del usuario que queremos listar los equipos guardados en la base de datos
	 */
	public void listarEquiposUsuario(int idUsuario) throws SQLException {
		DaoEquipo dao = new DaoEquipo();
		dao.listarEquiposUsuario(idUsuario);
	}
	
	
	
}
