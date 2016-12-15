import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class MenuPanel extends JPanel implements ActionListener
{
    private ArrayList<MenuItem> m_menuList = null;
    private ArrayList<MenuPanelItem> m_menuItemList = new ArrayList<MenuPanelItem>();
    private int m_nNumOfMenuTypes = MenuItem.ItemType.values().length;
    private boolean[] m_bItemTypesArray = new boolean[m_nNumOfMenuTypes];
    
    
    @SuppressWarnings("unchecked")
    public MenuPanel(ArrayList<MenuItem> menuList)
    {
	super();
	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS) );
	setAlignmentX(Component.LEFT_ALIGNMENT);
	m_menuList = (ArrayList<MenuItem>) menuList.clone();	
	for(MenuItem tempMenuItem : m_menuList)
	{
	    if(!m_bItemTypesArray[tempMenuItem.getItemType().getPos()])
	    {
		add(new JLabel(tempMenuItem.getItemType().toString()));
		m_bItemTypesArray[tempMenuItem.getItemType().getPos()] = true;
	    }
	    MenuPanelItem tempMenuPanelItem = new MenuPanelItem(tempMenuItem);
	    m_menuItemList.add(tempMenuPanelItem);
	    tempMenuPanelItem.setLayout(new FlowLayout(FlowLayout.LEFT));
	    tempMenuPanelItem.setAlignmentX(Component.LEFT_ALIGNMENT);
	    add(tempMenuPanelItem);
	}
    }
    
    public void resetMenuState()
    {
		for(MenuPanelItem tempMenuItem : m_menuItemList)
		{
		    tempMenuItem.resetContent();
		}
    }
    
    public float getTotalOrderPrice()
    {
	float fTemp = 0;
	for(MenuPanelItem tempMenuItem : m_menuItemList)
	{
	    fTemp += tempMenuItem.getTotalPrice();
	}
    
	return fTemp;
    }

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		StringBuilder myBuilder = new StringBuilder();
		for(MenuPanelItem tempMenuItem : m_menuItemList)
		{
			if(tempMenuItem.getIsChecked())
				myBuilder.append(tempMenuItem.toString() + "\r\n");
		}
		
		myBuilder.append("Total order cost = " + getTotalOrderPrice());
		Object[] options = {"Confirm" , "Update" , "Cancel"}; 
		int nSelection = JOptionPane.showOptionDialog(this, myBuilder.toString(), "Confirm Order", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		switch(nSelection)
		{
		case JOptionPane.YES_OPTION:
			saveOrderToFile(myBuilder.toString());
			resetMenuState();
			break;
		case JOptionPane.NO_OPTION:
			// do nothing for now
			break;
		case JOptionPane.CANCEL_OPTION:
			resetMenuState();
			break;		
		}		
	}
	
	public void saveOrderToFile(String orderInfo)
	{
		String strUserInput = JOptionPane.showInputDialog("Enter name and ID");
		if(strUserInput == null)
			return;
		strUserInput.trim();
		if(strUserInput.isEmpty())
			return;
			
		try 
		{
			strUserInput += ".txt";
			Formatter output = new Formatter(strUserInput);
			output.format(orderInfo, (Object[]) null);
			output.close();
			
		}
		catch (FileNotFoundException e) 
		{
			
			e.printStackTrace();
		}
		
	}
    
    
    
}
