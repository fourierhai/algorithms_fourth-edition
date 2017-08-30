package edu.princeton.cs.algs4;

public class BST<Key extends Comparable<Key>, Value> {
	private Node root;    //定义一个私有的名为root的 成员变量  ，二叉查找树的根节点
	
	private class Node   //Node类
	{//Node类的属性
		private Key key;   //键
		private Value val;  //值
		private Node left,right;  //指向子树的链接
		private int N; //以该节点为根的子树中的结点总数
		//Node类的方法
		public Node(Key key,Value val,int N)
		{this.key =key;
		this.val =val;
		this.N=N;
		}
	}
	
	public int size()
	{
		return size(root);
	}
	
	private int size(Node x)
	{
		if (x==null) return 0;
		else    return x.N;
	}
	
	public Value get(Key key)
	{
		return get(root,key);
	}
	
	private Value get(Node x,Key key)
	{//在以x为根节点的子树中查找并返回key对应的值；
	//如果找不到就返回null
		if(x==null) return null;
		int cmp =key.compareTo(x.key);
		if (cmp<0) return get(x.left,key);
		else if(cmp>0) return get(x.right,key);
		else return x.val;	
	}
	
	public void put(Key key,Value val)
	{//查找key，找到更新它的值，否则为它创建一个新的结点
		root = put(root,key,val);
	}
	
	private Node put(Node x, Key key ,Value val)
	{
		//如果key存在于以x为根节点的子树中则更新它的值；
		//否则将以key和val为键值对的新结点插入到该子树中
		if (x==null) return new Node(key,val,1);
		int cmp = key.compareTo(x.key);
		if (cmp<0) x.left =put(x,key,val);
		else if(cmp>0) x.right = put(x,key,val);
		else x.val =val;
		
		x.N=size(x.left)+size(x.right)+1;
		return x;
	}
	
	public Key min()
	{
		return min(root).key;
	}
	
	private Node min(Node x)
	{
		if (x.left == null) return x;
		return min(x.left);
	}
	
	public Key floor(Key key)
	{
		Node x = floor(root,key);
		if (x==null) return null;
		return x.key;
	}
	
	private Node floor(Node x,Key key)
	{//Largest key ≤ a given key.
		if (x==null) return null;
		int cmp = key.compareTo(x.key);
		
		if (cmp==0) return x;  //如果k equals the key at root
		                       //The floor of k is k.
		
		if (cmp<0) return floor(x.left,key);  //k is less than the key at root
		                                    //The floor of k is in the left subtree.             
		
		Node t = floor(x.right,key);//k is greater than the key at root
		if(t != null) return t;// (if there is any key ≤ k in right subtree);
		else return x;  //otherwise it is the key in the root.
	}
	
	public int rank(Key key)
	{ return rank(key,root);	}
	
	private int rank(Key key ,Node x)
	{//how many keys < the given k
		if (x==null) return 0;
		int cmp =key.compareTo(x.key);
		if (cmp<0) return rank(key,x.left);
		else if (cmp>0) return 1+size(x.left)+rank(key,x.right);
		else  return size(x.left);
	}
	
	public Iterable<Key> keys()
    {
		Queue<Key> q= new Queue<Key>();
		inorder (root,q);
		return q;
		}
	
	private void inorder(Node x, Queue<Key> q)
	{//顺序遍历
		if (x ==null) return;
		inorder (x.left,q);
		q.enqueue(x.key);
		inorder(x.right,q);
		}
	
	public void deleteMin()
	{ root = deleteMin(root);	}
	
	private Node deleteMin(Node x)
	{
		if(x.left == null ) return x.right;
		x.left = deleteMin(x.left);
		x.N =1+size(x.left)+size(x.right);
		return x;
	}
	
	public void delete(Key key)
	{ root = delete (root,key);}
	
	private Node delete(Node x,Key key)
	{
		if (x==null) return null;
		int cmp =key.compareTo(x.key);
		//search for key
		if(cmp<0) x.left = delete(x.left, key);
		else if (cmp>0) x.right = delete(x.right,key);
		else{
			//no right child
			if(x.right ==null) return x.left;
			//no left child
			if(x.left ==null)  return x.right;
			
			//repalce with successor
			//这是hibbard deletion
			//为什么是node的接班人，而不是前任？视频里并没有详细阐述
			//导致的后果是：在频繁的插入删除操作后，tree会变得非对称，左子树会比右子树多很多
			Node t =x;
			x =min(t.right);
			x.right =deleteMin(t.right);
			x.left =t.left;
		}
		//update subtree counts
		x.N =size(x.left)+size(x.right)+1;
		return x;
		}

}
	
	