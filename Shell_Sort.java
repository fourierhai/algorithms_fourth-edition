package edu.princeton.cs.algs4;

public class Shell_Sort {
	public static void sort(Comparable[] a)
	{//将a[]按升序排列
		int N = a.length;
		int h = 1;
		while (h< N/3) h= 3*h +1; //这是shell当时的式子，1,4,13,40,121,364...
		while (h>=1)
		{//将数组变为h有序
			for (int i=h; i<N;i++)
			{//将a[i]插入到a[i-h],a[i-2*h],a[i-3*h]...之中
				for (int j=i;j>=h && less(a[j],a[j-h]);j-=h)
					exch(a,j,j-h);
			}
			h=h/3;
		}
	}
	private static boolean less(Comparable v, Comparable w)
	{return v.compareTo(w)<0;}
	
	private static void exch(Comparable[] a,int i, int j)
	{Comparable t = a[i]; a[i]=a[j]; a[j]=t;}
	
	private static void show(Comparable[] a)
	{
		for (int i=0; i<a.length;i++)
			StdOut.print(a[i] + " ");
		StdOut.println();
	}
	
	public static void main(String[] args)
	{
		String[] a = {"S","H","E","L","L","S","O","R","T","E","X","A","M","P","L","E"};
		Selection_Sort.sort(a);
		show(a);
	}
}
				

