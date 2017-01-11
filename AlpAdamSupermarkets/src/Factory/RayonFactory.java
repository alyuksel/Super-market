package Factory;

import Rayons.AlimentaryRay;
import Rayons.BeautyRay;
import Rayons.Rayon;

public class RayonFactory {
	
	
	public Rayon createRayon(String type) throws NoSuchRayonException{
		switch (type){
		case "Alimentary":
			return new AlimentaryRay();
		case "Beauty":
			return new BeautyRay();
		default:
			throw new NoSuchRayonException();
		}
			
	}
}
