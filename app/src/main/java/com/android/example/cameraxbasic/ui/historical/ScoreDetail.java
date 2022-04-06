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

public class ScoreDetail extends ConstraintLayout {
    private Context mContext;
    private TextView score1;
    private TextView score2;
    private TextView score3;
    private TextView score4;
    private TextView score5;
    private TextView score6;
    public ScoreDetail(@NonNull Context context) {
        super(context);
    }

    public ScoreDetail(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }
    private void initView(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.score_detail,null);
        addView(view);
    }
}
