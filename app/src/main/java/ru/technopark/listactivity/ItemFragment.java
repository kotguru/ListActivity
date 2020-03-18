package ru.technopark.listactivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


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
        View view = inflater.inflate(R.layout.item_fragment, container, false);
        TextView textView = view.findViewById(R.id.num_text);

        textView.setText(String.valueOf(text));
        textView.setTextColor(color);

        return view;
    }
}
