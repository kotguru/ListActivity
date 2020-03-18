package ru.technopark.listactivity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
        implements ListFragment.OnListFragmentInteractionListener{

    public static final String MY_EXTRA = "MyExtra";
    public static final String SAVED_STATE = "state";;

    private ListFragment Lfragment;
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MyTAG", "MAIN:onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.savedInstanceState = savedInstanceState;
        if(this.savedInstanceState == null) {
            Lfragment = new ListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, Lfragment).commit();
        } else {
            Lfragment = (ListFragment) getSupportFragmentManager().getFragment(savedInstanceState, SAVED_STATE);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager()
                .putFragment(outState, SAVED_STATE, Lfragment);
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
