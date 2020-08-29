//import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;

public class ProjectFrame
{
	/*GUI Object Variables*/
	JFrame frame;
	private JLabel lblTeam;
	private JLabel lblFileLoaded;
	private JLabel lblFileSaved;
	private JTextPane errorPane;
	private JScrollPane errorScrollPane;
	private JScrollPane docScrollPane;
	private JTextPane editedFilePane;
	private JFileChooser fileChooser;
	private JFileChooser fileSaver;
	private BackEnd backEndObj;
	
	/*GUI Data Variables*/
	private String pathToFile;
	
	//Base Constructor
	/*public ProjectFrame()
	{
		
	}*/

	/*GUI Constructor application.*/
	public ProjectFrame(BackEnd backEnd) 
	{
		backEndObj = backEnd;
		initialize();
	}
	
	/*Clear Document*/
	//Empties the edited document panel of all text*/
	public boolean clearDoc()
	{
		editedFilePane.setText("");
		
		return true;
	}
	
	/*Add to Document*/
	/*Adds formatted text to the edited document panel*/
	public boolean addToDoc(String toAdd)
	{
		editedFilePane.setText(editedFilePane.getText() + toAdd);
		
		return true;
	}
	
	/*Get Document Text*/
	/*Returns the text in the document panel as formatted text*/
	public String getDocText()
	{
		return editedFilePane.getText();
	}
	
	/*Unable to Load*/
	/*Indicates to user that file could not be loaded*/
	public void UnableToLoad()
	{
		errorPane.setText(errorPane.getText() + "Error: File Could Not be Loaded! \n"); //Display Error
		lblFileLoaded.setForeground(Color.RED); //Change color
		lblFileLoaded.setText("Could not load file"); //Indicate status
	}
	
	/*Incorrect File Type*/
	/*Indicates to user that file was not of the right type*/
	public void incorrectFile()
	{
		errorPane.setText(errorPane.getText() + "Error: File of wrong type! \n"); //Display Error
		lblFileLoaded.setForeground(Color.RED); //Change color
		lblFileLoaded.setText("Could not load file"); //Indicate status
	}
	
	/*Incorrect Flag Type*/
	/*Indicates to user that an incorrect flag was used*/
	public void incorrectFlag(String flag)
	{
		errorPane.setText(errorPane.getText() + "Error: Incorrect flag " + flag + " type used! \n"); //Display Error
	}
	
	/*Unable to Save*/
	/*Indicates to the user that file could not be saved*/
	public void UnableToSave()
	{
		errorPane.setText(errorPane.getText() + "Error: File Could Not be Saved! \n"); //Display Error
		lblFileSaved.setForeground(Color.RED); //Change color
		lblFileSaved.setText("Could not save file"); //Indicate status
		lblFileLoaded.setText(""); //Change load label text
	}
	
	/*Incorrect Flag*/
	/*Indicates to the user that an invalid flag is in the text document*/
	public void InvalidFlag()
	{
		errorPane.setText(errorPane.getText() + "Error: Invalid Flag in Text File! \n"); //Display Error
	}
	
	/*Get File Path*/
	/*Returns the currently opened files' filepath*/
	public String getFilePath()
	{
		if(pathToFile != "")
			return pathToFile;
		else
			return null;
	}
	
