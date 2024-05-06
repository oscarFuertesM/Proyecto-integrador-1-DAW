package modelo;

public class Movimiento {

	private int id;
	private String nombre;
	private String tipo;
	
	public Movimiento() {
		
	}

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
