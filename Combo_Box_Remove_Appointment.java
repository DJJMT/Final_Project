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
class Combo_Box_Remove_Appointment extends JFrame
{
	//VARIABLES
	JFrame MainPanel = new JFrame();
	JTextField title_input = new JTextField();
	JButton Remove = new JButton("Remove");
	JLabel title_label = new JLabel("Event to Remove: ");
	
	//CONSTRUCTOR
	public Combo_Box_Remove_Appointment() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		//MAIN FRAME
		super("Team J's Scheduler | Remove Appointment");
		setBounds(50,50,500,300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLayout(null);
		setVisible(true);	
		  
		/*LABEL - TITLE_LABEL*/
		title_label.setLayout(null);
		title_label.setLocation(20, 10);
		title_label.setSize(150,25);
		add(title_label);
		
		//TITLE INPUT BOX
		title_input.setLayout(null);
		title_input.setLocation(200,10);
		title_input.setSize(210,25);
		add(title_input);
		
		//REMOVE BUTTON
		Remove.setLayout(null);
	    Remove.setLocation(150, 230);
	    Remove.setSize(170, 25);
		Remove.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//CLOSE WINDOW WHEN REMOVE BUTTON CLICKED
			setVisible(false);
				try {
					sql_remove_appointment();
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
	    
        //Set button to default button to select with enter key
        getRootPane().setDefaultButton(Remove);
	    
	}
	//REMOVE APPOINTMENT FUNCTION WITH SQL QUERIES
	public void sql_remove_appointment() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Statement s = GUI.con.createStatement();
		//CHANGE FUNCTIONS BASED ON HIERARCHY
		if(GUI.Level == 1)
		{
			s.executeUpdate ("DELETE FROM appointments WHERE Title = \'" + title_input.getText() + "\';");
		}
		else if(GUI.Level == 2 || GUI.Level == 3)
		{
			s.executeUpdate ("DELETE FROM appointments WHERE Title = \'" + title_input.getText() + "\' AND G_Name = \'"+GUI.ugn+"\';");
		}
		s.close();
	}
}
