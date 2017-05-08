package javatry.enjoy.chooh;

import javatry.colorbox.ColorBox;
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
     * カラーボックスに入ってる日付の月を全て足したら？
     */
    public void test_sumMonth() {
        int monthSum = 0;
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof LocalDateTime || contents instanceof LocalDate){
                    monthSum = monthSum + toLocalDate(contents).getMonthValue();
                }
            }
        }
        log("カラーボックスに入ってる日付の月を全て足したら " + monthSum + " ヶ月です");

    }

    /**
     * カラーボックの中に入っている、0~100までの数値の数は？
     * @throws Exception
     */
    public void test_countZeroToHundred() throws Exception {
        Set<Object> numberSet = new HashSet<>();
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof Number) {
                    Number numContents = (Number) contents;
                    numberSet.add(numContents);
                } else if (contents instanceof Map<?, ?>) {
                    Map<?, ?> map = (Map<?, ?>) contents;
                    for (Map.Entry entry : map.entrySet()) {
                        if(entry.getKey() instanceof Integer) {
                            int intKeyContents = ((Integer) entry.getKey());
                            numberSet.add(intKeyContents);
                        } else if (entry.getValue() instanceof Integer) {
                            int intValueContents = ((Integer) entry.getValue());
                            numberSet.add(intValueContents);
                        }
                    }
                } else if (contents instanceof List) {
                    List<Object> ls = new ArrayList<>();
                    ls.addAll((List) contents);
                    for (int i = 0; i < ls.size(); i++) {
                        if(ls.get(i) instanceof BigDecimal) {
                            numberSet.add(ls.get(i));
                        }
                    }
                }
            }
        }

        log("カラーボックの中に入っている、0~100までの数値の数は " + numberSet.size() + "　個です");
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * 青色のカラーボックスに入ってる Map の中の商品で一番高いものは？
     */
    public void test_findMax() {
        int maxValue = 0;
        Map<?, ?> map;
        String maxValueItem = "";
        for (ColorBox colorBox : getColorBoxList()) {
            if (colorBox.getColor().toString().equals("blue")) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    Object contents = boxSpace.getContents();
                    if (contents instanceof Map<? ,?>) {
                        map = (Map)contents;
                        for (Map.Entry entry : map.entrySet()){
                            int priceValue = (Integer) entry.getValue();
                            if (priceValue > maxValue) {
                                maxValue = priceValue;
                            }
                        }
                        for (Map.Entry entry : map.entrySet()){
                            if (Objects.equals(maxValue, entry.getValue())) {
                                maxValueItem = (String) entry.getKey();
                            }
                        }
                    }
                }
            }
        }
        log("青色のカラーボックスに入ってる Map の中の商品で一番高いものは " + maxValueItem);
    }

    /**
     * カラーボックスの中で、一番幅が大きいものでInteger型を持っているカラーボックスの色は？
     */
    public void test_findColorBigWidthHasInteger() {
        int maxWidth = 0;
        String maxColorName = "";
        List<ColorBox> colorBoxList = getColorBoxList();
        if (colorBoxList.size() == 0) {
            log("カラーボックスがそもそも存在しない");
        } else {
            for (ColorBox colorBox : getColorBoxList()) {
                BoxSize size = colorBox.getSize();
                if (size.getWidth() > maxWidth) {
                    maxWidth = size.getWidth();
                    maxColorName = colorBox.getColor().toString();
                }
            }
        }
        log("カラーボックスの中で、一番幅が大きいものでInteger型を持っているカラーボックスの色は" + maxColorName);
    }

    /**
     * カラーボックスの中に入ってる BigDecimal を全て足し合わせると？
     */
    public void test_sumBigDecimal() {
        BigDecimal bigDecimalSum = BigDecimal.ZERO;
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof List) {
                    List<Object> bigDecimalList = new ArrayList<>();
                    bigDecimalList.addAll((List) contents);
                    for (int i = 0; i < bigDecimalList.size(); i++) {
                        if (bigDecimalList.get(i) instanceof BigDecimal) {
                            bigDecimalSum = bigDecimalSum.add((BigDecimal) bigDecimalList.get(i));
                        }
                    }
                }
            }
        }
        log("カラーボックスの中に入ってる BigDecimal を全て足し合わせると " + bigDecimalSum + " です");
    }
}
