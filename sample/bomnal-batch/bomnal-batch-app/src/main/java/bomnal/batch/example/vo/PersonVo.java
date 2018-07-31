package bomnal.batch.example.vo;

public class PersonVo {
	private Long no;
	private String lastName;
    private String firstName;
    
    
    public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

    public PersonVo() {
    }
    
    public PersonVo( String firstName, String lastName ) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

	@Override
	public String toString() {
		return "PersonVo [no=" + no + ", lastName=" + lastName + ", firstName=" + firstName + "]";
	}
}
