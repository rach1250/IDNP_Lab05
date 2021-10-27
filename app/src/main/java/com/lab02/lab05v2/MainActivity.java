package com.lab02.lab05v2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BarChartView barChartView = (BarChartView) findViewById(R.id.barChart);
        ArrayList arrayList = new ArrayList<>();
        BarData barData = new BarData("Arequipa", 0.21f);
        arrayList.add(barData);
        barData = new BarData("Moquegua", 0.08f);
        arrayList.add(barData);
        barData = new BarData("Tacna", 0.29f);
        arrayList.add(barData);
        barData = new BarData("Cusco", 0.61f);
        arrayList.add(barData);

        BarData[] arrayBarData = (BarData[]) arrayList.toArray(new BarData[arrayList.size()]);
        barChartView.setYAxisData(arrayBarData);
    }
}