package coupleLink.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import common.MyFileRenamePolicy;
import member.model.vo.Member;
import shareFile.model.service.ShareFileService;
import shareFile.model.vo.ShareFile;

/**
 * Servlet implementation class UpdateCoupleInfoServlet
 */
@WebServlet("/updateInfo.co")
public class UpdateCoupleInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCoupleInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		

		if(ServletFileUpload.isMultipartContent(request)) {
			HttpSession session = request.getSession();
			int maxSize = 1024 * 1024 * 10;
			String resources = request.getSession().getServletContext().getRealPath("/resources");
			String savePath = resources + "/shareFile/";
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());


			ShareFile sf = new ShareFile();
	
			String cCode = multiRequest.getParameter("cCode");
			String meetDate = multiRequest.getParameter("meetDate");
			sf.setcCode(cCode);
			System.out.println(sf.getcCode());
			
			Enumeration<String> files = multiRequest.getFileNames();
			
			while(files.hasMoreElements()) {
				String name = files.nextElement();  //files에 담겨있는 파일 하나씩 뽑아내기 
				
				if(multiRequest.getFilesystemName(name) != null ) {  //파일이 null이 아닌경우 
					
					//수정명 
					String changeName = multiRequest.getFilesystemName(name); 
					//원본명 
					String originName = multiRequest.getOriginalFileName(name);
					

					sf.setcPicturePath(changeName);
					int result = new ShareFileService().updateCcode(sf, meetDate);
					
					if(result>0) {
						((ShareFile)request.getSession().getAttribute("sf")).setcPicturePath(changeName);
						request.getRequestDispatcher("views/common/indexLogin.jsp").forward(request, response);
					} else {
						request.setAttribute("msg", "커플 연동 실패");
						//커플코드 삭제하기 
						request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
