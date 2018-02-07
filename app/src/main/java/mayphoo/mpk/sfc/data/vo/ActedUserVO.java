package mayphoo.mpk.sfc.data.vo;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import mayphoo.mpk.sfc.persistence.MMNewsContract;

/**
 * Created by User on 12/3/2017.
 */

public class ActedUserVO {

    @SerializedName("user-id")
    private String userId;

    @SerializedName("user-name")
    private String userName;

    @SerializedName("profile-image")
    private String profileImage;

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    //Change object format to content values format
    public ContentValues parseToContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MMNewsContract.ActedUserEntry.COLUMN_USER_ID, userId);
        contentValues.put(MMNewsContract.ActedUserEntry.COLUMN_USER_NAME, userName);
        contentValues.put(MMNewsContract.ActedUserEntry.COLUMN_PROFILE_IMAGE, profileImage);
        return contentValues;
    }

    public static ActedUserVO parseFromCursor(Cursor cursor) {
        ActedUserVO actedUser = new ActedUserVO();
        actedUser.userId = cursor.getString(cursor.getColumnIndex(MMNewsContract.ActedUserEntry.COLUMN_USER_ID));
        actedUser.userName = cursor.getString(cursor.getColumnIndex(MMNewsContract.ActedUserEntry.COLUMN_USER_NAME));
        actedUser.profileImage = cursor.getString(cursor.getColumnIndex(MMNewsContract.ActedUserEntry.COLUMN_PROFILE_IMAGE));
        return actedUser;
    }
}
