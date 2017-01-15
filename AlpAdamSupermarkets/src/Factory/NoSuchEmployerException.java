package Factory;

public class NoSuchEmployerException extends Exception {

	
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "emplyers innexistants !";
	}
}
