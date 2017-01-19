package Produits;

import java.util.ArrayList;
import java.util.stream.Stream;

public enum AllProduct {
	nivea,
	nutella,
	yvesrocher,
	prince,
	cocacola,
	xmen,
	jeanlevis,
	tv,
	mrpropre;
	
	
	public static String[] getStrings() {
	    return Stream.of(AllProduct.values()).map(AllProduct::toString).toArray(String[]::new);
	}
	
	
	public static ArrayList<String> AllProductList(){
		ArrayList<String> allProductList = new ArrayList<>();
		for(String prod : AllProduct.getStrings())
			allProductList.add(prod);
		return allProductList;
	}
}
