package Tools;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import Produits.Produit;




public class GenericClass {
	public static <T> Integer numberOf(Set<T> set){
		return set.size();
	}
	
	/*public static <M extends Map<String,L>,L extends Collection<T>, T extends Produit> void addSome(M map,T product,ProductType typeWait){
		if(product.getProductType().equals(typeWait)){
			if(map.containsKey(product.getLabel())){
				
				if(map.get(product.getLabel()).contains(product)){
					System.err.println("ce produit est déjà présent");
				}
				else{
					map.get(product.getLabel()).add(product);
				}
			}
			else{
				L l = (L) new ArrayList<T>();	
				l.add(product);
				map.put(product.getLabel(), l);
			}
		}
		else{
			System.err.println("Bad Rayon");
		}
	}*/
	public static <T extends Produit> void addProduct(Map <String, ArrayList<Produit>> mapProduct,Produit produit){
		if(mapProduct.containsKey(produit.getLabel())){
			mapProduct.get(produit.getLabel()).add(produit);
		}
		else{
			ArrayList<Produit> list = new ArrayList<>();
			list.add(produit);
			mapProduct.put(produit.getLabel(), list);
		}
}
	
	public static <T,L extends Collection<T>> Integer getNumberOf(L set, Class<?> cls){

		return 0;
	}
	
	public static <T> Stream<T> getFiltredStream(Stream<T> st, Predicate<? super T> funct){
		return st.filter(funct);
	}
	
}
