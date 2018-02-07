package mayphoo.mpk.sfc.data.vo;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import mayphoo.mpk.sfc.persistence.MMNewsContract;

/**
 * Created by User on 12/3/2017.
 */

public class CommentActionVO {

    @SerializedName("comment-id")
    private String commentId;

    @SerializedName("comment")
    private String comment;

    @SerializedName("comment-date")
    private String commentDate;

    @SerializedName("acted-user")
    private ActedUserVO actedUser;

    public String getCommentId() {
        return commentId;
    }

    public String getComment() {
        return comment;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public ActedUserVO getActedUser() {
        return actedUser;
    }

    //Change object format to content values format, store into persistence.
    public ContentValues parseToContentValues(String newsId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MMNewsContract.CommentActionEntry.COLUMN_COMMENT_ID, commentId);
        contentValues.put(MMNewsContract.CommentActionEntry.COLUMN_COMMENT, comment);
        contentValues.put(MMNewsContract.CommentActionEntry.COLUMN_COMMENT_DATE, commentDate);
        contentValues.put(MMNewsContract.CommentActionEntry.COLUMN_NEWS_ID, newsId);
        contentValues.put(MMNewsContract.CommentActionEntry.COLUMN_USER_ID, actedUser.getUserId());
        return contentValues;
    }

    public static CommentActionVO parseFromCursor(Cursor cursor) {
        CommentActionVO comment = new CommentActionVO();
        comment.commentId = cursor.getString(cursor.getColumnIndex(MMNewsContract.CommentActionEntry.COLUMN_COMMENT_ID));
        comment.comment = cursor.getString(cursor.getColumnIndex(MMNewsContract.CommentActionEntry.COLUMN_COMMENT));
        comment.commentDate = cursor.getString(cursor.getColumnIndex(MMNewsContract.CommentActionEntry.COLUMN_COMMENT_DATE));
        comment.actedUser = ActedUserVO.parseFromCursor(cursor);
        return comment;
    }
}
