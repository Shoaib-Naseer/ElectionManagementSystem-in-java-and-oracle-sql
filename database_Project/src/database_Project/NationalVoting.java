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
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NationalVoting extends JFrame {
	
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	int key;
	int VId;
	int Cand_Id;
	int Const_Id;
	int Elec_Id;

	private JPanel contentPane;
	private JTextField CandidateName;
	private JTextField Party;
	private JTextField Constituency;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NationalVoting frame = new NationalVoting();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	int VotingId;
	public NationalVoting() {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 817, 477);
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
		
		JLabel lblNewLabel_1 = new JLabel("WELCOME TO VOTING");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 56, 224, 39);
		contentPane.add(lblNewLabel_1);
		
		JButton btnVote = new JButton("VOTE");
		btnVote.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(key == -1) {
					JOptionPane.showMessageDialog(null, "Select Candidate Please !");
				}else {
					try {
					//	VCount();
						Connection con = DriverManager.getConnection(url,"shoaib","103219");
						PreparedStatement Add = con.prepareStatement("insert into national_voting(national_votes,voters_voter_id,NATIONAL_CANDIDATES_N_CANDIDATE_ID,NATIONAL_ELECTIONS_N_ELECTION_ID,CONSTITUENCY_ID) values(?,?,?,?,? )");
						try {
				    		
							Statement st1 = con.createStatement();   
				    		ResultSet Rs1 = st1.executeQuery("select MAX(national_voters)+1 from national_voting");
				    		Rs1.next();
				    		VId = Rs1.getInt(1);
				    		Add.setInt(1, VId);
				    		
				    	}catch(Exception Ex) {
				    		JOptionPane.showMessageDialog(null,"Voters Counting error");
				    	}
						//try {
							//Add.setString(2, CandidateName.getText());		
						//}catch (Exception e1) {
							//JOptionPane.showMessageDialog(null,"Invalid NAME ", null, getDefaultCloseOperation());
						//}
						
						try {
							Add.setInt(3,Cand_Id);		
						}catch (Exception e1) {
							JOptionPane.showMessageDialog(null,"Invalid Candidate", null, getDefaultCloseOperation());
						}
						try {
							Add.setInt(4,Elec_Id);		
						}catch (Exception e1) {
							JOptionPane.showMessageDialog(null,"Invalid Election ", null, getDefaultCloseOperation());
						}	
						try {
							Add.setInt(5,Const_Id);	
						}catch (Exception e1) {
							JOptionPane.showMessageDialog(null,"Invalid Constituency  ", null, getDefaultCloseOperation());
						}	
						Add.executeUpdate();
						JOptionPane.showMessageDialog(null,"Vote Counted ", null, getDefaultCloseOperation());
						DisplayCandidates(); 
						con.close();
						
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(null,"Vote Didnt Counted ", null, getDefaultCloseOperation());
						
						
					}
				}
				
				
			}
		});
		btnVote.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnVote.setBounds(10, 302, 164, 33);
		contentPane.add(btnVote);
		
		JLabel lblNewLabel_2 = new JLabel("CANDIDATES LIST");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(236, 106, 156, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblName_1 = new JLabel("YOUR CANDIDATE NAME");
		lblName_1.setForeground(Color.WHITE);
		lblName_1.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 14));
		lblName_1.setBounds(10, 94, 224, 39);
		contentPane.add(lblName_1);
		
		CandidateName = new JTextField();
		CandidateName.setEditable(false);
		CandidateName.setColumns(10);
		CandidateName.setBounds(10, 125, 177, 20);
		contentPane.add(CandidateName);
		
		JLabel lblName_1_1 = new JLabel("PARTY");
		lblName_1_1.setForeground(Color.WHITE);
		lblName_1_1.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 14));
		lblName_1_1.setBounds(10, 144, 56, 39);
		contentPane.add(lblName_1_1);
		
		Party = new JTextField();
		Party.setEditable(false);
		Party.setColumns(10);
		Party.setBounds(10, 174, 177, 20);
		contentPane.add(Party);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Login().setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnBack.setBounds(10, 373, 95, 27);
		contentPane.add(btnBack);
		
		JLabel lblName_1_1_1 = new JLabel("CONSTITUENCY");
		lblName_1_1_1.setForeground(Color.WHITE);
		lblName_1_1_1.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 14));
		lblName_1_1_1.setBounds(10, 194, 118, 39);
		contentPane.add(lblName_1_1_1);
		
		Constituency = new JTextField();
		Constituency.setEditable(false);
		Constituency.setColumns(10);
		Constituency.setBounds(10, 224, 177, 20);
		contentPane.add(Constituency);
		
		JPanel panel = new JPanel();
		panel.setBounds(233, 144, 558, 283);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 558, 283);
		panel.add(scrollPane);
		DisplayCandidates();
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int MyIndex = table.getSelectedRow();
				key = Integer.valueOf(model.getValueAt(MyIndex, 0).toString());
				CandidateName.setText(model.getValueAt(MyIndex, 1).toString());
				Constituency.setText(model.getValueAt(MyIndex, 3).toString());
				Party.setText(model.getValueAt(MyIndex, 4).toString());
				Elec_Id = Integer.valueOf(model.getValueAt(MyIndex, 5).toString());
				Const_Id = Integer.valueOf(model.getValueAt(MyIndex, 2).toString());
				Cand_Id = Integer.valueOf(model.getValueAt(MyIndex, 0).toString());
				
				
			}
		});
		table.setSelectionBackground(Color.GREEN);
		table.setRowHeight(22);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Canidadate_Name", "Candidate_Constituency", "Candidate_Party"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(136);
		table.getColumnModel().getColumn(1).setPreferredWidth(142);
		table.getColumnModel().getColumn(2).setPreferredWidth(136);
		
	}
	ResultSet rs;
	
	private void DisplayCandidates() {
		try {
			Connection con = DriverManager.getConnection(url,"shoaib","103219");
			Statement s1 = con.createStatement();
			
			try{
				ResultSet rs = s1.executeQuery("select * from nationalvoting");
				table.setModel(DbUtils.resultSetToTableModel(rs));
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, "Query Didnt Worked");
			}
			
			
		}catch(Exception e2) {
			JOptionPane.showMessageDialog(null," Function Didnt worked");
			
		}
		
	}
	public NationalVoting(int voter_id) {
		VotingId = voter_id;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 817, 477);
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
		
		JLabel lblNewLabel_1 = new JLabel("WELCOME TO VOTING");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 56, 224, 39);
		contentPane.add(lblNewLabel_1);
		
		JButton btnVote = new JButton("VOTE");
		btnVote.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(key == -1 ) {
					JOptionPane.showMessageDialog(null, "Select Candidate Please !");
				}else {
					try {
					//	VCount();
						Connection con = DriverManager.getConnection(url,"shoaib","103219");
						PreparedStatement Add = con.prepareStatement("insert into national_voting(national_votes,voters_voter_id,NATIONAL_CANDIDATES_N_CANDIDATE_ID,NATIONAL_ELECTIONS_N_ELECTION_ID,CONSTITUENCY_ID) values(?,?,?,?,? )");
						try {
				    		
							Statement st1 = con.createStatement();   
				    		ResultSet Rs1 = st1.executeQuery("select MAX(national_votes)+1 from national_voting");
				    		Rs1.next();
				    		VId = Rs1.getInt(1);
				    		Add.setInt(1, VId);
				    		
				    	}catch(Exception Ex) {
				    		JOptionPane.showMessageDialog(null,"Voters Counting error");
				    	}
						try {
							Add.setInt(2, VotingId);		
						}catch (Exception e1) {
							JOptionPane.showMessageDialog(null,"Invalid Voting_id ", null, getDefaultCloseOperation());
						}
						
						try {
							Add.setInt(3,Cand_Id);		
						}catch (Exception e1) {
							JOptionPane.showMessageDialog(null,"Invalid Candidate", null, getDefaultCloseOperation());
						}
						try {
							Add.setInt(4,Elec_Id);		
						}catch (Exception e1) {
							JOptionPane.showMessageDialog(null,"Invalid Election ", null, getDefaultCloseOperation());
						}	
						try {
							Add.setInt(5,Const_Id);	
						}catch (Exception e1) {
							JOptionPane.showMessageDialog(null,"Invalid Constituency  ", null, getDefaultCloseOperation());
						}	
						Add.executeUpdate();
						JOptionPane.showMessageDialog(null,"Vote Counted ", null, getDefaultCloseOperation());
						DisplayCandidates(); 
						con.close();
						
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(null,"Vote Didnt Counted  ", null, getDefaultCloseOperation());
						
						
					}
				}
				
				
			}
		});
		
		JButton btnBack = new JButton("BACK");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Login().setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnBack.setBounds(10, 373, 95, 27);
		contentPane.add(btnBack);
		
		btnVote.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnVote.setBounds(10, 302, 164, 33);
		contentPane.add(btnVote);
		
		JLabel lblNewLabel_2 = new JLabel("CANDIDATES LIST");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(236, 106, 156, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblName_1 = new JLabel("YOUR CANDIDATE NAME");
		lblName_1.setForeground(Color.WHITE);
		lblName_1.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 14));
		lblName_1.setBounds(10, 94, 224, 39);
		contentPane.add(lblName_1);
		
		CandidateName = new JTextField();
		CandidateName.setEditable(false);
		CandidateName.setColumns(10);
		CandidateName.setBounds(10, 125, 177, 20);
		contentPane.add(CandidateName);
		
		JLabel lblName_1_1 = new JLabel("PARTY");
		lblName_1_1.setForeground(Color.WHITE);
		lblName_1_1.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 14));
		lblName_1_1.setBounds(10, 144, 56, 39);
		contentPane.add(lblName_1_1);
		
		Party = new JTextField();
		Party.setEditable(false);
		Party.setColumns(10);
		Party.setBounds(10, 174, 177, 20);
		contentPane.add(Party);
		
		
		
		JLabel lblName_1_1_1 = new JLabel("CONSTITUENCY");
		lblName_1_1_1.setForeground(Color.WHITE);
		lblName_1_1_1.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 14));
		lblName_1_1_1.setBounds(10, 194, 118, 39);
		contentPane.add(lblName_1_1_1);
		
		Constituency = new JTextField();
		Constituency.setEditable(false);
		Constituency.setColumns(10);
		Constituency.setBounds(10, 224, 177, 20);
		contentPane.add(Constituency);
		
		JPanel panel = new JPanel();
		panel.setBounds(233, 144, 558, 283);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 558, 283);
		panel.add(scrollPane);
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int MyIndex = table.getSelectedRow();
				key = Integer.valueOf(model.getValueAt(MyIndex, 0).toString());
				CandidateName.setText(model.getValueAt(MyIndex, 1).toString());
				Constituency.setText(model.getValueAt(MyIndex, 3).toString());
				Party.setText(model.getValueAt(MyIndex, 4).toString());
				Elec_Id = Integer.valueOf(model.getValueAt(MyIndex, 5).toString());
				Const_Id = Integer.valueOf(model.getValueAt(MyIndex, 2).toString());
				Cand_Id = Integer.valueOf(model.getValueAt(MyIndex, 0).toString());
				
				
			}
		});
		
		table.setSelectionBackground(Color.GREEN);
		table.setRowHeight(22);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Canidadate_Name", "Candidate_Constituency", "Candidate_Party"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(136);
		table.getColumnModel().getColumn(1).setPreferredWidth(142);
		table.getColumnModel().getColumn(2).setPreferredWidth(136);
		DisplayCandidates();
		
	
	}
	
		
	
}




