package member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import common.MyFileRenamePolicy;
import member.model.vo.*;

import member.model.service.MemberService;

/**
 * Servlet implementation class ChangeProfileServlet
 */
@WebServlet("/updateProfile.me")
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member loginUser = (Member)request.getSession().getAttribute("loginUser");
		String isDelete = (String)request.getParameter("isDelete");
		
		response.setContentType("text/html; charset=UTF-8");
		
		if(isDelete != null) {
			int result = new MemberService().updateProfile(isDelete, loginUser.getUserId());
			response.getWriter().write("success"); 
			return;
		}
	

		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024 * 1024 * 10;
			String resources = request.getSession().getServletContext().getRealPath("/resources");
			String savePath = resources + "/profile/";
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			ArrayList<String> changeFiles = new  ArrayList<>();
			ArrayList<String> originFiles = new ArrayList<>();
			
			Enumeration<String> files = multiRequest.getFileNames();
			
			while(files.hasMoreElements()) {
				String name = files.nextElement();  //files에 담겨있는 파일 하나씩 뽑아내기 
				System.out.println("name : "  + name);
				
				if(multiRequest.getFilesystemName(name) != null ) {  //파일이 null이 아닌경우 
					
					//수정명 
					String changeName = multiRequest.getFilesystemName(name); 
					//원본명 
					String originName = multiRequest.getOriginalFileName(name);
					
					changeFiles.add(changeName);
					originFiles.add(originName);
					
				}	
			}
			
			//dao로 보내서 바꿔줘야함..
			String path = "profile/" + changeFiles.get(0);
			int result = new MemberService().updateProfile(path, loginUser.getUserId());

			if(result > 0) {
				loginUser.setProfilePic(path);
				response.getWriter().write(path); 
			} else {
				response.getWriter().write("fail"); 
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
