import java.util.Comparator;

public class MenuItem implements Comparable<MenuItem> , Cloneable , Comparator<MenuItem>
{
    // an enum that represent the item type options. the nPos is used to allow comparing the MenuItem to it self
    public enum ItemType 
    {
	APPETIZER(0) , MAIN(1) , DESSERT(2) , DRINK(3);
	private final int m_nPos;
	ItemType(int nPos)
	{
	    this.m_nPos = nPos;
	}
	public int getPos()
	{
	    return m_nPos;
	}	
    }
    
    
    private String m_strItemName;	// the item name as read from the file
    private ItemType m_itemType;	// the item type as read from the file
    private float m_fItemPrice;		// the price for the menu item
    
    public MenuItem(String strItemName , ItemType itemType , float fItemPrice)
    {
	strItemName.trim();
	if(strItemName.isEmpty())
	    throw new IllegalArgumentException("item name may not be empty");
	setItemName(strItemName);
	setItemType(itemType);
	setItemPrice(fItemPrice);
    }
    
    private void setItemType(ItemType itemType)
    {
	m_itemType = itemType;
    }
    
    private void setItemName(String strItemName)
    {
	m_strItemName = strItemName;
    }
    
    public String getItemName()
    {
	return m_strItemName;
    }
    
    public ItemType getItemType()
    {
	return m_itemType;
    }

    public float getItemPrice()
    {
	return m_fItemPrice;
    }

    private void setItemPrice(float fItemPrice)
    {
	m_fItemPrice = fItemPrice;
    }

    @Override
    public int compareTo(MenuItem otherItem)
    {
	if(otherItem.getItemType().getPos() > getItemType().getPos())
	    return -1;
	else if(otherItem.getItemType().getPos() < getItemType().getPos())
	    return 1;
	else
	    return 0;
    }
    
    @Override
    public MenuItem clone() throws CloneNotSupportedException
    {
	MenuItem res = (MenuItem) super.clone();
	res.setItemType(getItemType());
	return res;
    }
    
    @Override
    public String toString()
    {
	return getItemName() + " - " + getItemPrice();
    }

    @Override
    public int compare(MenuItem o1, MenuItem o2)
    {
	return o1.compareTo(o2);
    }

}
