package tommista.com.turbine2.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tbrown on 2/13/15.
 */
public class Tweet {

    public Tweet(Tweet tweet){
        this.tweetId = tweet.tweetId;
        this.tweetText = tweet.tweetText;
        this.user = tweet.user;
        this.screenName = tweet.screenName;
        this.tweetEntities = tweet.tweetEntities;
        this.goodUrl = tweet.goodUrl;
    }

    public String goodUrl;

    @SerializedName("id_str")
    public String tweetId;

    @SerializedName("text")
    public String tweetText;

    @SerializedName("user")
    public User user;

    @SerializedName("entities")
    public TweetEntities tweetEntities;

    @SerializedName("screen_name")
    public String screenName;

    @Override
    public String toString(){
        return tweetEntities.urlList[0].expandedUrl;
    }

    private class TweetEntities {

        @SerializedName("urls")
        public TweetUrls urlList[];

    }

    private class User {

        @SerializedName("profile_image_url")
        public String profileImageURL;

    }

    private class TweetUrls {

        @SerializedName("expanded_url")
        public String expandedUrl;
    }

}