	/*File Search*/
	/*Opens a file search window for loading text files*/
	private String fileSearch()
	{
		fileChooser.setVisible(true); //Sets the file chooser to visible
		
		int returnVal = fileChooser.showOpenDialog(null);
		pathToFile = "";
		
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
			pathToFile = fileChooser.getSelectedFile().getAbsolutePath();
		}
		return pathToFile;
	}
	
	/*File Save*/
	/*Saves a file thru the search window*/
	private boolean fileSave() throws FileNotFoundException
	{
		boolean check = false;
		fileSaver.setVisible(true); //Make box visible
		int returnVal = fileSaver.showSaveDialog(null); //Shows save option
		
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
			String filepath = fileSaver.getSelectedFile().getAbsolutePath(); //Finds file path
			PrintWriter file = new PrintWriter(new File(filepath + ".txt")); //Opens & creates new file
			file.write(getDocText()); //Writes to the file
			file.close(); //Close file safely
			check = true; //Set file saved to true
		}
		else
		{
			UnableToSave(); //Throw an error
			check = false; //Set file saved to false
		}
		return check;
	}

	/**Initialize **/
	/*Initializes the contents of the GUI*/
	private void initialize() 
	{
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(102, 102, 102));
		frame.setBounds(100, 100, 832, 873);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/*Project Title Label*/
		lblTeam = new JLabel("Team 38 Group Project"); //Instantiate Label
		lblTeam.setForeground(Color.WHITE); //Color of Label Font
		lblTeam.setFont(new Font("Calibri", Font.BOLD, 20)); //Font style of label
		lblTeam.setBounds(311, 10, 214, 48); //Label boundary/location
		frame.getContentPane().add(lblTeam); //Add label to frame
		
		/*Load File Button*/
		JButton btnLoadFile = new JButton("Load File"); //Instantiate the Button
		btnLoadFile.addActionListener(new ActionListener() //Action Handler for button pressed
		{
			public void actionPerformed(ActionEvent arg0)
			{
				String filePath = fileSearch(); //Opens file chooser dialog box
				if(filePath == "") //Checks for no file selected
				{
					lblFileLoaded.setForeground(Color.RED); //Change load label color
					lblFileLoaded.setText("Unable to Load"); //Change load label text
					errorPane.setText(errorPane.getText() + "Error: Load Field Empty! \n");
					lblFileSaved.setText(""); //Change saved label text
				}
				else //If file is selected
				{
					lblFileLoaded.setForeground(Color.GREEN); //Change load label color
					lblFileLoaded.setText("File Loaded Successfully"); //Change load label text
					lblFileSaved.setText(""); //Change save label text
					try //Try to read the file
					{
						if(filePath.length() > 3) //Check for apropriate file
						{
							String last3 = filePath.substring(filePath.length() - 3);
							if(last3.equals("txt")) //Proceed to parse file
							{
								backEndObj.loadDocPressed(filePath);
							}
							else //Throw file type error
							{
								incorrectFile();
							}
						}
						else //Throw file type error
						{
							incorrectFile();
						}
					} 
					catch (IOException e) //If unable to read throws error message
					{
						UnableToLoad();
					}
				}
			}
		});
		btnLoadFile.setBackground(new Color(255, 204, 51)); //Color of the button
		btnLoadFile.setFont(new Font("Calibri", Font.BOLD, 12)); //Font style of button
		btnLoadFile.setBounds(78, 50, 105, 34); //Button Boundary/Location
		frame.getContentPane().add(btnLoadFile); //Add Button to frame
		
		/*Label Indicating File Load Status*/
		lblFileLoaded = new JLabel(""); //Instantiate the label
		lblFileLoaded.setForeground(Color.GREEN); //Basic start color
		lblFileLoaded.setFont(new Font("Calibri", Font.BOLD, 12)); //Font type
		lblFileLoaded.setBounds(88, 95, 160, 34); //Label boundary/location
		frame.getContentPane().add(lblFileLoaded); //Add to frame
		
		/*Save File Button*/
		JButton btnSaveFileAs = new JButton("Save File As"); //Instantiate the Button
		btnSaveFileAs.addActionListener(new ActionListener() //Perform action
		{
			public void actionPerformed(ActionEvent e) 
			{
				//Button Action
				try 
				{
					boolean check = fileSave(); //Run file save dialog box
					if(check) //Perform gui actions
					{
						lblFileSaved.setForeground(Color.GREEN); //Change save label color
						lblFileSaved.setText("File Saved Successfully"); //Change save label text
						lblFileLoaded.setText(""); //Change load label text
						backEndObj.saveDocPressed(); //Temp method to check stuff
					}
				} 
				catch (FileNotFoundException e1) //Exception for an empty field
				{
					UnableToSave();
				}
			}
		});
		btnSaveFileAs.setBackground(new Color(51, 204, 51)); //Color of the button
		btnSaveFileAs.setFont(new Font("Calibri", Font.BOLD, 12)); //Font style of button
		btnSaveFileAs.setBounds(78, 140, 105, 34); //Button Boundary/Location
		frame.getContentPane().add(btnSaveFileAs); //Add Button to frame
		
		/*Label Indicating File Save Status*/
		lblFileSaved = new JLabel(""); //Instantiate label
		lblFileSaved.setForeground(Color.GREEN); //Font color
		lblFileSaved.setFont(new Font("Calibri", Font.BOLD, 12)); //Font style
		lblFileSaved.setBounds(67, 185, 181, 34); //Label boundary/location
		frame.getContentPane().add(lblFileSaved); //Add to frame
		
		/*Text Pane to display formatted text*/
		editedFilePane = new JTextPane(); //Instantiate pane
		editedFilePane.setFont(new Font("Consolas", Font.PLAIN, 11));
		editedFilePane.setEditable(false); //Not editable by user
		docScrollPane = new JScrollPane(editedFilePane);
		docScrollPane.setBounds(265, 106, 541, 717); //Text Pane bounds/location
		frame.getContentPane().add(docScrollPane); //Add to frame
		
		/*Text Output Label*/
		JLabel lblTextOutput = new JLabel("Text Output"); //Instantiate label
		lblTextOutput.setForeground(Color.WHITE); //Font color
		lblTextOutput.setFont(new Font("Calibri", Font.BOLD, 15)); //Label font settings
		lblTextOutput.setBounds(491, 69, 83, 36); //Label location/bounds
		frame.getContentPane().add(lblTextOutput); //Add to frame
		
		/*Error Log Pane Holds the error log*/
		errorPane = new JTextPane(); //Instantiate pane
		errorPane.setEditable(false); //Not editable by user
		errorScrollPane = new JScrollPane(errorPane); //Create Scroll Pane wrap
		errorScrollPane.setBounds(10, 277, 245, 546); //Set scrollpane bounds
		frame.getContentPane().add(errorScrollPane); //Add to frame
		
		/*Error Log Label*/
		JLabel lblErrorLog = new JLabel("Error Log"); //Instantiate label
		lblErrorLog.setFont(new Font("Calibri", Font.BOLD, 15)); //Label font settings
		lblErrorLog.setForeground(Color.RED); //Font color
		lblErrorLog.setBounds(91, 247, 83, 19); //Label location/bounds
		frame.getContentPane().add(lblErrorLog); //Add to frame
		
		/*Allows choosing file from a window to load*/
		fileChooser = new JFileChooser(); //Create dialog box
		fileChooser.setVisible(false); //Set to not visible initially
		//fileChooser.setBounds(39, 11, 582, 397);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT files", "txt"); //Create text filter
		fileChooser.setFileFilter(filter); //Filter document choices
		frame.getContentPane().add(fileChooser); //Add to frame
		
		/*Allows saving file a from a window*/
		fileSaver = new JFileChooser();
		fileSaver.setVisible(false);
		//fileSaver.setBounds(39, 11, 582, 397);
		fileSaver.setFileFilter(filter);
		frame.getContentPane().add(fileSaver);
	}
}