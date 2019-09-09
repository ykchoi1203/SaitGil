package coupleLink.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coupleLink.model.service.CoupleLinkService;
import coupleLink.model.vo.CoupleAuth;
import member.model.dao.MemberDao;
import member.model.service.MemberService;
import member.model.vo.Member;
import notice.model.service.NoticeService;
import notice.model.vo.Notice;
import shareFile.model.service.ShareFileService;
import shareFile.model.vo.ShareFile;

/**
 * Servlet implementation class CoupleLinkServlet
 */
@WebServlet("/linkCouple.co")
public class CoupleLinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CoupleLinkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		
		String partnerName = request.getParameter("partnerName");
		String inputCode = request.getParameter("inputCode");
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName"); 
		int authNo = Integer.parseInt(inputCode);
		
		CoupleAuth ca = new CoupleAuth(); 
		ca.setUserId(userId);
		ca.setUserName(userName);
		ca.setFromName(partnerName);
		ca.setAuthNo(authNo);
		
		int result = new CoupleLinkService().isMatch(ca);
		
		if(result > 0) {
			String cCode = new CoupleLinkService().insertCcode(authNo);
			
			if(cCode != null) {
				Member loginUser = new MemberService().selectMember(userId);
				ShareFile sf = new ShareFileService().selectShareFile(cCode);
				Member partner = new MemberService().selectPartner(cCode, partnerName);
				Notice recentNotice = new NoticeService().selectRecent();
				session.setAttribute("recentNotice", recentNotice);
				session.setAttribute("loginUser", loginUser);
				session.setAttribute("partner", partner);
				session.setAttribute("sf", sf);
				new CoupleLinkService().deleteCoupleAuth(authNo);
				response.getWriter().write(cCode);
			} else {
				int r = new MemberService().deleteCcode(userId);
				response.getWriter().write("fail");
			}
		} else {
			response.getWriter().write("notMatch");
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
