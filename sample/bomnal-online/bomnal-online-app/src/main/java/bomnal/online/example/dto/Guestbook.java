package bomnal.online.example.dto;

import java.sql.Blob;
import java.util.List;

public class Guestbook {
	private Long no;
	private String name;
	private String password;
	private String content;
	private String regDate;
	private List<String> nameList;
	private String recentYn;
	private String blob_name;
	private byte[] blob_file;
	private String year;
	
	
	


	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getBlob_name() {
		return blob_name;
	}
	public void setBlob_name(String blob_name) {
		this.blob_name = blob_name;
	}

	public byte[] getBlob_file() {
		return blob_file;
	}
	public void setBlob_file(byte[] blob_file) {
		this.blob_file = blob_file;
	}
	public String getRecentYn() {
		return recentYn;
	}
	public void setRecentYn(String recentYn) {
		this.recentYn = recentYn;
	}
	public List<String> getNameList() {
		return nameList;
	}
	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
