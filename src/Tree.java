import java.util.*;

public class Tree 
{
	Scanner input = new Scanner(System.in);
	/// next enumerate number.
	int next_num;
	
	/// keep track of the root node and NYT node.
	public Node root , NYTNode; 
	
	/// Map to hold the all symbols existing in the tree.
	public HashMap<String, Node> existing_symbols = new HashMap<String, Node>();
	
	/// Node to swap.
	public Node maxNode = null;
	
	/// Map to hold the short code for symbols.
	public HashMap<String, String> ShortCodes = new HashMap<String, String>();
	public HashMap<String, String> ShortCodesdecom = new HashMap<String, String>();
	
	public Tree()
	{
		/// initialize the start of the tree.
		Node NYT = new Node(100,"");
		NYT.Counter = 0;
		
		this.maxNode = new Node(0,"");
		maxNode.Counter = 0;
		this.root = NYT;
		this.NYTNode = NYT;
		this.next_num = 99;
	}
	public boolean Is_root(Node test)
	{
		return this.root == test;
	}
	
	public Node get_root()
	{
		return this.root;
	}
	
	public String get_NodeCode(String symbol)
	{
		String result = "";
		
		boolean reach_to_root = false;
		
		/// to traverse the tree until we reach to root.
		Node current = null;
		Node parent = null;
		
		if(symbol == "")
		{
			current = this.NYTNode;
			if(this.Is_root(this.NYTNode))
			{
				return "0";
			}
		}else
		{
			current = this.existing_symbols.get(symbol);
		}
		parent = current.parent;
		while(!reach_to_root)
		{
			if(parent.rightchild == current)
			{
				result = "1" + result;
			}else
			{
				result = "0" + result;
			}
				
			if(this.Is_root(parent))
			{
				reach_to_root = true;
			}else
			{
				current = parent;
				parent = current.parent;
			}
		}
			return result;
	}
	
	public void take_ShortCode()
	{
		int NumberOfShortCode;
		String symbol;
		String shortcode;
		System.out.println("How many symbols do you want to enter the short code for?");
		NumberOfShortCode = input.nextInt();
		input.nextLine();
		System.out.println("Enter symbol followed by it's code: ");
		for(int i = 0; i < NumberOfShortCode; i++)
		{
			symbol = input.nextLine();
			shortcode = input.nextLine();
			ShortCodes.put(symbol, shortcode);
		}
		
	}
	
	public void take_ShortCodedecom()
	{
		int NumberOfShortCode;
		String symbol;
		String shortcode;
		System.out.println("How many symbols do you want to enter the short code for?");
		NumberOfShortCode = input.nextInt();
		input.nextLine();
		System.out.println("Enter symbol followed by it's code: ");
		for(int i = 0; i < NumberOfShortCode; i++)
		{
			symbol = input.nextLine();
			shortcode = input.nextLine();
			ShortCodesdecom.put(shortcode, symbol);
		}
		
	}
	
	public Node createNewNode(String symbol)
	{
		Node NewNode = new Node(this.next_num, symbol);
		Node NewParent = new Node(this.NYTNode.NodeNumber, this.NYTNode, NewNode);
		
		/// modify next_number.
		this.next_num -= 2;
		
		this.NYTNode.NodeNumber = NewParent.NodeNumber - 2;
		NewNode.parent = NewParent;
		if(this.NYTNode.parent == null)
		{
			this.root = NewParent;
		}else
		{
			if(this.NYTNode.parent.leftchild == this.NYTNode)
			{
				this.NYTNode.parent.leftchild = NewParent;
				NewParent.parent = this.NYTNode.parent;
			}
		}
		this.NYTNode.parent = NewParent;
		
		this.existing_symbols.put(symbol, NewNode);
		
		return NewNode;
		
	}
	
	
	public Node getNodeFromTree(String symbol)
	{
		return this.existing_symbols.get(symbol);
	}
	
	
	
	
	public boolean checkSymbol(String symbol)
	{
		return existing_symbols.containsKey(symbol);
	}
	public boolean IsEmpty()
	{
		return this.root == this.NYTNode;
	}
	public String getShortcode(String symbol)
	{
		return ShortCodes.get(symbol);
	}
	
