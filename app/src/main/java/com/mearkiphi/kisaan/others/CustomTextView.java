package com.mearkiphi.kisaan.others;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by anuragmaravi on 06/05/17.
 */

public class CustomTextView extends android.support.v7.widget.AppCompatTextView {

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextView(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-Light.ttf");
        setTypeface(tf ,1);

    }
}