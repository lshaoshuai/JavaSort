package sort;

import radom.DataUtils;
import radom.setradom;
import java.util.Arrays; //处理数组

/**归并排序**/
public class MergSort extends SortAdapter{
	
	@Override
    public void sort(int[] unsortedArray) {
		
		MSort(unsortedArray,unsortedArray,0,unsortedArray.length-1);
		
	}
	private void Merge(int[] R,int[] T,int l,int m,int h)
	{
		int i=l,j=m+1,k=l;
		while(i <= m && j <= h)
		{
			compnum++;
			if(R[i]>=R[j])
			{
				T[k++]=R[i++];
				 movenum++;
			}	
			else
			{
				T[k++]=R[j++];
				 movenum++;
			}		
			DataUtils.add(T);
			DataUtils.cadd(compnum);
            DataUtils.madd(movenum);
		}
		while(i<=m) 
		{
			T[k++]=R[i++];
			 movenum++;
			DataUtils.add(T);
			DataUtils.madd(movenum);
			DataUtils.cadd(compnum);
		}		
		while(j<=h) 
		{
			T[k++]=R[j++];
			 movenum++;
			DataUtils.add(T);
			DataUtils.madd(movenum);
			DataUtils.cadd(compnum);
		}
	}
	private void MSort(int[] R,int[] T,int l,int h)
	{
		int m;
		int[] S=Arrays.copyOf(R,R.length);
		if(l==h) T[l]=R[l];
		else
		{
			m=(l+h)/2;
			MSort(R,S,l,m);
			MSort(R,S,m+1,h);
			Merge(S,T,l,m,h);	
		}
		
	}

}
