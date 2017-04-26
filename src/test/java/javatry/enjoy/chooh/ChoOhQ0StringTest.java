package javatry.enjoy.chooh;

import javatry.colorbox.ColorBox;
import javatry.colorbox.color.BoxColor;
import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;

import java.time.LocalDateTime;
import java.util.*;
//import net.sf.json.JSONObject;


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
     * カラーボックスで文字列の値を取り出し、リストで返すメソッド
     */
    private ArrayList<String> getColorBoxStrContentList(){
        ArrayList<String> colorBoxStrContentList = new ArrayList<String>();

        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof String) {
                    String strContents = contents.toString();
                    colorBoxStrContentList.add(strContents);
                }
            }
        }
        return colorBoxStrContentList;
    }

    /**
     * カラーボックスに入ってる文字列の中で、一番長い文字列は？
     */
    public void test_length_findMax() {
        ArrayList<String> colorBoxStrContentList = getColorBoxStrContentList();
        colorBoxStrContentList.sort(Comparator.comparing(String::length).reversed());
        log("一番長い文字列は " + colorBoxStrContentList.get(0));
    }

    /**
     * カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？
     */
    public void test_length_findMaxMinDiff() {
        ArrayList<String> colorBoxStrContentList = getColorBoxStrContentList();
        colorBoxStrContentList.sort(Comparator.comparing(String::length).reversed());
        log("一番長いものと短いものの差は " +
                (colorBoxStrContentList.get(0).length() - colorBoxStrContentList.get(colorBoxStrContentList.size()-1).length()) + " 文字");
    }

    /**
     * カラーボックスに入ってる値 (文字列以外のものはtoString()) の中で、二番目に長い文字列は？ <br>
     * ソートして二番目を取得する、ってやり方で。
     */
    public void test_length_findSecondMax_bySort() {
        ArrayList<String> colorBoxStrContentList = new ArrayList<String>();
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents != null) {
                    String strContents = contents.toString();
                    colorBoxStrContentList.add(strContents);
                }
            }
        }

        colorBoxStrContentList.sort(Comparator.comparing(String::length).reversed());
        log("二番目に長い文字列は " + colorBoxStrContentList.get(1));
    }

    /**
     * カラーボックスに入ってる値 (文字列以外のものはtoString()) の中で、二番目に長い文字列は？ <br>
     * ただし、ソートして二番目を取得する、ってやり方は利用しないこと。
     */
    public void test_length_findSecondMax_nonSorted() {
        String secMaxLenStr = "";
        String maxLenStr = "";

        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents != null) {
                    String strContents = contents.toString();
                    if (strContents.length() > maxLenStr.length()) {
                        secMaxLenStr = maxLenStr;
                        maxLenStr = strContents;
                    } else {
                        if (strContents.length() > secMaxLenStr.length()) {
                            secMaxLenStr = strContents;
                        }
                    }
                }
            }
        }
        log("二番目に長い文字列は " + secMaxLenStr);
    }

    /**
     * カラーボックスに入ってる文字列の長さの合計は？
     */
    public void test_length_calculateLengthSum() {
        ArrayList<String> colorBoxStrContentList = getColorBoxStrContentList();
        int listSum = colorBoxStrContentList.stream().mapToInt(w -> w.length()).sum();
        log("文字列の長さの合計は " + listSum);
    }

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * 「かまくら」で始まる文字列をしまっているカラーボックスの色は？
     */
    public void test_startsWith_findFirstWord() {
        ArrayList<String> colorNameList = new ArrayList<String>();
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof String) {
                    String strContents = contents.toString();
                    if (strContents.startsWith("かまくら")){
                        colorNameList.add(colorBox.getColor().toString());
                    }
                }
            }
        }

        Iterator<String> i = colorNameList.iterator();
        if (colorNameList.isEmpty()) {
            log("「かまくら」で始まる文字列はありません");
        } else {
            while(i.hasNext()) {
                log("「かまくら」で始まる文字列をしまっているカラーボックスの色は " + i.next());
            }
        }
    }

    /**
     * 「いぬ」で終わる文字列をしまっているカラーボックスの色は？
     */
    public void test_endsWith_findLastWord() {
        ArrayList<String> colorNameList = new ArrayList<String>();
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof String) {
                    String strContents = contents.toString();
                    if (strContents.endsWith("いぬ")){
                        colorNameList.add(colorBox.getColor().toString());
                    }
                }
            }
        }
        Iterator<String> i = colorNameList.iterator();
        if (colorNameList.isEmpty()) {
            log("「いぬ」で終わる文字列はありません");
        } else {
            while(i.hasNext()) {
                log("「いぬ」で終わる文字列をしまっているカラーボックスの色は " + i.next());
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
        ArrayList<Integer> strPositionList = new ArrayList<Integer>();
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof String) {
                    String strContents = contents.toString();
                    if (strContents.endsWith("いぬ")){
                        strPositionList.add(strContents.indexOf("いぬ"));
                    }
                }
            }
        }
        Iterator<Integer> i = strPositionList.iterator();
        if (strPositionList.isEmpty()) {
            log("「いぬ」で終わる文字列はありません");
        } else {
            while(i.hasNext()) {
                log("「いぬ」で終わる文字列で、「いぬ」は " + i.next() + " 文字目から始まる");
            }
        }
    }

    /**
     * あなたのカラーボックスに入ってる「ず」を二つ以上含む文字列で、最後の「ず」は何文字目から始まる？
     */
    public void test_lastIndexOf_findIndex() {
        ArrayList<Integer> strPositionList = new ArrayList<Integer>();
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof String) {
                    String strContents = contents.toString();
                    if (strContents.indexOf("ず") != strContents.lastIndexOf("ず")){
                        strPositionList.add(strContents.lastIndexOf("ず"));
                    }
                }
            }
        }
        Iterator<Integer> i = strPositionList.iterator();
        if (strPositionList.isEmpty()) {
            log("「ず」で含む文字列はありませんまた「ず」は一つだけ");
        } else {
            while(i.hasNext()) {
                log("最後の「ず」は " + i.next() + " 文字目から始まる");
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
        ArrayList<String> strList = new ArrayList<String>();
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof String) {
                    String strContent = (String) contents;
                    if (strContent.endsWith("いぬ")){
                        strList.add(strContent.substring(0, 1));
                    }
                }
            }
        }
        Iterator<String> i = strList.iterator();
        if (strList.isEmpty()) {
            log("「いぬ」で終わる文字列はありません");
        } else {
            while(i.hasNext()) {
                log("「いぬ」で終わる文字列の最初の一文字は " + i.next());
            }
        }
    }

    /**
     * カラーボックスに入ってる「かまくら」で始まる文字列の最後の一文字は？
     */
    public void test_substring_findLastChar() {
        ArrayList<String> strList = new ArrayList<String>();
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof String) {
                    String strContent = (String) contents;
                    if (strContent.startsWith("かまくら")){
                        strList.add(strContent.substring(strContent.length() - 1, strContent.length()));
                    }
                }
            }
        }
        Iterator<String> i = strList.iterator();
        if (strList.isEmpty()) {
            log("「かまくら」で始まる文字列はありません");
        } else {
            while(i.hasNext()) {
                log("「かまくら」で始まる文字列の最後の一文字は " + i.next());
            }
        }
    }

    // ===================================================================================
    //                                                                           replace()
    //                                                                           =========
    /**
     * カラーボックスに入ってる「ー」を含んだ文字列から「ー」を全て除去したら何文字？
     */
    public void test_replace_removeBo() {
        ArrayList<Integer> strList = new ArrayList<Integer>();
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof String) {
                    String strContent = (String) contents;
                    if (strContent.contains("ー")){
                        strList.add(strContent.replace("ー","").length());
                    }
                }
            }
        }
        Iterator<Integer> i = strList.iterator();
        if (strList.isEmpty()) {
            log("「ー」を含んだ文字列はありません");
        } else {
            while(i.hasNext()) {
                log("「ー」を含んだ文字列から「ー」を全て除去したら " + i.next() + " 文字");
            }
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * カラーボックスの中で、色の名前が一番長いものは？
     */
    public void test_findMaxColorSize() throws Exception {
        ArrayList<String> colorNameList = new ArrayList<String>();
        for (ColorBox colorBox : getColorBoxList()) {
            BoxColor color = colorBox.getColor();
            colorNameList.add(color.toString());
        }
        colorNameList.sort(Comparator.comparing(String::length).reversed());
        log("色の名前が一番長いものは " + colorNameList.get(0));
    }

    /**
     * カラーボックスの中で、2012/06/04 を示す日付が持っている秒は？
     */
    public void test_findDBFluteBirthdateSecond() throws Exception {
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof LocalDateTime){
                    if (toLocalDate(contents).isEqual(toLocalDate("2012/06/04"))){
                        log("2012/06/04 を示す日付が持っている秒は " + toLocalDateTime(contents).getSecond());
                    }
                }
            }
        }
    }

    /**
     * カラーボックスの中に入っている Map を "map:{ key = value ; key = value ; ... }" という形式で表示。
     */
    public void test_showMap() throws Exception {
        String mapPrefix = "map:{ ";
        String mapSuffix = "}";
        StringBuilder mapToString = new StringBuilder();
        mapToString.append(mapPrefix);

        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof Map){
                    Map<Object, Object> map = (Map)contents;
                    for (Map.Entry entry : map.entrySet()){
                        mapToString.append(entry.getKey());
                        mapToString.append(" = ");
                        mapToString.append(entry.getValue());
                        mapToString.append(" ; ");
                    }
                }
            }
        }
        mapToString.delete(mapToString.lastIndexOf(";"), mapToString.length());
        mapToString.append(mapSuffix);
        log(mapToString);
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
        String mapPrefix = "map:";
        String mapString = "map:{ key1 = value1 ; key2 = value2 ; key3 = value3 }";
        Map<String, String> Map = new HashMap<>();

        String subMapString = mapString.substring(mapPrefix.length() + 1, mapString.length() - 1);
        String[] pairs = subMapString.split(";");
        for (int i = 0; i < pairs.length; i++) {
            String pair = pairs[i];
            String[] keyValue = pair.split("=");
            Map.put(keyValue[0], keyValue[1]);
        }
        log(mapPrefix + Map.toString());
    }

    /**
     * "map:{ key1 = value1 ; key2 = map:{ nkey21 = nvalue21 ; nkey22 = nvalue22 } ; key3 = value3 }" <br />
     * という文字列をMapに変換してtoString()すると？ <br />
     * <br />
     * "map:{ key1 = value1 ; key2 = value2 ; key3 = map:{ nkey31 = nvalue31 ; nkey32 = nvalue32 } }" <br />
     * でも、同じプログラムでMapに変換できるようにするべし。
     */

    public static Map<String, Object> toMap(String mapContent) {
        final String mapPrefix = "map:{";
        Map<String, Object> map = new HashMap<String, Object>();

        //只取本层的keys
        //等号前面
        int count;

        //String[] keyOut = object.split(";");
        //把key都取出来
        //value用substring

        for (int i = 0; i < mapContent.length(); i++) {

        }

        Iterator<String> keysItr = mapContent.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            //get 应该是substring
            //value 或者是value或者是map:{}
            Object value = mapContent.get(key);

            //此处是判断value前后是否有map:{ }
            if(value.toString().contains(mapPrefix)) {
                value = toMap(value);
            }
            map.put(key,value);
        }
        return map;

    }
    public void test_parseMap_deep() throws Exception {
        Map<String, Object> retMap = new HashMap<String, Object>();
        String mapString = "map:{ key1 = value1 ; key2 = map:{ nkey21 = nvalue21 ; nkey22 = nvalue22 } ; key3 = value3 }";
        String mapString2 = "map:{ key1 = value1 ; key2 = map:{ nkey1 = map:{ nnkey1 = map:{ nnnkey1 = map:{ nnnnkey1 = nnnnvalue1 } } } ; nkey2 = nvalue2 } ; key3 = value3 }";
        if(mapString != null) {
            retMap = toMap(mapString);
        }
        log(retMap.toString());
        //TODO:test1







        final String aaa = "map:{";
        final String bbb = "}";
        Map<String, String> testMap = new HashMap<String, String>();
        Map<String, String> inMap = new HashMap<String, String>();


        //String mapString = "map:{ key1 = value1 ; key2 = map:{ nkey21 = nvalue21 ; nkey22 = nvalue22 } ; key3 = value3 }";
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

        String maplString = "map:{ key1 = value1 ; key2 = map:{ nkey21 = nvalue21 ; nkey22 = nvalue22 } ; key3 = value3 }";

        log(testMap.toString());
    }
}
