
public class Animate implements Runnable {

	BlockBreaker b;
	Animate(BlockBreaker b){
		this.b=b;
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			b.update();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
