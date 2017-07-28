package edu.princeton.cs.algs4;

/*递归的将一个数组分成两半分别排序，然后将结果归并起来。
 * 归并排序总的时间复杂度为O(NlogN),比较稳定。
 * 缺点：所需的额外空间和N成正比。
 */
public class Merge_Sort {
	private static Comparable [] aux;//归并所需的辅助数组
	
	private static boolean less(Comparable v, Comparable w)
	{return v.compareTo(w)<0;}//比较函数
	
	public static void sort(Comparable [] a)
	{
		aux = new Comparable [a.length];
		sort(a, 0 ,a.length-1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi)
	{//将数组a[lo...hi]排序
		if (hi<=lo) return;
		int mid = lo +(hi-lo)/2;
		sort(a,lo,mid);//将左半边排序
		sort(a,mid+1,hi);//右半边排序
		merge(a,lo,hi);		//下面的代码，归并操作
	}
	
	public static void merge(Comparable[] a ,int lo, int hi)
	{//将a[lo...mid]和a[mid+1...hi]归并
		int mid = lo +(hi-lo)/2;
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
	
	private static void show(Comparable[] a)//打印函数
	{
		for (int i=0; i<a.length;i++)
			StdOut.print(a[i] + " ");
		StdOut.println();
	}
	
	public static void main(String[] args)
	{
		Comparable [] a = {51,46,20,18,65,97,82,30,77,50};
		Merge_Sort.sort(a);
		System.out.println("排序结果：");
		show(a);
		}
}
