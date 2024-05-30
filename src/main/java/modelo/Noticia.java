package modelo;

import java.sql.SQLException;

/**
 * Esta clase define objetos Noticia que contienen un entero(id) y dos String(titulo, texto). A su vez tenemos los metodos necesarios para
 * insertar, listar, modificar y borrar noticias.
 * @author: Oscar Fuertes Muñoz.
 * @version: v 0.7
 */

import com.google.gson.Gson;

import dao.DaoNoticia;
/**
 * Esta clase define los objetos Noticia, contiene un entero (idNoti) y dos String (titulo, texto).
 * @author Oscar Fuertes Munoz
 * @version 0.7
 */
public class Noticia {

	private int idNoti;
	private String titulo;
	private String texto;
	
	public Noticia(){
		
	}
	
	/**
	 * Constructor con ID para cuando pidamos datos a la BD
	 * @param idNoti ID de la noticia
	 * @param titulo Titulo de la noticia
	 * @param texto Contenido de la noticia
	 */
	public Noticia(int idNoti, String titulo, String texto) {
		super();
		this.idNoti = idNoti;
		this.titulo = titulo;
		this.texto = texto;
	}

	/**
	 * Constructor sin ID para cuando insertamos datos a la BD
	 * @param titulo Titulo de la noticia
	 * @param texto Contenido de la noticia
	 */
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
	
	/**
	 * Metodo que llama a la clase DaoNoticia y su metodo "insertarnoti"
	 */
	public void insertar() throws SQLException {
		DaoNoticia dao = new DaoNoticia();
		dao.insertarnoti(this);
	}
	
	/**
	 * Metodo que llama a la clase DaoNoticia y su metodo "obtenerPorId", el metodo de la clase DAO nos devuelve informacion de una
	 * noticia de la base de datos, nosotros le asignamos esa informacion a un objeto Noticia.
	 * @param idNoti Le pasamos este parametro para poder identificar la noticia en la base de datos.
	 */
	public void datosBD(int idNoti) throws SQLException {
		DaoNoticia dao = new DaoNoticia();
		Noticia aux = dao.obtenerPorId(idNoti);
		
		this.setIdNoti(aux.getIdNoti());
		this.setTitulo(aux.getTitulo());
		this.setTexto(aux.getTexto());
		
	}
	
	/**
	 * Metodo para pasar a formato JSON la informacion que le digamos.
	 * @return json Este return, nos devuelve la informacion que le hayamos dado en formato JSON.
	 */	
	public String dameJson() {
		String json = "";
		Gson gson = new Gson();
		
		json = gson.toJson(this);
		return json;
	}
	
	/**
	 * Metodo que llama a la clase DaoNoticia y su metodo "editar"
	 */
	public void editar() throws SQLException {
		DaoNoticia dao = new DaoNoticia();
		dao.editar(this);
	}
	
	/**
	 * Metodo que llama a la clase DaoNoticia y su metodo "borrar"
	 * @param id Le pasamos el id de la noticia que queremos borrar por parametros.
	 */
	public void borrar(int id) throws SQLException {
		DaoNoticia dao = new DaoNoticia();
		dao.borrar(id);
	}
	
	
	
	
	
	
	
}
