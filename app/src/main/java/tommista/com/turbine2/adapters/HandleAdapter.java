package tommista.com.turbine2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import timber.log.Timber;
import tommista.com.turbine2.R;

/**
 * Created by tbrown on 2/9/15.
 */
public class HandleAdapter extends ArrayAdapter<String> {

    private ArrayList<String> handles;

    public HandleAdapter(Context context, ArrayList<String> handles) {
        super(context, R.layout.handle_view , handles);
        this.handles = handles;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final String handle = getItem(position);
        Timber.i("position=%d,handle=%s", position, handle);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.handle_view, null);
        }

        TextView handle_text= (TextView) convertView.findViewById(R.id.handle_text);
        Button button = (Button) convertView.findViewById(R.id.del_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handles.remove(handle);
                notifyDataSetChanged();
            }
        });
        handle_text.setText(handle);
        return convertView;
    }

}
