package modelo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import dao.DaoUsuario;

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
	
	

	public Usuario(String nombre, String mail, int permiso) {
		super();
		this.nombre = nombre;
		this.mail = mail;
		this.permiso = permiso;
	}

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
	
	public void insertarUsuario() throws SQLException {
		DaoUsuario dao = new DaoUsuario();
		dao.insertar(this);
	}
	
	public void insertarPass(String pass) throws SQLException {
		DaoUsuario dao = new DaoUsuario();
		dao.insertarPass(this, pass);
	}
	
	
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
