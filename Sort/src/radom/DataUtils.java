package radom;

import java.util.ArrayList; //�ɱ߳������б�
import java.util.Arrays; //��������
import java.util.List;

public class DataUtils {
    
	//�����б�
    public static List<int[]> sData = new ArrayList<>();
    public static List<String> mData = new ArrayList<>();
    public static List<String> cData = new ArrayList<>();
    public static void add(int[] data) {
        int[] elem = Arrays.copyOf(data, data.length); //��������
        sData.add(elem); //���һ��������б�
    }
    public static void madd(int elem) {
        mData.add("" + elem); //��һ��Ԫ��׷�ӵ��б�ĩβ
    }
    public static void cadd(int elem) {
        cData.add("" + elem); //��һ��Ԫ��׷�ӵ��б�ĩβ
    }
    public static void clear() {
        sData.clear();
        mData.clear();
        cData.clear();
    }
}
