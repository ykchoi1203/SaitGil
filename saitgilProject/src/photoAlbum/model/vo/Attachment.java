package photoAlbum.model.vo;

import java.sql.Date;

public class Attachment {
	
	private int atId;			// 사진 고유 번호 
	private int fId;			// 파일 고유 번호
	private int bId;			// 참조하고 있는 게시글 번호
	private String originName;	// 파일 원본명
	private String changeName;	// 파일 수정명
	private String filePath;	// 파일이 저장된 경로
	private Date uploadDate;	// 파일 업로드일
	private String status;	

	
	public Attachment() {
		
	}


	public Attachment(int atId, int fId, int bId, String originName, String changeName, String filePath,
			Date uploadDate, String status) {
		super();
		this.atId = atId;
		this.fId = fId;
		this.bId = bId;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.status = status;
	}


	public int getAtId() {
		return atId;
	}


	public void setAtId(int atId) {
		this.atId = atId;
	}


	public int getfId() {
		return fId;
	}


	public void setfId(int fId) {
		this.fId = fId;
	}


	public int getbId() {
		return bId;
	}


	public void setbId(int bId) {
		this.bId = bId;
	}


	public String getOriginName() {
		return originName;
	}


	public void setOriginName(String originName) {
		this.originName = originName;
	}


	public String getChangeName() {
		return changeName;
	}


	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public Date getUploadDate() {
		return uploadDate;
	}


	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	
	
}
