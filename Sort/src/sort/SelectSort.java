package sort;

import radom.DataUtils;
import radom.setradom;

/**—°‘Ò≈≈–Ú**/
public class SelectSort extends SortAdapter{
	
	
	@Override
    public void sort(int[] unsortedArray) {
		
		 int k, t, i, j, h;
	        for (i = 0; i < unsortedArray.length; i++) {

	            k = i;

	            for (j = i + 1; j < unsortedArray.length; j++) {
	            	
	            	compnum++;
	                if (unsortedArray[k] < unsortedArray[j]) {
	                    k = j;
	                }
	            }

	            if (k != i) {
	            	
	                t = unsortedArray[i];
	                unsortedArray[i] =unsortedArray[k];
	                unsortedArray[k] = t;
	                movenum +=3;
	                
	            }
	            DataUtils.cadd(compnum);
            	DataUtils.madd(movenum);
                DataUtils.add(unsortedArray);

	        }
	        DataUtils.cadd(compnum);
        	DataUtils.madd(movenum);
            DataUtils.add(unsortedArray);
		
	}

}
