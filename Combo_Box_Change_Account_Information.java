//package Calendar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.sql.Statement;
import java.util.regex.Pattern;

//CLASS COMBO_BOX CHANGE ACCOUNT INFORMATION
@SuppressWarnings("serial")
class Combo_Box_Change_Account_Information extends JFrame
{
	//VARIABLES
	JFrame MainPanel = new JFrame();
	JTextField lname_input = new JTextField();
	JTextField fname_input = new JTextField();
	JTextField change_input = new JTextField();
	String box_options[] = {"-- Choose Option -- ", "Hierarchy", "First Name", "Last Name", "Username", "Password", "Group Name"};
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox options = new JComboBox(box_options);
	int choice = 0;
	JButton Change = new JButton("Change");
	JLabel lname_label = new JLabel("Last Name of Account Holder: ");
	JLabel fname_label = new JLabel("First Name of Account Holder: ");
	JLabel box_label = new JLabel("Attribute to Change: ");
	JLabel change_label = new JLabel("Change To: ");
	
	//CONSTRUCTOR
	public Combo_Box_Change_Account_Information() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		//MAIN FRAME
		super("Team J's Scheduler | Change Account Information");
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
		
		/*LABEL - BOX_LABEL*/
		box_label.setLayout(null);
		box_label.setLocation(20, 70);
		box_label.setSize(200,25);
		add(box_label);
		
		//DROPDOWN BOX
		options.setLocation(250,70);
		options.setSize(200,25);
		add(options);
		
		/*LABEL - CHANGE_LABEL*/
		change_label.setLayout(null);
		change_label.setLocation(20, 100);
		change_label.setSize(200,25);
		add(change_label);
		
		//CHANGE ATTRIBUTE INPUT BOX
		change_input.setLayout(null);
		change_input.setLocation(250,100);
		change_input.setSize(210,25);
		add(change_input);		
		
		//CHANGE ATTRIBUTE BUTTON
		Change.setLayout(null);
	    Change.setLocation(150, 230);
	    Change.setSize(170, 25);
		Change.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			String FirstName_Input = fname_input.getText();
       	 	String LastName_Input = lname_input.getText();
       	 	String Change_Input = change_input.getText();
       	 	
       	 	Pattern Letters_Numbers = Pattern.compile("[a-zA-Z0-9]");
       	 	Pattern Letters_Only = Pattern.compile("[a-zA-Z]");
       	 	
       	 	boolean FirstName_hasSpecialChar = Letters_Only.matcher(FirstName_Input).find();
       	 	boolean LastName_hasSpecialChar = Letters_Only.matcher(LastName_Input).find();
       	 	boolean Change_hasSpecialChar = Letters_Numbers.matcher(Change_Input).find();
       	 	
       	 if( FirstName_hasSpecialChar == false  || LastName_hasSpecialChar == false)
 	 	 	{
       		 //Checks to make sure first and last name are present
       		 JOptionPane.showMessageDialog(null,
				    "Please enter a valid first and last name!",
				    "Name Error",
				    JOptionPane.ERROR_MESSAGE);
 	 	 	}
       	 	
       	if(Change_hasSpecialChar == false)
	   		{
       		//Checks to make sure Change field has proper values
       		JOptionPane.showMessageDialog(null,
			    "Please enter a valid Change value",
			    "Change Error",
			    JOptionPane.ERROR_MESSAGE);
	   		}
       	if(FirstName_hasSpecialChar == true  && LastName_hasSpecialChar == true
       		&& Change_hasSpecialChar == true)
       	{
			//CLOSE WINDOW WHEN CHANGE ATTRIBUTE BUTTON CLICKED
			
				try {
					choice = options.getSelectedIndex();
					sql_change_acct_info(choice);
					setVisible(false);
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
		}
		});
	    add(Change);	
	}
	//CHANGE ACCOUNT METHOD WITH SQL QUERIES
	public void sql_change_acct_info(int choice) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Statement s = GUI.con.createStatement();
		if(choice == 1)
		{
			s.executeUpdate ("UPDATE all_users SET Hierarchy = \'"+change_input.getText()+"\' WHERE Last_Name = \'" + lname_input.getText() + "\' AND First_Name = \'" + fname_input.getText() + "\';");
		}
		else if(choice == 2)
		{
			s.executeUpdate ("UPDATE all_users SET First_Name = \'"+change_input.getText()+"\' WHERE Last_Name = \'" + lname_input.getText() + "\' AND First_Name = \'" + fname_input.getText() + "\';");
		}
		else if(choice == 3)
		{
			s.executeUpdate ("UPDATE all_users SET Last_Name = \'"+change_input.getText()+"\' WHERE Last_Name = \'" + lname_input.getText() + "\' AND First_Name = \'" + fname_input.getText() + "\';");
		}
		else if(choice == 4)
		{
			s.executeUpdate ("UPDATE all_users SET User_Name = \'"+change_input.getText()+"\' WHERE Last_Name = \'" + lname_input.getText() + "\' AND First_Name = \'" + fname_input.getText() + "\';");
		}
		else if(choice == 5)
		{
			s.executeUpdate ("UPDATE all_users SET Pass_Word = \'"+change_input.getText()+"\' WHERE Last_Name = \'" + lname_input.getText() + "\' AND First_Name = \'" + fname_input.getText() + "\';");
		}
		else if(choice == 6)
		{
			s.executeUpdate ("UPDATE all_users SET G_Name = \'"+change_input.getText()+"\' WHERE Last_Name = \'" + lname_input.getText() + "\' AND First_Name = \'" + fname_input.getText() + "\';");
		}
		s.close();
	}
}


