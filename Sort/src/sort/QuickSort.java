package sort;

import radom.DataUtils;
import radom.setradom;

/**øÏÀŸ≈≈–Ú**/
public class QuickSort extends SortAdapter{
	
	
	@Override
    public void sort(int[] unsortedArray) {
		 qSort(unsortedArray, 0, unsortedArray.length - 1);
		
	}
	private void qSort(int[] arr, int l, int h) {

        if (l < h) {
            int pivot = Partition(arr, l, h);
            qSort(arr, l, pivot - 1);
            qSort(arr, pivot + 1, h);
        }

    }

    private int Partition(int[] arr, int l, int h) {

        int k = arr[l];
        int pivot = arr[l];

        while (l < h) {

            while (l < h && arr[h] <= pivot) {
            	compnum++;
                --h;
            }
            DataUtils.cadd(compnum);
            arr[l] = arr[h];
            movenum++;
            DataUtils.madd(movenum);
            DataUtils.add(arr);

            while (l < h && arr[l] >= pivot) {
            	compnum++;
                ++l;
            }
            DataUtils.cadd(compnum);
            arr[h] = arr[l];
            movenum++;
            DataUtils.madd(movenum);
            DataUtils.add(arr);
        }

        arr[l] = k; 
        movenum+=2;
        DataUtils.madd(movenum);
        DataUtils.add(arr);
        DataUtils.cadd(compnum);
      
        return l;

    }

}
