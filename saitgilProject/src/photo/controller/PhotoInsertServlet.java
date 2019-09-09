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

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import common.MyFileRenamePolicy;
import photo.model.service.FolderService;
import photo.model.vo.Attachment;
import photo.model.vo.Folder;

/**
 * Servlet implementation class PhtoInsert2Servlet
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			
			
			if(ServletFileUpload.isMultipartContent(request)) {
				
				int maxSize = 1024 * 1024 * 10;
				
				String resources = request.getSession().getServletContext().getRealPath("/resources");
				
				String savePath = resources + "/uploadImages/";
				
				MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
				
				int fId = Integer.parseInt(multiRequest.getParameter("fId"));
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
				
				ArrayList<Attachment> fileList = new ArrayList<>();
				
				for(int i=originFiles.size()-1; i>=0; i--) {
					
					Attachment at = new Attachment();
					at.setFilePath(savePath);
					at.setOriginName(originFiles.get(i));
					at.setChangeName(changeFiles.get(i));
					
					
					
					fileList.add(at);
				}
				
				int result = new FolderService().insertAttachment(fId, fileList);
				
				if(result > 0) {
					response.sendRedirect("detail.ph?fId=" + fId);
				}else {
					
					for(int i=0; i<changeFiles.size(); i++) {
						
						File failedFile = new File(savePath + changeFiles.get(i));
						failedFile.delete();
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
