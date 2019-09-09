package photo.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import photo.model.dao.FolderDao;
import photo.model.vo.Attachment;
import photo.model.vo.Folder;

public class FolderService {

	/**
	 * 占쎈쨨占쎈쐭 占쎄문占쎄쉐
	 * @param f
	 * @param fileList
	 * @return
	 */
	public int insertFolder(Folder f, ArrayList<Attachment> fileList) {
		
		Connection conn = getConnection();
		
		int result1 = new FolderDao().insertFolder(conn, f);
		int result2 = new FolderDao().insertAttachment(conn, fileList);
		
		
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1;
	}
	
	/**
	 * 占쎈쨨占쎈쐭?뵳?딅뮞占쎈뱜
	 * @return
	 */
	public ArrayList<Folder> selectFolderList(String cCode){
		Connection conn = getConnection();
		
		ArrayList<Folder> list = new FolderDao().selectFolderList(conn, cCode);
		
		close(conn);
		
		return list;
		
	}
	
	/**
	 * 占쎈쨨占쎈쐭 占쎄텢筌욑옙 ?뵳?딅뮞占쎈뱜
	 * @return
	 */
	public ArrayList<Attachment> selectAtList(){
		Connection conn = getConnection();
		
		ArrayList<Attachment> list = new FolderDao().selectAtList(conn);
		close(conn);
		return list;
	}

	/**
	 * 占쎈쨨占쎈쐭占쎈꺗 占쎄텢筌욑옙 ?뵳?딅뮞占쎈뱜
	 * @param fId
	 * @return
	 */
	public ArrayList<Attachment> selectAttachment(int fId) {
		
		Connection conn = getConnection();
		
		ArrayList<Attachment> list = new FolderDao().selectAttachment(conn, fId);
		
		close(conn);
		
		return list;
		
	
	}

	/**
	 * 占쎈쨨占쎈쐭 占쎌젟癰귨옙 鈺곌퀬?돳
	 * @param fId
	 * @return
	 */
	public Folder selectFolder(int fId) {

		Connection conn = getConnection();

		Folder f = new FolderDao().selectFolder(conn, fId);
		close(conn);
		return f;

	}

	/**
	 * 占쎈쨨占쎈쐭占쎈꺗 占쎄텢筌욑옙 ?빊遺쏙옙
	 * @param f
	 * @param fileList
	 * @return
	 */
	public int insertAttachment(int fId, ArrayList<Attachment> fileList) {
		
		Connection conn = getConnection();
		
		int result = new FolderDao().insertAtt(conn, fId ,fileList);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int deleteFolder(int fId) {
		
		Connection conn = getConnection();
		
		int result = new FolderDao().deleteFolder(conn, fId);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public int deleteAttachment(int atId) {
		
		Connection conn = getConnection();
		
		int result = new FolderDao().deleteAttachment(conn, atId);
				
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result; 
		
	}

	public Folder changefName(Folder f) {
		Connection conn = getConnection();
		
		int result = new FolderDao().changefName(conn, f);
		
		Folder changeFolder = null;
		
		if(result > 0) {
			commit(conn);
			
			changeFolder = new FolderDao().selectFolder(conn, f.getfId());
		}else {
			rollback(conn);
			
		}
		close(conn);
		
		return changeFolder;
	}

	public int movePhoto(int fId, int atId) {
		
		Connection conn = getConnection();
		
		int result = new FolderDao().movePhoto(conn, fId, atId);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	

	
	
	
}
