package edu.princeton.cs.algs4;

public class Heap {
	public static void sort(Comparable [] a){
		int N = a.length;
		//构建堆,结果是形成一个有序的大根堆
		for (int k= N/2; k>=1; k--)
			sink(a,k,N);
		//下沉排序
		while (N>1)
		{
			exch(a,1,N);
			sink(a,1,--N);
		}
	}
	
	private static void sink(Comparable [] a, int k, int N)
	{
		while(2*k <=N){
			int j=2*k;
			if (j<N && less(a,j,j+1))
				j++;
			if (!less(a,k,j))
				break;
			exch(a,k,j);
			k=j;
		}		
	}
	
	private static boolean less(Comparable [] a,int i,int j)

	{
		return a[i-1].compareTo(a[j-1])<0;	
	}
	
	private static void exch (Object [] a, int i, int j)
	{
		Object swap = a[i-1];
		a[i-1] = a[j-1];
		a[j-1] = swap;		
	}
	
	 private static void show(Comparable[] a) {
	        for (int i = 0; i < a.length; i++) {
	            StdOut.println(a[i]);
	        }
	    }
	
	 public static void main(String[] args)
		{
			Comparable [] a = {3,5,3,0,8,6,1,5,8,6,2,4,9,4,7,0,1,8,9,7,3,1,2,5,9,7,4,0,2,6};
			sort(a);
			System.out.println("排序结果：");
			show(a);
			}
}
