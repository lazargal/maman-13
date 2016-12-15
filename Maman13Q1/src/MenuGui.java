import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
		    mainWindow.setSize(600, 480);
		    mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    
		    loadedMenuItems.sort(null);
		    
		    MenuPanel menuPanel = new MenuPanel(loadedMenuItems);
		    menuPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		    JScrollPane vertScroll = new JScrollPane(menuPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		    vertScroll.setAlignmentX(Component.LEFT_ALIGNMENT);
		    mainWindow.add(vertScroll,BorderLayout.CENTER);
		    
		    JButton button = new JButton("Order");
		    button.addActionListener(menuPanel);
		    JPanel tempPanel = new JPanel();
		    tempPanel.setLayout(new BorderLayout());
		    tempPanel.add(button , BorderLayout.EAST);
		    mainWindow.add(tempPanel,BorderLayout.SOUTH);
		    
		    mainWindow.setVisible(true);
		    
		} 
		catch (FileNotFoundException | IllegalFileFormat e)
		{
		    e.printStackTrace();
		}
    }

}
