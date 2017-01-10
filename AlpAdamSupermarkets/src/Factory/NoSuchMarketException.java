package Factory;

public class NoSuchMarketException extends Exception {
	private static final long serialVersionUID = 1L;
	@Override
	public String getMessage() {
		return "type Innexistant";
	}
}
