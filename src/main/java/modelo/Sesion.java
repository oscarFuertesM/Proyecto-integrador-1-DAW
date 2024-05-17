package modelo;

public class Sesion {

	private int id;
	private int permiso;
	
	public Sesion () {
		
	}
	
	public Sesion(int id, int permiso) {
		super();
		this.id = id;
		this.permiso = permiso;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPermiso() {
		return permiso;
	}

	public void setPermiso(int permiso) {
		this.permiso = permiso;
	}

	@Override
	public String toString() {
		return "Sesion [id=" + id + ", permiso=" + permiso + "]";
	}
	
	
	
	
	
	
}
