package sort;


import radom.DataUtils;
import radom.setradom;

/**÷±Ω”≤Â»Î≈≈–Ú**/
public class InsertSort extends SortAdapter{
	@Override
    public void sort(int[] unsortedArray) {
		
		 int t, i, j;

	        for (i = 1; i < unsortedArray.length; i++) {
	        	
	        	compnum++;
	            if (unsortedArray[i] > unsortedArray[i - 1]) {
	            	
	                t = unsortedArray[i];

	                for (j = i; j > 0 && t > unsortedArray[j - 1]; j--) {
	                	
	                	compnum++;
	                    unsortedArray[j] = unsortedArray[j - 1];
	                    movenum++;

	                }
	                unsortedArray[j] = t;
	                movenum+=2;
	                

	            }
	            DataUtils.cadd(compnum);
	            DataUtils.madd(movenum);
	            DataUtils.add(unsortedArray);

	        }
	}

}
