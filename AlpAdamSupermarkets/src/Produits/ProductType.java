package Produits;

import java.util.stream.Stream;

public enum ProductType{
	Alimentary,
	Beauty,
	VideoGames,
	Movies,
	Cleaning;
	
	public static String[] getStrings() {
	    return Stream.of(ProductType.values()).map(ProductType::toString).toArray(String[]::new);
	}
}
