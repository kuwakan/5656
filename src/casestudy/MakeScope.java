package casestudy;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class ConnectionOrDie
 */
@WebServlet("/MakeScope")
public class MakeScope extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeScope() {
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
		request.setCharacterEncoding("UTF-8");

			ServletContext application = this.getServletContext();
			Beans t = (Beans) application.getAttribute("beans");

			//PasswordOKorNot pON = new PasswordOKorNot();
			//request.setAttribute("pON",pON);

			if(t==null){
				RealData rd = new RealData();
				AircraftSerch acs = new AircraftSerch();
				Beans beans = new Beans();
				beans.setRd(rd);
				beans.setAcs(acs);
				application.setAttribute("beans",beans);
			}else{

			}





			response.sendRedirect("N1/login.jsp");

	}

}
