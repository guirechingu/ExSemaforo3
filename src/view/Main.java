package view;

import java.util.concurrent.Semaphore;

import controller.BestTimeLaps;
import controller.ThreadF1Cars;

public class Main {
	public static void main(String[] args) {
		
		int scuderiaLength=BestTimeLaps.scuderia.length;
		Semaphore smLap = new Semaphore(5);
		Semaphore smScuderia[]= new Semaphore[scuderiaLength];
		
		for(int qtyScuderia=0;qtyScuderia<scuderiaLength;qtyScuderia++) {
			smScuderia[qtyScuderia]=new Semaphore(1);
		}
		
		
		for(int idScuderia=0;idScuderia<scuderiaLength;idScuderia++) {
			for(int idCar=0;idCar<2;idCar++) {
				Thread f1Car = new ThreadF1Cars(((idScuderia*2)+idCar), idScuderia, smLap, smScuderia);
				f1Car.start();
			}
		}
		
	}
}
