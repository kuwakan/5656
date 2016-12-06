package casestudy;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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


			ServletContext application = this.getServletContext();
			Beans t = (Beans) application.getAttribute("beans");



			request.setCharacterEncoding("UTF-8");


			PasswordOKorNot pON = new PasswordOKorNot();

		    String pass = request.getParameter("pass");




		    if (pass.equals("system")) {
		    	t.setLogin(true);
		    	response.sendRedirect("N1/administrator2.jsp");

		    }else{
		    	//t.setLogin(false);
		    	response.sendRedirect("N1/loginFalse.jsp");
		    	pON.setOk(false);
		    }


	}

}
