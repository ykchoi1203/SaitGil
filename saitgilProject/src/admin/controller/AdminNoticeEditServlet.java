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
 * Servlet implementation class AdminEditNoticeServlet
 */
@WebServlet("/editNotice.ad")
public class AdminNoticeEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminNoticeEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nNo = Integer.parseInt(request.getParameter("nNo"));
		String nTitle = request.getParameter("nTitle");
		String nContent = request.getParameter("nContent");
		nContent.replace("\r\n", "<br>");
		
		Notice n = new Notice();
		n.setNoticeNo(nNo);
		n.setNoticeTitle(nTitle);
		n.setNoticeContent(nContent);
		
		int result = new AdminService().editNotice(n);
		
		if(result>0) {
			request.setAttribute("msg", "수정에 성공하였습니다");
			response.sendRedirect("nList.ad");
		} else {
			request.setAttribute("msg", "수정에 실패하였습니다");
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
