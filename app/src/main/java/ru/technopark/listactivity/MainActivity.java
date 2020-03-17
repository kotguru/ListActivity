package ru.technopark.listactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements ListFragment.OnListFragmentInteractionListener{

    public static final String MY_EXTRA = "MyExtra";
    private ListFragment Lfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MyTAG", "MAIN:onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Lfragment = new ListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, Lfragment).commit();
//        setContentView(R.layout.activity_main);
//
//        RecyclerView recyclerView = findViewById(R.id.list);
//
//        mAdapter = new MyDataAdapter(DataSource.getInstance().getData());
//        recyclerView.setAdapter(mAdapter);
//        int spanCount = getResources().getBoolean(R.bool.is_horizontal) ? 4 : 3;
//        recyclerView.setLayoutManager(new GridLayoutManager(this, spanCount));
//
//        Button button = findViewById(R.id.add_button);
//        button.setOnClickListener(v -> {
//            DataSource.addData(mAdapter.mData);
//            recyclerView.getAdapter().notifyDataSetChanged();
//        });
    }

    @Override
    public void onListFragmentInteraction(DataSource.MyData item) {
        Log.d("MyTAG", "MAIN:onListFragmentInteraction");
        ItemFragment fragment = ItemFragment.newInstance(item.mNumber, item.mColor);
        getSupportFragmentManager()
                .beginTransaction()
                .remove(Lfragment)
                .add(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
