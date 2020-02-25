import java.util.*;

public class Node 
{
	public int Counter; 		 /// # of the occurrence of the char.
	public int NodeNumber; 		/// enumerate node in descending order
	public String symbol; 	   /// symbol for this node.
	
	public Node leftchild;
	public Node rightchild;
	public Node parent;
	
	/// to modify the parent.
	public Node(int nodenumber, Node left, Node right)
	{
		this.NodeNumber = nodenumber;
		this.symbol = null;
		this.leftchild = left;
		this.rightchild = right;
		this.Counter = left.Counter + right.Counter;
	}
	/// constructor for leaf node.

	public Node(int num, String value)
	{
		this.Counter = 1;
		this.NodeNumber = num;
		this.symbol = value;
		this.leftchild = null;
		this.rightchild = null;
		this.parent = null;
	}
	
	
	
}
