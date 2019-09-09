package shareFile.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shareFile.model.service.ShareFileService;

/**
 * Servlet implementation class CutCoupleServlet
 */
@WebServlet("/cutCouple.sf")
public class CutCoupleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CutCoupleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cCode = request.getParameter("cCode");
		
		int result = new ShareFileService().deleteCoupleCode(cCode); 
		
		if(result > 0) {
			request.getSession().invalidate();
			request.getSession().setAttribute("msg", "커플 연동이 끊어졌습니다. 다시 로그인 해주세요.");
			request.getRequestDispatcher("/").forward(request, response);;
		} else {
			request.setAttribute("msg", "커플 연동끊기 실패! 둘이 평생 만나야만 합니다 ");
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
