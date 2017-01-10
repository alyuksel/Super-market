package Produits;

import java.util.stream.Stream;

public enum ProductType {
	Alimentary,
	Beauty,
	Digital,
	IT,
	Clothes,
	Cleaning;
	
	public static String[] getStrings() {
	    return Stream.of(ProductType.values()).map(ProductType::toString).toArray(String[]::new);
	}
	
}
