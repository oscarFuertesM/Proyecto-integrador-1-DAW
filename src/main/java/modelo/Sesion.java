package modelo;

/**
 * Esta clase define los objetos Sesion, unicamente contiene dos enteros, uno para el id y otro para los permisos.
 * Esta clase no contiene metodos, unicamente la utilizamos para generar objetos con estos datos y enviarlos al front-end.
 * @author Oscar Fuertes Munoz
 * @version 0.7
 */

public class Sesion {

	private int id;
	private int permiso;
	
	public Sesion () {
		
	}
	
	/**
	 * Constructor con 2 parametros
	 * @param id ID de la sesion
	 * @param permiso Nivel de permisos de la sesion
	 */
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
