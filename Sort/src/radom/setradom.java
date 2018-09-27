package radom;

import java.util.Random;

public class setradom {
	
	//private static int ARRAY_SIZE; //设置数组空间大小
	
	//private static final int ARRAY_SIZE ; //设置数组空间大小
	public static int n = 0;
	public static int SIZE = 200;
	
	public static boolean isEmpty(int sortedArray[]) {
        if (sortedArray == null || sortedArray.length == 0) {
            return true;
        }

        return false;
    }
	public static int[] createRadomData() {
		
		Random random_1 = new Random(System.currentTimeMillis());
		
        n = random_1.nextInt(100) + 201;
        
        int[] arr = new int[n]; //定义数组
        
        //System.currentTimeMillis()获得当前时间
        Random random = new Random(System.currentTimeMillis()); //对应系统时间产生随机数
        
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(200) + 101; //nextint产生上限为200的int型随机数
        }
        return arr; //返回数组首地址
    }
	public static int[] createinvertedData() {
		
	      int[] arr = new int[SIZE];
	      
	      for(int i = 0;i < SIZE;i++)
	      {
	    	  arr[i] = i+1;
	      }
	      return arr;
	}
	public static int[] createpositiveData() {
		
		  int[] arr = new int[SIZE];
		  
	      for(int i = 0;i < SIZE;i++)
	      {
	    	  arr[i] = SIZE-i;
	      }
	      return arr;
	}
	public static void printArray(int[] arr) {
        for (int i = 0 ;i < arr.length;i++) //输出数组内容
        {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
