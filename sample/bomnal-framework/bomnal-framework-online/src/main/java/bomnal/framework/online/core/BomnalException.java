package bomnal.framework.online.core;

import hone.bom.CodedBomException;

public class BomnalException extends CodedBomException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8824349327393922245L;
	

	public BomnalException (String messageId) {
		super(messageId);
	}

	public BomnalException (String messageId, String... arguments) {
		super(messageId, arguments);
	}

	public BomnalException (String messageId, Throwable e) {
		super(messageId, e);
	}

	public BomnalException (String messageId, Throwable e, String... arguments) {
		super(messageId, e, arguments);
	}
}