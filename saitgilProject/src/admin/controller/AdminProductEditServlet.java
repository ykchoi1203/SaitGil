package admin.controller;

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
import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;

import admin.model.service.AdminService;
import common.MyFileRenamePolicy;
import product.model.vo.Product;
import product.model.vo.ProductAttachment;

/**
 * Servlet implementation class AdminProductEditServlet
 */
@WebServlet("/editProduct.ad")
public class AdminProductEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProductEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 폼 전송을 multipart/form-data 로 전송하는 경우 다른 방식으로 값 추출
		// 파일 업로드를 위한 라이브러리 : cos.jar (com.orelilly.servlet의 약자 cos)
		// http://www.servlets.com
		
		// enctype이 multipart-data로 전송되었는지 확인
		if(ServletFileUpload.isMultipartContent(request)) {
			// 1. 전송된 파일을 가지고 작업할 내용 ( 전송파일 용량제한, 저장될 경로)
			
			// 1_1. 전송파일 용량 제한 (10Mbyte로 제한한 경우)
			// 1바이트 단위이므로 1024 * 1024 * 10으로 계산
			int maxSize = 1024*1024*10;
			
			// 1_2. 웹 서버 컨테이너 경로(WebContent)/resources 경로 지정 ( resources에 저장 )
			// request.getSession().getServletContext().getRealPath("/") : WebContent까지의 경로를 알아냄
			String resources = request.getSession().getServletContext().getRealPath("/resources");
			
//			System.out.println(resources);
			// 한 폴더에는 총 65000개 까지의 파일만 저장 가능
			// 폴더별로 구분을 해놓는 것이 좋음
			
			// 1_3. 파일이 실제로 저장될 경로(resources/thumbnail_uploadFile/)
			String savePath = resources + "/product_uploadFiles/";
			
			/*
			 * 2. 파일명 수정 및 저장 작업
			 * 
			 * HttpServletRequest ==> MultipartRequest로 변경
			 * 
			 * MultipartRequest multiRequest 
			 * = new MultiPartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
			 * 
			 * 위의 MultiPartRequest 객체 생성과 동시에 업로드한 파일들이 서버에 업로드됨
			 * --> 문제가 있든, 없든지 간에 우선 서버에 업로드됨
			 * --> 문제가 생기는 경우 업로드된 파일을 삭제해야함.
			 * 
			 * 사용자가 올린 파일명 그대로 저장하지 않는게 일반적.
			 * - 같은 파일명이 있을경우 덮어씌워질 수 있기 때문
			 * - 한글로된 파일명, 특수기호, 띄어쓰기같은 경우 서버에 따라 문제가 생길 수 있다.
			 * 
			 * DefaultFileRenamePolicy 는 cos.jar에 존재하는 클래스
			 * 위의 multiRequest 객체 생성 시 DefaultFileRenamePolicy클래스의 rename메소드가 실행되면서 파일명 수정됨.
			 * 같은 파일명이 존재하면 파일명 뒤에 카운팅된 숫자를 붙여준다.
			 * ex) aaa.zip, aaa1.zip, aaa2.zip
			 * 
			 * 우리는 직접 rename 작업을 함
			 * --> common패키지의 MyFileRenamePolicy 클래스 만들기.
			 * */
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// 2_2. DB에 저장하기 위해 change_name과 origin_name 각각의 ArrayList만들기.
			// 실제로 저장된 파일의 이름(수정명)을 저장할 ArrayList
			ArrayList<String> changeFiles = new ArrayList<>();
			ArrayList<String> originFiles = new ArrayList<>();
			
			Enumeration<String> files = multiRequest.getFileNames();	// 전송된 역순으로 저장됨 push pop개념인듯

			while(files.hasMoreElements()) {
				String name = files.nextElement();	//files에 담겨있는 파일 하나씩 뽑기.
				
				if(multiRequest.getFilesystemName(name) != null) {	// (수정된)파일이 null이 아닐경우
					// 수정명 : getFilesystemName()
					String changeName = multiRequest.getFilesystemName(name);
					// 원본명 : getOriginalFileName()
					String originName = multiRequest.getOriginalFileName(name);
					
					changeFiles.add(changeName);
					originFiles.add(originName);
				}
			}
			
			String pName = multiRequest.getParameter("pName");
			int price = Integer.parseInt(multiRequest.getParameter("price"));
			String category = multiRequest.getParameter("category");
			String content = multiRequest.getParameter("content");
			int amount = Integer.parseInt(multiRequest.getParameter("amount"));
			int pNo = Integer.parseInt(multiRequest.getParameter("pNo"));
			
			String[] oldName = multiRequest.getParameter("getoldPic").split(",");
			
			String num1 = multiRequest.getParameter("hid1");
			String num2 = multiRequest.getParameter("hid2");
			String num3 = multiRequest.getParameter("hid3");
			String num4 = multiRequest.getParameter("hid4");

			int[] a = {Integer.parseInt(num1), Integer.parseInt(num2), Integer.parseInt(num3), Integer.parseInt(num4)};
			int[] num = {0,0,0,0};
			int cnt = 0;
			for(int i=0; i<num.length; i++) {
				if(a[i]!=0) {
					if(oldName.length+1<a[i]) {num[cnt] = oldName.length+cnt+1;}
					else {num[cnt] = a[i];}
					cnt++;
				}
			}
			
			Product p = new Product();
			
			p.setpName(pName);
			p.setPrice(price);
			p.setCategory(category);
			p.setContents(content);
			p.setAmount(amount);
			p.setpNo(pNo);
			
			
			
			// 3_2. Attachment 테이블에 값 삽입할 것들 작업하기. --> Attachment 객체들을 담을 리스트
			
			ArrayList<ProductAttachment> fileList = new ArrayList<>();
			
			// 역순으로 담겨져있기 때문
			for(int i=originFiles.size()-1; i>=0; i--) {
				ProductAttachment at = new ProductAttachment();
				
				at.setFilePath(savePath);
				at.setOriginName(originFiles.get(i));
				at.setChangeName(changeFiles.get(i));
				at.setFileLevel(num[originFiles.size()-i-1]);
				
				fileList.add(at);
			}
			System.out.println(fileList.size());
			int result = new AdminService().updateProduct(p, fileList, num, oldName.length);
			
			
			if(result > 0) {
				for(int i=0; i<num.length; i++) {
					if(num[i]!=0 && num[i]<=oldName.length) {
						File failedFile = new File(savePath + oldName[(num[i])-1]);
						failedFile.delete();
					}
				}
				
				response.sendRedirect("pList.ad");
			} else {
				// 서버에 저장된 파일 삭제하기.
				for(int i=0; i<changeFiles.size();i++) {
					// 삭제할 파일 객체 생성
					File failedFile = new File(savePath + changeFiles.get(i));
					failedFile.delete();
				}
				
				request.setAttribute("msg", "상품 등록에 실패했습니다.");
				response.sendRedirect("pList.ad");
			}
			
		} else {
			System.out.println("ㅈㅈ");
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
