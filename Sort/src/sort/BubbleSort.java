package sort;

import radom.DataUtils;
import radom.setradom;
/**Ã°ÅÝÅÅÐò**/
public class BubbleSort extends SortAdapter{
	
	
	@Override
    public void sort(int[] unsortedArray) {
		
		int j,n=unsortedArray.length-1,i=1;
		
        int t;

        while((n >= 0) && (i == 1))
        {
        	i=0;
        	for(j = 0;j < n;j++)
        	{
        		compnum++;
        		if (unsortedArray[j] < unsortedArray[j+1]) {
        		    i = 1;
                    t = unsortedArray[j];
                    unsortedArray[j] = unsortedArray[j+1];
                    unsortedArray[j+1] = t;
                    movenum += 3;
                  
                }  
        		
            }
        	DataUtils.cadd(compnum);
            DataUtils.madd(movenum);
            DataUtils.add(unsortedArray);
            n--;
        }
    }
}
