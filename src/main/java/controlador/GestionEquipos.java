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

import com.google.gson.Gson;

/**
 * Servlet implementation class GestionEquipos
 */
public class GestionEquipos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       HttpSession sesion;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionEquipos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		sesion = request.getSession(false);
			
        if (sesion == null || sesion.getAttribute("id") == null) {
        	System.out.println("No hay sesión");
        }

        int idUsuario = (int)sesion.getAttribute("id");
        
        Gson json = new Gson();
        Equipo e1 = json.fromJson(request.getReader(), Equipo.class);
        
        try {
			int idEquipo = e1.insertarEquipo();
			
			e1.relacionUsuarioEquipo(idUsuario, idEquipo);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        
		}
        
        
        
	}

}
