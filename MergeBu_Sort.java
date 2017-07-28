package edu.princeton.cs.algs4;
/*自顶向下的归并排序，递归的方法；自底向上的是循序渐进。
 *会多次遍历整个数组，根据子数组的大小进行两两归并。子数组的大小sz的初始值为1，每次加倍。最后一子数组的大小只有在数组大小是sz的偶数倍的时候才会等于sz（否则会比sz小）
 *自底向上的归并排序，当数组长度为2的幂时，该排序和自顶向下的归并排序所用的比较次数和数组访问次数都一样，只是顺序不同，其他时候，两者会有一些差别
 *自底向上的归并排序比较适合“链表”组织的数据，这样可以将链表原地排序，不需要创建任何新的链表节点。
 *
 *
 */

public class MergeBu_Sort {
	
	private static Comparable [] aux;//声明归并所需的辅助数组
	
	private static boolean less(Comparable v, Comparable w)
	{return v.compareTo(w)<0;}//比较函数
	
	public static void merge(Comparable[] a ,int lo, int mid,int hi)
	{//将a[lo...mid]和a[mid+1...hi]归并
		int i =lo, j=mid+1;
		
		for (int k=lo; k<=hi;k++)//将a[lo...hi]复制到aux[lo...hi]
			aux[k]=a[k];
		
		for (int k=lo; k<=hi; k++)//归并回到a[lo...hi]
			if (i>mid)   //如果i越界了，那么最小的肯定是右半边的j，赋值后再自增1
				a[k]=aux[j++];
			else if(j>hi) //如果j越界了，那么最小的肯定是左半边的i，赋值后再自增1
				a[k]=aux[i++];
			else if(less(aux[j],aux[i])) //如果右半边的数j小于左半边的数i，将j赋值后再自增
				a[k]=aux[j++];
			else
				a[k]=aux[i++];		
	}
	 
	public static void sort(Comparable [] a)
	{//进行lgN次两两归并
		int N = a.length;
		aux = new Comparable[N];
		for (int sz =1; sz<N; sz=sz+sz)  //sz子数组大小
			for(int lo=0; lo<N-sz; lo+=sz+sz)//lo:子数组索引
				merge(a,lo,lo+sz-1,Math.min(lo+sz+sz-1, N-1));
		
	}
	
	private static void show(Comparable[] a)//打印函数
	{
		for (int i=0; i<a.length;i++)
			StdOut.print(a[i] + " ");
		StdOut.println();
	}
	
	public static void main(String[] args)
	{
		Comparable [] a = {51,46,20,18,65,97,82,30,77,50};
		MergeBu_Sort.sort(a);
		System.out.println("排序结果：");
		show(a);
		}

}
