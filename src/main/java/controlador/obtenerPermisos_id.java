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
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.google.gson.Gson;
import modelo.Sesion;  // Asegúrate de que la clase Sesion está correctamente definida en tu modelo.

public class obtenerPermisos_id extends HttpServlet {
    private static final long serialVersionUID = 1L;
    HttpSession sesion;

    public obtenerPermisos_id() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0; 
        int permiso = 0; 
        HttpSession sesion = request.getSession(false); 

        if (sesion != null && sesion.getAttribute("id") != null) {
            id = (int) sesion.getAttribute("id"); 
            permiso = sesion.getAttribute("permiso") != null ? (int) sesion.getAttribute("permiso") : 0;
        }

        Sesion datosSesion = new Sesion(id, permiso);
        Gson json = new Gson();
        response.setContentType("application/json");
        response.getWriter().write(json.toJson(datosSesion));
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
