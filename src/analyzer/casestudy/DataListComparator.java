package casestudy;

import java.util.Comparator;

/**
 * Dataクラスのソートを行うためのクラス
 */
public class DataListComparator implements Comparator<Data> {

    /**
     * 比較メソッド タイムスタンプを基に昇順でソートする（データクラスを比較して-1, 0, 1を返すように記述する）
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Data a, Data b) {

        long no1 = a.getTimeStamp();
        long no2 = b.getTimeStamp();

        //昇順でソートされる
        if (no1 < no2) {
            return 1;

        } else if (no1 == no2) {
            return 0;

        } else {
            return -1;

        }

    }

}