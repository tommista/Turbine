package tommista.com.turbine2.models;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;

public class Tweet implements Comparable<Tweet>{

    public Tweet(Tweet tweet){
        this.tweetId = tweet.tweetId;
        this.tweetText = tweet.tweetText;
        this.user = tweet.user;
        this.handle = tweet.handle;
        this.tweetEntities = tweet.tweetEntities;
        this.goodUrl = tweet.goodUrl;
        this.createdAt = tweet.createdAt;
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
    public String handle;

    @SerializedName("created_at")
    public String createdAt;

    @Override
    public String toString(){
        //return tweetEntities.urlList[0].expandedUrl;
        return createdAt;
    }

    public int compareTo(Tweet tweet){
        DateFormat format = new SimpleDateFormat("EEEE MMMM d H:m:s Z y", Locale.ENGLISH);

        Date myDate = null;
        Date tweetDate = null;

        try{
            myDate = format.parse(this.createdAt);
            tweetDate = format.parse(tweet.createdAt);

        } catch(ParseException e){
            Timber.i("Error parsing dates: %s and %s", this.createdAt, tweet.createdAt);
        }

        if(myDate == null || tweetDate == null){
            return 0;
        } else{
            return myDate.compareTo(tweetDate) * -1; // Need to reverse the natural ordering of Dates.
        }
    }

}
