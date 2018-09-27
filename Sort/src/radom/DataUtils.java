package radom;

import java.util.ArrayList; //可边长数组列表
import java.util.Arrays; //处理数组
import java.util.List;

public class DataUtils {
    
	//建立列表
    public static List<int[]> sData = new ArrayList<>();
    public static List<String> mData = new ArrayList<>();
    public static List<String> cData = new ArrayList<>();
    public static void add(int[] data) {
        int[] elem = Arrays.copyOf(data, data.length); //复制数组
        sData.add(elem); //添加一个数组进列表
    }
    public static void madd(int elem) {
        mData.add("" + elem); //将一个元素追加到列表末尾
    }
    public static void cadd(int elem) {
        cData.add("" + elem); //将一个元素追加到列表末尾
    }
    public static void clear() {
        sData.clear();
        mData.clear();
        cData.clear();
    }
}
