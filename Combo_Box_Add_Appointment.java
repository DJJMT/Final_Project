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
class Combo_Box_Add_Appointment extends JFrame
{
	//VARIABLES
	JFrame MainPanel = new JFrame();
	JTextField title_input = new JTextField();
	JTextField description_input = new JTextField();
	JTextField location_input = new JTextField();
	JTextField start_hour_input = new JTextField();
	JTextField start_minute_input = new JTextField();
	JTextField end_hour_input = new JTextField();
	JTextField end_minute_input = new JTextField();
	JTextField month_input = new JTextField();
	JTextField day_input = new JTextField();
	JTextField year_input = new JTextField();
	JTextField group_input = new JTextField();
	JButton Add = new JButton("Add");
	JLabel title_label = new JLabel("Event Title: ");
	JLabel description_label = new JLabel("Description: ");
	JLabel location_label = new JLabel("Location: ");
	JLabel start_label = new JLabel("Start Time (MILITARY): ");
	JLabel end_label = new JLabel("End Time (MILITARY): ");
	JLabel date_label = new JLabel("Date (i.e. 04  09 2014): ");
	JLabel group_label = new JLabel("Group Name: ");
	
	//CONSTRUCTOR
	public Combo_Box_Add_Appointment() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		//MAIN FRAME
		super("Team J's Scheduler | Add Appointment");
		setBounds(50,50,500,300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLayout(null);
		setVisible(true);	
		  
		/*LABEL - TITLE_LABEL*/
		title_label.setLayout(null);
		title_label.setLocation(20, 10);
		title_label.setSize(80,25);
		add(title_label);
		
		//TITLE INPUT BOX
		title_input.setLayout(null);
		title_input.setLocation(150,10);
		title_input.setSize(210,25);
		add(title_input);
		
		/*LABEL - DESCRIPTION_LABEL*/
		description_label.setLayout(null);
		description_label.setLocation(20, 40);
		description_label.setSize(80,25);
		add(description_label);
		
		//DESCRIPTION INPUT BOX
		description_input.setLayout(null);
		description_input.setLocation(150,40);
		description_input.setSize(210,25);
		add(description_input);
		
		/*LABEL - LOCATION_LABEL*/
		location_label.setLayout(null);
		location_label.setLocation(20, 70);
		location_label.setSize(80,25);
		add(location_label);
		
		//LOCATION INPUT BOX
		location_input.setLayout(null);
		location_input.setLocation(150,70);
		location_input.setSize(210,25);
		add(location_input);
		
		/*LABEL - START_LABEL*/
		start_label.setLayout(null);
		start_label.setLocation(20, 100);
		start_label.setSize(150,25);
		add(start_label);
		
		//START HOUR INPUT BOX
		start_hour_input.setLayout(null);
		start_hour_input.setLocation(150,100);
		start_hour_input.setSize(110,25);
		add(start_hour_input);
		
		//START MINUT INPUT BOX
		start_minute_input.setLayout(null);
		start_minute_input.setLocation(270,100);
		start_minute_input.setSize(110,25);
		add(start_minute_input);
		
		/*LABEL - END_LABEL*/
		end_label.setLayout(null);
		end_label.setLocation(20, 130);
		end_label.setSize(150,25);
		add(end_label);
		
		//END HOUR INPUT BOX
		end_hour_input.setLayout(null);
		end_hour_input.setLocation(150,130);
		end_hour_input.setSize(110,25);
		add(end_hour_input);
		
		//END MINUTE INPUT BOX
		end_minute_input.setLayout(null);
		end_minute_input.setLocation(270,130);
		end_minute_input.setSize(110,25);
		add(end_minute_input);
		
		/*LABEL - DATE_LABEL*/
		date_label.setLayout(null);
		date_label.setLocation(20, 160);
		date_label.setSize(150,25);
		add(date_label);
		
		//MONTH INPUT BOX
		month_input.setLayout(null);
		month_input.setLocation(150,160);
		month_input.setSize(75,25);
		add(month_input);
		
		//DAY INPUT BOX
		day_input.setLayout(null);
		day_input.setLocation(230,160);
		day_input.setSize(75,25);
		add(day_input);
		
		//YEAR INPUT BOX
		year_input.setLayout(null);
		year_input.setLocation(310,160);
		year_input.setSize(75,25);
		add(year_input);
		
		if(GUI.Level == 1)
		{
			/*LABEL - GROUP_LABEL*/
			group_label.setLayout(null);
			group_label.setLocation(20, 190);
			group_label.setSize(80,25);
			add(group_label);
			
			//GROUP INPUT BOX
			group_input.setLayout(null);
			group_input.setLocation(150,190);
			group_input.setSize(210,25);
			add(group_input);
		}
		
		
		
		//CREATE BUTTON
		Add.setLayout(null);
	    Add.setLocation(150, 230);
	    Add.setSize(170, 25);
		Add.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//CLOSE WINDOW WHEN CREATE BUTTON CLICKED
			setVisible(false);
				try {
					sql_add_appointment();
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
	    add(Add);	
	}
	//ADD APPOINTMENT FUNCTION WITH SQL QUERIES
	public void sql_add_appointment() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Statement s = GUI.con.createStatement();
		//CHANGE FUNCTION BASED ON HIERARCHY
		if(GUI.Level == 1)
		{
			s.executeUpdate ("INSERT INTO appointments VALUES(default," + "\'" + group_input.getText() + "\'" + "," + "\'" + start_hour_input.getText() + ":" + start_minute_input.getText() + ":00\'" + "," + "\'" + end_hour_input.getText() + ":" + end_minute_input.getText() + ":00\'" + "," + "\'" + year_input.getText() + "-" + month_input.getText() + "-" + day_input.getText() + "\'" + "," + "\'" + location_input.getText() + "\'" + "," + "\'" + description_input.getText() + "\'" + "," + "\'" + title_input.getText() + "\'"+");");
		}
		else if(GUI.Level == 2)
		{
			s.executeUpdate ("INSERT INTO appointments VALUES(default," + "\'" + GUI.ugn + "\'" + "," + "\'" + start_hour_input.getText() + ":" + start_minute_input.getText() + ":00\'" + "," + "\'" + end_hour_input.getText() + ":" + end_minute_input.getText() + ":00\'" + "," + "\'" + year_input.getText() + "-" + month_input.getText() + "-" + day_input.getText() + "\'" + "," + "\'" + location_input.getText() + "\'" + "," + "\'" + description_input.getText() + "\'" + "," + "\'" + title_input.getText() + "\'"+");");
		}
		s.close();
	}
}
