package inquiry.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import inquiry.model.dao.InquiryDao;
import inquiry.model.vo.Inquiry;
import notice.model.dao.NoticeDao;
import notice.model.vo.Notice;


public class InquiryService {
	
	
	/** 1. 문의게시판 리스트 불러오기 
	 * @return
	 */
	public ArrayList<Inquiry> selectList(int currentPage, int boardLimit, String userId) { 
		Connection conn = getConnection();
		
		ArrayList<Inquiry> list = new InquiryDao().selectList(conn, currentPage, boardLimit, userId);
		
		close(conn);
		
		return list;
	}
	

	
	/** 2. 문의게시판 상세보기 
	 * @param inquiryNo
	 * @return
	 */
	public Inquiry selectInquiry(int inquiryNo) {
		Connection conn = getConnection();
		
		Inquiry i = new InquiryDao().selectInquiry(conn, inquiryNo); 
		
		if(i !=  null) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return i;
	}
	
	
	/** 문의사항 작성하기
	 * @param n
	 * @return
	 */
	public int insertInquiry(Inquiry i) {
		Connection conn = getConnection(); 
		
		int result = new InquiryDao().insertInquiry(conn, i);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn); 
		} 
		
		close(conn); 
		
		return result;
		 
	}
	
	
	
	/** 문의게시판 수정하기
	 * @param n
	 * @return
	 */
	public int updateInquiry(Inquiry i) {
		Connection conn = getConnection(); 
		
		int result = new InquiryDao().updateInquiry(conn, i); 
		
		if(result > 0) {
			commit(conn); 
		} else {
			rollback(conn); 
		}
		
		close(conn);
		
		return result;
		
		
	}
	
	
	/** 문의 게시판 삭제 
	 * @param 
	 * @return
	 */
	public int deleteInquiry(int inquiryNo) {
		Connection conn = getConnection();
		
		int result = new InquiryDao().deleteInquiry(conn, inquiryNo); 
		
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}



	public int getListCount(String userId) {
		Connection conn = getConnection();
		
		int listCount = new InquiryDao().getListCount(conn, userId); 
		
		close(conn); 
		
		return listCount;
	}
	
	
	
	
	
	

}
