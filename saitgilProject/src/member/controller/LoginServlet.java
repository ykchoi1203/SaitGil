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
import notice.model.service.NoticeService;
import notice.model.vo.Notice;
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
		
		if( result > 0) {
			Member loginUserAll = new MemberService().loginMember(userId, userPwd);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUserAll);
			
			if(userId.equals("admin")) {
				request.getRequestDispatcher("views/admin/adminMain.jsp").forward(request, response);
			} else {
			
			switch(result) {//0:濡쒓렇�씤�떎�뙣, 1:而ㅽ뵆肄붾뱶O, 2:而ㅽ뵆肄붾뱶X
			case 1: 
				ShareFile sf = new ShareFileService().selectShareFile(loginUserAll.getcCode());
				System.out.println(sf);
				Member partner = new MemberService().selectMember(loginUserAll.getPartnerId());
				System.out.println(partner);
				Notice recentNotice = new NoticeService().selectRecent();
				System.out.println(recentNotice);
				session.setAttribute("recentNotice", recentNotice);
				session.setAttribute("partner", partner);
				session.setAttribute("sf", sf);
				System.out.println(loginUserAll.getUserName() + "");
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
