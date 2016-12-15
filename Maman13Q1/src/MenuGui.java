import java.awt.Component;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class MenuGui
{
    public static void main(String[] args)
    {
	try
	{
	    MenuFileReader myMenuReader = new MenuFileReader("menu.txt");
	    ArrayList<MenuItem> loadedMenuItems = myMenuReader.getMenuItemArray();
	    JFrame mainWindow = new JFrame();
	    loadedMenuItems.sort(null);
	    
	    MenuPanel menuPanel = new MenuPanel(loadedMenuItems);
	    menuPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    JScrollPane vertScroll = new JScrollPane(menuPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    vertScroll.setAlignmentX(Component.LEFT_ALIGNMENT);
	    mainWindow.add(vertScroll);
	    mainWindow.setSize(200, 100);
	    mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainWindow.setVisible(true);
	    
	} 
	catch (FileNotFoundException | IllegalFileFormat e)
	{
	    e.printStackTrace();
	}
    }
}
