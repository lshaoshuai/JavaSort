package sort;

import interfaces.Sort;
/***��Ӧsort�ӿڵ�sort������***/

public class SortAdapter implements Sort{ 
	
	public static int movenum;
	
	public static int compnum;
	
	public SortAdapter() {
		movenum = 0;
		compnum = 0;
	}
	@Override
	public void sort(int unsortedArray[]){
		
		
	}
    public static int getmovenum(){
		return movenum;
	}
    public static int getcompnum(){
		return compnum;
	}
	public static void printcdd()
	{
		System.out.print(compnum + " ");
		System.out.println("");
		System.out.print(movenum + " ");
	}

}
