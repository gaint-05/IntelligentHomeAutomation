package com.rajhack4.homeautomation.database;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;


public class UserContract {

    public static final String CONTENT_AUTHORITY = "com.rajhack4.homeautomation.database";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_USER = "user";

    public static final class UserEntry implements BaseColumns{
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_USER);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;

        public static final String TABLE_NAME = "user_history";
        public static final String _ID = BaseColumns._ID;
        public static final String CHANNEL_NAME = "channel_name";
        public static final String TIME = "time";
        public static final String CHANNEL_IMPRESSION = "channel_impression";
    }


}
