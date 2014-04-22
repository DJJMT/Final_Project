//package Calendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.sql.Statement;

//CLASS COMBO_BOX CHANGE ACCOUNT INFORMATION
@SuppressWarnings("serial")
class Combo_Box_Change_Group_Information extends JFrame
{
	//VARIABLES
	JFrame MainPanel = new JFrame();
	JTextField group_name_input = new JTextField();
	JTextField change_input = new JTextField();
	String box_options[] = {"-- Choose Option -- ", "Group Name"};
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox options = new JComboBox(box_options);
	int choice = 0;
	JButton Change = new JButton("Change");
	JLabel group_label = new JLabel("Group to Change: ");
	JLabel box_label = new JLabel("Attribute to Change: ");
	JLabel change_label = new JLabel("Change To: ");
	
	//CONSTRUCTOR
	public Combo_Box_Change_Group_Information() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		//MAIN FRAME
		super("Team J's Scheduler | Change Group Information");
		setBounds(50,50,500,300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLayout(null);
		setVisible(true);	
		  
		/*LABEL - GROUP_LABEL*/
		group_label.setLayout(null);
		group_label.setLocation(20, 10);
		group_label.setSize(150,25);
		add(group_label);
		
		//GROUP NAME INPUT BOX
		group_name_input.setLayout(null);
		group_name_input.setLocation(150,10);
		group_name_input.setSize(210,25);
		add(group_name_input);
		
		/*LABEL - BOX_LABEL*/
		box_label.setLayout(null);
		box_label.setLocation(20, 40);
		box_label.setSize(150,25);
		add(box_label);
		
		//DROPDOWN BOX
		options.setLocation(150,40);
		options.setSize(200,25);
		add(options);
		
		/*LABEL - CHANGE_LABEL*/
		change_label.setLayout(null);
		change_label.setLocation(20, 70);
		change_label.setSize(150,25);
		add(change_label);
		
		//CHANGE GROUP INFO BOX
		change_input.setLayout(null);
		change_input.setLocation(150,70);
		change_input.setSize(210,25);
		add(change_input);		
		
		//CHANGE BUTTON
		Change.setLayout(null);
	    Change.setLocation(150, 230);
	    Change.setSize(170, 25);
		Change.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
				try {
					choice = options.getSelectedIndex();
					sql_edit_group(choice);
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
	    add(Change);	
	}
	//EDIT GROUP INFORMATION METHOD WITH SQL QUERIES
	public void sql_edit_group(int choice) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Statement s = GUI.con.createStatement();
		if(choice == 1)
		{
			s.executeUpdate ("UPDATE groups SET G_Name = \'"+change_input.getText()+"\' WHERE G_Name = \'" + group_name_input.getText() + "\';");
		}
		
		s.close();
	}
}



