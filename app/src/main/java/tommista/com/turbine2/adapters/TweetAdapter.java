package tommista.com.turbine2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import timber.log.Timber;
import tommista.com.turbine2.R;
import tommista.com.turbine2.Tweets;
import tommista.com.turbine2.models.Tweet;

public class TweetAdapter extends ArrayAdapter<Tweet>{

    private Tweets tweets;
    private Context context;
    private Picasso picasso;

    public TweetAdapter(Context context, Tweets tweets, Picasso picasso) {
        super(context, R.layout.tweet_view , tweets.getSortedList());
        this.tweets = tweets;
        this.context = context;
        this.picasso = picasso;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Tweet tweet = getItem(position);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.tweet_view, null);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.image_view);
        picasso.with(context).load(tweet.user.profileImageURL).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                Timber.i("picasso success for url: %s", tweet.user.profileImageURL);
            }

            @Override
            public void onError() {
                Timber.i("picasso failure for url: %s", tweet.user.profileImageURL);
            }
        });

        TextView twitterName = (TextView) convertView.findViewById(R.id.twitter_name);
        twitterName.setText(tweet.user.name);

        TextView handleName = (TextView) convertView.findViewById(R.id.twitter_handle);
        handleName.setText(tweet.handle);

        TextView tweetText = (TextView) convertView.findViewById(R.id.tweet_text);
        tweetText.setText(tweet.tweetText);

        return convertView;
    }

}
