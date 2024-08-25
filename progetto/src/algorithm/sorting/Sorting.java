package src.algorithm.sorting;

/**
 * This class contains various sorting algorithms
 */
public class Sorting {

	//ho deciso di usare quicksort per il costo asintotico di nlogn nel caso medio e ottimo,
	//non ho usato il mergesort che ha costo nlogn anche nel caso pessimo per il fatto che
	// non e' in place, quindi in caso di grafi molto grandi occuperebbe molto piu' spazio
	private static <T extends Comparable<T>> void swap(T A[], int i, int j) {
		T tmp = A[i];
		A[i]  = A[j];
		A[j]  = tmp;
	}


	/**
	 * Sorts the specified array according to the ordering induced by the compareTo() method in O(n<sup>2</sup>) and O(nlogn) on the average
	 * <p>
	 * Implements the quicksort algorithm.
	 * <ul>
	 * <li> Worst-case cost:  &Theta;(n<sup>2</sup>)
	 * <li> Average/Best-case cost: &Theta;(nlogn)
	 * </ul>
	 * @param A the array to be sorted
	 * @param <> class of the object in the array
	 */


	public static <T extends Comparable<T>> void quicksort(T []A) {
		int p=0;
		int r=A.length-1;
		quicksort(A, p, r);
	}
	public static <T extends Comparable<T>> void quicksort(T[] A, int p, int r) {
		if(p<r){
			int q=partition(A, p, r);
			quicksort(A,p,q-1);
			quicksort(A,q+1,r);
		}
	}
	private static <T extends Comparable<T>> int partition (T[] A, int p, int r){
		T x=A[r];
		int i=p-1;
		for(int j=p; j<r; j++){
			if(A[j].compareTo(x) <= 0){ //A[j]<=x){
				i++;
				swap(A, i, j);
			}
		}
		swap(A, i+1, r);
		return i+1;

	}

	/**
	 * Sorts the specified array into ascending numerical order in O(n<sup>2</sup>) and O(nlogn) on the average
	 * <p>
	 * Implements the quicksort algorithm.
	 * <ul>
	 * <li> Worst-case cost:  &Theta;(n<sup>2</sup>)
	 * <li> Average/Best-case cost: &Theta;(nlogn)
	 * </ul>
	 * @param A the array to be sorted
	 */
	public static void quicksort(int A[]) {

	}

}