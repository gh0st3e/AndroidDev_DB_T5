package denisleonov.fit.bstu.by.db_lab_11_15.Provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import denisleonov.fit.bstu.by.db_lab_11_15.DBHelper.Constants;
import denisleonov.fit.bstu.by.db_lab_11_15.DBHelper.DBHelper;

public class StudentProvider extends ContentProvider {

    private DBHelper db;

    static final String CONTENT_AUTHORITY = "denisleonov.fit.bstu.by.provider";
    static final String GROUPS_LIST_PATH = "groupslist";
    static final String STUDENT_LIST_PATH = "studentlist";
    static final Uri CONTENT_AUTHORITY_URI = Uri.parse("content://"+CONTENT_AUTHORITY+"/"+GROUPS_LIST_PATH);

    static final int URI_GROUPS = 1;
    static final int URI_GROUP_ID = 2;
    static final int URI_STUDENTS = 3;
    static final int URI_STUDENT_ID = 4;

    public int choose;

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(CONTENT_AUTHORITY,GROUPS_LIST_PATH,URI_GROUPS);
        uriMatcher.addURI(CONTENT_AUTHORITY,GROUPS_LIST_PATH + "/#",URI_GROUP_ID);
        uriMatcher.addURI(CONTENT_AUTHORITY,STUDENT_LIST_PATH,URI_STUDENTS);
        uriMatcher.addURI(CONTENT_AUTHORITY,STUDENT_LIST_PATH + "/#",URI_STUDENT_ID);
    }

    @Override
    public boolean onCreate() {
        db = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (uriMatcher.match(uri)){
            case URI_GROUPS:
                Log.d("Lab11-15","StudentProvider.URI_GROUPS");

                if(TextUtils.isEmpty(sortOrder)){
                    sortOrder = Constants.NAME_COL + " ASC";
                }
                choose = 1;
                break;
            case URI_GROUP_ID:
                String id = uri.getLastPathSegment();
                Log.d("Lab11-15","StudentProvider.URI_GROUPS_ID");

                if(TextUtils.isEmpty(selection)){
                    selection = Constants.ID_GROUP_COL + " = " + id;
                } else{
                    selection = selection + " AND " + Constants.ID_GROUP_COL + " = " + id;
                }
                choose = 1;
                break;
            case URI_STUDENTS:
                Log.d("Lab11-15","StudentProvider.URI_STUDENTS");
                if(TextUtils.isEmpty(sortOrder)){
                    sortOrder = Constants.NAME_COL + " ASC";
                }
                choose = 2;
                break;
            case URI_STUDENT_ID:
                String uid = uri.getLastPathSegment();
                Log.d("Lab11-15","StudentProvider.URI_GROUPS_ID");

                if(TextUtils.isEmpty(selection)){
                    selection = Constants.ID_STUDENT_COL + " = " + uid;
                } else{
                    selection = selection + " AND " + Constants.ID_STUDENT_COL + " = " + uid;
                }
                choose = 2;
                break;
            default:
                throw new IllegalArgumentException("Wrong URI " + uri);
        }

        Cursor cursor = null;
        if(choose==1){
            cursor = db.getWritableDatabase().query(Constants.TABLE_NAME_GROUP,projection,selection,selectionArgs,null,null, sortOrder);
        }else{
            cursor = db.getWritableDatabase().query(Constants.TABLE_NAME_STUDENT,projection,selection,selectionArgs,null,null, sortOrder);
        }

        cursor.setNotificationUri(getContext().getContentResolver(),CONTENT_AUTHORITY_URI);

        Log.d("Lab11-15","query completed, " + uri.toString());

        return cursor;

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        Uri result = null;

        if(uriMatcher.match(uri) == URI_GROUPS){
            long rowID = db.getWritableDatabase().insert(Constants.TABLE_NAME_GROUP,null,values);

            result = ContentUris.withAppendedId(CONTENT_AUTHORITY_URI,rowID);
        }
        else if(uriMatcher.match(uri) == URI_STUDENTS){
            long rowID = db.getWritableDatabase().insert(Constants.TABLE_NAME_STUDENT,null,values);

            result = ContentUris.withAppendedId(CONTENT_AUTHORITY_URI,rowID);
        }
        else{
            throw new IllegalArgumentException("Wrong URI " + uri);
        }


        getContext().getContentResolver().notifyChange(result,null);

        Log.d("Lab11-15","insert completed" + uri.toString());
        return result;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (uriMatcher.match(uri)){
            case URI_GROUPS:
                break;
            case URI_GROUP_ID:
                String id = uri.getLastPathSegment();
                Log.d("Lab11-15","URI_GROUP_ID = " + id );
                if(TextUtils.isEmpty(selection)){
                    selection = Constants.ID_GROUP_COL + " = " + id;
                } else{
                    selection = selection + " AND " + Constants.ID_GROUP_COL + " = " + id;
                }
                choose = 1;
                break;
            case URI_STUDENTS:
                break;
            case URI_STUDENT_ID:
                String uid = uri.getLastPathSegment();
                Log.d("Lab11-15","URI_STUDENT_ID = " + uid );
                if(TextUtils.isEmpty(selection)){
                    selection = Constants.ID_STUDENT_COL + " = " + uid;
                } else{
                    selection = selection + " AND " + Constants.ID_GROUP_COL + " = " + uid;
                }
                choose = 2;
                break;
            default:
                throw new IllegalArgumentException("Wrong URI " + uri);
        }

        int rowCount =0;

        if(choose == 1){
            rowCount = db.getWritableDatabase().delete(Constants.TABLE_NAME_GROUP,selection,selectionArgs);
        }
        else{
            rowCount = db.getWritableDatabase().delete(Constants.TABLE_NAME_STUDENT,selection,selectionArgs);
        }

        getContext().getContentResolver().notifyChange(uri,null);

        Log.d("Lab11-15","delete completed, " + uri.toString());
        return rowCount;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (uriMatcher.match(uri)){
            case URI_GROUPS:
                break;
            case URI_GROUP_ID:
                String id = uri.getLastPathSegment();
                Log.d("Lab11-15","URI_GROUP_ID" + id);
                if(TextUtils.isEmpty(selection)){
                    selection = Constants.ID_GROUP_COL + " = " + id;
                } else{
                    selection = selection + " AND " + Constants.ID_GROUP_COL + " = " + id;
                }
                choose = 1;
                break;
            case URI_STUDENTS:
                break;
            case URI_STUDENT_ID:
                String uid = uri.getLastPathSegment();
                Log.d("Lab11-15","URI_STUDENT_ID" + uid);
                if(TextUtils.isEmpty(selection)){
                    selection = Constants.ID_STUDENT_COL + " = " + uid;
                } else{
                    selection = selection + " AND " + Constants.ID_STUDENT_COL + " = " + uid;
                }
                choose = 2;
                break;
            default:
                throw new IllegalArgumentException("Wrong URI " + uri);
        }

        int rowCount = 0;

        if(choose == 1){
            rowCount = db.getWritableDatabase().update(Constants.TABLE_NAME_GROUP,values,selection,selectionArgs);
        }
        else{
            rowCount = db.getWritableDatabase().update(Constants.TABLE_NAME_STUDENT,values,selection,selectionArgs);
        }

        getContext().getContentResolver().notifyChange(uri,null);

        Log.d("Lab11-15","update" + uri.toString());
        return rowCount;
    }
}
