package edu.princeton.cs.algs4;

import java.util.Arrays;

public class Quick_Sort {
	public static void sort(Comparable[] a)
	{
		StdRandom.shuffle(a);   //消除对输入的依赖
		System.out.println("随机打乱的顺序是："+'\n'+Arrays.toString(a));
		sort(a, 0, a.length-1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi)
	{
		if (hi<=lo) return;
		int j = partition(a,lo,hi);
		sort (a, lo,j-1);
		sort (a, j+1,hi);
	}
	private static boolean less(Comparable v, Comparable w)
	{return v.compareTo(w)<0;}//比较函数
	
	private static void exch(Comparable[] a,int i, int j)
	{Comparable t = a[i]; a[i]=a[j]; a[j]=t;}
	
	public static int partition(Comparable[] a, int lo, int hi)
	{//将数组切分为a[lo...i-1],a[i],a[i+1...hi]
		int i= lo, j=hi+1;   // 左右扫描指针
		Comparable v = a[lo]; //切分元素
		while (true)
		{//扫描左右，检查扫描是否结束并交换元素
			while (less(a[++i],v))
				if(i==hi)
					break;
			while (less(v,a[--j]))
				if(j==lo)
					break;
			if(i>=j)
				break;
			exch(a,i,j);			
		}
		exch(a,lo,j); //将v=a[j]放入正确的位置
		return j;   //a[lo...i-1],a[i],a[i+1...hi]达成			
	}
	
	private static void show(Comparable[] a)//打印函数
	{
		for (int i=0; i<a.length;i++)
			StdOut.print(a[i] + " ");
		StdOut.println();
	}
	
	public static void main(String[] args)
	{
		Comparable [] a = {'Q','U','I','C','K','S','O','R','T','E','X','A','M','P','L','E'};
		Quick_Sort.sort(a);
		System.out.println("快速排序的结果是：");
		show(a);
		}

}