	public String getsymbol(String shortcode)
	{
		return ShortCodesdecom.get(shortcode);
	}
	public void addsymbol(String symbol)
	{
		Node check = this.existing_symbols.get(symbol);
		Node OldNYT;
		boolean foundRoot = false;
		if(check == null)
		{
			check = createNewNode(symbol);
			/// GOTO old NYT.
			check = check.parent;
		}else
		{
			
			
			CheckForSwap(this.root, check);
			if(maxNode.NodeNumber != 0)
			{
				swap(check);
				
			}
			
			check.Counter++;
		}
		OldNYT = check;
		if(this.Is_root(check))
		{
			foundRoot = true;
		}
		while(!foundRoot)
		{
			OldNYT = OldNYT.parent;
			if(this.Is_root(OldNYT))/// means that in root.
			{
				OldNYT.Counter++;
				break;
			}
			CheckForSwap(this.root,OldNYT);
			if(maxNode.NodeNumber != 0)
			{
				swap(OldNYT);
			}
			
			OldNYT.Counter++;
			
			/// TODO
		   ///	check for swap.
		  ///	increment symbol node counter(oldnyt).
			
			if(this.Is_root(OldNYT))
			{
				foundRoot = true;
			}else
			{
				OldNYT = OldNYT.parent;
			}
			
			if(this.Is_root(OldNYT))
			{
				foundRoot = true;
				OldNYT.Counter++;
			}
			
		}
			
	} 
	
	public void CheckForSwap(Node traverse,Node n)    
	{
		
		if(traverse == null || n == null)
		{
			return;
		}
			
		else
		{
			if(traverse.NodeNumber > n.NodeNumber
					&& n.Counter >= traverse.Counter
					&& traverse.NodeNumber > this.maxNode.NodeNumber
					&& n.parent != traverse
					&& !this.Is_root(traverse))
			{
				maxNode = traverse;
			}else
			{
				if(traverse.leftchild != null)
				{
					CheckForSwap(traverse.leftchild, n);
				}
				if(traverse.rightchild != null)
				{
					CheckForSwap(traverse.rightchild, n);
				}
			}
			
			
		}		
		
	}
	 public void swapIds(Node firstNode, Node secondNode)
	 {
	        //swap space for the id numbers when they need to be swapped
	        int idSwap = firstNode.NodeNumber;

	        //set the first node's id to the second node.
	        firstNode.NodeNumber = secondNode.NodeNumber;

	        //set the second node's id to the first node's by using the id held in the swap space.
	        secondNode.NodeNumber = idSwap;

	  }

	public void swap(Node n)
	{
		
		swapIds(n, maxNode);
		if(n.parent == maxNode.parent && n.parent.leftchild == n)
		{
			Node temp = maxNode;
			Node Parent = n.parent;
			
			n.parent.rightchild = n;
			n.parent = Parent;
			
			
			n.parent.leftchild = temp;
			temp.parent = Parent;
			//maxNode = new Node(0,"");
			//maxNode.Counter = 0;
			maxNode.Counter = 0;
			maxNode.NodeNumber = 0;
			maxNode.leftchild = null;
			maxNode.rightchild = null;
			maxNode.parent = null;
			return;
			
		}

		
		if(n.parent.rightchild == n)
		{
			Node temp = maxNode;
			Node parent1 = n.parent;
			Node parent2 = temp.parent;
			
			if(parent2.leftchild == temp)
			{
				parent1.rightchild = temp;
				temp.parent = parent1;
				parent2.leftchild = n;
				n.parent = parent2;
			}else
			{
				parent1.rightchild = temp;
				temp.parent = parent1;
				parent2.rightchild = n;
				n.parent = parent2;
			}
			//maxNode = new Node(0,"");
			//maxNode.Counter = 0;
			maxNode.Counter = 0;
			maxNode.NodeNumber = 0;
			maxNode.leftchild = null;
			maxNode.rightchild = null;
			maxNode.parent = null;
			
			return;
		}
		
		if(n.parent.leftchild == n)
		{
			Node temp = maxNode;
			Node parent1 = n.parent;
			Node parent2 = temp.parent;
			
			
			if(parent2.leftchild == temp)
			{
				parent1.leftchild = temp;
				temp.parent = parent1;
				parent2.leftchild = n;
				n.parent = parent2;
			}else
			{
				parent1.leftchild = temp;
				temp.parent = parent1;
				parent2.rightchild = n;
				n.parent = parent2;
			}
			//maxNode = new Node(0,"");
			//maxNode.Counter = 0;
			maxNode.Counter = 0;
			maxNode.NodeNumber = 0;
			maxNode.leftchild = null;
			maxNode.rightchild = null;
			maxNode.parent = null;
			return;
		}
		
		
		
	}
	
		
}

