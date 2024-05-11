package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Movimiento;

public class DaoMov {

	public static Connection con = null;

	public DaoMov() throws SQLException {

		this.con = ConexionDB.getConexion();

	}

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

	public String listarJson() throws SQLException {

		String txtJSON = "";

		Gson gson = new Gson();

		txtJSON = gson.toJson(this.listar());

		return txtJSON;

	}

}
