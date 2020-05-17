package general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/*
 * Instructions: 
 * First: Read through the assignment specification, make sure you understand what the assignment is asking for.
 * Second: There are number of "TODO" instructions within this code, make sure to complete all of them fully.
 * Third: Test you code.
 */


// TODO: Name and Pledge

// Pledge: I pledge my honor that I have abided by the Stevens Honor System
// Name: Aparajita Rana


/**
 * HW4 CS284 Spring 2019
 * Implements a Huffman encoding tree.
 * The included code has been commented for the student's benefit, feel free to read through it.
 */
public class HuffmanTree {

	// ******************** Start of Stub Code ******************** //
	// ************************************************************ //
    /** Node<E> is an inner class and it is abstract.
     * There will be two kinds
     * of Node, one for leaves and one for internal nodes. */
    abstract static class Node implements Comparable<Node>{
        /** The frequency of all the items below this node */
        protected int frequency;
        
        public Node(int freq) {
        	this.frequency = freq;
        }
        
		/** Needed for the Minimum Heap used later in this stub. */
		public int compareTo(Node other) {
			return this.frequency - other.frequency;
		}
    }
    /** Leaves of a Huffman tree contain the data items */
    protected static class LeafNode extends Node {
        // Data Fields
        /** The data in the node */
        protected char data;
        /** Constructor to create a leaf node (i.e. no children) */
        public LeafNode(char data, int freq) {
            super(freq);
            this.data = data;
        }
        /** toString method */
        public String toString() {
            return "[value= "+this.data + ",freq= "+frequency+"]";
        }
    }
    /** Internal nodes contain no data,
     * just references to left and right subtrees */
    protected static class InternalNode extends Node {
        /** A reference to the left child */
        protected Node left;
        /** A reference to the right child */
        protected Node right;

        /** Constructor to create an internal node */
        public InternalNode(Node leftC, Node rightC) {
            super(leftC.frequency + rightC.frequency);
            left = leftC; right = rightC;
        }
        public String toString() {
            return "(freq= "+frequency+")";
        }
    }
	
	// Enough space to encode all "extended ascii" values
	// This size is probably overkill (since many of the values are not "printable" in the usual sense)
	private static final int codex_size = 256;
	
	/* Data Fields for Huffman Tree */
	private Node root;
	
	public HuffmanTree(String s) {
		root = buildHuffmanTree(s);
	}
	
	/**
	 * Returns the frequencies of all characters in s.
	 * @param s
	 * @return
	 */
	public static int[] frequency(String s) {
		int[] freq = new int[codex_size];
		for (char c: s.toCharArray()) {
			freq[c]++;
		}
		return freq;
	}
	
	/**
	 * Builds the actual Huffman tree for that particular string.
	 * @param s
	 * @return
	 */
	private static Node buildHuffmanTree(String s) {
		int[] freq = frequency(s);
		
		// Create a minimum heap for creating the Huffman Tree
		// Note to students: You probably won't know what this data structure
		// is yet, and that is okay.
		PriorityQueue<Node> min_heap = new PriorityQueue<Node>();
		
		// Go through and create all the nodes we need
		// as in, all the nodes that actually appear in our string (have a frequency greater then 0)
		for(int i = 0; i < codex_size; i++) {
			if (freq[i] > 0) {
				// Add a new node (for that character) to the min_heap, notice we have to cast our int i into a char.
				min_heap.add(new LeafNode((char) i, freq[i]));
			}
		}
		
		// Edge case (string was empty)
		if (min_heap.isEmpty()) {
			throw new NullPointerException("Cannot encode an empty String");
		}
		
		// Now to create the actual Huffman Tree 
		// NOTE: this algorithm is a bit beyond what we cover in cs284, 
		// you'll see this in depth in cs385
		
		// Merge smallest subtrees together
		while (min_heap.size() > 1) {
			Node left = min_heap.poll();
			Node right = min_heap.poll();
			Node merged_tree = new InternalNode(left, right);
			min_heap.add(merged_tree);
		}
		
		// Return our structured Huffman Tree
		return min_heap.poll();
	}
	
