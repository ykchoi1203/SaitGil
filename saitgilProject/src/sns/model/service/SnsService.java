package sns.model.service;
import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import sns.model.dao.SnsDao;
import sns.model.vo.Board;
import sns.model.vo.Comment;
import sns.model.vo.Photo;

public class SnsService {
	
	/**
	 * 1.sns 寃뚯떆湲� 異붽��븯湲� �꽌鍮꾩뒪
	 * @param b
	 * @param fileList
	 * @return
	 */
	public int insertBoard(Board b, ArrayList<Photo> fileList) {
		
		Connection conn = getConnection();
		int result1 = new SnsDao().insertBoard(conn, b);
		
		if(result1 > 0) {
			commit(conn);
			int result2 = new SnsDao().insertPhoto(conn, fileList);
			if(result2 > 0) {
				commit(conn);
			}else {
				rollback(conn);
			}
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result1;
	}
	
	/**
	 * 2_1. sns 怨듭쑀寃뚯떆湲� 由ъ뒪�듃 議고쉶 �꽌鍮꾩뒪(isPublic=Y�씤 �븷�뱾)
	 * @return
	 */
	public ArrayList<Board> selectShareList(int st, int ed){
		Connection conn = getConnection();
		
		ArrayList<Board> bList = new SnsDao().selectShareList(conn, st, ed);
		
		close(conn);
		
		return bList;
	}
	
	/**
	 * 3. sns 寃뚯떆湲� �궗吏� 由ъ뒪�듃 議고쉶 �꽌鍮꾩뒪
	 * @return
	 */
	public ArrayList<Photo> selectSharePhList(){
		Connection conn = getConnection();
		
		ArrayList<Photo> phList = new SnsDao().selectSharePhList(conn);
		
		close(conn);
		return phList;
	}
	
	/**
	 * 4. sns 湲� 由ъ뒪�듃 媛쒖닔 議고쉶 �꽌鍮꾩뒪
	 * @return
	 */
	public int getListCount() {
		Connection conn = getConnection();
		
		int result = new SnsDao().getListCount(conn);
		
		close(conn);
		
		return result;
	}
	
	public ArrayList<Comment> selectShareComment(){
		Connection conn = getConnection();
		
		ArrayList<Comment> comList = new SnsDao().selectShareComment(conn);
		
		close(conn);
		
		return comList;
	}
	
	/**
	 * 6. sns �뙎湲� �궫�엯, 議고쉶 �꽌鍮꾩뒪
	 * @param c
	 * @return
	 */
	public ArrayList<Comment> insertSelectComment(Comment c){
		Connection conn = getConnection();
		
		int result = new SnsDao().insertComment(conn, c);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		ArrayList<Comment> comList = new SnsDao().selectComment(conn, c.getbNo());
		
		close(conn);
		return comList;
	}
	
	public int likeCount(int bNo, String like, String userId) {
		Connection conn = getConnection();
		int result = 0;
		int result2 = 0;
		if(like.equals("Y")) {	// 좋아요 버튼을 눌렀을때
			// 1. LIKE_TABLE에 값 입력하기
			result = new SnsDao().insertLike(conn, bNo, userId);
			// 2. BOARD_TABLE에 좋아요 수 1증가
			result2 = new SnsDao().addLikeCount(conn, bNo);
			if(result > 0 && result2 > 0) {
				commit(conn);
			}else {
				rollback(conn);
			}
		}else if(like.equals("N")){	// 좋아요 버튼을 취소했을때
			// 1. LIKE_TABLE에 값 삭제하기
			result = new SnsDao().deleteLike(conn, bNo, userId);
			// 2. BOARD_TABLE에 좋아요 수 1감소
			result2 = new SnsDao().minusLikeCount(conn, bNo);
			if(result > 0 && result2 > 0) {
				commit(conn);
			}else {
				rollback(conn);
			}
		}
		
		int likeCount = new SnsDao().selectLikeCount(conn, bNo);
		
		close(conn);
		
		return likeCount;
	}

	public ArrayList<Board> selectLike() {
		Connection conn = getConnection();
		
		ArrayList<Board> likeList = new SnsDao().selectLike(conn);
		
		close(conn);
		
		return likeList;
		
		
	}

	public int insertScrap(int bNo, String cCode, String scrap) {
		
		Connection conn = getConnection();
		
		int result = 0;
		
		if(scrap.equals("Y")) {
			result = new SnsDao().insertScrap(conn, bNo, cCode);
			
		}else if(scrap.equals("N")) {
			result = new SnsDao().deleteScrap(conn, bNo, cCode);
		}
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public ArrayList<Board> selectScrap() {
		Connection conn = getConnection();
		
		ArrayList<Board> scrapList = new SnsDao().selectScrap(conn);
		
		close(conn);
		
		return scrapList;
	}

	public ArrayList<Board> selectSecretList(String cCode, int st, int ed) {
		Connection conn = getConnection();
		
		ArrayList<Board> bList = new SnsDao().selectSecretList(conn, cCode, st, ed);
		
		close(conn);
		
		return bList;
	}

	public ArrayList<Photo> selectSecretPhList() {
		Connection conn = getConnection();
		
		ArrayList<Photo> phList = new SnsDao().selectSecretPhList(conn);
		
		close(conn);
		return phList;
	}

	public ArrayList<Comment> selectSecretComment() {
		Connection conn = getConnection();
		
		ArrayList<Comment> comList = new SnsDao().selectSecretComment(conn);
		
		close(conn);
		
		return comList;
	}
	
	public int getSecretListCount() {
		Connection conn = getConnection();
		
		int result = new SnsDao().getSecretListCount(conn);
		
		close(conn);
		
		return result;
	}

	public ArrayList<Board> selectLikeList(int st, int ed, String userId) {
		Connection conn = getConnection();
		
		ArrayList<Board> bList = new SnsDao().selectLikeList(conn, st, ed, userId);
		
		close(conn);
		
		return bList;
	}

	public ArrayList<Board> selectScrapList(int st, int ed, String cCode) {
		Connection conn = getConnection();
		
		ArrayList<Board> bList = new SnsDao().selectScrapList(conn, st, ed, cCode);
		
		close(conn);
		
		return bList;
	}
	
	public ArrayList<Photo> selectPhList() {
		Connection conn = getConnection();
		
		ArrayList<Photo> phList = new SnsDao().selectPhList(conn);
		
		close(conn);
		return phList;
	}

	public ArrayList<Comment> selectComList() {
		Connection conn = getConnection();
		
		ArrayList<Comment> comList = new SnsDao().selectComList(conn);
		
		close(conn);
		
		return comList;
	}
	
	public int getLikeListCount(String userId) {
		Connection conn = getConnection();
		
		int result = new SnsDao().getLikeListCount(conn, userId);
		
		close(conn);
		
		return result;
	}

	public int getScrapListCount(String cCode) {
		Connection conn = getConnection();
		
		int result = new SnsDao().getScrapListCount(conn, cCode);
		
		close(conn);
		
		return result;
	}

	public int deleteBoard(int bNo) {
		Connection conn = getConnection();
		
		int result = new SnsDao().deleteBoard(conn, bNo);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
		
	}

	public ArrayList<Board> selectMyList(int st, int ed, String userName) {
		
		Connection conn = getConnection();
		
		ArrayList<Board> bList = new SnsDao().selectMyList(conn, st, ed, userName);
		
		close(conn);
		
		return bList;
		
	}
	
	public int getMyListCount(String userName) {
		Connection conn = getConnection();
		
		int result = new SnsDao().getMyListCount(conn, userName);
		
		close(conn);
		
		return result;
	}




}
