package tommista.com.turbine2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import tommista.com.turbine2.Handles;
import tommista.com.turbine2.R;
import tommista.com.turbine2.Tweets;

// ArrayAdapter that displays the list of all handles for a ListView

public class HandleAdapter extends ArrayAdapter<String> {

  private Handles handles;
  private Tweets tweets;

  public HandleAdapter(Context context, Handles handles, Tweets tweets) {
    super(context, R.layout.handle_view, handles.getHandleList());
    this.handles = handles;
    this.tweets = tweets;
  }

  @Override
  public View getView(final int position, View convertView, ViewGroup parent) {
    final String handle = getItem(position);

    if (convertView == null) {
      LayoutInflater inflater =
          (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = inflater.inflate(R.layout.handle_view, null);
    }

    TextView handle_text = (TextView) convertView.findViewById(R.id.handle_text);
    Button button = (Button) convertView.findViewById(R.id.del_button);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        deleteButtonPressed(handle);
      }
    });
    handle_text.setText(handle);
    return convertView;
  }

  private void deleteButtonPressed(String handle){
    handles.removeHandle(handle);
    tweets.removeTweetsByHandle(handle);
    notifyDataSetChanged();
  }
}
