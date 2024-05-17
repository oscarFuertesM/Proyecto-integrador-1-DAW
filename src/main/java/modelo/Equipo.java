package modelo;

import java.sql.SQLException;

import dao.DaoEquipo;

public class Equipo {

	private int idEquipo;
	private String datosEquipo;
	
	public Equipo() {
		
	}

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
	
	public int insertarEquipo() throws SQLException {
		DaoEquipo dao = new DaoEquipo();
		return dao.insertarEquipo(this);
		
	}
	
	public void relacionUsuarioEquipo(int idUsuario, int idEquipo) throws SQLException {
		DaoEquipo dao = new DaoEquipo();
		dao.relacionUsuarioEquipo(idUsuario, idEquipo);
	}
	
	public void listarEquiposUsuario(int idUsuario) throws SQLException {
		DaoEquipo dao = new DaoEquipo();
		dao.listarEquiposUsuario(idUsuario);
	}
	
	
	
}
