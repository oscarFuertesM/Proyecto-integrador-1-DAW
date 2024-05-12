package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       HttpSession sesion;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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

		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		
		Usuario f = new Usuario();
		f.setMail(mail);
		
		String hash = Usuario.getMD5(pass);

		
		
		//proteccion
		
		try {
			if (f.login(hash)) {
				
				sesion = request.getSession();
				
				sesion.setAttribute("id", f.getId());
				sesion.setAttribute("permiso", f.getPermiso());
				
				response.sendRedirect("planner.html");
			}else {
				
				response.sendRedirect("login.html");
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	
	}

}
