//package Calendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.sql.Statement;
import java.util.regex.Pattern;

//CLASS COMBO_BOX ADD GROUP
@SuppressWarnings("serial")
class Combo_Box_Add_Group extends JFrame
{
	//VARIABLES
	JFrame MainPanel = new JFrame();
	JTextField group_name_input = new JTextField();
	JTextField member_count_input = new JTextField();
	JButton Add = new JButton("Add");
	JLabel group_label = new JLabel("Group Name: ");
	JLabel member_count_label = new JLabel("Member Count: ");
	
	//CONSTRUCTOR
	public Combo_Box_Add_Group() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		//MAIN FRAME
		super("Team J's Scheduler | Add Group");
		setBounds(50,50,500,300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLayout(null);
		setVisible(true);	
		  
		/*LABEL - GROUP_LABEL*/
		group_label.setLayout(null);
		group_label.setLocation(20, 10);
		group_label.setSize(80,25);
		add(group_label);
		
		//GROUP NAME INPUT BOX
		group_name_input.setLayout(null);
		group_name_input.setLocation(150,10);
		group_name_input.setSize(210,25);
		add(group_name_input);
		
		/*LABEL - MEMBER_COUNT_LABEL*/
		member_count_label.setLayout(null);
		member_count_label.setLocation(20, 40);
		member_count_label.setSize(100,25);
		add(member_count_label);
		
		//MEMBER COUNT INPUT BOX
		member_count_input.setLayout(null);
		member_count_input.setLocation(150,40);
		member_count_input.setSize(210,25);
		add(member_count_input);
		
		
		//CREATE BUTTON
		Add.setLayout(null);
	    Add.setLocation(150, 230);
	    Add.setSize(170, 25);
		Add.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			String GroupName_Input = group_name_input.getText();
			String MemberCount_Input = member_count_input.getText();
			
			Pattern Letters_Only = Pattern.compile("[a-zA-Z]");
			Pattern Numbers_Only = Pattern.compile("[0-9]");
			
			boolean GroupName_hasSpecialChar = Letters_Only.matcher(GroupName_Input).find();
			boolean MemberCount_hasSpecialChar = Numbers_Only.matcher(MemberCount_Input).find();

			if(GroupName_hasSpecialChar == false)
				{ //Makes sure group name is valid
        		//Checks to make sure group name is letters only
        		JOptionPane.showMessageDialog(null,
    				    "Please enter a valid Group Name!",
    				    "Name Error",
    				    JOptionPane.ERROR_MESSAGE);
        	   	}
			
			if(MemberCount_hasSpecialChar == false)
				{
        		//Checks to make sure Member count is an integer
        		JOptionPane.showMessageDialog(null,
    				    "Please enter a valid Group Count!",
    				    "Count Error",
    				    JOptionPane.ERROR_MESSAGE);
        	   	}
			
			if(GroupName_hasSpecialChar == true && MemberCount_hasSpecialChar == true)
			//CLOSE WINDOW WHEN CREATE BUTTON CLICKED
			{
				try {
					sql_add_group();
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
	    add(Add);	
	}
	//ADD GROUP FUNCTION WITH SQL QUERIES
	public void sql_add_group() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Statement s = GUI.con.createStatement();
		s.executeUpdate("INSERT INTO groups VALUES(default," +"\'"+group_name_input.getText()+"\',"+member_count_input.getText()+");");
		s.close();
	}
}

