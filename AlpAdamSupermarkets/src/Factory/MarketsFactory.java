package Factory;


import Supermarket.Alimentary;
import Supermarket.Generalist;
import Supermarket.SuperMarket;

public class MarketsFactory {
	public SuperMarket createMarket(String name,String type) throws NoSuchMarketException{
		switch (type) {
		case "Generalist":
			return new Generalist(name);
		case "Alimentary":
			return new Alimentary(name);
		default:
			throw new NoSuchMarketException();
		}
	}
}
