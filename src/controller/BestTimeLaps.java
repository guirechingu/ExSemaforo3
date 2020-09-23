package controller;

public class BestTimeLaps {
	
	private static int BestTimeLaps[][]=new int[14][3];
	public static String  scuderia[]= {"Mercedes","Ferrari","Red Bull Racing","Renault F1 Team","Williams","McLaren","AlphaTauri"};

	public static int[][] getBestTimeLaps() {
		return BestTimeLaps;
	}

	public static void reciveTimeLap(int idCar,int idScuderia,int time ) {
		BestTimeLaps[idCar][0] = idCar;
		BestTimeLaps[idCar][1]=idScuderia;
		BestTimeLaps[idCar][2]=time;
	}
	
	public static void boobleSource() {
		 int aux[],length=BestTimeLaps.length;
         for(int i=0; i<(length-1); i++) {
            for(int j=i+1; j<length; j++) {
                if(BestTimeLaps[i][2]>BestTimeLaps[j][2]) {
                    aux = BestTimeLaps[i];
                    BestTimeLaps[i] = BestTimeLaps[j];
                    BestTimeLaps[j] = aux;
                }
            }
         }
	}
	
	public static void showGrid() {
		int btlLength=BestTimeLaps.length;
		for(int x=0;x<btlLength;x++) {
			System.out.println((x+1)+"º - Carro #"+BestTimeLaps[x][0]+" Da "+scuderia[BestTimeLaps[x][1]]+" - 0"+((BestTimeLaps[x][2] / 1000) / 60) + ":0" + ((BestTimeLaps[x][2] / 1000)-60) + "." + (BestTimeLaps[x][2] % 1000)) ;
		}
	}
	
}
