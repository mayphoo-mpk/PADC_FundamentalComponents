package mayphoo.mpk.sfc.data.vo;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import mayphoo.mpk.sfc.persistence.MMNewsContract;

/**
 * Created by User on 12/3/2017.
 */

public class FavoriteActionVO {

    @SerializedName("favorite-id")
    private String favoriteId;

    @SerializedName("favourite-date")
    private String favoriteDate;

    @SerializedName("acted-user")
    private ActedUserVO actedUser;

    public String getFavoriteId() {
        return favoriteId;
    }

    public String getFavoriteDate() {
        return favoriteDate;
    }

    public ActedUserVO getActedUser() {
        return actedUser;
    }

    //Change object format to content values format
    public ContentValues parseToContentValues(String newsId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MMNewsContract.FavoriteActionEntry.COLUMN_FAVORITE_ID, favoriteId);
        contentValues.put(MMNewsContract.FavoriteActionEntry.COLUMN_FAVORITE_DATE, favoriteDate);
        contentValues.put(MMNewsContract.FavoriteActionEntry.COLUMN_USER_ID, actedUser.getUserId());
        contentValues.put(MMNewsContract.FavoriteActionEntry.COLUMN_NEWS_ID, newsId);
        return contentValues;
    }

    public static FavoriteActionVO parseFromCursor(Cursor cursor) {
        FavoriteActionVO favorite = new FavoriteActionVO();
        favorite.favoriteId = cursor.getString(cursor.getColumnIndex(MMNewsContract.FavoriteActionEntry.COLUMN_FAVORITE_ID));
        favorite.favoriteDate = cursor.getString(cursor.getColumnIndex(MMNewsContract.FavoriteActionEntry.COLUMN_FAVORITE_DATE));
        favorite.actedUser = ActedUserVO.parseFromCursor(cursor);
        return favorite;
    }

}
