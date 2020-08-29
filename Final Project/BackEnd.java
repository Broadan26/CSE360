import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BackEnd 
{
	private ProjectFrame window; //Object to the GUI
	
	/*Global flag variables*/
	private int justified = 0; //0 for Left, 1 for centered, 2 for Right
	private int title = 0; //0 for not title, 1 for title
	private int spacing = 0; //0 for single, 1 for double
	private int indent = 0; //0 for no indent, 1 for regular, 2 for block
	private int columns = 0; //0 for a single col, 1 for a double column
	private int maxWidth = 80; //Max Allowable Width
	
	//Base Constructor
	public BackEnd()
	{
		
	}
	
	/*Set Project Frame*/
	/*Sets which GUI this back end is associated with*/
	public void setProjectFrame(ProjectFrame GUI)
	{
		window = GUI;
	}
	
	/*Reset Defaults*/
	/*Resets the default values of the document formatting*/
	public void resetDefaults()
	{
		justified = 0;
		title = 0;
		spacing = 0;
		indent = 0;
		columns = 0;
	}
	
	/*Parse Flags*/
	/*Parses the flag variables and returns true if flags altered.*/
	private boolean parseFlags(String add)
	{
		boolean flagCheck = false;
		String[] tokens = add.split(" "); //Split input
		int count = 0;
		
		while(count < tokens.length)
		{
			if(tokens[count].trim().equals("-l")) //Check left justified flag
			{
				justified = 0;
				flagCheck = true;
			}
			else if(tokens[count].trim().equals("-c")) //Check center justified flag
			{
				justified = 1;
				flagCheck = true;
			}
			else if(tokens[count].trim().equals("-r")) //Check right justified flag
			{
				justified = 2;
				flagCheck = true;
			}
			else if(tokens[count].trim().equals("-t")) //Check for title flag
			{
				title = 1;
				flagCheck = true;
			}
			else if(tokens[count].trim().equals("-s")) //Check for single space flag
			{
				spacing = 0;
				flagCheck = true;
			}
			else if(tokens[count].trim().equals("-d")) //Check for double space flag
			{
				spacing = 1;
				flagCheck = true;
			}
			else if(tokens[count].trim().equals("-i")) //Check for indent flag
			{
				indent = 1;
				flagCheck = true;
			}
			else if(tokens[count].trim().equals("-b")) //Check for block flag
			{
				indent = 2;
				flagCheck = true;
			}
			else if(tokens[count].trim().equals("-n")) //Check for end block flag
			{
				indent = 0;
				flagCheck = true;
			}
			else if(tokens[count].trim().equals("-1")) //Check for 1 column text flag
			{
				columns = 0;
				flagCheck = true;
			}
			else if(tokens[count].trim().equals("-2")) //Check for 2 columns text flag
			{
				columns = 1;
				flagCheck = true;
			}
			else if(tokens[count].trim().equals("-e")) //Check for blank line flag
			{
				window.addToDoc("\n");
				flagCheck = true;
			}
			else if(tokens[count].trim().length() > 0) //Check for incorrect flag usage
			{
				tokens[count] = tokens[count].trim();
				if(tokens[count].substring(0, 1).equals("-")) //Look for flag delimiter
				{
					String flag = tokens[count]; //Isolate flag
					window.incorrectFlag(flag); //Send error
					flagCheck = true; //Still check flag box
				}
			}
			else if(add.equals(""))
			{
				flagCheck = true;
			}
			count++;
		}
		return flagCheck;
	}
	
	/*Add Spacing*/
	/*Adds spacing between lines so they aren't in a single line*/
	private String[] addSpacing(String[] editedLine)
	{
		int spot = 0;
		while(spot < editedLine.length)
		{
			if(editedLine[spot] != "")
				editedLine[spot] = editedLine[spot] + "\n";
			spot++;
		}
		
		return editedLine;
	}
	
	/*Double Space*/
	/*Double spaces the required lines*/
	private String[] doubleSpace(String[] editedLine)
	{
		int spot = 0;
		while(spot < editedLine.length)
		{
			if(editedLine[spot] != "")
				editedLine[spot] = editedLine[spot] + "\n";
			spot++;
		}
		
		return editedLine;
	}
	
	/*Justify Line Center*/
	/*Justifies the line to the center if necessary*/
	private String[] justifyCenter(String[] editedLine)
	{
		int spot = 0;
		
		while(spot < editedLine.length)
		{
			while(editedLine[spot].length() < maxWidth - 2 && editedLine[spot] != "") //Add spaces both sides equally
			{
				editedLine[spot] = " " + editedLine[spot];
				editedLine[spot] = editedLine[spot] + " ";
			}
			spot++;
		}
		
		return editedLine;
	}
	
	/*Justify Line Right*/
	/*Justifies the line to the right if necessary*/
	private String[] justifyRight(String[] editedLine)
	{
		int spot = 0;
		
		while(spot < editedLine.length)
		{
			if(editedLine[spot].endsWith(" ")) //Trim spacing to the right
			{
				editedLine[spot] = editedLine[spot].trim();
			}
			while(editedLine[spot].length() < maxWidth-1 && editedLine[spot] != "") //Add spaces to the left
			{
				editedLine[spot] = " " + editedLine[spot];
			}
			spot++;
		}
		
		return editedLine;
	}
	
	/*Justify Line Right*/
	/*Justifies the line to the right if necessary for multiple columns*/
	private String[] justifyRight(String[] editedLine, int colMax)
	{
		int spot = 0;
		
		while(spot < editedLine.length)
		{
			if(editedLine[spot].endsWith(" ")) //Trim spacing to the right
			{
				editedLine[spot] = editedLine[spot].trim();
			}
			while(editedLine[spot].length() < colMax-1 && editedLine[spot] != "") //Add spaces to the left
			{
				editedLine[spot] = " " + editedLine[spot];
			}
			spot++;
		}
		
		return editedLine;
	}
	
	/*Justify Line Center*/
	/*Justifies the line center for multiple columns*/
	private String[] justifyCenter(String[] editedLine, int colMax)
	{
		int spot = 0;
		
		while(spot < editedLine.length)
		{
			while(editedLine[spot].length() < colMax - 2 && editedLine[spot] != "") //Add spaces both sides equally
			{
				editedLine[spot] = " " + editedLine[spot];
				editedLine[spot] = editedLine[spot] + " ";
			}
			spot++;
		}
		
		return editedLine;
	}
	
	/*Justify Left*/
	/*Justifies the line to the left for multiple columns*/
	private String[] justifyLeft(String[] editedLine, int colMax)
	{
		int spot = 0;
		
		while(spot < editedLine.length)
		{
			if(editedLine[spot] != "")
			{
				while(editedLine[spot].length() < colMax - 1) //Add spacing to the right
				{
					editedLine[spot] = editedLine[spot] + " ";
				}
			}
			spot++;
		}
		
		return editedLine;
	}
	
	/*Load Doc Pressed*/
	/*Opens the file and displays the edited version to the user*/
	public void loadDocPressed(String file) throws IOException
	{
		File fileOpen = new File(file);
		BufferedReader br = null;
		try //Try to open the file
		{
			br = new BufferedReader(new FileReader(fileOpen));
		} 
		catch (FileNotFoundException e) //Could not open the file
		{
			window.UnableToLoad();
		}
		
		String add; //For line to read
		while((add = br.readLine()) != null) //Adds document text to the user screen
		{
			
			boolean flagCheck = parseFlags(add); //Make flag changes
			
			if(!flagCheck) //Parse the line to add
			{
				String[] addLine; //String to be added to preview window
				
				/*Parse based on flag settings*/
				if(title == 1) //For title
					addLine = parseTitle(add);
				else if(columns == 0) //1 column
					addLine = parseOneCol(add);
				else if(columns == 1) //2 columns
					addLine = parseTwoCol(add); 
				else //Make sure addLine has a value
					addLine = parseOneCol(add);
				
				for(int i = 0; i < addLine.length; i++) //Add strings to GUI
				{
					if(addLine[i] != "")
						window.addToDoc(addLine[i]);
				}
			}
		}
		
		resetDefaults(); //Reset defaults for next file
	}
	
	/*Parse Two Col*/
	/*Parses a line into two columns*/
	private String[] parseTwoCol(String line)
	{
		int spot = 0; //Tracks spot in editedLine
		int count = 0; //Tracks spot in tokens
		String[] tokens = line.split(" "); //Split input
		String[] editedLine = new String[tokens.length]; //Formatted input
		int twoColMax = 35; //Max width of a single column
		String columnSpace = "          "; //Space between the two columns
		int lineNum = 1; //Tracks number of lines created to find halfway point
		
		for(int i = 0; i < editedLine.length; i++) //Initialize array to null
		{
			editedLine[i] = "";
		}
		
		while(count < tokens.length)
		{
			if(editedLine[spot].length() + tokens[count].length() + 1 < twoColMax) //Check for fit
			{
				if(indent == 2 && editedLine[spot].length() < 10) //Check for block first time
				{
					editedLine[spot] = "          ";
				}
				if(indent == 1) //Indent
				{
					editedLine[spot] = "     ";
					editedLine[spot] = editedLine[spot] + tokens[count] + " "; //Add to array
					indent = 0; //Reset indent
				}
				else if(indent == 0 || indent == 2) //No indent
				{
					editedLine[spot] = editedLine[spot] + tokens[count] + " "; //Add to array
				}
				count++;
			}
			else //Else move to next spot
			{
				spot++;
				lineNum++;
			}
		}

		if(justified == 2) //Justify the line right
		{
			editedLine = justifyRight(editedLine, twoColMax);
		}
		else if(justified == 1) //Justify line center
		{
			editedLine = justifyCenter(editedLine, twoColMax);
		}
		else if(justified == 0) //Justify line left
		{
			editedLine = justifyLeft(editedLine, twoColMax);
		}
		
		//Half the input and separate into two columns
		spot = 0;
		int secondSpot;
		if(lineNum % 2 == 0) //This one is buggy
		{
			secondSpot = lineNum / 2;
			
			while(secondSpot < lineNum)
			{
				if(spot != secondSpot)
				{
					editedLine[spot] = editedLine[spot] + columnSpace + editedLine[secondSpot];
				}
				else
					editedLine[spot] = editedLine[spot];
				spot++;
				secondSpot++;
			}
		}
		else //This one is working fine
		{
			secondSpot = ((lineNum + 1) / 2);
			
			while(secondSpot <= lineNum)
			{
				if(spot != secondSpot)
				{
					editedLine[spot] = editedLine[spot] + columnSpace + editedLine[secondSpot];
				}
				else
					editedLine[spot] = editedLine[spot];
				spot++;
				secondSpot++;
			}
		}
		
		//Eliminate the duplicated parts
		while(spot < lineNum)
		{
			editedLine[spot] = "";
			spot++;
		}
		
		editedLine = addSpacing(editedLine); //Add spacing between lines
		
		if(spacing == 1) //Check for double spacing
		{
			editedLine = doubleSpace(editedLine);
		}
		
		return editedLine;
	}
	
	/*Parse One Column*/
	/*Parses a line into one column*/
	private String[] parseOneCol(String line)
	{
		int spot = 0; //Tracks spot in editedLine
		int count = 0; //Tracks spot in tokens
		String[] tokens = line.split(" "); //Split input
		String[] editedLine = new String[tokens.length]; //Formatted input
		
		for(int i = 0; i < editedLine.length; i++) //Initialize array to null
		{
			editedLine[i] = "";
		}
		
		while(count < tokens.length)
		{
			if(editedLine[spot].length() + tokens[count].length() + 1 < maxWidth) //Check for fit
			{
				if(indent == 2 && editedLine[spot].length() < 10) //Check for block first time
				{
					editedLine[spot] = "          ";
				}
				if(indent == 1) //Indent
				{
					editedLine[spot] = "     ";
					editedLine[spot] = editedLine[spot] + tokens[count] + " "; //Add to array
					indent = 0; //Reset indent
				}
				else if(indent == 0 || indent == 2) //No indent
				{
					editedLine[spot] = editedLine[spot] + tokens[count] + " "; //Add to array
				}
				count++;
			}
			else //Else move to next spot
			{
				spot++;
			}
		}
		
		if(justified == 2) //Justify the line right
		{
			editedLine = justifyRight(editedLine);
		}
		else if(justified == 1) //Justify line center
		{
			editedLine = justifyCenter(editedLine);
		}
		//If it skips both it's justified left
		
		editedLine = addSpacing(editedLine); //Add spacing between lines
		
		if(spacing == 1) //Add double spacing between lines
		{
			editedLine = doubleSpace(editedLine);
		}
		
		return editedLine;
	}
	
	/*Parse Title*/
	/*Parses the title line so that it fits document*/
	private String[] parseTitle(String line)
	{
		int spot = 0; //Tracks spot in editedLine
		int count = 0; //Tracks spot in tokens
		String[] tokens = line.split(" "); //Split input
		String[] editedLine = new String[tokens.length]; //Formatted input
		
		for(int i = 0; i < editedLine.length; i++)
		{
			editedLine[i] = "";
		}
		
		while(count < tokens.length) //While the line read still works
		{
			if(editedLine[spot].length() + tokens[count].length() + 1 < maxWidth) //Check for single space fit
			{
				editedLine[spot] = editedLine[spot] + tokens[count] + " "; //Add to array
				count++;
			}
			else
			{
				spot++;
			}
				
		}
		editedLine = justifyCenter(editedLine); //Center the title
		
		editedLine = addSpacing(editedLine); //Add spacing
		
		if(spacing == 1) //Add double spacing between lines
		{
			editedLine = doubleSpace(editedLine);
		}
		
		title = 0; //Reset title
		return editedLine;
	}
	
	/*Save Document Pressed*/
	/*Clear the strings user sees*/
	public void saveDocPressed()
	{
		window.clearDoc(); 
	}
}