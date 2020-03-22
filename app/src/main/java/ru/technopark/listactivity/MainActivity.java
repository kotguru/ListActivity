package ru.technopark.listactivity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
        implements ListFragment.OnListFragmentInteractionListener{

    public static final String SAVED_STATE = "state";

    private ListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MyTAG", "MAIN:onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null) {
            listFragment = new ListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, listFragment)
                    .commit();
        } else {
            listFragment = (ListFragment) getSupportFragmentManager().getFragment(savedInstanceState, SAVED_STATE);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager()
                .putFragment(outState, SAVED_STATE, listFragment);
    }

    @Override
    public void onListFragmentInteraction(DataSource.MyData item) {
        Log.d("MyTAG", "MAIN:onListFragmentInteraction");
            ItemFragment fragment = ItemFragment.newInstance(item.mNumber, item.mColor);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
//                    .remove(listFragment)
//                    .add(R.id.container, fragment)
                    .addToBackStack(null)
                    .commit();
    }
}
