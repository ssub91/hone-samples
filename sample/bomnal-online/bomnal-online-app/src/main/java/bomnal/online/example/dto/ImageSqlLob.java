package bomnal.online.example.dto;

import java.io.ByteArrayInputStream;

import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;

/**
 * 
 * @author sblee
 *
 * SqlLobValue 필드 타입 형태
 * 
 */
public class ImageSqlLob {
	private Long no;
	private SqlLobValue data;
	
	public Long getNo() {
		return no;
	}
	
	public void setNo(Long no) {
		this.no = no;
	}
	
	public SqlLobValue getData() {
		return data;
	}
	
	public void setData(SqlLobValue data) {
		this.data = data;
	}
	
	public void setData(byte[] data) {
		this.data = new SqlLobValue(new ByteArrayInputStream(data), data.length, new DefaultLobHandler());
	}
}