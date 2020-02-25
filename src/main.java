import java.util.*;
 
public class main 
{
	public static void compression()
	{
		Scanner in = new Scanner(System.in);
		String input;
		String result = "";
		
		System.out.println("Enter the message that you want to compress: ");
		input = in.nextLine();
		
		Tree test = new Tree();
		test.take_ShortCode();
		for(int i = 0; i < input.length(); i++)
		{
			/// for first -> send short code only.
			if(i == 0 && !test.checkSymbol(Character.toString(input.charAt(i))))
			{
				result += test.getShortcode(Character.toString(input.charAt(i)));
				test.addsymbol(Character.toString(input.charAt(i)));
			}
			/// first occurrence of symbol.
			if(!test.checkSymbol(Character.toString(input.charAt(i))) && i != 0)
			{
				result += test.get_NodeCode("");
				result += test.getShortcode(Character.toString(input.charAt(i)));
				test.addsymbol(Character.toString(input.charAt(i)));
			}
			else if(test.checkSymbol(Character.toString(input.charAt(i))) && i != 0)
			{
				result += test.get_NodeCode(Character.toString(input.charAt(i)));
				test.addsymbol(Character.toString(input.charAt(i)));
			}
			
		}
		
		System.out.println(result);
		
	}
	
	public static void decompression()
	{
		Scanner in = new Scanner(System.in);
		String input;
		String result = "";
		System.out.println("Enter the message that you want to decompress: ");
		input = in.nextLine();
		Tree test = new Tree();
		test.take_ShortCodedecom();
		
		
		
		if(test.root == test.NYTNode) 
		{ 
			
			for(int i = 0; i < input.length(); i++) 
			{
				
				if(test.ShortCodesdecom.containsKey(input.substring(0,i+1))) 
				{
					
					result += test.ShortCodesdecom.get(input.substring(0,i+1));
					test.addsymbol(test.ShortCodesdecom.get(input.substring(0,i+1)));
					input = input.substring(i+1);
					
					break;
				}
			}
		
			
		}
		Node curr = test.root;
	    for (int i=0;i<input.length();i++) 
	    { 
	    	

	        if (input.charAt(i) == '0') {
	        	curr = curr.leftchild;
	        }
	            

	        else 
	        {
	        	curr = curr.rightchild;

	        	
	        }

	            
	  

	        if (curr.leftchild == null && curr.rightchild == null) 
	        { 
	        	if(curr == test.NYTNode) 
	        	{
					i++;
        			int indexstart= i;
	        		for(int k = i; k<input.length();k++) 
	        		{

	    				
						if(test.ShortCodesdecom.containsKey(input.substring(indexstart,k+1))) 
						{
			        										
							result += test.ShortCodesdecom.get(input.substring(indexstart,k+1));
							test.addsymbol(test.ShortCodesdecom.get(input.substring(indexstart,k+1)));


							i++;				

							break;

							
							
						}
					}



				
	        	}
	        	else if(curr.symbol != "")
	        	{
	        		test.addsymbol(curr.symbol);
	        		result += curr.symbol;
	        	}
	        	
	             
	            curr = test.root; 
	        } 
	    } 
	    
	    
	    
		
	    System.out.println(result);	
	}

	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		int choice;
		boolean work = true;
		while(work)
		{
			System.out.println("if you want to compress message please press 1 \npress 2 to decompress \nwhen you finish please enter 0:  ");

			choice = in.nextInt();
			if(choice == 1)
			{
				compression();
			}
			else if(choice == 2)
			{
				decompression();
			}else if(choice == 0)
			{
				System.out.println("Thank you, regarsds");
				work = false;
			}else
			{
				System.out.println("Invalid input");
			}
			
			
		}
		
	}

}
