package database_Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Election extends JFrame {

	private JPanel contentPane;
	private JTextField Name;
	private JTextField Year;
	
    
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Election frame = new Election();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver Loaded");
            Connection con = DriverManager.getConnection(url,"shoaib","103219");
            System.out.println("Conection Established");
        	
            
        }
        catch(ClassNotFoundException e1){
            System.out.println(e1);
        }
        catch(SQLException e1){
            System.out.println("connection Not Established");
        }
        
	}
	
	int EId  ;
	int key ;
	
	

	/**
	 * Create the frame.
	 */
	public Election() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 796, 525);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(75, 0, 130));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ELECTION MANAGEMENT SYSTEM");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 21));
		lblNewLabel.setBounds(10, 11, 494, 55);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Manage Elections");
		lblNewLabel_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(10, 65, 135, 39);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setForeground(Color.WHITE);
		lblName_1.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 14));
		lblName_1.setBounds(10, 115, 56, 39);
		contentPane.add(lblName_1);
		
		Name = new JTextField();
		Name.setBounds(10, 153, 164, 20);
		contentPane.add(Name);
		Name.setColumns(10);
		
		JLabel lblName_1_1 = new JLabel("YEAR");
		lblName_1_1.setForeground(Color.WHITE);
		lblName_1_1.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 14));
		lblName_1_1.setBounds(10, 172, 56, 39);
		contentPane.add(lblName_1_1);
		
		Year = new JTextField();
		Year.setColumns(10);
		Year.setBounds(10, 210, 164, 20);
		contentPane.add(Year);
		
		JButton btnAddElection = new JButton("ADD ELECTION");
	
		
		
        
		btnAddElection.addMouseListener(new MouseAdapter() {
			@Override
			
			public void mouseClicked(MouseEvent e) {
				String url = "jdbc:oracle:thin:@localhost:1521:orcl";
				if(Name.getText().isEmpty() ||  Year.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"Enter proper Election Name and Year ",null, getDefaultCloseOperation());
					
				}else
				{
					try {
						Connection con = DriverManager.getConnection(url,"shoaib","103219");
						PreparedStatement Add = con.prepareStatement("insert into national_elections(n_election_id,n_election_year,election_name) values(? , ? , ? )");
						
						try {
				    		
							Statement st1 = con.createStatement();   
				    		ResultSet Rs1 = st1.executeQuery("select MAX(N_Election_id)+1 from national_elections");
				    		Rs1.next();
				    		EId = Rs1.getInt(1);
				    		Add.setInt(1, EId);
				    		
				    	}catch(Exception Ex) {
				    		JOptionPane.showMessageDialog(null,"EID error");
				    			
				    	}
						
						Add.setInt(2,Integer.parseInt( Year.getText()));
						Add.setString(3, Name.getText());
						Add.executeUpdate();
						JOptionPane.showMessageDialog(null,"Election Added Successfully ", null, getDefaultCloseOperation());
						DisplayElections(); 
						con.close();
						}
					catch (Exception e1) {
						JOptionPane.showMessageDialog(null,"Didnt added due to wrong data type ", null, getDefaultCloseOperation());
					}
				}
			}
		});
		btnAddElection.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnAddElection.setBounds(10, 272, 164, 33);
		contentPane.add(btnAddElection);
		
		
		
		// Edit Election Event Handler
		
		JButton btnEditElection = new JButton("EDIT ELECTION");
		btnEditElection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(key == -1 || Name.getText().isEmpty() || Year.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Missing Information");
					
					
				}else {
					try {
						String url = "jdbc:oracle:thin:@localhost:1521:orcl";
						Connection con = DriverManager.getConnection(url,"shoaib","103219");
						String Query = "update national_elections set election_name=? , n_election_year=? where n_election_id=?";
						PreparedStatement update = con.prepareStatement(Query) ;
						
						update.setString(1,Name.getText() );
						update.setString(2,Year.getText() );
						update.setInt(3,key );
						update.executeUpdate();
						JOptionPane.showMessageDialog(null,"Updated ");
						DisplayElections();
						
					}catch(Exception e2) {
						JOptionPane.showMessageDialog(null,"Failed ");
					}
				}
			}
		});
		btnEditElection.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnEditElection.setBounds(10, 315, 164, 33);
		contentPane.add(btnEditElection);
		
		JButton btnDeleteElection = new JButton("DELETE ELECTION");
		btnDeleteElection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (key == -1 || Name.getText().isEmpty() || Year.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "PLease Select the Election to be Deleted");
					
					
				}else {
					try {
						String url = "jdbc:oracle:thin:@localhost:1521:orcl";
						Connection con = DriverManager.getConnection(url,"shoaib","103219");
						String Query = "Delete from national_elections where n_election_id ="+key;
						Statement del = con.createStatement();
						del.executeUpdate(Query);
						JOptionPane.showMessageDialog(null,"Deleted ");
						DisplayElections();
						
					}catch(Exception e2) {
						JOptionPane.showMessageDialog(null,"Unsuccessfull");
						
					}
				}
			}
			
		});
		btnDeleteElection.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnDeleteElection.setBounds(10, 359, 164, 33);
		contentPane.add(btnDeleteElection);
		
		JButton btnBack = new JButton("BACK");
		btnBack.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnBack.setBounds(10, 431, 106, 33);
		contentPane.add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(235, 79, 535, 385);
		contentPane.add(scrollPane);
		
		
		// To View in Tab When elections Get Selected
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					int MyIndex = table.getSelectedRow();
					key = Integer.valueOf(model.getValueAt(MyIndex, 0).toString());
					Name.setText(model.getValueAt(MyIndex, 2).toString());
					Year.setText(model.getValueAt(MyIndex, 1).toString());
					
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Elections_Name", "Elections_Year"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		DisplayElections(); 
	}
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private JTable table;
	
	
	// Function To Diplay/Populate elections in Table 
	private void DisplayElections() {
		try {
			Connection con = DriverManager.getConnection(url,"shoaib","103219");
			Statement s1 = con.createStatement();
			ResultSet rs = s1.executeQuery("Select * from national_elections");
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			
		}catch(Exception e2) {
			
		}
		
	}
	
}
