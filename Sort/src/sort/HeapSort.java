package sort;

import radom.DataUtils;
import radom.setradom;

/**¶ÑÅÅÐò**/
public class HeapSort extends SortAdapter {

	@Override
    public void sort(int[] unsortedArray) {
		
		int x;
		CreatHeap(unsortedArray);
		for(int i=unsortedArray.length-1;i>0;i--)
		{
			x = unsortedArray[0];
			unsortedArray[0] = unsortedArray[i];
			unsortedArray[i] = x;
			movenum+=3;
			HeapAdjust(unsortedArray,0,i-1);
		}

	}
	private void HeapAdjust(int[] unsortedArray,int s,int m)
	{
		int k, j,l=1;
		k = unsortedArray[s];
		for(j=2*s+1;j<=m;j=j*2+1)
		{
			compnum+=2;
			DataUtils.cadd(compnum);
			if(j<m && unsortedArray[j]>unsortedArray[j+1])
			{
				j++;
			}
			
			if(k<=unsortedArray[j]) 
			{
				l=0;
				break;
			}
			unsortedArray[s]=unsortedArray[j]; 
			s=j;
			movenum++;
			DataUtils.madd(movenum);
			DataUtils.add(unsortedArray);
			
		}
		if(l==1)
		{
			DataUtils.cadd(compnum);
		}
		unsortedArray[s]=k;
		movenum+=2;
		DataUtils.madd(movenum);
		DataUtils.add(unsortedArray);
		
	}
	private void CreatHeap(int[] unsortedArray)
	{
		int n;
		if(unsortedArray.length % 2==0)
		{
			n=unsortedArray.length/2-1;
		}
		else
		{
			n=unsortedArray.length/2;
		}
		for(int i=n;i>=0;i--)
		{
			HeapAdjust(unsortedArray,i,unsortedArray.length-1);
		}
			 
	}
	
}
