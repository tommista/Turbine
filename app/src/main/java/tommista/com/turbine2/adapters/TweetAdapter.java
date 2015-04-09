package tommista.com.turbine2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import tommista.com.turbine2.R;
import tommista.com.turbine2.models.Tweet;

/**
 * Created by tbrown on 4/9/15.
 */
public class TweetAdapter extends ArrayAdapter<Tweet>{

    public TweetAdapter(Context context, ArrayList<Tweet> tweets) {
        super(context, R.layout.tweet_view , tweets);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Tweet tweet = getItem(position);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.handle_view, null);
        }

        return convertView;
    }

}
