package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;

/**
 * Servlet implementation class AdminAnswerInquiryServlet
 */
@WebServlet("/updateAnswer.ad")
public class AdminAnswerInquiryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAnswerInquiryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String answer = request.getParameter("answer");
		System.out.println(answer);
		int qNo = Integer.parseInt(request.getParameter("pNo"));
		
		int result = new AdminService().answerInquiry(qNo, answer);
		
		if(result > 0) {
			request.setAttribute("msg", "등록에 성공하였습니다");
			response.sendRedirect("qList.ad");
		} else {
			request.setAttribute("msg", "등록에 실패하였습니다");
			response.sendRedirect("qList.ad");
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
