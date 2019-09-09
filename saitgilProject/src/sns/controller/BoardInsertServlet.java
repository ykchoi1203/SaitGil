package sns.controller;

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
import sns.model.service.SnsService;
import sns.model.vo.Board;
import sns.model.vo.Photo;

/**
 * Servlet implementation class BoardInsertServlet
 */
@WebServlet("/insert.bo")
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
request.setCharacterEncoding("UTF-8");
		
		// �뤌 �쟾�넚�쓣 multipart/form-data 濡� �쟾�넚�븯�뒗 寃쎌슦 �떎瑜� 諛⑹떇�쑝濡� 媛� 異붿텧
		// �뙆�씪 �뾽濡쒕뱶瑜� �쐞�븳 �씪�씠釉뚮윭由� : cos.jar    (com.orelilly.servlet�쓽 �빟�옄)
		// http://www.servlets.com
		
		// enctype�씠 multipart/form-data濡� �쟾�넚�릺�뿀�뒗吏� �솗�씤!
		if(ServletFileUpload.isMultipartContent(request)) {
			System.out.println("�븘�땲 �솢 �븞�릺吏�?");
			// 1. �쟾�넚�맂 �뙆�씪�뱾�쓣 媛�吏�怨� �옉�뾽�븷�궡�슜(�쟾�넚�뙆�씪 �슜�웾 �젣�븳, ���옣�맆 寃쎈줈)
			
			// 1_1. �쟾�넚�뙆�씪 �슜�웾 �젣�븳 : 10Mbyte濡� �젣�븳 �븳 寃쎌슦
			//		1Kbyte = 1024byte
			//		1Mbyte = 1024Kbyte = 1024 * 1024 byte
			//		10Mbyte = 1024 * 1024 * 10 byte
			int maxSize = 1024 * 1024 * 10;
			
			// 1_2. �쎒 �꽌踰� 而⑦뀒�씠�꼫 寃쎈줈(WebContent)/resources 寃쎈줈 異붿텧
			String resources = request.getSession().getServletContext().getRealPath("/resources");
			
//			System.out.println(resources);
			
			// �븳 �뤃�뜑�뿉�뒗 珥� 65000媛쒓퉴吏��쓽 �뙆�씪留� ���옣 媛��뒫�븯�떎.
			// �뤃�뜑蹂꾨줈 援щ텇�쓣 �빐�넃�뒗寃� 醫뗭쓬
			
			// 1_3. �뙆�씪�씠 �떎�젣濡� ���옣�맆 寃쎈줈(resources/thumbnail_uploadFiles)
			String savePath = resources + "/snsImages/";
			
//			System.out.println(savePath);
			
			/*
			 * 2. �뙆�씪紐� �닔�젙 諛� ���옣 �옉�뾽
			 * 
			 * HttpServletRequest --> MultipartRequest濡� 蹂�寃�
			 * 
			 * MultipartRequest multiRequest 
			 * 	= new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
			 * 
			 * �쐞�쓽 MultipartRequest 媛앹껜 �깮�꽦怨� �룞�떆�뿉 �뾽濡쒕뱶�븳 �뙆�씪�뱾�씠 �꽌踰꾩뿉 �뾽濡쒕뱶 �맂�떎.
			 * --> 利�, 臾몄젣媛� �엳�뱺 �뾾�뱺 媛꾩뿉 �슦�꽑 �꽌踰꾩뿉 �뾽濡쒕뱶 �맂�떎.
			 * --> 留뚯빟�뿉 臾몄젣媛� �엳�쓣 寃쎌슦 �뾽濡쒕뱶�맂 �뙆�씪�쓣 �궘�젣�떆耳쒖빞�맖
			 * 
			 * �궗�슜�옄媛� �삱由� �뙆�씪紐� 洹몃�濡� ���옣�븯吏� �븡�뒗寃� �씪諛섏쟻!!!
			 * - 媛숈� �뙆�씪紐낆씠 �엳�쓣 寃쎌슦 �뜮�뼱�뵆�썙吏� �닔�룄 �엳�떎.
			 * - �븳湲�濡� �맂 �뙆�씪紐�, �듅�닔湲고샇�굹 �쓣�뼱�벐湲� �벑�� �꽌踰꾩뿉 �뵲�씪 臾몄젣媛� �깮湲몄닔�룄 �엳�떎.
			 * 
			 * DefaultFileRenamePolicy �뒗 cos.jar�븞�뿉 議댁옱�븯�뒗 �겢�옒�뒪
			 * �쐞�쓽 multiRequest媛앹껜 �깮�꽦 �떆 DefaultFileRenamePolicy�겢�옒�뒪�쓽 rename硫붿냼�뱶媛� �떎�뻾�릺硫댁꽌 �뙆�씪紐� �닔�젙 �맖
			 * 媛숈� �뙆�씪紐낆씠 議댁옱�븯硫� �뙆�씪紐� �뮘�뿉 移댁슫�똿�맂 �닽�옄瑜� 遺숈뿬以��떎.
			 * ex) aaa.zip, aaa1.zip, aaa2.zip
			 * 
			 * �슦由щ뒗 吏곸젒 rename �븷爰쇱엫
			 * --> common�뙣�궎吏��뿉 MyFileRenamePolicy �겢�옒�뒪 留뚮뱾�옄!!
			 * 
			 */
			

			// 2_1. MultipartRequest 媛앹껜 �깮�꽦�븯湲�
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			// --> �씠�닚媛� �꽌踰꾩뿉 �뙆�씪 �뾽濡쒕뱶�맖
			String isPublic = multiRequest.getParameter("isPublic");
			System.out.println(isPublic);
			// 2_2. DB�뿉 ���옣�븯湲� �쐞�빐 change_name怨� origin_name 媛곴컖�쓽 ArrayList 留뚮뱾�뼱以꾧볼�엫 --> �떎以묓뙆�씪�씠湲� �븣臾�
			
			// �떎�젣濡� ���옣�맂 �뙆�씪�쓽 �씠由�(�닔�젙紐�)�쓣 ���옣�븷 ArrayList
			ArrayList<String> changeFiles = new ArrayList<>();
			
			
			// �뤌�뿉�꽌 �쟾�넚�맂 �뙆�씪 由ъ뒪�듃�뱾 諛쏆븘�삤湲�
			Enumeration<String> files = multiRequest.getFileNames();	// �쟾�넚�맂 �뿭�닚�쑝濡� �떞寃⑥엳�떎.
			
			while(files.hasMoreElements()) {
				
				String name = files.nextElement();	// files�뿉 �떞寃⑥엳�뒗 �뙆�씪 �븯�굹�뵫 戮묒븘�궡湲�
				if(multiRequest.getFilesystemName(name) != null) {
					
					// �닔�젙紐� : getFilesystemName()
					String changeName = multiRequest.getFilesystemName(name);
					
					changeFiles.add(changeName);
				}
			}
			
			// 3_1. �뙆�씪 �쇅�뿉 �궡�슜, �옉�꽦�옄 諛쏆븘�삤湲� --> Board 媛앹껜 �깮�꽦
			String writer = multiRequest.getParameter("writer");	// user_id
			String content = multiRequest.getParameter("content");
			String cCode = multiRequest.getParameter("cCode");
			
			Board b = new Board();
			b.setUserId(writer);
			b.setbContent(content);
			b.setcCode(cCode);
			b.setIsPublic(isPublic);
			
			// 3_2. Attachment �뀒�씠釉붿뿉 媛� �궫�엯�븷 寃껊뱾 �옉�뾽�븯湲� --> Attachment 媛앹껜�뱾�쓣 �떞�쓣 由ъ뒪�듃
			
			ArrayList<Photo> fileList = new ArrayList<>();
			
			for(int i = changeFiles.size()-1; i>=0; i--) {
				
				Photo p = new Photo();
				p.setPhotoPath(savePath);
				p.setChangeName(changeFiles.get(i));
				
				fileList.add(p);
			}
			
			// 4. �궗吏� 寃뚯떆�뙋 �옉�꽦�슜 �꽌鍮꾩뒪 �슂泥�(board 媛앹껜, 泥⑤��뙆�씪 由ъ뒪�듃 �쟾�떖)
			int result = new SnsService().insertBoard(b, fileList);
			
			if(result > 0) {
				response.sendRedirect("list.bo");
			}else {
				
				// ���옣�맂 �궗吏� �궘�젣
				for(int i=0; i<changeFiles.size(); i++) {
					
					// �궘�젣�븷 �뙆�씪 媛앹껜 �깮�꽦
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
