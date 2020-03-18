package ru.technopark.listactivity;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

class DataSource {

    private List<MyData> mData;

    private static DataSource sInstance;

    private DataSource(){
        mData = new ArrayList<>();

        for(int i = 1; i <= 100; i++) {
            mData.add(new MyData(i));
        }
    }

    synchronized static DataSource getInstance(){
        if (sInstance == null){
            sInstance = new DataSource();
        }
        return sInstance;
    }

    List<MyData> getData() {
        return  mData;
    }

    static void addData(List<MyData> data){ data.add(new MyData(data.size() + 1)); }


    static class  MyData{
        int mNumber;
        int mColor;

        MyData(int mNumber) {
            this.mNumber= mNumber;
            mColor = (mNumber % 2) == 1 ? Color.BLUE : Color.RED;
        }
    }
}
