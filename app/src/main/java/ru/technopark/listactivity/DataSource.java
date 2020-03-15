package ru.technopark.listactivity;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private List<MyData> mData;

    private static DataSource sInstance;

    public DataSource(){
        mData = new ArrayList<>();

        for(int i = 1; i <= 100; i++) {
            mData.add(new MyData(i));
        }
    }

    public synchronized static DataSource getInstance(){
        if (sInstance == null){
            sInstance = new DataSource();
        }
        return sInstance;
    }

    public List<MyData> getData() {
        return  mData;
    }

    public static void addData(List<MyData> data){ data.add(new MyData(data.size() + 1)); }


    public static class  MyData{
        int mNumber;
        int mColor;

        public MyData(int mNumber) {
            this.mNumber= mNumber;
            mColor = (mNumber % 2) == 1 ? Color.BLUE : Color.RED;
        }
    }
}
