package photo.model.vo;

public class Folder {
	
	private int fId;			
	private String fName;		
	private String cCode;
	private String writer;
	private String status;
	private String mainAttachment;
	
	
	public Folder() {
		
	}

	
	

	public Folder(int fId, String fName, String cCode, String writer, String status, String mainAttachment) {
		super();
		this.fId = fId;
		this.fName = fName;
		this.cCode = cCode;
		this.writer = writer;
		this.status = status;
		this.mainAttachment = mainAttachment;
	}




	public Folder(int fId, String fName, String cCode, String writer, String status) {
		super();
		this.fId = fId;
		this.fName = fName;
		this.cCode = cCode;
		this.writer = writer;
		this.status = status;
	}




	public Folder(int fId, String fName, String mainAttachment) {
		super();
		this.fId = fId;
		this.fName = fName;
		this.mainAttachment = mainAttachment;
	}
	
	
	public Folder(int fId) {
		super();
		this.fId = fId;
	}
	


	public String getMainAttachment() {
		return mainAttachment;
	}




	public void setMainAttachment(String mainAttachment) {
		this.mainAttachment = mainAttachment;
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




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public int getfId() {
		return fId;
	}


	public void setfId(int fId) {
		this.fId = fId;
	}


	public String getfName() {
		return fName;
	}


	public void setfName(String fName) {
		this.fName = fName;
	}

	
	
}
