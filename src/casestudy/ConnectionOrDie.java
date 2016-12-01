package casestudy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class ConnectionOrDie
 */
@WebServlet("/ConnectionOrDie")
public class ConnectionOrDie extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectionOrDie() {
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



			RealData rd = new RealData();
			AircraftSerch acs = new AircraftSerch();
			Beans beans = new Beans();
			beans.setRd(rd);
			beans.setAcs(acs);

			HttpSession session = request.getSession();
	        session.setAttribute("beans",beans);



		RequestDispatcher dispatcher = request.getRequestDispatcher("/ConnectionOrDie2");
		dispatcher.forward(request, response);

	}

}
