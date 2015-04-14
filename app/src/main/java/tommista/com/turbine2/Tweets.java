package tommista.com.turbine2;

import java.util.ArrayList;
import java.util.HashMap;

import tommista.com.turbine2.models.Tweet;

public class Tweets {

    private HashMap<String, Tweet> tweetMap;
    private ArrayList<Tweet> sortedList;

    public Tweets(){
        tweetMap = new HashMap<>();
    }

    public void addTweet(Tweet tweet){
        tweetMap.put(tweet.tweetId, tweet);
    }

    public boolean tweetExists(Tweet tweet){
        return (tweetMap.get(tweet.tweetId) != null);
    }

    public void removeTweet(Tweet tweet){
        tweetMap.remove(tweet.tweetId);
    }

    public ArrayList<Tweet> getSortedList(){
        return sortedList;
    }
}
