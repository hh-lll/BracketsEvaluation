package com.android.example.cameraxbasic.ui.historical;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.example.cameraxbasic.R;

public class HistoricalItem extends ConstraintLayout {
    private Context mContext;
    private TextView his_type;
    private TextView his_score;
    private TextView his_date;
    private TextView his_time;
    public HistoricalItem(@NonNull Context context) {
        super(context);
    }

    public HistoricalItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }
    private void initView(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.historical_item,null);
        addView(view);
    }
}
