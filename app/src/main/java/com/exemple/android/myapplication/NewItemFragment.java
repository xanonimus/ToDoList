package com.exemple.android.myapplication;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class NewItemFragment extends Fragment{
    private OnNewItemAddedListener onNewItemAddedListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {onNewItemAddedListener = (OnNewItemAddedListener)activity;
        } catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString() + "mast to implement OnNewItemAddedLisener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_item_fragment, container, false);
        final EditText editText = (EditText) view.findViewById(R.id.txtEdit);
        editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER) || (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        String newItem = editText.getText().toString();
                        onNewItemAddedListener.onNewItemAdded(newItem);
                        editText.setText("");
                        return true;
                    }
                return false;
            }
        });
        return view;
    }

    public interface OnNewItemAddedListener {
         void onNewItemAdded (String newItem);
    }

}
