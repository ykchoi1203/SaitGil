package photo.model.vo;

import java.sql.Date;

public class Attachment {
	
	private int atId;			
	private int fId;			
	private String cCode;
	private String writer;
	private String originName;	
	private String changeName;	
	private String filePath;	
	private Date uploadDate;	
	private String status;	

	
	public Attachment() {
		
	}
	


	public Attachment(int atId, int fId, String cCode, String writer, String originName, String changeName,
			String filePath, Date uploadDate, String status) {
		super();
		this.atId = atId;
		this.fId = fId;
		this.cCode = cCode;
		this.writer = writer;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.status = status;
	}



	

	public String getcCode() {
		return cCode;
	}



	public void setcCode(String cCode) {
		this.cCode = cCode;
	}



	public String getWriter() {
		return writer;
	}



	public void setWriter(String writer) {
		this.writer = writer;
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
