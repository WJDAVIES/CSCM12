public class TestClass {
    public int findK_High_Low(int[] myArray, int k) {
      int low = 0, high = myArray.length - 1, position = myArray.length - k;
      while (low < high) {
        int pivot = partion(myArray, low, high);
        if (pivot < position){
			low = pivot + 1;
		}
          else if (pivot > position){
			  high = pivot - 1;
		  }
          else return myArray[pivot];
      }
      return myArray[low];
    }
    public int partion(int[] myArray, int low, int high) {
      int pivot = low, temp;
      while (low <= high) {
        while (low <= high && myArray[low] <= myArray[pivot]) low++;
        while (low <= high && myArray[high] > myArray[pivot]) high--;
          if (low > high) break;
            temp = myArray[low];
			myArray[low] = myArray[high];
            myArray[high] = temp;
      }
      temp = myArray[high];
      myArray[high] = myArray[pivot];
      myArray[pivot] = temp;
      return high;
    }
    public int[] largest_Values(int[] myArray, int k_highest, int K) { 
      int[] largest_k_values = new int[K];
	  int  j = 0;
      for (int i = 0; i < myArray.length; i++) {
        if (myArray[i] >= k_highest) {
          largest_k_values[j] = myArray[i];
         j++;
        }
      }
      return largest_k_values;
    }
    public int[] smallest_Values(int[] myArray, int k_Smallest, int K) { 
      int[] smallest_k_values = new int[K];
	  int j = K-1;
      for (int i = 0; i < myArray.length-1; i++) {
        if (myArray[i] <= k_Smallest) {
          smallest_k_values[j] = myArray[i];
          j--;
        }
      }
      return smallest_k_values;
    }
	public static boolean checkOrdered(int[] arr) {
		boolean myFlag = false;
		for (int i = 1; i < arr.length; i++) {
		    if (arr[i-1] < arr[i]) {
				myFlag =  true;
			}
			else return false;
		}
		return myFlag;
	}
	
	public static boolean checkOrder(int[] arr) {
		boolean myFlag = false;
		for (int i = 1; i < arr.length; i++) {
		    if (arr[i-1] > arr[i]) {
				myFlag = true;
		}
		else return false;
		}
		return myFlag;
	}
    public int XORsum(int[] smallest_k_values, int[] largest_k_values, int K) {
      int sum = 0;
      for (int i = 0; i < K; i++) {
        sum ^= smallest_k_values[i];
        sum ^= largest_k_values[i];
      }
      return sum;
    }
	public static int nextIndex(int max , int last,int offset,int len){
		last = (last * max + offset ) % len ;
		return last % max;
	}
	
	public static void main(String[] args) throws Exception {
		TestClass TC = new TestClass();
		int K = Integer.parseInt(args[0]);
		int myArray[] = FastRead.FastReadArray(args[1]); 
		Stopwatch st = new Stopwatch();
		int max = myArray.length;
		int len = myArray.length;
		int offset = 1 ; 
		int last = (int) (System.currentTimeMillis() % max);
		boolean isUp = checkOrdered(myArray);
		boolean isDown = checkOrder(myArray);
	 
		if (isUp == true || isDown == true) {
			for (int i = 0; i < myArray.length; i++) {
				int myIndex = nextIndex(max++,last,offset++,len);
				if(myIndex < 0){
					myIndex = myIndex*-1;
				}
        int temp = myArray[i];
		myArray[i] = myArray[myIndex];
		myArray[myIndex] = temp;
			}  
		} 
      int kHigh = TC.findK_High_Low(myArray, K);
      int kLow = TC.findK_High_Low(myArray, myArray.length-K+1);
	  int[] l_vals = TC.largest_Values(myArray, kHigh, K);
      int[] s_vals = TC.smallest_Values(myArray, kLow, K);
      System.out.println(TC.XORsum(s_vals, l_vals, K)); 
      double time = st.elapsedTime();
      System.err.println("Elapsed Time: "+time+" s");
    }
}