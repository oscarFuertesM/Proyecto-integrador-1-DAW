package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Pokemon;

/**
 * Esta es la clase DAO que trabaja con los objetos Pokemon, contiene todos los metodos necesarios para llevar a cabo los
 * procedimientos requeridos.
 * @author Oscar Fuertes Munoz
 * @version 0.7
 */

public class DaoPoke {
	
	/**
	 * Metodo para conectar a la base de datos
	 */
	public static Connection con = null;

	public DaoPoke() throws SQLException {

		this.con = ConexionDB.getConexion();

	}
	
	//Variable de instancia para recoger y almacenar todos los Pokemon de la base de datos
	public ArrayList<Pokemon> listar() throws SQLException{
		
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
		
		//System.out.println(pokes.get(3).getNombre());
		
		return pokes;
		
	}
	
	/**
	 * Metodo para pasar todos los objetos Pokemon a formato JSON
	 * @return Nos devuelve el String formado por todos los objetos Pokemon en formato JSON
	 */
	public String listarJson() throws SQLException {
		
		String txtJSON = "";
		
		
		Gson gson = new Gson();
		
		txtJSON = gson.toJson(this.listar());
		
		
		return txtJSON;

	}

	
}
