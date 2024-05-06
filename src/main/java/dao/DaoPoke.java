package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Pokemon;

public class DaoPoke {
	
	

	public static Connection con = null;

	public DaoPoke() throws SQLException {

		this.con = ConexionDB.getConexion();

	}
	
	
	private ArrayList<Pokemon> listar() throws SQLException{
		
		String sql = "SELECT * FROM pokes";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet result = ps.executeQuery();
		
		ArrayList<Pokemon> pokes=null;
		
		while(result.next()) {
			
			if(pokes==null) {
				pokes=new ArrayList<Pokemon>();
			}
			
			pokes.add(new Pokemon(result.getInt("pokedex"), result.getString("nombre"), result.getString("tipo1"), result.getString("tipo2")));
			
		}
		return pokes;
		
	}
	
public String listarJson() throws SQLException {
		
		String txtJSON = "";
		
		
		Gson gson = new Gson();
		
		txtJSON = gson.toJson(this.listar());
		
		
		return txtJSON;
		
		
		
		
		
	}

	
}
