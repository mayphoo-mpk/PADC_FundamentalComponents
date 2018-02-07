package mayphoo.mpk.sfc.persistence;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.concurrent.Semaphore;

/**
 * Created by User on 12/16/2017.
 */

public class MMNewsProvider extends ContentProvider {

    public static final int ACTED_USERS = 100;
    public static final int PUBLICATIONS = 200;
    public static final int NEWS = 300;
    public static final int IMAGES_IN_NEWS = 400;
    public static final int FAVORITE_ACTIONS = 500;
    public static final int COMMENT_ACTIONS = 600;
    public static final int SENT_TO_ACTIONS = 700;

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private static final SQLiteQueryBuilder sNewsWithPublication_IJ;
    private static final SQLiteQueryBuilder sFavoriteActionsWithUsers_IJ;
    private static final SQLiteQueryBuilder sCommentActionsWithUsers_IJ;
    private static final SQLiteQueryBuilder sSentToActionsWithUsers_IJ;

    static {
        sNewsWithPublication_IJ = new SQLiteQueryBuilder();
        sNewsWithPublication_IJ.setTables(
                MMNewsContract.NewsEntry.TABLE_NAME + " INNER JOIN " +
                        MMNewsContract.PublicationEntry.TABLE_NAME + " ON " +
                        MMNewsContract.NewsEntry.TABLE_NAME + "." + MMNewsContract.NewsEntry.COLUMN_PUBLICATION_ID + " = " +
                        MMNewsContract.PublicationEntry.TABLE_NAME + "." + MMNewsContract.PublicationEntry.COLUMN_PUBLICATION_ID
        );
    }

    static{
        sFavoriteActionsWithUsers_IJ = new SQLiteQueryBuilder();
        sFavoriteActionsWithUsers_IJ.setTables(
                MMNewsContract.FavoriteActionEntry.TABLE_NAME + " INNER JOIN " +
                        MMNewsContract.ActedUserEntry.TABLE_NAME + " ON " +
                        MMNewsContract.FavoriteActionEntry.TABLE_NAME + "." + MMNewsContract.FavoriteActionEntry.COLUMN_USER_ID + " = " +
                        MMNewsContract.ActedUserEntry.TABLE_NAME + "." + MMNewsContract.ActedUserEntry.COLUMN_USER_ID
        );
    }

    static{
        sCommentActionsWithUsers_IJ = new SQLiteQueryBuilder();
        sCommentActionsWithUsers_IJ.setTables(
                MMNewsContract.CommentActionEntry.TABLE_NAME + " INNER JOIN " +
                        MMNewsContract.ActedUserEntry.TABLE_NAME + " ON " +
                        MMNewsContract.CommentActionEntry.TABLE_NAME + "." + MMNewsContract.CommentActionEntry.COLUMN_USER_ID + " = " +
                        MMNewsContract.ActedUserEntry.TABLE_NAME + "." + MMNewsContract.ActedUserEntry.COLUMN_USER_ID
        );
    }

    static {
        sSentToActionsWithUsers_IJ = new SQLiteQueryBuilder();
        sSentToActionsWithUsers_IJ.setTables(
                MMNewsContract.SentToActionEntry.TABLE_NAME + " INNER JOIN " +
                        MMNewsContract.ActedUserEntry.TABLE_NAME + " ON " +
                        MMNewsContract.SentToActionEntry.TABLE_NAME + "." + MMNewsContract.SentToActionEntry.COLUMN_SENDER_ID + " = " +
                        MMNewsContract.ActedUserEntry.TABLE_NAME + "." + MMNewsContract.ActedUserEntry.COLUMN_USER_ID +
                        " INNER JOIN " + MMNewsContract.ActedUserEntry.TABLE_NAME + " AS au ON " +
                        MMNewsContract.SentToActionEntry.TABLE_NAME + "." + MMNewsContract.SentToActionEntry.COLUMN_RECEIVER_ID + " = " +
                        "au." + MMNewsContract.ActedUserEntry.COLUMN_USER_ID
        );
    }

    private MMNewsDBHelper mDBHelper;

