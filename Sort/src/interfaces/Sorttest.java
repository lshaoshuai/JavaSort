package interfaces;
 
import sort.*;
import radom.DataUtils;
import radom.setradom;

import sort.BubbleSort;
public class Sorttest {

	    public static void main(String[] args) {
	    	
	        int[] arr = setradom.createRadomData(); //����һ���������

	        setradom.printArray(arr); //����������

	        Sort sort = new ShellSort();  //����ð������
	        
	        sort.sort(arr);

	        setradom.printArray(arr); //����������
	        
	        SortAdapter.printcdd();

	    }

}
