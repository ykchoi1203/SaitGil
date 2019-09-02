package photoAlbum.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import photoAlbum.model.dao.FolderDao;
import photoAlbum.model.vo.Attachment;
import photoAlbum.model.vo.Folder;

public class FolderService {

	/**
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
	
	public ArrayList<Folder> selectFolderList(){
		Connection conn = getConnection();
		
		ArrayList<Folder> list = new FolderDao().selectFolderList(conn);
		
		close(conn);
		
		return list;
		
	}
	
	public ArrayList<Attachment> selectAtList(){
		Connection conn = getConnection();
		
		ArrayList<Attachment> list = new FolderDao().selectAtList(conn);
		close(conn);
		return list;
	}

	public ArrayList<Attachment> selectAttachment(int fId) {
		
		Connection conn = getConnection();
		
		ArrayList<Attachment> list = new FolderDao().selectAttachment(conn, fId);
		
		close(conn);
		
		return list;
		
	
	}

	public Folder selectFolder(int fId) {

		Connection conn = getConnection();

		Folder f = new FolderDao().selectFolder(conn, fId);
		close(conn);
		return f;

	}
	
	
}
