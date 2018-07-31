package bomnal.online.example.dto;

/**
 * 
 * @author sblee
 * 
 * BLOB 처리
 * byte[] 필드 타입 형태 
 *
 */
public class Image {
	private Long no;
	private byte[] data;

	public Image() {
	}

	public Image(Long no, byte[] data) {
		this.no = no;
		this.data = data;
	}

	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
}
