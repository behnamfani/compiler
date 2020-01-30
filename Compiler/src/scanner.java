import java.util.Scanner;
import java.io.*;

public class scanner extends Token{

	Scanner s = new Scanner(System.in);
	char ch=' ';
	String str;
	String result;
	Token token ;

	public scanner(String parser) {
		// TODO Auto-generated constructor stub
		token = new Token();
		str = parser; 
		result ="";
	}
	
	public Token read()
	{				
					ch = str.charAt(0);
					switch(ch)
					{
					case ' ': case '\t': case '\n': case '\r': break;
					case '(':
						token.type = Tokentype.st ;
						token.value= "(" ;
						return token;
					case ')':
						token.type = Tokentype.st ;
						token.value= ")" ;
						return token;
					case '+':
						if(str.length() == 2)
						{
							ch = str.charAt(1);
						
							if( ch == '+')
							{
								token.type = Tokentype.st ;
								token.value = "++";
								return token;
							}
							else
							{
								token.type = Tokentype.st;
								token.value = "+";
								return token;
							
							}
						}
						else if(str.length() > 2)
						{
							token.type = Tokentype.error;
							return token;
						}
						else
						{
							token.type = Tokentype.st;
							token.value = "+";
							return token;
						
						}
					case '-':
						if(str.length() == 2)
						{
							ch = str.charAt(1);
							if( ch == '-')
							{
								token.type = Tokentype.st ;
								token.value = "--";
								return token;
							}
							else
							{
								token.type = Tokentype.st;
								token.value = "-";
								return token;
							}
						}
						else if(str.length() > 2)
						{
							token.type = Tokentype.error;
							return token;
						}
						else
						{
							token.type = Tokentype.st;
							token.value = "-";
							return token;
						}
					case ';':
						token.type = Tokentype.st;
						token.value = ";";
						return token;
					case '*':
						token.type = Tokentype.st;
						token.value = "*";
						return token;
					case '/':
						token.type = Tokentype.st;
						token.value = "/";
						return token;
					case '}':
						token.type = Tokentype.st;
						token.value = "}";
						return token;
					case '{':
						token.type = Tokentype.st;
						token.value = "{";
						return token;
					case ',':
						token.type = Tokentype.st;
						token.value = ",";
						return token;
					case '%':
						token.type = Tokentype.st;
						token.value = "%";
						return token;
					case '!':
						if(str.length() == 2 && str.charAt(1) == '=')
						{
							token.type = Tokentype.st;
							token.value = "!=";
							return token;
						}
						else
						{
							token.type = Tokentype.error;
							return token;
						}
					case '&':
						if(str.length() == 2 && str.charAt(1) == '&')
						{
							token.type = Tokentype.st;
							token.value = "&&";
							return token;
						}
						else
						{
							token.type = Tokentype.error;
							return token;
						}
					case '|':
						if(str.length() == 2 && str.charAt(1) == '|')
						{
							token.type = Tokentype.st;
							token.value = "||";
							return token;
						}
						else
						{
							token.type = Tokentype.error;
							return token;
						}
								
					case '=':
						if(str.length() == 2)
						{
							ch = str.charAt(1);
							if( ch == '=')
							{
								token.type = Tokentype.st ;
								token.value = "==";
								return token;
							}
							else if(str.length() > 2)
							{
								token.type = Tokentype.error;
								return token;
							}
							else
							{
								token.type = Tokentype.st;
								token.value = "=";
								return token;
							}
						}
						else
						{
							token.type = Tokentype.st;
							token.value = "=";
							return token;
						}
					case '>':
						if(str.length() == 2)
						{
							ch = str.charAt(1);
							if( ch == '=')
							{
								token.type = Tokentype.st ;
								token.value = ">=";
								return token;
							}
							else
							{
								token.type = Tokentype.error;
								return token;
							}
						}
						else if(str.length() > 2)
						{
							token.type = Tokentype.error;
							return token;
						}
						else
						{
							token.type = Token.Tokentype.st;
							token.value = ">";
							return token;
						}
					case '<':
						if(str.length() == 2)
						{
							ch = str.charAt(1);
							if( ch == '=')
							{
								token.type = Tokentype.st ;
								token.value = "<=";
								return token;
							}
							else
							{
								token.type = Tokentype.error;
								return token;
							}
						}
						else if(str.length() > 2)
						{
							token.type = Tokentype.error;
							return token;
						}
						else
						{
							token.type = Tokentype.st;
							token.value = "<";
							return token;
						}
					default :
						int j = 0;
						if(isLetter(ch))
						{
							
							result += ch;
							for(int i = 1 ; i < str.length() ; i++)
							{
								ch = str.charAt(i);
								if(isLetter(ch) || isNumber(ch) || ch=='_'){
									result += ch;
								}
								else
								{
									i = str.length();
								}
	                    		
	                    	}
							Tag tag = new Tag();
							if(tag.findKW(result))
							{
								token.type = Tokentype.kw;
								token.value = result;
								return token;
							}
							else
							{
								token.type = Tokentype.id;
								token.value = result;
								return token;
							}
	                 	
						}
	                    if(isNumber(ch))
	                    {
	                    	
								result=" ";
								result += ch;
								for(int i = 1 ; i < str.length() ; i++)
								{
									
									ch = str.charAt(i);
									if(isNumber(ch) || ch == '.'){
										result += ch;
									}
									else
									{
										i = str.length();
									}
		                    		
		                    	}
	                    	
	                    	token.type = Tokentype.num;
	                    	token.value = result;
	                    	return token;
	                    }
	                    else
	                    {
	                    	token.type = Tokentype.error;
	                    	return token;
						}
					}
					token.type = Tokentype.num;
					token.value = "null";
					return token;
				
		}

		
	
	static boolean isNumber(char c){
        return c>='0' && c<='9';
    }
	static boolean isLetter(char c){
	        return c>='a' && c<='z'
	                || c>='A'&& c<='Z';
	    }
	
}
