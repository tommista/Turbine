package tommista.com.turbine2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import tommista.com.turbine2.models.Tweet;

public class Tweets {

    private HashMap<String, Tweet> tweetMap;

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

    public List<Tweet> getSortedList(){
        ArrayList<Tweet> tempList = new ArrayList<Tweet>(tweetMap.values());
        Collections.sort(tempList);
        return tempList;
    }
}
