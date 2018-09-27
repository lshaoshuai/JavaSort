package interfaces;
 
import sort.*;
import radom.DataUtils;
import radom.setradom;

import sort.BubbleSort;
public class Sorttest {

	    public static void main(String[] args) {
	    	
	        int[] arr = setradom.createRadomData(); //返回一个随机数组

	        setradom.printArray(arr); //输出随机数组

	        Sort sort = new ShellSort();  //测试冒泡排序
	        
	        sort.sort(arr);

	        setradom.printArray(arr); //输出随机数组
	        
	        SortAdapter.printcdd();

	    }

}
