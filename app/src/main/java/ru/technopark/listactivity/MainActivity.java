package ru.technopark.listactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String MY_EXTRA = "MyExtra";
    private MyDataAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.list);

        mAdapter = new MyDataAdapter(DataSource.getInstance().getData());
        recyclerView.setAdapter(mAdapter);
        int spanCount = getResources().getBoolean(R.bool.is_horizontal) ? 4 : 3;
        recyclerView.setLayoutManager(new GridLayoutManager(this, spanCount));

        Button button = findViewById(R.id.add_button);
        button.setOnClickListener(v -> {
            DataSource.addData(mAdapter.mData);
            recyclerView.getAdapter().notifyDataSetChanged();
        });
    }

    class MyDataAdapter extends RecyclerView.Adapter<MyViewHolder>{

        List<DataSource.MyData> mData;

        public MyDataAdapter(List<DataSource.MyData> mData) {
            this.mData = mData;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return  new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            DataSource.MyData myData = mData.get(position);

            holder.textView.setText(Integer.toString(myData.mNumber));
            holder.textView.setTextColor(myData.mColor);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.number);
            textView.setOnClickListener(view -> {
                int adapterPosition = getAdapterPosition();
                DataSource.MyData myData = mAdapter.mData.get(adapterPosition);
                Toast.makeText(getApplicationContext(),
                        "clicked" + ' ' + myData.mNumber,
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, second_activity.class);
                intent.putExtra( MY_EXTRA, Integer.parseInt(String.valueOf(myData.mNumber)));
                startActivity(intent);
            });
        }
    }
}
