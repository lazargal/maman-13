import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class MenuFileReader
{
    private ArrayList<MenuItem> m_menuItmeArray = new ArrayList<MenuItem>(); 
    
    
    public MenuFileReader(String strFilePath) throws FileNotFoundException, IllegalFileFormat
    {
	strFilePath.trim();
	Scanner inputFile = new Scanner(new File(strFilePath));
	readFileData(inputFile);
	
	inputFile.close();	
    }
    
    private void readFileData(Scanner inputFile) throws IllegalFileFormat
    {
	String strTempItemName;
	float fTempItemPrice = 0;
	String strTemp;
	int nLineNumber = 0;
	MenuItem.ItemType tempType = MenuItem.ItemType.APPETIZER;
	
	while(inputFile.hasNext())
	{
	    strTempItemName = inputFile.nextLine();
	    nLineNumber++;
	    
	    strTempItemName.trim();
	    if(strTempItemName.isEmpty() )
		throw new IllegalFileFormat("item name may not be empty. Line number = " + nLineNumber);
	    if(!inputFile.hasNext())
		throw new IllegalFileFormat("next line is not compatible with the wanted file format . Last line number = " + nLineNumber);
	    
	    strTemp = inputFile.nextLine();
	    nLineNumber++;
	    strTemp.trim();
	    if(strTemp.isEmpty() )
		throw new IllegalFileFormat("item type may not be empty. Line number = " + nLineNumber);
	    if(!inputFile.hasNextFloat())
		throw new IllegalFileFormat("next line is not compatible with the wanted file format . Last line number = " + nLineNumber);
	    
	    switch(strTemp)
	    {
	    case"APPETIZER":
		tempType = MenuItem.ItemType.APPETIZER;
		break;
	    case"MAIN":
		tempType = MenuItem.ItemType.MAIN;
		break;
	    case"DESSERT":
		tempType = MenuItem.ItemType.DESSERT;
		break;
	    case"DRINK":
		tempType = MenuItem.ItemType.DRINK;
		break;
	    }
	    
	    fTempItemPrice = inputFile.nextFloat();
	    if(inputFile.hasNextLine())
		inputFile.nextLine();
	    nLineNumber++;
	    m_menuItmeArray.add(new MenuItem(strTempItemName, tempType, fTempItemPrice));
	    
	}
    }
    
    @SuppressWarnings("unchecked")
    public ArrayList<MenuItem> getMenuItemArray()
    {
	ArrayList<MenuItem> res = (ArrayList<MenuItem>) m_menuItmeArray.clone();
	return res;
    }

}
