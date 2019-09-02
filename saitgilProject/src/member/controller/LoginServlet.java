package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;
import shareFile.model.service.ShareFileService;
import shareFile.model.vo.ShareFile;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="LoginServlet", urlPatterns="/login.me")
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
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId"); 
		String userPwd = request.getParameter("userPwd");
		
		int result = new MemberService().loginPage(userId, userPwd);
		System.out.println(result);
		
		if( result > 0) {
			Member loginUserAll = new MemberService().loginMember(userId, userPwd);
			HttpSession session = request.getSession();
			if(userId.equals("admin")) {
				RequestDispatcher view = request.getRequestDispatcher("views/admin/adminMain.jsp"); 
				view.forward(request, response);

			}else {
			
				session.setMaxInactiveInterval(1200);
				session.setAttribute("loginUser", loginUserAll);
				
				switch(result) {//0:로그인실패, 1:커플코드O, 2:커플코드X
				case 1: 
					ShareFile sf = new ShareFileService().selectShareFile(loginUserAll.getcCode());
					Member partner = new MemberService().selectMember(loginUserAll.getPartnerId());
					session.setAttribute("partner", partner);
					session.setAttribute("sf", sf);
					RequestDispatcher view = request.getRequestDispatcher("views/common/indexLogin.jsp"); 
					view.forward(request, response);
					
					break;
				case 2 : 
					request.getRequestDispatcher("views/member/invitationPage.jsp").forward(request, response);
					break;
				}
			}
		} else {
			request.setAttribute("msg", "로그인 실패");
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
