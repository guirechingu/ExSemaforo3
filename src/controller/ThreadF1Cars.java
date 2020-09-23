package controller;

import java.util.concurrent.Semaphore;

public class ThreadF1Cars extends Thread {
	private static int qtyCarsFinished;
	private int idCar;
	private int idScuderia;
	private int qtyLaps=3;
	private int bestLap;
	private Semaphore semaphoreLap;
	private Semaphore semaphoreScuderia[];

	public ThreadF1Cars(int idCar, int idScuderia, Semaphore smLap, Semaphore smScuderia[]) {
		this.idCar = idCar;
		this.idScuderia = idScuderia;
		this.semaphoreLap = smLap;
		this.semaphoreScuderia = smScuderia;

	}

	@Override
	public void run() {
		try {
			semaphoreScuderia[this.idScuderia].acquire();
			try {
				semaphoreLap.acquire();
				makeLaps(qtyLaps);
				qtyCarsFinished++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				semaphoreLap.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaphoreScuderia[this.idScuderia].release();
		}
		SendBestLap();
		if(qtyCarsFinished==14) {
			BestTimeLaps.boobleSource();
			BestTimeLaps.showGrid();
		}
	}

	private void makeLaps(int qtyLaps) {
		for (int x = 0; x < qtyLaps; x++) {
			int lapTime = (int) ((Math.random() * 2331) + 67000);
			if (lapTime < this.bestLap || this.bestLap == 0) {
				this.bestLap = lapTime;
			}
			try {
				Thread.sleep(lapTime/10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("O Carro #" + this.idCar + " Da Scuderia #" + BestTimeLaps.scuderia[this.idScuderia] + " Fez a " + (x + 1)
					+ "ª Volta \n" + ((lapTime / 1000) / 60) + ":0" + ((lapTime / 1000)-60) + "." + (lapTime % 1000));
		}
	}
	
	private void SendBestLap() {
		BestTimeLaps.reciveTimeLap(this.idCar, this.idScuderia,this.bestLap);
	}
	
}
