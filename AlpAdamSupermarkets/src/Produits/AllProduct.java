package Produits;

import java.util.stream.Stream;

public enum AllProduct {
	nivea,nutella,yvesrocher,prince;
	
	
	public static String[] getStrings() {
	    return Stream.of(AllProduct.values()).map(AllProduct::toString).toArray(String[]::new);
	}
}
