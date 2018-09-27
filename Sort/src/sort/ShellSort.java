package sort;

import radom.DataUtils;
import radom.setradom;

/**Ï£¶ûÅÅÐò**/
public class ShellSort extends SortAdapter{
	
	
	//int t = 0;
	@Override
    public void sort(int[] unsortedArray) {
        int t;
        int[] dk=new int[100];      
		t=setdk(unsortedArray,dk);
		ShellSort(unsortedArray,dk,t);
	}
	private int setdk(int[] unsortedArray,int[] dk)
	{
		int t=0;
		for(int i = unsortedArray.length/2;i>0;i/=2)
		{
			dk[t]=i;
			t++;
		}
		return t;
	}
	private void ShellSort(int[] unsortedArray,int[] dk,int t)
	{
		for(int i=0;i<t;i++)
		{
			ShellInsert(unsortedArray,dk[i]);
		}
	}
	private void ShellInsert(int[] unsortedArray,int dk)
	{
		int i, k, j;
		
		for(i = dk;i < unsortedArray.length;i++)
		{
			compnum++;
			if(unsortedArray[i] > unsortedArray[i-dk])
			{
				k = unsortedArray[i];
				for(j=i-dk; j>=0 && k > unsortedArray[j];j-=dk)
				{
					compnum++;
					unsortedArray[j+dk]=unsortedArray[j];
					movenum++;
				}
				unsortedArray[j+dk] = k;
				movenum+=2;
               
			} 
			DataUtils.cadd(compnum);
            DataUtils.madd(movenum);
            DataUtils.add(unsortedArray);
			
		}
	}

}
