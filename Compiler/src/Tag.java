
public class Tag {

	String keyword[]= {"int" , "char" , "float" , "double" , "if" , "else" , "for" , "do" , 
			"while" , "main", "return" , "endif" };
	
	
	public boolean findKW(String str)
	{
		for(int i = 0 ; i < keyword.length ; i++)
		{
			if(str.equals(keyword[i]))
				return true;
		}
		return false;
		
	}
	
	
}


