package modelo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import dao.DaoUsuario;

/**
 * Esta clase define objetos Usuario, que contienen dos enteros (id, permiso) y dos String (nombre, mail). Tenemos los métodos 
 * que llaman a la clase DAO para insertar los nuevos usuarios en la base de datos. Tambien tenemos el metodo para  
 * encriptar las contrasenas y otro metodo que llama al DAO para insertar la contrasena sin almacenarla en ningun objeto. 
 * Por ultimo tenemos un metodo para comprobar los datos de los usuarios al loguearse.
 * @author Oscar Fuertes Munoz
 * @version 0.7
 */

public class Usuario {

	private int id;
	private String nombre;
	private String mail;
	private int permiso;
	
	//Permiso 1 = usuario normal
	//Permiso 7 o mayor = usuario admin
	
	//La idea por ahora es que todos los usuarios que se registren
	//sean usuarios normales, y desde la base de datos nosotros
	//gestionamos que usuario es admin.
	
	public Usuario() {
		
	}
	
	/**
	 * Constructor con 3 parametros
	 * @param nombre Nombre del usuario
	 * @param mail Mail del usuario
	 * @param permiso Nivel de permisos del usuario
	 */
	public Usuario(String nombre, String mail, int permiso) {
		super();
		this.nombre = nombre;
		this.mail = mail;
		this.permiso = permiso;
	}
	
	/**
	 * Constructor con 4 parametros
	 * @param id ID del usuario
	 * @param nombre Nombre del usuario
	 * @param mail Mail del usuario
	 * @param permiso Nivel de permisos del usuario
	 */
	public Usuario(int id, String nombre, String mail, int permiso) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.mail = mail;
		this.permiso = permiso;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getPermiso() {
		return permiso;
	}

	public void setPermiso(int permiso) {
		this.permiso = permiso;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", mail=" + mail + ", permiso=" + permiso + "]";
	}
	
	/**
	 * A este metodo le pasamos las contrasenas con un String, las encripta y nos las devuelve encriptadas.
	 * @param input Contrasena que queremos encriptar
	 * @return Contrasena encriptada
	 */
	public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
	/**
	 * Este metodo llama a la clase DAO junto a su metodo "insertar"
	 */
	public void insertarUsuario() throws SQLException {
		DaoUsuario dao = new DaoUsuario();
		dao.insertar(this);
	}
	
	/**
	 * Este metodo llama a la clase DAO junto a su metodo "insertarPass"
	 * @param pass Pasamos la contrasena que queremos insertar en la base de datos
	 */
	public void insertarPass(String pass) throws SQLException {
		DaoUsuario dao = new DaoUsuario();
		dao.insertarPass(this, pass);
	}
	
	/**
	 * Con este metodo comprobamos los datos de inicio de sesion, si son correctos devolvemos un boolean true junto a los datos del usuario.
	 * Este metodo llama a la clase DAO y a su metodo "loging", le pasamos un objeto Usuario vacio y la contrasena del usuario que 
	 * esta iniciando sesion. Si el metodo del DAO nos devuelve los datos de un usuario los asignamos al objeto que hemos creado
	 * previamente.
	 * @param pass Contrasena del usuario que esta iniciando sesion
	 * @return El usuario existe y los datos de inicio de sesion son correctos
	 */
	public boolean login(String pass) throws SQLException {
		
		boolean ok = false;
		
		DaoUsuario dao = new DaoUsuario();
		Usuario aux = dao.loging(this, pass);
		
		if(aux != null) {
			ok = true;
			this.setId(aux.getId());
			this.setNombre(aux.getNombre());
			this.setMail(aux.getMail());
			this.setPermiso(aux.getPermiso());
			
		}
		
		return ok;
		
	}
	
	
	
	
}
