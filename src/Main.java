import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("BlockBreaker");
		
		BlockBreaker panel = new BlockBreaker();
		frame.getContentPane().add(panel);
		
		frame.setSize(490, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	}

}
