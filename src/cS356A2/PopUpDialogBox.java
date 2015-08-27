package cS356A2;
import javax.swing.JOptionPane;

/**
 * This class is used to display pop up boxes.
 * @author Oscar Nevarez	
 * @version 1.0
 *
 */
public class PopUpDialogBox {
	
	/**
	 * This method creates a pop up box with a message
	 * @param infoMessage the message that appears in the pop up box
	 * @param titleBar the title bar for the window
	 */
	public void infoBox(String infoMessage, String titleBar)
	{
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
}
