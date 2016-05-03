package com.example.niva.sqlitetimeentries;

import android.provider.BaseColumns;

/**
 * Created by Niva on 03/05/2016.
 */
public class AppEntryTimeContract {

    public AppEntryTimeContract() {};

    public static abstract class AppEntryTime implements BaseColumns {


        public static final String TABLE_NAME = "entryTimes";
        public static final String ENTRY_TIME = "entryTimeColumn";
    }


}