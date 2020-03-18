package ru.technopark.listactivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListFragment extends Fragment {

    private MyDataAdapter mAdapter;
    private OnListFragmentInteractionListener mListener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("MyTAG", "LIST_FRAGM:onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("MyTAG", "LIST_FRAGM:onCreateView");

        View view = inflater.inflate(R.layout.list_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.list);

        mAdapter = new MyDataAdapter(DataSource.getInstance().getData(), mListener);
        recyclerView.setAdapter(mAdapter);
        int spanCount = getResources().getBoolean(R.bool.is_horizontal) ? 4 : 3;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));

        Button button = view.findViewById(R.id.add_button);
        button.setOnClickListener(v -> {
            DataSource.addData(mAdapter.mData);
            recyclerView.getAdapter().notifyDataSetChanged();
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        Log.d("MyTAG", "LIST_FRAGM:onAttach");

        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(DataSource.MyData item);
    }

    class MyDataAdapter extends RecyclerView.Adapter<MyViewHolder>{

        List<DataSource.MyData> mData;
        OnListFragmentInteractionListener mListener;


        public MyDataAdapter(List<DataSource.MyData> mData, OnListFragmentInteractionListener listener) {
            this.mData = mData;
            this.mListener = listener;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.d("MyTAG", "ADAPTER:onCreateViewHolder");

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return  new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Log.d("MyTAG", "ADAPTER:onBindViewHolder");

            DataSource.MyData myData = mData.get(position);

            holder.textView.setText(Integer.toString(myData.mNumber));
            holder.textView.setTextColor(myData.mColor);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        mListener.onListFragmentInteraction(myData);
                    }
                }
            });
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
        }
    }
}
