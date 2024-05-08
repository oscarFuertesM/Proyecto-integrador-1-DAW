package modelo;

import java.sql.SQLException;

import dao.DaoNoticia;

public class Noticia {

	private int idNoti;
	private String titulo;
	private String texto;
	
	public Noticia(){
		
	}
	
	//Constructor con ID para cuando pidamos datos a la BD
	public Noticia(int idNoti, String titulo, String texto) {
		super();
		this.idNoti = idNoti;
		this.titulo = titulo;
		this.texto = texto;
	}

	//Constructor sin ID para insertar datos a la BD, la BD le asigna un ID
	public Noticia(String titulo, String texto) {
		super();
		this.titulo = titulo;
		this.texto = texto;
	}

	public int getIdNoti() {
		return idNoti;
	}

	public void setIdNoti(int idNoti) {
		this.idNoti = idNoti;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public String toString() {
		return "Noticia [idNoti=" + idNoti + ", titulo=" + titulo + ", texto=" + texto + "]";
	}
	
	//insertar noticia
	public void insertar() throws SQLException {
		DaoNoticia dao = new DaoNoticia();
		dao.insertarnoti(this);
	}
	
	
	
	
	
	
}
