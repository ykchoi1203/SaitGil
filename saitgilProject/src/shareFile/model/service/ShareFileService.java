package shareFile.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;

import member.model.dao.MemberDao;
import shareFile.model.dao.ShareFileDao;
import shareFile.model.vo.ShareFile;

public class ShareFileService {
	
	/** 1. 커플 사귄 날짜 변경 서비스 
	 * @param fristDate
	 * @return
	 */
	public ShareFile changeMeetDate(String meetDate, String cCode) {
		Connection conn = getConnection(); 
		
		int result = new ShareFileDao().changeMeetDate(conn, meetDate, cCode); 
		
		ShareFile sf = null;
		if(result > 0) {
			sf = new ShareFileDao().selectShareFile(conn, cCode);
			commit(conn); 
		} else {
			rollback(conn);
		}
		
		close(conn); 
		
		return sf;

	}
	
	
	/** 2. 커플 공유폴더 정보 가져오기 서비스 
	 * @param cCode
	 * @return
	 */
	public ShareFile selectShareFile(String cCode) {
		Connection conn = getConnection(); 
		
		ShareFile sf = new ShareFileDao().selectShareFile(conn, cCode);
		
		close(conn); 
		
		return sf;

	}
}
