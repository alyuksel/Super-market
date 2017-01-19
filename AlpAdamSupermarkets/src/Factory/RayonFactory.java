package Factory;

import Rayons.AlimentaryRay;
import Rayons.BeautyRay;
import Rayons.CleaningRay;
import Rayons.ClothesRay;
import Rayons.DigitalRay;
import Rayons.ITRay;
import Rayons.Rayon;

public class RayonFactory {
	
	
	public Rayon createRayon(String type) throws NoSuchRayonException{
		switch (type){
		case "Alimentary":
			return new AlimentaryRay();
		case "Beauty":
			return new BeautyRay();
		case "Clothes":
			return new ClothesRay();
		case "IT" :
			return new ITRay();
		case "Digital":
			return new DigitalRay();
		case "Cleaning":
			return new CleaningRay();
		default:
			throw new NoSuchRayonException();
		}
			
	}
}
