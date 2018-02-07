package mayphoo.mpk.sfc.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import mayphoo.mpk.sfc.persistence.MMNewsContract;

/**
 * Created by User on 12/2/2017.
 */

public class NewsVO {

    @SerializedName("news-id")
    private String newsId;

    @SerializedName("brief")
    private String brief;

    @SerializedName("details")
    private String details;

    @SerializedName("images")
    private List<String> images;

    @SerializedName("posted-date")
    private String postedDate;

    @SerializedName("publication")
    private PublicationVO publication;

    @SerializedName("favorites")
    private List<FavoriteActionVO> favorites;

    @SerializedName("comments")
    private List<CommentActionVO> commentActions;

    @SerializedName("sent-tos")
    private List<SentToActionVO> sentToActions;

    public NewsVO() {
    }

    public NewsVO(String brief, String details, List<String> images, String postedDate) {
        this.newsId = String.valueOf(System.currentTimeMillis() / 1000);
        this.brief = brief;
        this.details = details;
        this.images = images;
        this.postedDate = postedDate;
    }

    public String getNewsId() {
        return newsId;
    }

    public String getBrief() {
        return brief;
    }

    public String getDetails() {
        return details;
    }

    public void setPublication(PublicationVO publication) {
        this.publication = publication;
    }

    public List<String> getImages() {
        if(images == null){
            images = new ArrayList<>();
        }
        return images;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public PublicationVO getPublication() {
        return publication;
    }

    public List<FavoriteActionVO> getFavoriteActions() {
        if(favorites == null) {
            favorites = new ArrayList<>();
        }
        return favorites;
    }

    public List<CommentActionVO> getCommentActions() {
        if(commentActions == null){
            commentActions = new ArrayList<>();
        }
        return commentActions;
    }

    public List<SentToActionVO> getSentToActions() {
        if(sentToActions == null){
            sentToActions = new ArrayList<>();
        }
        return sentToActions;
    }

    //Change object format to content values format
    public ContentValues parseToContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MMNewsContract.NewsEntry.COLUMN_NEWS_ID, newsId);
        contentValues.put(MMNewsContract.NewsEntry.COLUMN_BRIEF, brief);
        contentValues.put(MMNewsContract.NewsEntry.COLUMN_DETAILS, details);
        contentValues.put(MMNewsContract.NewsEntry.COLUMN_POSTED_DATE, postedDate);
        if(publication != null) {
            contentValues.put(MMNewsContract.NewsEntry.COLUMN_PUBLICATION_ID, publication.getPublicationId()); //publication id is not _id(persistence layer), id comes from web
        }

        return contentValues;
    }

    public static NewsVO parseFromCursor(Context context, Cursor cursor) {
        NewsVO news = new NewsVO();
        news.newsId = cursor.getString(cursor.getColumnIndex(MMNewsContract.NewsEntry.COLUMN_NEWS_ID));
        news.brief = cursor.getString(cursor.getColumnIndex(MMNewsContract.NewsEntry.COLUMN_BRIEF));
        news.details = cursor.getString(cursor.getColumnIndex(MMNewsContract.NewsEntry.COLUMN_DETAILS));
        news.postedDate = cursor.getString(cursor.getColumnIndex(MMNewsContract.NewsEntry.COLUMN_POSTED_DATE));

        news.publication = PublicationVO.parseFromCursor(cursor);
        news.images = loadImagesInNews(context, news.newsId);
        news.favorites = loadFavoriteActionsInNews(context, news.newsId);
        news.commentActions = loadCommentActionsInNews(context, news.newsId);
        news.sentToActions = loadSentToActionsInNews(context, news.newsId);
        return news;
    }

    private static List<String> loadImagesInNews(Context context, String newsId) {
        Cursor imagesInNewsCursor = context.getContentResolver().query(MMNewsContract.ImageInNewsEntry.CONTENT_URI,
                null,
                MMNewsContract.ImageInNewsEntry.COLUMN_NEWS_ID + " = ? ", new String[] {newsId},
                null);

        if(imagesInNewsCursor != null && imagesInNewsCursor.moveToFirst()) {
            List<String> imagesInNews = new ArrayList<>();
            do{
                imagesInNews.add(
                        imagesInNewsCursor.getString(
                                imagesInNewsCursor.getColumnIndex(MMNewsContract.ImageInNewsEntry.COLUMN_IMAGE_URL)));
            } while (imagesInNewsCursor.moveToNext());
            imagesInNewsCursor.close();
            return imagesInNews;
        }
        return null;
    }

    private static List<FavoriteActionVO> loadFavoriteActionsInNews(Context context, String newsId) {
        Cursor favoriteActionInNewsCursor = context.getContentResolver().query(MMNewsContract.FavoriteActionEntry.CONTENT_URI,
                null,
                MMNewsContract.FavoriteActionEntry.COLUMN_NEWS_ID + " = ? ", new String[]{newsId},
                null);

        if (favoriteActionInNewsCursor != null && favoriteActionInNewsCursor.moveToFirst()) {
            List<FavoriteActionVO> favoriteActionVOList = new ArrayList<>();
            do {
                favoriteActionVOList.add(
                        FavoriteActionVO.parseFromCursor(favoriteActionInNewsCursor));
            } while (favoriteActionInNewsCursor.moveToNext());
            favoriteActionInNewsCursor.close();
            return favoriteActionVOList;
        }
        return null;
    }

    private static List<CommentActionVO> loadCommentActionsInNews(Context context, String newsId){
        Cursor commentActionInNewsCursor = context.getContentResolver().query(MMNewsContract.CommentActionEntry.CONTENT_URI,
                null,
                MMNewsContract.CommentActionEntry.COLUMN_NEWS_ID + " = ? ", new String[]{newsId},
                null);

        if(commentActionInNewsCursor != null && commentActionInNewsCursor.moveToFirst()) {
            List<CommentActionVO> commentVOList = new ArrayList<>();
            do {
                commentVOList.add(
                        CommentActionVO.parseFromCursor(commentActionInNewsCursor));
            } while (commentActionInNewsCursor.moveToNext());
            commentActionInNewsCursor.close();
            return commentVOList;
        }
        return null;
    }

    private static List<SentToActionVO> loadSentToActionsInNews(Context context, String newsId) {
        Cursor sentToActionInNewsCursor = context.getContentResolver().query(MMNewsContract.SentToActionEntry.CONTENT_URI,
                null,
                MMNewsContract.SentToActionEntry.COLUMN_NEWS_ID + " = ? ", new String[]{newsId},
                null);

        if(sentToActionInNewsCursor != null && sentToActionInNewsCursor.moveToFirst()) {
            List<SentToActionVO> sentToActionVOList = new ArrayList<>();
            do {
                sentToActionVOList.add(
                        SentToActionVO.parseFromCursor(sentToActionInNewsCursor));
            } while (sentToActionInNewsCursor.moveToNext());
            sentToActionInNewsCursor.close();
            return sentToActionVOList;
        }
        return null;
    }

}
