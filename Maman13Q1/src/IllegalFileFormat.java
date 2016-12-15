import java.io.IOException;

public class IllegalFileFormat extends IOException
{
    public IllegalFileFormat(String strMessage)
    {
	super(strMessage);	
    }

}
