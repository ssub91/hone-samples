package bomnal.online.example.dto;

import hone.bom.web.simple.dto.SerializableDto;

public class Customer implements SerializableDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6420623359523700169L;
	/**
	 * 실손할인보험료(계산)
	 */
	private int calcPmmiDcpm;

	public int getCalcPmmiDcpm() {
		return calcPmmiDcpm;
	}

	public void setCalcPmmiDcpm(final int calcPmmiDcpm) {
		this.calcPmmiDcpm = calcPmmiDcpm;
	}
	
}
