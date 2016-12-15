import java.awt.Checkbox;
import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPanelItem extends JPanel
{
    private Integer[] m_comboOption = {1,2,3,4,5,6,7,8,9,10}; 
    private MenuItem m_myMenuItem = null;
    private Checkbox m_chekBox = new Checkbox();
    private JComboBox m_comboBox = new JComboBox(m_comboOption);
    
    public MenuPanelItem(MenuItem menuItem)
    {
	super();
	try
	{
	    m_myMenuItem = (MenuItem)menuItem.clone();
	} 
	catch (CloneNotSupportedException e)
	{
	    m_myMenuItem = new MenuItem("Empty Menu Item" , MenuItem.ItemType.APPETIZER , 0);
	    e.printStackTrace();
	}
	add(m_chekBox);
	add(new JLabel(m_myMenuItem.toString()));
	add(m_comboBox);	
	
    }
    
    public void resetContent()
    {
	m_chekBox.setState(false);
	m_comboBox.setSelectedIndex(0);
    }
    
    public boolean getIsChecked()
    {
	return m_chekBox.getState();
    }
    
    public int getComboSelectionIndex()
    {
	return m_comboBox.getSelectedIndex();
    }
    
    public float getTotalPrice()
    {
	float fTemp;
	if(getIsChecked())
	    fTemp = (getComboSelectionIndex()+1) * m_myMenuItem.getItemPrice();
	else
	    fTemp = 0;
	
	return fTemp;
    }
    
    @Override
    public String toString()
    {
	String strRes;
	if(getIsChecked())
	{
	    strRes = m_myMenuItem.toString() + " X " + (getComboSelectionIndex()+1) + " = " + getTotalPrice();
	}
	else
	    strRes = "Item not Selected";	
	
	return strRes;
    }
    

}
