package com.android.example.cameraxbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.example.cameraxbasic.utils.OffsetTable;
import com.android.example.cameraxbasic.utils.ScoreTable;
import com.bin.david.form.core.SmartTable;
import com.bin.david.form.core.TableConfig;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;


public class HistoricalDetailActivity extends AppCompatActivity {

    private SmartTable tableScore;
    private SmartTable tableOffset;
    private QMUITopBar topbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_detail);
        initview();
    }
    private void initview(){


        topbar = findViewById(R.id.topbar);
        topbar.setTitle("详细评分结果");

        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(HistoricalDetailActivity.this,MainActivity.class);
                intent.putExtra("page",1);
                startActivity(intent);
            }
        });

        List<ScoreTable> listScore = new ArrayList<>();
        tableScore = findViewById(R.id.a_his_table_score);
        listScore.add(new ScoreTable("A3",-1, 0, -1, 8));
        listScore.add(new ScoreTable("A2",0, 0, 0, 10));
        listScore.add(new ScoreTable("A1",0, 0, 0, 10));
        listScore.add(new ScoreTable("B1",0, 0, 0, 10));
        listScore.add(new ScoreTable("B2",0, -1, 0, 9));
        listScore.add(new ScoreTable("B3",-1, -1, -1, 7));
        TableConfig tableConfigScore = tableScore.getConfig();
        tableConfigScore.setMinTableWidth(400);
        tableConfigScore.setShowXSequence(false);
        tableConfigScore.setShowYSequence(false);
        tableConfigScore.setShowTableTitle(false);
        tableScore.setData(listScore);


        List<OffsetTable> listOffset = new ArrayList<>();
        tableOffset = findViewById(R.id.a_his_table_offset);
        listOffset.add(new OffsetTable("A3","偏远中", "——", "近中倾斜"));
        listOffset.add(new OffsetTable("A2","——", "——", "——"));
        listOffset.add(new OffsetTable("A1","——", "——", "——"));
        listOffset.add(new OffsetTable("B1","——", "——", "——"));
        listOffset.add(new OffsetTable("B2","——", "偏根方", "——"));
        listOffset.add(new OffsetTable("B3","偏远中", "偏切方", "近中倾斜"));
        TableConfig tableConfigOffset = tableOffset.getConfig();
        tableConfigOffset.setMinTableWidth(400);
        tableConfigOffset.setShowXSequence(false);
        tableConfigOffset.setShowYSequence(false);
        tableConfigOffset.setShowTableTitle(false);
        tableOffset.setData(listOffset);
    }
}