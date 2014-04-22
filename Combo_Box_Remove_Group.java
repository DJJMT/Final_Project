//package Calendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.sql.Statement;

//CLASS COMBO_BOX REMOVE GROUP
@SuppressWarnings("serial")
class Combo_Box_Remove_Group extends JFrame
{
	//VARIABLES
	JFrame MainPanel = new JFrame();
	JTextField group_name_input = new JTextField();
	JButton Remove = new JButton("Remove");
	JLabel group_label = new JLabel("Group to Remove: ");
	
	//CONSTRUCTOR
	public Combo_Box_Remove_Group() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		//MAIN FRAME
		super("Team J's Scheduler Remove Group");
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
		group_name_input.setLocation(200,10);
	    group_name_input.setSize(210,25);
		add(group_name_input);
		
		//REMOVE BUTTON
		Remove.setLayout(null);
	    Remove.setLocation(150, 230);
	    Remove.setSize(170, 25);
		Remove.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//CLOSE WINDOW WHEN REMOVE BUTTON CLICKED
			setVisible(false);
				try {
					sql_remove_group();
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
	//REMOVE GROUP FUNCTION WITH SQL QUERIES
	public void sql_remove_group() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Statement s = GUI.con.createStatement();
		s.executeUpdate ("DELETE FROM groups WHERE G_Name = \'" + group_name_input.getText() + "\';");
		s.close();
	}
}
