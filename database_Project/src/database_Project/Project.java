package database_Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;

public class Project {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Project window = new Project();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Project() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 25));
		frame.getContentPane().setBackground(new Color(75, 0, 130));
		frame.getContentPane().setForeground(new Color(255, 228, 181));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ELECTION MANAGEMENT SYSTEM");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 20));
		lblNewLabel.setBounds(40, 27, 344, 39);
		frame.getContentPane().add(lblNewLabel);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 236, 414, 14);
		frame.getContentPane().add(progressBar);
		
		JLabel lblLoadingModules = new JLabel("LOADING MODULES");
		lblLoadingModules.setForeground(Color.WHITE);
		lblLoadingModules.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 15));
		lblLoadingModules.setBounds(10, 186, 174, 39);
		frame.getContentPane().add(lblLoadingModules);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\uzma\\Desktop\\n.png"));
		lblNewLabel_1.setBounds(168, 77, 87, 111);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
