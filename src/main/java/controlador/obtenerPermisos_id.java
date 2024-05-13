package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Sesion;

import java.io.IOException;

import com.google.gson.Gson;

/**
 * Servlet implementation class obtenerPermisos_id
 */
public class obtenerPermisos_id extends HttpServlet {
	private static final long serialVersionUID = 1L;
       HttpSession sesion;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public obtenerPermisos_id() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		sesion = request.getSession();
		
		int id = (int) sesion.getAttribute("id");
		int permiso;
		
		if (sesion.getAttribute("permiso") == null) {
			
			permiso = 0;
			
		}else {
			
			permiso = (int)sesion.getAttribute("permiso");
			
		}
		
		Sesion Sesion = new Sesion(id, permiso);
		
		String datosSesion = "";
		
		Gson json = new Gson();
		
		datosSesion = json.toJson(Sesion);
		
		response.getWriter().write(datosSesion.toString());
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
