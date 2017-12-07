package mayphoo.mpk.sfc.data.vo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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
    private List<CommentVO> comment;

    @SerializedName("sent-tos")
    private List<SentToVO> sentTo;

    public String getNewsId() {
        return newsId;
    }

    public String getBrief() {
        return brief;
    }

    public String getDetails() {
        return details;
    }

    public List<String> getImages() {
        return images;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public PublicationVO getPublication() {
        return publication;
    }

    public List<FavoriteActionVO> getFavorites() {
        return favorites;
    }

    public List<CommentVO> getComment() {
        return comment;
    }

    public List<SentToVO> getSentTo() {
        return sentTo;
    }
}
