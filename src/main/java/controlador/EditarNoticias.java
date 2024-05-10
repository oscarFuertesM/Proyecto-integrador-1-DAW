package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Noticia;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class EditarNoticias
 */
public class EditarNoticias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarNoticias() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter out = response.getWriter();
		
		String idNotiSt = request.getParameter("id");
		//int idNoti = Integer.parseInt(request.getParameter("idNoti"));
		System.out.println(idNotiSt +"hola");
		Noticia p = new Noticia();
		System.out.println(idNotiSt +"hola2");
		if (idNotiSt != null) {
			
			int idNoti = Integer.parseInt(idNotiSt);
			System.out.println(idNoti);
		try {
			p.datosBD(idNoti);
			out.print(p.dameJson());
			System.out.println("hola");
			System.out.println(p.dameJson());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  } else {
		  System.out.println("Ha fallado");
	  }
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String titulo = request.getParameter("titulo");
		String texto = request.getParameter("contenido");
		int id = Integer.parseInt(request.getParameter("id"));
		
		Noticia n1 = new Noticia(id, titulo, texto);
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
		
		response.sendRedirect("noticias.html");
		
	}
		
		
	}


