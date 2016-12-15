import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class MenuPanel extends JPanel
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
    
    
    
}
