package com.android.example.cameraxbasic.utils;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

@SmartTable(name = "详细得分")
public class ScoreTable {
    public ScoreTable(String yawei, int jyz, int chuizhi, int zqj, int score) {
        this.yawei = yawei;
        this.jyz = jyz;
        this.chuizhi = chuizhi;
        this.zqj = zqj;
        this.score = score;
    }

    //    name：版块名称，count：目标值，restaurant：餐饮数量，ka：KA数量，wholesale：流通批发数量，industry：工业加工数量，other：其它数量
//    @SmartColumn(id = 0, name = "牙位", autoMerge = true)
    @SmartColumn(id = 0, name = "牙位")
    private String yawei;
    @SmartColumn(id = 1, name = "近远中")
    private int jyz;
    @SmartColumn(id = 2, name = "垂直")
    private int chuizhi;
    @SmartColumn(id = 3, name = "轴倾角")
    private int zqj;
    @SmartColumn(id = 4, name = "得分")
    private int score;
}