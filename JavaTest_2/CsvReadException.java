package nakamura;

import java.io.IOException;

public class CsvReadException extends IOException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8707234543023201943L;

	public CsvReadException() {
		super();
	}

	public CsvReadException(String message, Throwable cause) {
		super(message, cause);
	}

	public CsvReadException(String message) {
		super(message);
	}

	public CsvReadException(Throwable cause) {
		super(cause);
	}


}
