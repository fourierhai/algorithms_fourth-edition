/*
 * 三向切分的快速排序
 * 针对大量重复元素的数组，时间复杂度很可能是线性的(比线性对数级别还要低)
 * 熵最优 很常用
 */
package edu.princeton.cs.algs4;

import java.util.Arrays;

public class Quick3way_Sort {
	public static void sort(Comparable[] a)
	{
		StdRandom.shuffle(a);   //消除对输入的依赖
		System.out.println("随机打乱的顺序是："+'\n'+Arrays.toString(a));
		sort(a, 0, a.length-1);
	}
	
	private static void exch(Comparable[] a,int i, int j)
	{Comparable t = a[i]; a[i]=a[j]; a[j]=t;}
	
	private static void sort(Comparable[] a ,int lo, int hi)
	{
		if (hi<=lo) return;
		int lt = lo,  i=lo+1, gt = hi ;
		Comparable v = a[lo];
		while(i<=gt)
		{
			int cmp =a[i].compareTo(v);
			if (cmp<0)
				exch(a,lt++,i++);
			else if(cmp>0)
				exch(a,i,gt--);
			else i++;
		}
		sort(a,lo,lt-1);
		sort(a,gt+1,hi);
	}
	
	private static void show(Comparable[] a)//打印函数
	{
		for (int i=0; i<a.length;i++)
			StdOut.print(a[i] + " ");
		StdOut.println();
	}
	
	public static void main(String[] args)
	{
		Comparable [] a = {'R','B','W','W','R','W','B','R','R','W','B','R'};
		Quick3way_Sort.sort(a);
		System.out.println("快速排序的结果是：");
		show(a);
		}
}
