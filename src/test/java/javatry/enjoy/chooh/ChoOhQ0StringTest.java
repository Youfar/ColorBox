package javatry.enjoy.chooh;

import javatry.colorbox.ColorBox;
import javatry.colorbox.color.BoxColor;
import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;

import java.time.LocalDateTime;
import java.util.*;


/**
 * 文字列のテスト。<br>
 * 何々は？と言われたら、それに該当するものをログに出力すること。
 * @author ChoOh
 */
public class ChoOhQ0StringTest extends ColorBoxTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * 最初のカラーボックスの色の名前の文字数は？
     */
    public void test_length_basic() {
        List<ColorBox> colorBoxList = getColorBoxList();
        ColorBox colorBox = colorBoxList.get(0);
        BoxColor boxColor = colorBox.getColor();
        String colorName = boxColor.getColorName();
        log(colorName, colorName.length());
    }

    /**
     * カラーボックスに入ってる文字列の中で、一番長い文字列は？
     */
    public void test_length_findMax() {
        String maxString = "";

        //List<ColorBox> colorBoxList = getColorBoxList();
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof String) {
                    String strContents = (String) contents;
                    if (strContents.length() > maxString.length()){
                        maxString = strContents;
                    }
                }
            }
        }
        log(maxString);
    }

    /**
     * カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？
     */
    public void test_length_findMaxMinDiff() {
        ArrayList<String> list = new ArrayList<String>();

        List<ColorBox> colorBoxList = getColorBoxList();
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                if (contents != null) {
                    if (contents instanceof String) {
                        String strContents = contents.toString();
                        list.add(strContents);
                    }
                }
            }
        }
        list.sort(Comparator.comparing(String::length).reversed());
        log(list.get(0).length() - list.get(list.size()-1).length());
    }

    /**
     * カラーボックスに入ってる値 (文字列以外のものはtoString()) の中で、二番目に長い文字列は？ <br>
     * ソートして二番目を取得する、ってやり方で。
     */
    public void test_length_findSecondMax_bySort() {
        ArrayList<String> list = new ArrayList<String>();

        List<ColorBox> colorBoxList = getColorBoxList();
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                if (contents != null) {
                    String strContents = contents.toString();
                    list.add(strContents);
                }

            }
        }
        list.sort(Comparator.comparing(String::length).reversed());
        log(list.get(1));
    }

    /**
     * カラーボックスに入ってる値 (文字列以外のものはtoString()) の中で、二番目に長い文字列は？ <br>
     * ただし、ソートして二番目を取得する、ってやり方は利用しないこと。
     */
    public void test_length_findSecondMax_nonSorted() {
        String secMaxStr = "";
        String maxString = "";

        List<ColorBox> colorBoxList = getColorBoxList();
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                if (contents != null) {
                    String strContents = contents.toString();
                    if (strContents.length() > maxString.length()) {
                        secMaxStr = maxString;
                        maxString = strContents;
                    } else {
                        if (strContents.length() > secMaxStr.length()) {
                            secMaxStr = strContents;
                        }
                    }
                }
            }
        }
        log(secMaxStr);
    }

    /**
     * カラーボックスに入ってる文字列の長さの合計は？
     */
    public void test_length_calculateLengthSum() {
        ArrayList<String> list = new ArrayList<String>();
        //Iterator<String> colorBoxIter = list.iterator();
        int length = 0;

        List<ColorBox> colorBoxList = getColorBoxList();
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                if (contents != null) {
                    if (contents instanceof String) {
                        String strContents = contents.toString();
                        list.add(strContents);
                    }
                }
            }
        }

        int listSum = list.stream().mapToInt(w -> w.length()).sum();
        log(listSum);
    }

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * 「かまくら」で始まる文字列をしまっているカラーボックスの色は？
     */
    public void test_startsWith_findFirstWord() {
        ArrayList<String> list = new ArrayList<String>();

        List<ColorBox> colorBoxList = getColorBoxList();
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                if (contents != null) {
                    if (contents instanceof String) {
                        String strContent = (String) contents;
                        if (strContent.startsWith("かまくら")){
                            log(colorBox.getColor());
                        }
                    }
                }
            }
        }
    }

    /**
     * 「いぬ」で終わる文字列をしまっているカラーボックスの色は？
     */
    public void test_endsWith_findLastWord() {
        ArrayList<String> list = new ArrayList<String>();

        List<ColorBox> colorBoxList = getColorBoxList();
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                if (contents != null) {
                    if (contents instanceof String) {
                        String strContent = (String) contents;
                        if (strContent.endsWith("いぬ")){
                            log(colorBox.getColor());
                        }
                    }
                }
            }
        }
    }

    // ===================================================================================
    //                                                            indexOf(), lastIndexOf()
    //                                                            ========================
    /**
     * あなたのカラーボックスに入ってる「いぬ」で終わる文字列で、「いぬ」は何文字目から始まる？
     */
    public void test_indexOf_findIndex() {
        ArrayList<String> list = new ArrayList<String>();

        List<ColorBox> colorBoxList = getColorBoxList();
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                if (contents != null) {
                    if (contents instanceof String) {
                        String strContent = (String) contents;
                        if (strContent.endsWith("いぬ")){
                            log(strContent.indexOf("いぬ"));
                        }
                    }
                }
            }
        }
    }

    /**
     * あなたのカラーボックスに入ってる「ず」を二つ以上含む文字列で、最後の「ず」は何文字目から始まる？
     */
    public void test_lastIndexOf_findIndex() {
        ArrayList<String> list = new ArrayList<String>();

        List<ColorBox> colorBoxList = getColorBoxList();
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                if (contents != null) {
                    if (contents instanceof String) {
                        String strContent = (String) contents;
                        if (strContent.indexOf("ず") != strContent.lastIndexOf("ず")){
                            log(strContent.lastIndexOf("ず"));
                        }
                    }
                }
            }
        }
    }

    // ===================================================================================
    //                                                                         substring()
    //                                                                         ===========
    /**
     * カラーボックスに入ってる「いぬ」で終わる文字列の最初の一文字は？
     */
    public void test_substring_findFirstChar() {
        List<ColorBox> colorBoxList = getColorBoxList();
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                if (contents != null) {
                    if (contents instanceof String) {
                        String strContent = (String) contents;
                        if(strContent.endsWith("いぬ")){
                            log(strContent.substring(0,1));
                        }
                    }
                }
            }
        }
    }

    /**
     * カラーボックスに入ってる「かまくら」で始まる文字列の最後の一文字は？
     */
    public void test_substring_findLastChar() {
        List<ColorBox> colorBoxList = getColorBoxList();
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                if (contents != null) {
                    if (contents instanceof String) {
                        String strContent = (String) contents;
                        if(strContent.startsWith("かまくら")){
                            log(strContent.substring(strContent.length() - 1, strContent.length()));
                        }
                    }
                }
            }
        }
    }

    // ===================================================================================
    //                                                                           replace()
    //                                                                           =========
    /**
     * カラーボックスに入ってる「ー」を含んだ文字列から「ー」を全て除去したら何文字？
     */
    //Todo:没有-怎么办
    public void test_replace_removeBo() {
        List<ColorBox> colorBoxList = getColorBoxList();
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                if (contents != null) {
                    if (contents instanceof String) {
                        String strContent = (String) contents;
                        if(strContent.contains("ー")){
                            strContent.replace("ー","");
                            log(strContent.length());
                        }
                    }
                }
            }
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * カラーボックスの中で、色の名前が一番長いものは？
     */
    //Todo: all null判断
    public void test_findMaxColorSize() throws Exception {
        List<ColorBox> colorBoxList = getColorBoxList();
        ArrayList<String> colorNameList = new ArrayList<String>();

        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            BoxColor color = colorBox.getColor();
            colorNameList.add(color.toString());
        }
        colorNameList.sort(Comparator.comparing(String::length).reversed());
        log(colorNameList.get(0));


    }

    /**
     * カラーボックスの中で、2012/06/04 を示す日付が持っている秒は？
     */
    public void test_findDBFluteBirthdateSecond() throws Exception {
        List<ColorBox> colorBoxList = getColorBoxList();
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                if (contents != null) {
                    if (contents instanceof LocalDateTime){
                        if(toLocalDate(contents).isEqual(toLocalDate("2012/06/04"))){
                            log(toLocalDateTime(contents).getSecond());
                        }
                    }
                }
            }
        }
    }

    /**
     * カラーボックスの中に入っている Map を "map:{ key = value ; key = value ; ... }" という形式で表示。
     */
    public void test_showMap() throws Exception {
        List<ColorBox> colorBoxList = getColorBoxList();
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                if (contents != null) {
                    if (contents instanceof Map){
                        Map<Object, Object> map = (Map)contents;
                        for (Map.Entry entry : map.entrySet()){
                            log(entry.getKey() + " = " + entry.getValue());
                        }
                    }
                }
            }
        }
    }

    /**
     * "map:{ key1 = value1 ; key2 = value2 ; key3 = value3 }" という文字列をMapに変換してtoString()すると？
     * <pre>
     * 変換後のMapの中身は、以下のようになっていること
     *  o key1というキーに対してvalue1という値
     *  o key2というキーに対してvalue2という値
     *  o key3というキーに対してvalue3という値
     * </pre>
     */
    public void test_parseMap() throws Exception {
        Map<String, String> Map = new HashMap<String, String>();
        String mapString = "map:{ key1 = value1 ; key2 = value2 ; key3 = value3 }";
        String subMapString = mapString.substring(5,mapString.length()-1);
        String[] pairs = subMapString.split(";");
        for (int i = 0; i < pairs.length; i++) {
            String pair = pairs[i];
            String[] keyValue = pair.split("=");
            Map.put(keyValue[0],keyValue[1]);
        }
        log(Map.toString());
    }

    /**
     * "map:{ key1 = value1 ; key2 = map:{ nkey21 = nvalue21 ; nkey22 = nvalue22 } ; key3 = value3 }" <br />
     * という文字列をMapに変換してtoString()すると？ <br />
     * <br />
     * "map:{ key1 = value1 ; key2 = value2 ; key3 = map:{ nkey31 = nvalue31 ; nkey32 = nvalue32 } }" <br />
     * でも、同じプログラムでMapに変換できるようにするべし。
     */
    public void test_parseMap_deep() throws Exception {
        /*final String aaa = "map:{";
        final String bbb = "}";

        Map<String, String> testMap = new HashMap<String, String>();
        Map<String, Map<String, String>> map = new HashMap<String, Map<String,String>>();
        String mapString = "map:{ key1 = value1 ; key2 = map:{ nkey21 = nvalue21 ; nkey22 = nvalue22 } ; key3 = value3 }";
        String subMapString = mapString.substring(5,mapString.length()-2);
        String nestMapString = subMapString.substring(subMapString.indexOf(aaa)+5,subMapString.indexOf(bbb));
        String nest1MapString = subMapString.substring(subMapString.indexOf(aaa),subMapString.indexOf(bbb)+1);
        String reMapString = subMapString.replace(nest1MapString,"xxx");

        String[] pairsOut = reMapString.split(";");
        String[] pairsIn = nest1MapString.split(";");

        for (int i = 0; i < pairsOut.length; i++) {
            String pairOut = pairsOut[i];
            String[] keyValue = pairOut.split("=");
            if(keyValue[1] == "xxx"){
                for (int j = 0; j < pairsIn.length; j++) {

                }
            } else {
                testMap.put(keyValue[0], keyValue[1]);
            }
        }
        log(testMap.toString());

        log(nest1MapString);
        log(reMapString);
        //log(reMapString);*/

        //TODO:test1
        final String aaa = "map:{";
        final String bbb = "}";

        Map<String, String> testMap = new HashMap<String, String>();
        Map<String, String> inMap = new HashMap<String, String>();

        Map<String, Map<String, String>> map = new HashMap<String, Map<String,String>>();

        String mapString = "map:{ key1 = value1 ; key2 = map:{ nkey21 = nvalue21 ; nkey22 = nvalue22 } ; key3 = value3 }";
        String subMapString = mapString.substring(5,mapString.length()-2);
        String nestMapString = subMapString.substring(subMapString.indexOf(aaa)+5,subMapString.indexOf(bbb));
        String nest1MapString = subMapString.substring(subMapString.indexOf(aaa),subMapString.indexOf(bbb)+1);
        String reMapString = subMapString.replace(nest1MapString,"xxx");

        String[] pairsOut = reMapString.split(";");
        String[] pairsIn = nestMapString.split(";");

        for (int i = 0; i < pairsOut.length; i++) {
            String pairOut = pairsOut[i];
            String[] keyValue = pairOut.replace(" ","").split("=");
            if(keyValue[1].equals("xxx")){
                for (int j = 0; j < pairsIn.length; j++) {
                    String pairIn = pairsIn[j];
                    String[] keyValueIn = pairIn.replace(" ","").split("=");
                    inMap.put(keyValueIn[0], keyValueIn[1]);
                }
                testMap.put(keyValue[0], inMap.toString());
            } else {
                testMap.put(keyValue[0], keyValue[1]);
            }
        }

        //log(inMap.toString());
        log(testMap.toString());

        //log(nestMapString);
        //log(reMapString);
    }
}
