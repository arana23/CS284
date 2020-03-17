package general;
//Shay McCarthy and Aparajita Rana

public class Sort {

	private static int[] merge(int[] a, int[] b) {
		   int[] c = new int[a.length+b.length];
		   int ia=0;
		   int ib=0;
		   int ic=0;
		   
		   while (ia<a.length && ib<b.length) {
			   if (a[ia]<b[ib]) {
				   c[ic] = a[ia];
				   ia++;
			   } else {
				   c[ic] = b[ib];
				   ib++;
			   }
			   ic++;
		   }
		   
		   while (ia<a.length) {
			   c[ic]=a[ia];
			   ia++;
			   ic++;
		   }
		   
		   while (ib<b.length) {
			   c[ic]=b[ib];
			   ib++;
			   ic++;
		   }
		   
		   
		   
		   return c;
	}
	
	public static <E extends Comparable<E>> void qs(E[] a) {
		qs(a, 0, a.length - 1);
	}

	private static <E extends Comparable<E>> void qs(E[] a, int first, int last) {
		if (first < last) { // There is data to be sorted. 
			int pivIndex = partition(a, first, last); 
			qs(a, first, pivIndex - 1); 
			qs(a, pivIndex + 1, last);
		}
		}
	
	public static <E extends Comparable<E>> int partition(E[] a, int first, int last) { 
/*		E temp;
		int i=1;

	    for(int x=1;x<a.length;x++)
	    {
	        if((int) a[x]<=first)
	        {
	            temp=a[x];
	            a[x]=a[i];
	            a[i]=temp;
	            i++;
	        }
	    }

	    temp=a[i-1];
	    a[i-1]=a[0];
	    a[0]=temp;
	    return i;*/
	    
	    int pivot = first;
	      while (first < last)
	      {
	         if (first== pivot || last == pivot) 
	         {
	            return first;
	         }
	         while (first < pivot)
	        	 {
	        	 first++;
	        	 }
	         while (last > pivot)
	        	 {
	        	 last--;
	        	 }
	         swap (a,first,last);
	      }
	      return first;

	}
	
	public static <E extends Comparable<E>> void swap (E[]a, int x, int y)
	   {
	      E temp = a[x];
	      a[x] = a[y];
	      a[y] = temp;
	   }
	
	public static void main(String[] args) {
		Integer[] a = {10,7,6,5};
		int x=partition(a,10,5);
		System.out.println(x);
		
	}
}
