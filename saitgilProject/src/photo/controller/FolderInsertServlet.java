package photo.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import photo.model.service.FolderService;
import photo.model.vo.Attachment;
import photo.model.vo.Folder;
import common.MyFileRenamePolicy;
import member.model.vo.Member;


/**
 * Servlet implementation class PhotoInsertSevlet
 */
@WebServlet("/fInsert.ph")
public class FolderInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FolderInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			request.setCharacterEncoding("utf-8");
			HttpSession session = request.getSession();
			
			Member loginUser = (Member)session.getAttribute("loginUser");
			String writer = loginUser.getUserId();
			String cCode = loginUser.getcCode();

			
		if(ServletFileUpload.isMultipartContent(request)) {
			
			
			int maxSize = 1024 * 1024 * 10;
			
			
			String resources = request.getSession().getServletContext().getRealPath("/resources");
			
			System.out.println(resources);
			String savePath = resources + "/uploadImages/";
			

			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			ArrayList<String> changeFiles = new ArrayList<>();
			ArrayList<String> originFiles = new ArrayList<>();
			
			Enumeration<String> files = multiRequest.getFileNames();
			
			while(files.hasMoreElements()) {
				
				String name = files.nextElement();	
				
				if(multiRequest.getFilesystemName(name) != null) {	
					
				
					String changeName = multiRequest.getFilesystemName(name);
					
					String originName = multiRequest.getOriginalFileName(name);
					
					changeFiles.add(changeName);
					originFiles.add(originName);
				}
				
			}
				
			String title = multiRequest.getParameter("title");
			System.out.println(title);
							
			Folder f = new Folder();
			f.setfName(title);
//			f.setbContent(content);
//			f.setbWriter(bWriter);

			
			
			
			ArrayList<Attachment> fileList = new ArrayList<>();
			
			
			for(int i=originFiles.size()-1; i>=0; i--) {
				
				Attachment at = new Attachment();
				at.setFilePath(savePath);
				at.setOriginName(originFiles.get(i));
				at.setChangeName(changeFiles.get(i));
				
				
				
				fileList.add(at);
				f.setMainAttachment(fileList.get(0).getChangeName());
			}
			
			f.setcCode(cCode);
			f.setWriter(writer);
			int result = new FolderService().insertFolder(f, fileList);
	
			if(result > 0) {

				//request.getRequestDispatcher("list.ph").forward(request, response);
			}else {
				
				
				for(int i=0; i<changeFiles.size(); i++) {
					
					
					File failedFile = new File(savePath + changeFiles.get(i));
					failedFile.delete();
					
				}

				request.setAttribute("msg", "�궗吏� 寃뚯떆�뙋 �벑濡� �떎�뙣!!");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
