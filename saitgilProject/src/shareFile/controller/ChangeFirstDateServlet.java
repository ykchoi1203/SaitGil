package shareFile.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shareFile.model.service.ShareFileService;
import shareFile.model.vo.ShareFile;

/**
 * Servlet implementation class ChangeFirstDateServlet
 */
@WebServlet("/changeDate.sh")
public class ChangeFirstDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeFirstDateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String meetDate = request.getParameter("meetDate");
		String cCode = request.getParameter("cCode"); 

		ShareFile sf = new ShareFileService().changeMeetDate(meetDate,cCode);
		
		if(sf != null) {
			HttpSession session = request.getSession();
			session.setAttribute("sf", sf);
			RequestDispatcher view = request.getRequestDispatcher("views/common/indexLogin.jsp"); 
			view.forward(request, response);
			
		} else {
			request.setAttribute("msg", "커플코트 변경 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
