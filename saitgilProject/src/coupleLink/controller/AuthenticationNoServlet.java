package coupleLink.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coupleLink.model.service.CoupleLinkService;
import member.model.service.MemberService;
import coupleLink.model.vo.CoupleAuth;
import member.model.vo.Member;
import net.nurigo.java_sdk.examples.Message.ExampleSend;

/**
 * Servlet implementation class AuthenticationNoServlet
 */
@WebServlet("/authNo.co")
public class AuthenticationNoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticationNoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String partnerName = request.getParameter("partnerName");
		String partnerPhone = request.getParameter("partnerPhone"); 
		String fromId = request.getParameter("fromId");
		String fromName = request.getParameter("fromName");
		response.setContentType("text/html; charset=UTF-8");
		
		String partnerId = new CoupleLinkService().selectId(partnerName, partnerPhone);
		
		if(partnerId != null) {
			if(partnerId.equals(fromId)) {
				response.getWriter().write("notAvaliable");
				return;
			}
			
		boolean isCouple = new CoupleLinkService().checkCouple(partnerId);
		
		if(isCouple) {
			response.getWriter().write("fail"); // 전송실패(이미 커플이 존재함 )
			return;
			
		} else {  // 커플이 아니고 싱글이면!(즉, 커플 코드가 없는 회원)
		
			int authNo = new CoupleLinkService().getAuthNo(); 
			//인증번호 문자로 전송하기
			String sendMessage = new ExampleSend().send(authNo, partnerPhone); 
			
			if(sendMessage.equals("완료")) {
				CoupleAuth ca = new CoupleAuth();
				ca.setAuthNo(authNo);
				ca.setUserId(partnerId);
				ca.setUserName(partnerName);
				ca.setFromId(fromId);
				ca.setFromName(fromName);
				System.out.println(ca.getAuthNo());
				int result = new CoupleLinkService().insertCoupleAuth(ca);
				// 1: 전송선공, 0: 전송실패(커플존재), -1(유효한 인증번호)
				if(result == -1) {
					response.getWriter().write("exist"); // 이미 유효한 인증번호 존재 
				}
				else if(result > 0) {
					response.getWriter().write("success"); // 전송 선공 
				} else { 
					response.getWriter().write("fail"); // 전송실패(이미 커플이 존재함 )
				}
		
			   } 
			}
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
