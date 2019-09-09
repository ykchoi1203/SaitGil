package shareFile.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.Date;

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
	
	
	
	/** 3. 커플코드 생성하기 
	 * @param sf
	 * @param meetDate
	 * @return
	 */
	public int updateCcode(ShareFile sf, String meetDate) {
		Connection conn = getConnection();

		int result = new ShareFileDao().updateCcode(conn, sf, meetDate);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
		
	}
	
	/** 커플 사진 업데이트 
	 * @param name
	 * @param cCode
	 * @return
	 */
	public int updateProfile(String name, String cCode) {
		Connection conn = getConnection();
		
		int result = new ShareFileDao().updateProfile(conn, name, cCode);
		
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result; 
	}
	
	
	
	public int deleteCoupleCode(String cCode) {
		Connection conn = getConnection();
		
		int result = new ShareFileDao().deleteCoupleCode(conn, cCode);
		
		int result1 = 0;
		if(result == 2) {
			result1 = new ShareFileDao().deleteShareFile(conn, cCode);
			if(result1 > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} else {
			rollback(conn);
		}
		System.out.println("cCode : " + cCode + "result : " + result + ", result1 : " + result1);
		close(conn);
		
		
		return result1;
	}
	
}
