package modelo;

/**
 * Esta clase define objetos Movimiento, contiene un entero (id) y dos String (nombre, tipo). No tiene métodos, unicamente
 * la utilizamos para crear los objetos y llevarlos al front.
 * @author Oscar Fuertes Munoz
 * @version 0.7
 */

public class Movimiento {

	private int id;
	private String nombre;
	private String tipo;
	
	public Movimiento() {
		
	}
	
	/**
	 * Constructor con 3 parametros
	 * @param id ID del movimiento
	 * @param nombre Nombre del movimiento
	 * @param tipo Tipo del movimiento
	 */
	public Movimiento(int id, String nombre, String tipo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Movimiento [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + "]";
	}
	
	
	
	
	
	
}
