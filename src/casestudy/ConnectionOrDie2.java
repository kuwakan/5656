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
 * Servlet implementation class ConnectionOrDie2
 */
@WebServlet("/ConnectionOrDie2")
public class ConnectionOrDie2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectionOrDie2() {
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



		//リクエスト取得

		String inYesOrNo = request.getParameter("inYesOrNo");
		String xYesOrNo = request.getParameter("xYesOrNo");

		response.setContentType("text/html;charset=UTF-8");



		HttpSession session = request.getSession();
		if(inYesOrNo.equals("1")){
	        Beans t = (Beans) session.getAttribute("beans");

			t.getRd().doin = true;
			t.getRd().start();

		}else if(inYesOrNo.equals("0")){

			Beans t = (Beans) session.getAttribute("beans");
			t.getRd().doin=false;
			RealData rd = new RealData();
			t.setRd(rd);

		}


		if(xYesOrNo.equals("1")){
			Beans t = (Beans) session.getAttribute("beans");

			t.getAcs().dox = true;
			t.getAcs().start();
		}else if(xYesOrNo.equals("0")){
			Beans t = (Beans) session.getAttribute("beans");
			t.getAcs().dox=false;
			AircraftSerch acs = new AircraftSerch();
			t.setAcs(acs);
		}






		RequestDispatcher dispatcher = request.getRequestDispatcher("/dbconnection.jsp");
		dispatcher.forward(request, response);

	}

}
