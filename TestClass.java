public class TestClass {
    public int findKth(int[] nums, int k) {
      int start = 0, end = nums.length - 1, index = nums.length - k;
      while (start < end) {
        int pivot = partion(nums, start, end);
        if (pivot < index) start = pivot + 1; 
          else if (pivot > index) end = pivot - 1;
          else return nums[pivot];
      }
      return nums[start];
    }
    public int partion(int[] nums, int start, int end) {
      int pivot = start, temp;
      while (start <= end) {
        while (start <= end && nums[start] <= nums[pivot]) start++;
        while (start <= end && nums[end] > nums[pivot]) end--;
          if (start > end) break;
            temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
      }
      temp = nums[end];
      nums[end] = nums[pivot];
      nums[pivot] = temp;
      return end;
    }
    public int[] largest(int[] arr, int kth, int K) { 
      int[] largest = new int[K];
      for (int i = 0, e = 0; i < arr.length; i++) {
        if (arr[i] >= kth) {
          largest[e] = arr[i];
          e++;
        }
      }
      return largest;
    }
    public int[] smallest(int[] arr, int kth, int K) { 
      int[] smallest = new int[K];
      for (int i = 0, e = K-1; i < arr.length; i++) {
        if (arr[i] <= kth) {
          smallest[e] = arr[i];
          e--;
        }
      }
      return smallest;
    }
    public int XORsum(int[] smallest, int[] largest, int K) {
      int sum = 0;
      for (int i = 0; i < K; i++) {
        sum ^= smallest[i];
        sum ^= largest[i];
      }
      return sum;
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
	public static int nextIndex(int max , int last,int offset,int len){
    last = (last * max + offset ) % len ;
    return last % max;
}
	public static void main(String[] args) throws Exception {
	
    int K = Integer.parseInt(args[0]);
    int myArray[] = FastRead.FastReadArray(args[1]);
	//System.out.println(arr.length);
    Stopwatch st = new Stopwatch();
	
	int max = myArray.length;
	int len = myArray.length;
	int offset = 1 ; 
	int last = (int) (System.currentTimeMillis() % max);
	boolean isUp = checkOrdered(myArray);
	boolean isDown = checkOrder(myArray);
	
	System.out.println(isDown + " " + isUp); 
	
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
	
	//for (int i = 0; i < arr.length; i++) {
	  //System.out.println(arr[i]);
	//}
   
      TestClass g = new TestClass(); 
      int kth = g.findKth(myArray, K);
      int kth2 = g.findKth(myArray, myArray.length-K+1);
      int[] largest = g.largest(myArray, kth, K);
      int[] smallest = g.smallest(myArray, kth2, K);
      System.out.println(g.XORsum(smallest, largest, K)); 

      double time = st.elapsedTime();
      System.err.println("Elapsed Time: "+time+" s");
	  
  }
}
