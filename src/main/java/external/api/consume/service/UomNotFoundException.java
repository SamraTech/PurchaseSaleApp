package external.api.consume.service;



public class UomNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String msg;

	public UomNotFoundException(String msg) {
		
		this.msg = msg;
	}
	

}
