package mayphoo.mpk.sfc.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 12/3/2017.
 */

public class SentToVO {

    @SerializedName("sent-to-id")
    private String sendToId;

    @SerializedName("sent-date")
    private String sentDate;

    @SerializedName("acted-user")
    private ActedUserVO sender;

    @SerializedName("received-user")
    private ActedUserVO received;

    public String getSendToId() {
        return sendToId;
    }

    public String getSentDate() {
        return sentDate;
    }

    public ActedUserVO getSender() {
        return sender;
    }

    public ActedUserVO getReceived() {
        return received;
    }
}
