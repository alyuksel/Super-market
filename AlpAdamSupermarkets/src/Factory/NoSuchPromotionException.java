package Factory;

public class NoSuchPromotionException extends Exception {

	private static final long serialVersionUID = 1L;
	@Override
	public String getMessage() {
		return "Promotion innexistante";
	}
}
