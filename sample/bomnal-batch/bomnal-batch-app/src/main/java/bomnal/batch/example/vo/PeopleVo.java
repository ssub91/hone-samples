package bomnal.batch.example.vo;

public class PeopleVo {
	private Long no;
	private String fullName;
	
	public Long getNo() {
		return no;
	}
	
	public void setNo(Long no) {
		this.no = no;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "PeopleVo [no=" + no + ", fullName=" + fullName + "]";
	}
}
