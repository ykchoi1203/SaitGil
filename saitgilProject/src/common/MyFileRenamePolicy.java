package common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File originFile) {	//원본 파일을 받음
		// 원본명 --> 변경명 : 파일 업로드한 시간 (년,월,일,시,분,초)+0~100000사이의 랜덤값 (5자리의 랜덤값) 부여.
		
		// 현재시간 long 형으로 받기
		long currentTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		int ranNum = (int)(Math.random()*100000);
		
		// 파일명은 변경되어도 확장자는 그대로 유지.
		// 별도로 확장자명 가지고 오기
		String name = originFile.getName();
		
		int dot = name.lastIndexOf(".");
		String ext = "";
		if(dot != -1) {
			// .포함하여 확장자 추출(ext에 저장)
			ext = name.substring(dot);	// .의 위치값부터 마지막까지 전체 추출.
		}
		
		String fileName = sdf.format(new Date(currentTime)) + ranNum + ext;
		
		File renameFile = new File(originFile.getParentFile(), fileName);
		
//		String ext = name.split(".")[name.split(".").length-1];
		
		return renameFile;
	}

}
