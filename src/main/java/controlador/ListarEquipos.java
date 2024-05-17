package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Equipo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import dao.DaoEquipo;

/**
 * Servlet implementation class ListarEquipos
 */
public class ListarEquipos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       HttpSession sesion;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarEquipos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		sesion = request.getSession(false);
		
		if (sesion == null || sesion.getAttribute("id") == null) {
        	System.out.println("No hay sesión");
        }
		
		int idUsuario = (int)sesion.getAttribute("id");
		
		
		ArrayList<Equipo> arrayE1 = null;
		
		try {
			DaoEquipo e1 = new DaoEquipo();
			
		
			arrayE1 = e1.listarEquiposUsuario(idUsuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 Gson gson = new Gson();
	        String equiposJson = gson.toJson(arrayE1);
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(equiposJson);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
