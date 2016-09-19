package com.xjtu.yuzhuo.recyclerview;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzhuo on 16/9/11.
 */
public class SpannableTextView extends TextView {
    private Context context;

    public SpannableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void setSpecifiedTextsColor(String text, String specifiedTexts,
                                       int color,int textSize) {
        //color is the text need change the color
        List<Integer> sTextsStartList = new ArrayList<Integer>();
        specifiedTexts = specifiedTexts.toLowerCase();
        int sTextLength = specifiedTexts.length();
        String temp = text.toLowerCase();
        int lengthFront = 0;// 记录被找出后前面的字段的长度
        int start = -1;
        do {
            start = temp.indexOf(specifiedTexts);

            if (start != -1) {
                start = start + lengthFront;
                sTextsStartList.add(start);
                lengthFront = start + sTextLength;
                temp = text.substring(lengthFront);
            }
        } while (start != -1);

        SpannableStringBuilder styledText = new SpannableStringBuilder(text);
        for (Integer i : sTextsStartList) {
            styledText.setSpan(new ForegroundColorSpan(color), i, i
                    + sTextLength, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            if(textSize>0) {
                styledText.setSpan(new AbsoluteSizeSpan(sp2px(context, textSize)), i, i
                        + sTextLength, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        setText(styledText);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param context
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}
