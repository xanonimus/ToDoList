package com.exemple.android.myapplication;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NewItemFragment.OnNewItemAddedListener{

    ArrayList<String> alist;
    ArrayAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null){
            alist = savedInstanceState.getStringArrayList("a");
        } else {
            alist = new ArrayList<String>();
        }
            aa = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, alist);
        FragmentManager fm = getFragmentManager();
        ToDoListFragment toDoListFragment = (ToDoListFragment) fm.findFragmentById(R.id.todo_list_fragment);
        toDoListFragment.setListAdapter(aa);
    }

    @Override
    public void onNewItemAdded(String newItem) {
        alist.add(newItem);
        aa.notifyDataSetChanged();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("a", alist);
    }
}
