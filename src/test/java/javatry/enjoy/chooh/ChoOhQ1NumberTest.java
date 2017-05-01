package javatry.enjoy.chooh;

import javatry.colorbox.ColorBox;
import javatry.colorbox.color.BoxColor;
import javatry.colorbox.size.BoxSize;
import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 数値関連のテスト。<br>
 * 何々は？と言われたら、それに該当するものをログに出力すること。
 * @author ChoOh
 */
public class ChoOhQ1NumberTest extends ColorBoxTestCase {

    // ===================================================================================
    //                                                                             Convert
    //
    //
    //                                                                            =======

    /**
     * カラーボックスで文字列の値を取り出し、リストで返すメソッド
     */
    private List<Integer> getColorBoxNumContentList(){
        List<Integer> colorBoxIntContentList = new ArrayList<Integer>();

        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof Integer) {
                    int intContents = ((Integer) contents);
                    colorBoxIntContentList.add(intContents);
                }
            }
        }
        return colorBoxIntContentList;
    }

    /**
     * カラーボックスに入ってる日付の月を全て足したら？
     */
    public void test_sumMonth() {
        int sum = 0;
        List<ColorBox> colorBoxList = getColorBoxList();
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                if (contents != null) {
                    if (contents instanceof LocalDateTime || contents instanceof LocalDate){
                        sum = sum + toLocalDate(contents).getMonthValue();
                    }
                }
            }
        }
        log(sum);

    }

    /**
     * カラーボックの中に入っている、0~100までの数値の数は？
     * @throws Exception
     */
    public void test_countZeroToHundred() throws Exception {
        Set<Object> colorNameSet = new HashSet<>();
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof Integer) {
                    int intContents = ((Integer) contents);
                    colorNameSet.add(intContents);
                } else if (contents instanceof Map<?, ?>) {
                    Map<?, ?> map = (Map<?, ?>) contents;
                    for (Map.Entry entry : map.entrySet()) {
                        if(entry.getKey() instanceof Integer) {
                            int intKeyContents = ((Integer) entry.getKey());
                            colorNameSet.add(intKeyContents);
                        } else if (entry.getValue() instanceof Integer) {
                            int intValueContents = ((Integer) entry.getValue());
                            colorNameSet.add(intValueContents);
                        }
                    }
                } else if (contents instanceof List) {
                    List<Object> ls = new ArrayList<>();
                    ls.addAll((List) contents);
                    for (int i = 0; i < ls.size(); i++) {
                        if(ls.get(i) instanceof BigDecimal) {
                            colorNameSet.add(ls.get(i));
                        }
                    }
                }
            }
        }

        log(colorNameSet.size());
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * 青色のカラーボックスに入ってる Map の中の商品で一番高いものは？
     */
    public void test_findMax() {
        int value = 0;
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof Map<? ,?>) {
                    Map<?, ?> map = (Map)contents;
                    Map.Entry<Objects, Objects> maxEntry = null;
                    for (Map.Entry entry : map.entrySet()){
                        int goodValue = (Integer) entry.getValue();
                        if (goodValue > value) {
                            value = goodValue;
                        }
                    }
                }
            }
        }
        log(value);
    }

    /**
     * カラーボックスの中で、一番幅が大きいものでInteger型を持っているカラーボックスの色は？
     */
    public void test_findColorBigWidthHasInteger() {
        int maxDepth = 0;
        List<ColorBox> colorBoxList = getColorBoxList();
        if (colorBoxList.size() == 0) {
            log("カラーボックスがそもそも存在しない");
        } else {
            for (ColorBox colorBox : getColorBoxList()) {
                BoxSize size = colorBox.getSize();
                if (size.getDepth() > maxDepth) {
                    maxDepth = size.getDepth();
                }
            }
        }
        log(maxDepth);
    }

    /**
     * カラーボックスの中に入ってる BigDecimal を全て足し合わせると？
     */
    public void test_sumBigDecimal() {
        BigDecimal sum = BigDecimal.valueOf(0);
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof List) {
                    List<Object> ls = new ArrayList<>();
                    ls.addAll((List) contents);
                    for (int i = 0; i < ls.size(); i++) {
                        if (ls.get(i) instanceof BigDecimal) {
                            sum = sum.add((BigDecimal) ls.get(i));
                        }
                    }
                }
            }
        }
        log(sum);
    }
}
