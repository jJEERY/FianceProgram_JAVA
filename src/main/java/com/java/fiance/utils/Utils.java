package com.java.fiance.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.List;

/**
 * @author wuxincheng
 */
public class Utils {
    /**
     * 求出两个列表的交集
     * @param list1 运算完之后list1中仅包含交集
     * @param list2
     * @return
     */
    public static boolean retainList(List list1, List list2) {
        return list1.retainAll(list2);
    }

    /**
     * 求两个列表的差集
     * @param list1 运算完之后list1中不包含list2的内容
     * @param list2
     * @return
     */
    public static boolean removeList(List list1, List list2) {
        return list1.removeAll(list2);
    }

    /**
     * 两个列表的无重复并集
     * @param list1
     * @param list2
     * @return
     */
    public static boolean addAllList(List list1, List list2) {
//        return list2.removeAll(list1) && list1.addAll(list2) ? true : false;
        return list1.addAll(list2);
    }

    /**
     * 获得指定字符串的拼音首字母
     * @param chinese
     * @return
     */
    public static String getSpellUpper(String chinese) {
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i ++) {
            if (' ' == newChar[i]) {
                continue;
            } else if (i > 0 && newChar[i - 1] == '银' && newChar[i] == '行') {
                pinyinStr += 'H';
            } else if ('长' == newChar[i]) {
                pinyinStr += 'C';
            } else if ('Ａ' == newChar[i]) {
                pinyinStr += 'A';
            } else if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], format)[0].charAt(0);
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
            } else {
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr.toUpperCase();
    }


}
