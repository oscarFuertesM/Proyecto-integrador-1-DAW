package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Movimiento;

/**
 * Esta es la clase DAO que trabaja con los objetos Movimiento, contiene todos los metodos necesarios para llevar a cabo los
 * procedimientos requeridos.
 * @author Oscar Fuertes Munoz
 * @version 0.7
 */

public class DaoMov {
	
	/**
	 * Metodo para conectar a la base de datos
	 */
	public static Connection con = null;

	public DaoMov() throws SQLException {

		this.con = ConexionDB.getConexion();

	}

	//Variable de instancia para recoger y almacenar todos los movimientos de la base de datos
	public ArrayList<Movimiento> listar() throws SQLException {

		String sql = "SELECT * FROM movimientos";

		PreparedStatement ps = con.prepareStatement(sql);

		ResultSet result = ps.executeQuery();

		ArrayList<Movimiento> movs = null;

		while (result.next()) {

			if (movs == null) {
				movs = new ArrayList<Movimiento>();
			}

			movs.add(new Movimiento(result.getInt("id"), result.getString("nombre"), result.getString("tipo")));

		}

		return movs;

	}
	
	/**
	 * Metodo para pasar todos los objetos Movimiento a formato JSON
	 * @return Nos devuelve el String formado por todos los objetos Movimiento en formato JSON
	 */
	public String listarJson() throws SQLException {

		String txtJSON = "";

		Gson gson = new Gson();

		txtJSON = gson.toJson(this.listar());

		return txtJSON;

	}

}
