package notice.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import notice.model.dao.NoticeDao;
import notice.model.vo.Notice;

public class NoticeService {
	
	/** 1. 공지사항 리스트 불러오기 
	 * @return
	 */
	public ArrayList<Notice> selectList(int currentPage, int boardLimit) { 
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectList(conn, currentPage, boardLimit);
		
		close(conn);
		
		return list;
	}
	
	
	/** 2. 공지사항 작성하기 
	 * @param n
	 * @return
	 */
	public int insertNotice(Notice n) {
		Connection conn = getConnection(); 
		
		int result = new NoticeDao().insertNotice(conn, n);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn); 
		} 
		
		close(conn); 
		
		return result;
		 
	}
	
	
	/** 3. 공지사항 조회수 증가 
	 * @param noticeNo
	 * @return
	 */
	public int increaseCount(int noticeNo) {
		Connection conn = getConnection(); 
		
		//조회수 증가시키기 
		int result = new NoticeDao().increaseCount(conn, noticeNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn); 
		
		return result;
	}
	
	
	
	
	/** 4. 공지사항 불러오기
	 * @param noticeNo
	 * @return
	 */
	public Notice selectNotice(int noticeNo) {
		Connection conn = getConnection();
		
		Notice n = new NoticeDao().selectNotice(conn, noticeNo); 
		
		if(n !=  null) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return n;
	}
	
	
	/** 공지사항 수정하기 
	 * @param n
	 * @return
	 */
	public int updateNotice(Notice n) {
		Connection conn = getConnection(); 
		
		int result = new NoticeDao().updateNotice(conn, n); 
		
		if(result > 0) {
			commit(conn); 
		} else {
			rollback(conn); 
		}
		
		close(conn);
		
		return result;
		
		
	}
	
	
	
	/** 공지사항 삭제 
	 * @param noticeNo
	 * @return
	 */
	public int deleteNotice(int noticeNo) {
		Connection conn = getConnection();
		
		int result = new NoticeDao().deleteNotice(conn, noticeNo); 
		
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	
	
	/** 공지사항 페이징처리를 위한 게시글 수 구하는 서비스 
	 * @return
	 */
	public int getListCount() {
		Connection conn = getConnection();
		
		int listCount = new NoticeDao().getListCount(conn); 
		
		close(conn); 
		
		return listCount;
		
	}
	
	
	
	
	
	

}
