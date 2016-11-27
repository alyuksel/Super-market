package Factory;

public class NoSuchRayonException extends Exception{
	@Override
	public String getMessage() {
		return "Rayon innexistant";
	}
}
