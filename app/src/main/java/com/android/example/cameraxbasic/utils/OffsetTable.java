package com.android.example.cameraxbasic.utils;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

@SmartTable(name = "详细得分")
public class OffsetTable {
    public OffsetTable(String yawei, String jyz, String chuizhi, String zqj) {
        this.yawei = yawei;
        this.jyz = jyz;
        this.chuizhi = chuizhi;
        this.zqj = zqj;
    }

    //    name：版块名称，count：目标值，restaurant：餐饮数量，ka：KA数量，wholesale：流通批发数量，industry：工业加工数量，other：其它数量
//    @SmartColumn(id = 0, name = "牙位", autoMerge = true)
    @SmartColumn(id = 0, name = "牙位")
    private String yawei;
    @SmartColumn(id = 1, name = "近远中")
    private String jyz;
    @SmartColumn(id = 2, name = "垂直")
    private String chuizhi;
    @SmartColumn(id = 3, name = "轴倾角")
    private String zqj;
}