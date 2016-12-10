package com.example.karol.reminder;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Karol on 12.11.2016.
 */

public class OnItemClickDialog extends Dialog implements
        android.view.View.OnClickListener {

    public Activity activity;
    public Dialog d;
    public Button editButton, deleteButton;
    public int id;

    //Dialog while LongClick on listView object. To do asap!

    public OnItemClickDialog(Context context, int itemId) {
        super(context);
        // TODO Auto-generated constructor stub
        this.id = itemId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.listview_item_options);
        editButton = (Button) findViewById(R.id.editItemButton);
        deleteButton = (Button) findViewById(R.id.deleteItemButton);
        editButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.editItemButton:
                Log.d("Item dialog", "Editing item");
                dismiss();
                break;
            case R.id.deleteItemButton:
                Log.d("Item dialog", "Deleting item");
                deleteData();
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }

    private void deleteData() {
        String idString = String.valueOf(id);
        BackgroundTask backgroundTask = new BackgroundTask(getContext());
        backgroundTask.execute("delete_info", idString);
        Log.d("Deleting item", "Deleting " + idString + ". item");
    }
}