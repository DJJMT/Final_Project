//package Calendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.sql.Statement;

//CLASS COMBO_BOX CHANGE APPOINTMENT INFORMATION (MANAGERS ONLY) 
@SuppressWarnings("serial")
class M_Combo_Box_Change_Appointment_Information extends JFrame
{
	//VARIABLES
	JFrame MainPanel = new JFrame();
	JTextField title_input = new JTextField();
	JTextField change_input = new JTextField();
	String box_options[] = {"-- Choose Option -- ", "Title", "Description", "Location", "Start Time", "End Time", "Date"};
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox options = new JComboBox(box_options);
	int choice = 0;
	JButton Change = new JButton("Change");
	
	//CONSTRUCTOR
	public M_Combo_Box_Change_Appointment_Information() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		//MAIN FRAME
		super("Team J's Scheduler | Change Appointment Information");
		setBounds(50,50,500,300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLayout(null);
		setVisible(true);	
		  
		//INPUTER BOX
		title_input.setLayout(null);
		title_input.setLocation(150,10);
		title_input.setSize(210,25);
		add(title_input);
		
		options.setLocation(150,40);
		options.setSize(200,25);
		add(options);
		
		//INPUTER BOX
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
			//CLOSE WINDWO WHEN CHANGE BUTTON CLICKED
			setVisible(false);
				try {
					choice = options.getSelectedIndex();
					sql_change_appointment_info(choice);
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
	    
        //Set button to default button to select with enter key
        getRootPane().setDefaultButton(Change);
	    
	}
	//CHANGE APPOINTMENT INFORMATION METHOD WITH SQL QUERIES
	public void sql_change_appointment_info(int choice) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Statement s = GUI.con.createStatement();
		//CHANGE FUNCTION BASED ON DROPDOWN BOX INPUT
		if(choice == 1)
		{
			s.executeUpdate ("UPDATE appointments SET Title = \'"+change_input.getText()+"\' WHERE G_Name = \'"+GUI.ugn+"\' AND Title = \'" + title_input.getText() + "\';");
		}
		else if(choice == 2)
		{
			s.executeUpdate ("UPDATE appointments SET Description = \'"+change_input.getText()+"\' WHERE G_Name = \'"+GUI.ugn+"\' AND Title = \'" + title_input.getText() + "\';");
		}
		else if(choice == 3)
		{
			s.executeUpdate ("UPDATE appointments SET Location = \'"+change_input.getText()+"\' WHERE G_Name = \'"+GUI.ugn+"\' AND Title = \'" + title_input.getText() + "\';");
		}
		else if(choice == 4)
		{
			s.executeUpdate ("UPDATE appointments SET Start_Time = \'"+change_input.getText()+"\' WHERE G_Name = \'"+GUI.ugn+"\' AND Title = \'" + title_input.getText() + "\';");
		}
		else if(choice == 5)
		{
			s.executeUpdate ("UPDATE appointments SET End_Time = \'"+change_input.getText()+"\' WHERE G_Name = \'"+GUI.ugn+"\' AND Title = \'" + title_input.getText() + "\';");
		}
		else if(choice == 6)
		{
			s.executeUpdate ("UPDATE appointments SET A_Date = \'"+change_input.getText()+"\' WHERE G_Name = \'"+GUI.ugn+"\' AND Title = \'" + title_input.getText() + "\';");
		}
		s.close();
	}
}


