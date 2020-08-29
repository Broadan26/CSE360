import java.awt.EventQueue;

public class Driver 
{
	static ProjectFrame window;
	
	/**Launch the application.*/
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					BackEnd backEnd = new BackEnd(); //Create back end object
					window = new ProjectFrame(backEnd); //Instantiate GUI
					window.frame.setVisible(true); //Show GUI
					window.frame.setTitle("Team 38 File Formatter"); //Set title of window
					backEnd.setProjectFrame(window);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
}