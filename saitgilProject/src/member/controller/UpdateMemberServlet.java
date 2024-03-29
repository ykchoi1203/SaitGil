package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class UpdateMemberServlet
 */
@WebServlet("/update.me")
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Member loginUser = (Member) request.getSession().getAttribute("loginUser");
		String userId = loginUser.getUserId(); 
		String userPwd = loginUser.getUserPwd();
		String userName = loginUser.getUserName();
		String birth = request.getParameter("birth");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String address = request.getParameter("address");
		
		System.out.println(userId + userPwd + userName + birth);
		
		Member mem = new Member(userId, userPwd, userName, birth, phone, email, gender, address);
		
		int result  = new MemberService().updateMember(mem);
		
		if(result > 0) {
			Member updateMem = new MemberService().selectMember(userId);
			if(updateMem != null) {
				request.getSession().setAttribute("loginUser", updateMem);
				request.getSession().setAttribute("msg", "성공적으로 회원정보를 수정했습니다.");
				request.getRequestDispatcher("views/member/myPage.jsp").forward(request, response);
				System.out.println("수정 완료");
			} else {
				request.setAttribute("msg", "회원 정보 수정후 불러오기에 실패했습니다.");
				RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");  
				view.forward(request, response);
			}
		} else {
			request.setAttribute("msg", "회원 정보 수정에 실패했습니다.");
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");  
			view.forward(request, response);
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
