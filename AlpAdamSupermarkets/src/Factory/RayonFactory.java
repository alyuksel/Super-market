package Factory;

import Rayons.AlimentaryRay;
import Rayons.BeautyRay;
import Rayons.Rayon;

public class RayonFactory {
	
	
	public Rayon createRayon(String type) throws NoSuchRayonException{
		switch (type){
		case "alimentaire":
			return new AlimentaryRay();
		case "beauty":
			return new BeautyRay();
		default:
			throw new NoSuchRayonException();
		}
			
	}
}
