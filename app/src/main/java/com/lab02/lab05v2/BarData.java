package com.lab02.lab05v2;

public class BarData {
    private String mXAxisName;
    private float mValue;

    public BarData(String XAxisName, float value) {
        mXAxisName = XAxisName;
        mValue = value;
    }

    public String getXAxisName() {
        return mXAxisName;
    }

    public void setXAxisName(String XAxisName) {
        mXAxisName = XAxisName;
    }

    public float getValue() {
        return mValue;
    }

    public void setValue(float value) {
        mValue = value;
    }
}
