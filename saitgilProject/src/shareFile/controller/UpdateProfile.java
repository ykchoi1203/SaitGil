package shareFile.controller;

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
import member.model.service.MemberService;
import member.model.vo.*;
import shareFile.model.service.ShareFileService;
import shareFile.model.vo.*;

/**
 * Servlet implementation class UpdateProfile
 */
@WebServlet("/updateProfile.co")
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ShareFile sf = (ShareFile)request.getSession().getAttribute("sf");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024 * 1024 * 10;
			String resources = request.getSession().getServletContext().getRealPath("/resources");
			String savePath = resources + "/shareFile/";
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			ArrayList<String> changeFiles = new  ArrayList<>();
			ArrayList<String> originFiles = new ArrayList<>();
			
			Enumeration<String> files = multiRequest.getFileNames();
			
			while(files.hasMoreElements()) {
				String name = files.nextElement();  //files에 담겨있는 파일 하나씩 뽑아내기 
				
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
			String cCode = ((Member)request.getSession().getAttribute("loginUser")).getcCode();
			String changeName = changeFiles.get(0);
			
			int result = new ShareFileService().updateProfile(changeName, cCode);
			
			if(result > 0) {
				((ShareFile)request.getSession().getAttribute("sf")).setcPicturePath(changeName);
				response.getWriter().write(changeName); 
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
