package bomnal.online.example.dto;

import java.util.Date;

import hone.bom.web.simple.dto.SerializableDto;

public class Memo implements SerializableDto {

	/**
	 * 
	 */
	private String memoId;
	private String memoTitle;
	private String memoContent;
	private Date regDate;
	private String regId;

	public String getMemoId() {
		return memoId;
	}

	public void setMemoId(final String memoId) {
		this.memoId = memoId;
	}

	public String getMemoTitle() {
		return memoTitle;
	}

	public void setMemoTitle(final String memoTitle) {
		this.memoTitle = memoTitle;
	}

	public String getMemoContent() {
		return memoContent;
	}

	public void setMemoContent(final String memoContent) {
		this.memoContent = memoContent;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(final Date regDate) {
		this.regDate = regDate;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(final String regId) {
		this.regId = regId;
	}

}