	// ******************** End of Stub Code ******************** //
	// ********************************************************** //
	
	
	public String bitsToString(Boolean[] encoding) {
		String total="";
		if(encoding.length==0)
		{
			return null;
		}
		
		for(boolean check : encoding)
		{
			if(check)
			{
				total=total+"1";
			}
			else
			{
				total=total+"0";
			}
			
		}
	}
	
	public String toString(int count, Node curr) {
		// TODO Complete toString method (see assignment specification)
		// HINT: Might need helper method for preOrderTraversal
		String s="";
        
		for(int x=0;x<count;x++)
        {
        	s=s+"	";
        }
		
		if(curr instanceof InternalNode)
		{
			InternalNode one=(InternalNode) curr;
			s=s+"(frequency: "+one.frequency+")\n";
			s=s+toString(count+1,one.left);
			s=s+toString(count+1,one.right);
		}
		else
		{
			LeafNode two= (LeafNode) curr;
			s=s+"[value= "+two.data+", frequency= "+two.frequency+" ]\n";
		}
		
		return s;
    }
	
	public String toString()
	{
		return toString(0,root).toString();
	}
	
	public String decode(Boolean[] coding) {
		Node curr=root;
		String y = "";
		
		for(int x=0; x<coding.length;x++)
		{
			if(coding[x])
			{
				curr=((InternalNode)curr).right;
				if(curr instanceof LeafNode) {
					y+=((LeafNode) curr).data;
					curr=root;
				}
			}
			else {
				curr=((InternalNode)curr).left;
				if(curr instanceof LeafNode) {
					y+=((LeafNode)curr).data;
					curr=root;
				}
			}
		}
		return y;
	}
	
	public Boolean[] encode(String inputText) {
		String[] vals=inputText.split("");
		String num="";
		for(int x=0; x<vals.length;x++)
		{
			String a=encodeHelper(vals[x].charAt(0), root, "");
			if (a.equals("")) throw new IllegalArgumentException();
			else num+=a;
		}
		Boolean[] end= new Boolean[num.length()];
		String[] valnum=num.split("");
		for(int x=0; x<valnum.length;x++)
		{
			if(valnum[x]=="0")
			{
				end[x]=false;
			}
			else
				end[x]=true;
		}
		return end;
	}
	
	public String encodeHelper(char i, Node n, String x)
	{
		if(n==null)
			return "";
		if(n instanceof LeafNode)
		{
			if(((LeafNode) n).data == i)
			{
				return x;
			}
			else
				return "";
		}
		
		return encodeHelper(i, ((InternalNode)n).left,x+"0")+encodeHelper(i, ((InternalNode)n).right,x+"1");
		
	}
	
	public Boolean[] efficientEncode(String inputText) {
		 HashMap<Character, String> sean = new HashMap<Character, String>();
		 efficient(this.root, "", sean);
	        String x = "";
	        for (int i = 0; i < inputText.length(); i++){
	            if (sean.containsKey(inputText.charAt(i))) x+= sean.get(inputText.charAt(i));
	            else throw new IllegalArgumentException();
	        }
	        Boolean[] s = new Boolean[x.length()];
	        for (int i = 0; i < x.length(); i++){
	            if (x.substring(i,i+1).equals("1")) s[i] = true;
	            else s[i] = false;
	        }
	        return s;
	}
	
	private void efficient(Node n, String x, HashMap h){
        //check m
        if (n == null) return; 
        //check if leaf node
        if (n instanceof LeafNode){
            h.put(((LeafNode)n).data, x);
        }
        //check left and right
        else {
        	efficient(((InternalNode)n).left, x+"0", h);
        	efficient(((InternalNode)n).right, x+"1", h);
        }
    }
	
		public static void main(String[] args) {
		// Code to see if stuff works...
		String s = "Some string you want to encode";
		HuffmanTree t = new HuffmanTree(s); // Creates specific Huffman Tree for "s"
		// Now you can use encode, decode, and toString to interact with your specific Huffman Tree
	}
}