    //helper method
    private static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);    //NO_MATCH -> initial value
        // add uri value to support
        uriMatcher.addURI(MMNewsContract.CONTENT_AUTHORITY, MMNewsContract.PATH_ACTED_USERS, ACTED_USERS);
        uriMatcher.addURI(MMNewsContract.CONTENT_AUTHORITY, MMNewsContract.PATH_PUBLICATIONS, PUBLICATIONS);
        uriMatcher.addURI(MMNewsContract.CONTENT_AUTHORITY, MMNewsContract.PATH_NEWS, NEWS);
        uriMatcher.addURI(MMNewsContract.CONTENT_AUTHORITY, MMNewsContract.PATH_IMAGE_IN_NEWS, IMAGES_IN_NEWS);
        uriMatcher.addURI(MMNewsContract.CONTENT_AUTHORITY, MMNewsContract.PATH_FAVORITE_ACTIONS, FAVORITE_ACTIONS);
        uriMatcher.addURI(MMNewsContract.CONTENT_AUTHORITY, MMNewsContract.PATH_COMMENT_ACTIONS, COMMENT_ACTIONS);
        uriMatcher.addURI(MMNewsContract.CONTENT_AUTHORITY, MMNewsContract.PATH_SENT_TO_ACTIONS, SENT_TO_ACTIONS);

        return uriMatcher;
    }

    //helper method
    //return table name
    private String getTableName(Uri uri){
        switch (sUriMatcher.match(uri)){
            case ACTED_USERS:
                return MMNewsContract.ActedUserEntry.TABLE_NAME;
            case PUBLICATIONS:
                return MMNewsContract.PublicationEntry.TABLE_NAME;
            case NEWS:
                return MMNewsContract.NewsEntry.TABLE_NAME;
            case IMAGES_IN_NEWS:
                return MMNewsContract.ImageInNewsEntry.TABLE_NAME;
            case FAVORITE_ACTIONS:
                return MMNewsContract.FavoriteActionEntry.TABLE_NAME;
            case COMMENT_ACTIONS:
                return MMNewsContract.CommentActionEntry.TABLE_NAME;
            case SENT_TO_ACTIONS:
                return MMNewsContract.SentToActionEntry.TABLE_NAME;
        }
        return null;
    }

    public Uri getContentUri(Uri uri) {
        switch(sUriMatcher.match(uri)){
            case ACTED_USERS:
                return MMNewsContract.ActedUserEntry.CONTENT_URI;
            case PUBLICATIONS:
                return MMNewsContract.PublicationEntry.CONTENT_URI;
            case IMAGES_IN_NEWS:
                return MMNewsContract.ImageInNewsEntry.CONTENT_URI;
            case NEWS:
                return MMNewsContract.NewsEntry.CONTENT_URI;
            case FAVORITE_ACTIONS:
                return MMNewsContract.FavoriteActionEntry.CONTENT_URI;
            case COMMENT_ACTIONS:
                return MMNewsContract.CommentActionEntry.CONTENT_URI;
            case SENT_TO_ACTIONS:
                return MMNewsContract.SentToActionEntry.CONTENT_URI;
        }
        return null;
    }


    //set up persistence layer
    @Override
    public boolean onCreate() {
        mDBHelper = new MMNewsDBHelper(getContext());
        return true;
    }

    //query is retrieve
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor queryCursor;
        switch(sUriMatcher.match(uri)){
            case NEWS:
                queryCursor = sNewsWithPublication_IJ.query(mDBHelper.getReadableDatabase(),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case FAVORITE_ACTIONS:
                queryCursor = sFavoriteActionsWithUsers_IJ.query(mDBHelper.getReadableDatabase(),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

            case COMMENT_ACTIONS:
                queryCursor = sCommentActionsWithUsers_IJ.query(mDBHelper.getReadableDatabase(),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

            case SENT_TO_ACTIONS:
                queryCursor = sSentToActionsWithUsers_IJ.query(mDBHelper.getReadableDatabase(),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

            default:
                queryCursor = mDBHelper.getReadableDatabase().query(getTableName(uri),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

        }

        Context context = getContext();
        if (context != null) {
            //when changes uri(eg: insert, update, delete), notify cursor.
            queryCursor.setNotificationUri(context.getContentResolver(), uri);
        }

        return queryCursor;

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch(sUriMatcher.match(uri)){
            case ACTED_USERS:
                return MMNewsContract.ActedUserEntry.DIR_TYPE;
            case PUBLICATIONS:
                return MMNewsContract.PublicationEntry.DIR_TYPE;
            case IMAGES_IN_NEWS:
                return MMNewsContract.ImageInNewsEntry.DIR_TYPE;
            case NEWS:
                return MMNewsContract.NewsEntry.DIR_TYPE;
            case FAVORITE_ACTIONS:
                return MMNewsContract.FavoriteActionEntry.DIR_TYPE;
            case COMMENT_ACTIONS:
                return MMNewsContract.CommentActionEntry.DIR_TYPE;
            case SENT_TO_ACTIONS:
                return MMNewsContract.SentToActionEntry.DIR_TYPE;
        }
        return null;
    }

    //insert single data
    @Nullable
    @Override
    //Parameter: contentValues is insert objects datas (only primitive data type)
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        long _id = db.insert(tableName, null, values);
        if(_id > 0){
            Uri tableContentUri = getContentUri(uri);
            Uri insertedUri = ContentUris.withAppendedId(tableContentUri, _id);

            if(getContext() != null){
                //notify uri when change in database (insert)
                getContext().getContentResolver().notifyChange(uri, null);
            }

            return insertedUri;
        }

        return null;
    }

    //insert data collection
    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        int insertedCount = 0;

        try {
            db.beginTransaction();
            for (ContentValues cv : values) {
                long _id = db.insert(tableName, null, cv);
                if (_id > 0) {
                    insertedCount++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            //db.close();
        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedCount;
    }

    //parameters: selection and selectionArgs means 'where' clause
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int rowDeleted;
        String tableName = getTableName(uri);

        rowDeleted = db.delete(tableName, selection, selectionArgs);
        Context context = getContext();
        if(context != null && rowDeleted > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int rowUpdated;
        String tableName = getTableName(uri);

        rowUpdated = db.update(tableName, contentValues, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowUpdated > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowUpdated;
    }
}
