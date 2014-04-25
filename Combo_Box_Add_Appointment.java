//package Calendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.sql.Statement;
import java.util.regex.Pattern;

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
		title_input.setLocation(165,10);
		title_input.setSize(210,25);
		add(title_input);
		
		/*LABEL - DESCRIPTION_LABEL*/
		description_label.setLayout(null);
		description_label.setLocation(20, 40);
		description_label.setSize(100,25);
		add(description_label);
		
		//DESCRIPTION INPUT BOX
		description_input.setLayout(null);
		description_input.setLocation(165,40);
		description_input.setSize(210,25);
		add(description_input);
		
		/*LABEL - LOCATION_LABEL*/
		location_label.setLayout(null);
		location_label.setLocation(20, 70);
		location_label.setSize(80,25);
		add(location_label);
		
		//LOCATION INPUT BOX
		location_input.setLayout(null);
		location_input.setLocation(165,70);
		location_input.setSize(210,25);
		add(location_input);
		
		/*LABEL - START_LABEL*/
		start_label.setLayout(null);
		start_label.setLocation(20, 100);
		start_label.setSize(150,25);
		add(start_label);
		
		//START HOUR INPUT BOX
		start_hour_input.setLayout(null);
		start_hour_input.setLocation(165,100);
		start_hour_input.setSize(110,25);
		add(start_hour_input);
		
		//START MINUT INPUT BOX
		start_minute_input.setLayout(null);
		start_minute_input.setLocation(285,100);
		start_minute_input.setSize(110,25);
		add(start_minute_input);
		
		/*LABEL - END_LABEL*/
		end_label.setLayout(null);
		end_label.setLocation(20, 130);
		end_label.setSize(150,25);
		add(end_label);
		
		//END HOUR INPUT BOX
		end_hour_input.setLayout(null);
		end_hour_input.setLocation(165,130);
		end_hour_input.setSize(110,25);
		add(end_hour_input);
		
		//END MINUTE INPUT BOX
		end_minute_input.setLayout(null);
		end_minute_input.setLocation(285,130);
		end_minute_input.setSize(110,25);
		add(end_minute_input);
		
		/*LABEL - DATE_LABEL*/
		date_label.setLayout(null);
		date_label.setLocation(20, 160);
		date_label.setSize(150,25);
		add(date_label);
		
		//MONTH INPUT BOX
		month_input.setLayout(null);
		month_input.setLocation(165,160);
		month_input.setSize(75,25);
		add(month_input);
		
		//DAY INPUT BOX
		day_input.setLayout(null);
		day_input.setLocation(245,160);
		day_input.setSize(75,25);
		add(day_input);
		
		//YEAR INPUT BOX
		year_input.setLayout(null);
		year_input.setLocation(325,160);
		year_input.setSize(75,25);
		add(year_input);
		
		if(GUI.Level == 1)
		{
			/*LABEL - GROUP_LABEL*/
			group_label.setLayout(null);
			group_label.setLocation(20, 190);
			group_label.setSize(100,25);
			add(group_label);
			
			//GROUP INPUT BOX
			group_input.setLayout(null);
			group_input.setLocation(165,190);
			group_input.setSize(210,25);
			add(group_input);
		}
		
		
		
		//CREATE BUTTON
		Add.setLayout(null);
	    Add.setLocation(165, 230);
	    Add.setSize(170, 25);
		Add.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			String Title_Input = title_input.getText();
			String Location_Input = location_input.getText();	
			String Starthour_Input = start_hour_input.getText();		
			String Startminute_Input = start_minute_input.getText();
			String Endhour_Input = end_hour_input.getText();
			String Endminute_Input = end_minute_input.getText();	
			String Month_Input = month_input.getText();	
			String Day_Input = day_input.getText();
			String Year_Input = year_input.getText();
			String Group_Input = group_input.getText();

			Pattern Letters_Numbers = Pattern.compile("[a-zA-Z0-9]");
       	 	Pattern Letters_Only = Pattern.compile("[a-zA-Z]");
       	 	Pattern Numbers_Only = Pattern.compile("[0-9]");
       	 
       	 	boolean TitleInput_hasSpecialChar = Letters_Only.matcher(Title_Input).find();
       	 	boolean LocationInput_hasSpecialChar = Letters_Numbers.matcher(Location_Input).find();
       	 	boolean StarthourInput_hasSpecialChar = Numbers_Only.matcher(Starthour_Input).find();
       	 	boolean StartminuteInput_hasSpecialChar = Numbers_Only.matcher(Startminute_Input).find();
       	 	boolean EndhourInput_hasSpecialChar = Numbers_Only.matcher(Endhour_Input).find();
       	 	boolean EndminuteInput_hasSpecialChar = Numbers_Only.matcher(Endminute_Input).find();
       	 	boolean MonthInput_hasSpecialChar = Numbers_Only.matcher(Month_Input).find();
       	 	boolean DayInput_hasSpecialChar = Numbers_Only.matcher(Day_Input).find();
       	 	boolean YearInput_hasSpecialChar = Numbers_Only.matcher(Year_Input).find();
       	 	boolean GroupInput_hasSpecialChar = Letters_Only.matcher(Group_Input).find();
       	 	
       	 	if(TitleInput_hasSpecialChar == false)
       	 	{//Makes sure Title has valid value
       	 		JOptionPane.showMessageDialog(null, 
       	 				"Please enter a Title for the Appointment!",
       	 				"Title Error",							
       	 				JOptionPane.ERROR_MESSAGE);
       	 	}	
       	 	
       	 	if(LocationInput_hasSpecialChar == false)
   	 		{//a Valid Location
   	 			JOptionPane.showMessageDialog(null, 
   	 					"Please enter a Location for the Appointment!",
   	 					"Location Error",							
   	 					JOptionPane.ERROR_MESSAGE);
   	 		}
       	 	
       	 	if((StarthourInput_hasSpecialChar == false || StartminuteInput_hasSpecialChar == false ||
       	 	   EndhourInput_hasSpecialChar == false || EndminuteInput_hasSpecialChar == false)  
       	 	   )
       	 	{//Has a valid start and end time
       	 		JOptionPane.showMessageDialog(null, 
	 					"Please enter a correct start and end time for the Appointment!",
	 					"Timing Error",							
	 					JOptionPane.ERROR_MESSAGE); 
       	 	}
       	 	
       	 	if(MonthInput_hasSpecialChar == false || DayInput_hasSpecialChar == false ||
         	 	   YearInput_hasSpecialChar == false)
         	{//Has a valid month, day, and year
         	 		JOptionPane.showMessageDialog(null, 
  	 					"Please enter a correct date for the Appointment!",
  	 					"Date Error",							
  	 					JOptionPane.ERROR_MESSAGE); 
       	 	}
       	 	
       	 	if(GroupInput_hasSpecialChar == false)
    	 	{//Valid Group name
    	 		JOptionPane.showMessageDialog(null, 
    	 				"Please enter a valid Group for the Appointment!",
    	 				"Group Error",							
    	 				JOptionPane.ERROR_MESSAGE);
    	 	}
       	 	
       	 	if(TitleInput_hasSpecialChar == true &&	
       	 		LocationInput_hasSpecialChar == true &&	StarthourInput_hasSpecialChar == true && StartminuteInput_hasSpecialChar == true &&
            	 	   EndhourInput_hasSpecialChar == true && EndminuteInput_hasSpecialChar == true && 
               	 	   MonthInput_hasSpecialChar == true && DayInput_hasSpecialChar == true &&
               	 	   YearInput_hasSpecialChar == true && GroupInput_hasSpecialChar == true)
       	 	{
       	 		//Makes sure all conditions are met, and then enters data into database
				try {
					sql_add_appointment();
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
	    
        //Set button to default button to select with enter key
//        getRootPane().setDefaultButton(Add);
        SwingUtilities.getRootPane(Add).setDefaultButton(Add);
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
