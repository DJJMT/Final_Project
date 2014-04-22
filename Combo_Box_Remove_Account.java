//package Calendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.sql.Statement;

//CLASS COMBO_BOX ADD APPOINTMENT
@SuppressWarnings("serial")
class Combo_Box_Remove_Account extends JFrame
{
	//VARIABLES
	JFrame MainPanel = new JFrame();
	JTextField lname_input = new JTextField();
	JTextField fname_input = new JTextField();
	JButton Remove = new JButton("Remove");
	JLabel lname_label = new JLabel("Last Name of Account to Remove: ");
	JLabel fname_label = new JLabel("First Name of Account to Remove: ");
	
	//CONSTRUCTOR
	public Combo_Box_Remove_Account() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		//MAIN FRAME
		super("Team J's Scheduler | Remove Account");
		setBounds(50,50,500,300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLayout(null);
		setVisible(true);	
		  
		/*LABEL - LNAME_LABEL*/
		lname_label.setLayout(null);
		lname_label.setLocation(20, 10);
		lname_label.setSize(200,25);
		add(lname_label);
		
		//LAST NAME INPUT BOX
		lname_input.setLayout(null);
		lname_input.setLocation(250,10);
		lname_input.setSize(210,25);
		add(lname_input);
		
		/*LABEL - FNAME_LABEL*/
		fname_label.setLayout(null);
		fname_label.setLocation(20, 40);
		fname_label.setSize(200,25);
		add(fname_label);
		
		//FIRST NAME INPUT BOX
		fname_input.setLayout(null);
		fname_input.setLocation(250,40);
		fname_input.setSize(210,25);
		add(fname_input);
		
		//REMOVE BUTTON
		Remove.setLayout(null);
	    Remove.setLocation(150, 230);
	    Remove.setSize(170, 25);
		Remove.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//CLOSE WINDOW WHEN REMOVE BUTTON CLICKED
			setVisible(false);
				try {
					sql_remove_account();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}          
		});
	    add(Remove);	
	}
	//REMOVE ACCOUNT METHOD WITH SQL QUERIES
	public void sql_remove_account() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Statement s = GUI.con.createStatement();
		//CHANGE FUNCTIONS BASED ON HIERARCHY
		if(GUI.Level == 1)
		{
			s.executeUpdate ("DELETE FROM all_users WHERE Last_Name = \'" + lname_input.getText() + "\' AND First_Name = \'" + fname_input.getText() + "\';");
		}
		else if(GUI.Level == 2)
		{
			s.executeUpdate ("DELETE FROM all_users WHERE G_Name = \'"+GUI.ugn+"\' AND Last_Name = \'" + lname_input.getText() + "\' AND First_Name = \'" + fname_input.getText() + "\';");
		}
		s.close();
	}
}
