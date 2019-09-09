package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class AdminInsertNoticeServlet
 */
@WebServlet("/insertNotice.ad")
public class AdminInsertNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminInsertNoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nTitle = request.getParameter("nTitle");
		String nContent = request.getParameter("nContent");
		nContent.replace("\r\n", "<br>");
		
		Notice n = new Notice();
		
		n.setNoticeTitle(nTitle);
		n.setNoticeContent(nContent);
		
		int result = new AdminService().insertNotice(n);
		
		if(result > 0) {
			request.setAttribute("msg", "등록에 성공하였습니다");
			response.sendRedirect("nList.ad");
		} else {
			request.setAttribute("msg", "등록에 실패하였습니다");
			response.sendRedirect("nList.ad");
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
