package photoAlbum.controller;

import java.io.File;
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

import photoAlbum.model.service.FolderService;
import photoAlbum.model.vo.Attachment;
import photoAlbum.model.vo.Folder;
import common.MyFileRenamePolicy;


/**
 * Servlet implementation class PhotoInsertSevlet
 */
@WebServlet("/insert.ph")
public class PhotoInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
			request.setCharacterEncoding("utf-8");
			String title = (String)request.getAttribute("title");
			System.out.println(title);
			
		
			
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
				
			
			String name = multiRequest.getParameter("name");			
			
			Folder f = new Folder();
			f.setfName(name);
//			f.setbContent(content);
//			f.setbWriter(bWriter);
			
			
			
			
			
			ArrayList<Attachment> fileList = new ArrayList<>();
			
			
			for(int i=originFiles.size()-1; i>=0; i--) {
				
				Attachment at = new Attachment();
				at.setFilePath(savePath);
				at.setOriginName(originFiles.get(i));
				at.setChangeName(changeFiles.get(i));
				
				
				
				fileList.add(at);
			}
			
			
			int result = new FolderService().insertFolder(f, fileList);
			
			if(result > 0) {
				response.sendRedirect("list.ph");
			}else {
				
				
				for(int i=0; i<changeFiles.size(); i++) {
					
					
					File failedFile = new File(savePath + changeFiles.get(i));
					failedFile.delete();
					
				}
				
				request.setAttribute("msg", "사진 게시판 등록 실패!!");
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
