package com.example.niva.sqlitetimeentries;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    private AppEntryTimeDAL dal;
    ListView right, left;
    private ArrayAdapter arrayAdapter;
    private SimpleCursorAdapter cursorAdapter;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dal = new AppEntryTimeDAL(this);
        left = (ListView)findViewById(R.id.listView);
        right = (ListView)findViewById(R.id.listView2);
        //add time entry
        String time = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        dal.addEntryTime(time);

        //array adapter
        final ArrayList<String> list = dal.getAllEntryTimesList();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        right.setAdapter(arrayAdapter);

        right.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String time = list.get(position);
                Toast.makeText(view.getContext(), time, Toast.LENGTH_SHORT).show();
            }
        });


        //Cursor adapter
        String[] columns = new String[] {
                AppEntryTimeContract.AppEntryTime.ENTRY_TIME
        };

        int[] viewsID = new int[] {
                android.R.id.text1
        };

        cursor = dal.getAllEntryTimesCursor();
        cursorAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                cursor,
                columns,
                viewsID,
                0);

        left.setAdapter(cursorAdapter);


        left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (cursor.moveToPosition(position)) {
                    int timeIndex = cursor.getColumnIndex(AppEntryTimeContract.AppEntryTime.ENTRY_TIME);

                    String time = cursor.getString(timeIndex);
                    Toast.makeText(view.getContext(), time, Toast.LENGTH_SHORT).show();

                }
            }
        });

    }




}
