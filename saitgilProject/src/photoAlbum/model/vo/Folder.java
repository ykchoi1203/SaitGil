package photoAlbum.model.vo;

public class Folder {
	
	private int fId;			// 폴더 고유 번호
	private String fName;		// 폴더명
	
	
	public Folder() {
		
	}


	public Folder(int fId, String fName) {
		super();
		this.fId = fId;
		this.fName = fName;
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
