package modelo;

public class Pokemon {

	private int pokedex;
	private String nombre;
	private String tipo1;
	private String tipo2;
	
	public Pokemon() {
		
	}

	public Pokemon(int id, String nombre, String tipo1, String tipo2) {
		super();
		this.pokedex = id;
		this.nombre = nombre;
		this.tipo1 = tipo1;
		this.tipo2 = tipo2;
	}

	public int getId() {
		return pokedex;
	}

	public void setId(int id) {
		this.pokedex = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo1() {
		return tipo1;
	}

	public void setTipo1(String tipo1) {
		this.tipo1 = tipo1;
	}

	public String getTipo2() {
		return tipo2;
	}

	public void setTipo2(String tipo2) {
		this.tipo2 = tipo2;
	}

	@Override
	public String toString() {
		return "Pokemon [id=" + pokedex + ", nombre=" + nombre + ", tipo1=" + tipo1 + ", tipo2=" + tipo2 + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
}