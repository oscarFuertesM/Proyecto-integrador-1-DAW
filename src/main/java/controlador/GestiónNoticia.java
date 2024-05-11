package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Noticia;

import java.io.IOException;
import java.sql.SQLException;

import dao.DaoNoticia;
import dao.DaoPoke;

/**
 * Servlet implementation class GestiónNoticia
 */
public class GestiónNoticia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestiónNoticia() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		try {
			
			//PrintWriter out = response.getWriter();
		
			DaoNoticia dao = new DaoNoticia();
			
			String listaNoticia = dao.listarJson();
			
			
			response.getWriter().append(listaNoticia);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String titulo = request.getParameter("titulo");
		String texto = request.getParameter("contenido");
		//int id = Integer.parseInt(request.getParameter("id"));
		
		String idParametro = request.getParameter("id");
		int id = 0; 

		if (idParametro != null && !idParametro.isEmpty()) {
		    id = Integer.parseInt(idParametro);
		}
		Noticia n1 = new Noticia(id, titulo, texto);
		//Noticia n1 = new Noticia(titulo, texto);

		//System.out.println(n1.toString());
		
		try {
			if(id == 0) {
				n1.insertar();
			}else {
				n1.setIdNoti(id);
				n1.editar();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("insertarnoticias.html");
		
	}

}
