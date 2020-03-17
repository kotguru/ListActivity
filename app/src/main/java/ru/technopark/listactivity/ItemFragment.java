package ru.technopark.listactivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ItemFragment extends Fragment {
    private static final String ARG_TEXT = "text_value";
    private static final String ARG_COLOR = "color_value";

    private int text;
    private int color;

    public ItemFragment() {
        Log.d("MyTAG", "ITEM_FRAGM:emptyConstructor");
    }

    static ItemFragment newInstance(int text, int color) {
        Log.d("MyTAG", "ITEM_FRAGM:newInstance");
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TEXT, text);
        args.putInt(ARG_COLOR, color);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            text = getArguments().getInt(ARG_TEXT);
            color = getArguments().getInt(ARG_COLOR);
        }
        Log.d("MyTAG", "ITEM_FRAGM:onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("MyTAG", "ITEM_FRAGM:onCreateView");
//        View v = inflater.inflate(R.layout.item_fragment, container, false);
//        TextView textView = v.findViewById(R.id.frgmText);
//        textView.setText(text);
//        textView.setTextColor(ContextCompat.getColor(inflater.getContext(), color));
        View view = inflater.inflate(R.layout.item_fragment, container, false);
        Log.d("MyTAG", "ITEM_FRAGM:onCreateView___11111111111");
        //RecyclerView recyclerView = view.findViewById(R.id.list);
        TextView textView = view.findViewById(R.id.num_text);
        Log.d("MyTAG", "ITEM_FRAGM:onCreateView___22222222222");

        //  int a = text;
        textView.setText(Integer.toString(text));
        textView.setTextColor(color);
     //   Log.d("MyTAG", "ITEM_FRAGM:onCreateView___333333333333");

        //        mAdapter = new ListFragment.MyDataAdapter(DataSource.getInstance().getData(), mListener);
//        recyclerView.setAdapter(mAdapter);
//        int spanCount = getResources().getBoolean(R.bool.is_horizontal) ? 4 : 3;
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));


        return view;
    }
}
