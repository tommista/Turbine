package tommista.com.turbine2.ui.Settings;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import javax.inject.Inject;

import tommista.com.turbine2.R;
import tommista.com.turbine2.TurbineApp;
import tommista.com.turbine2.adapters.HandleAdapter;
import tommista.com.turbine2.models.Handle;
import tommista.com.turbine2.net.TwitterAPI;

import static tommista.com.turbine2.TurbineModule.HandleList;

/**
 * Created by tbrown on 2/9/15.
 */
public class SettingsView extends LinearLayout {

    private Context context;
    private HandleAdapter adapter;
    private Button addButton;
    private EditText newHandleEditText;

    @Inject @HandleList ArrayList<Handle> handleList;
    @Inject TwitterAPI twitterAPI;


    public SettingsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TurbineApp.get(context).inject(this);
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();

        adapter = new HandleAdapter(context, handleList) ;

        final ListView listView = (ListView) this.findViewById(R.id.listview);

        addButton = (Button) this.findViewById(R.id.add_button);
        newHandleEditText = (EditText) this.findViewById(R.id.edit_text);

        newHandleEditText.setHint("  " + newHandleEditText.getHint());

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newHandleEditText = (EditText) findViewById(R.id.edit_text);
                String name = newHandleEditText.getText().toString();
                if(name.isEmpty()){
                    return;
                }
                else if(!name.startsWith("@")){
                    name = "@" + name;
                }

                handleList.add(new Handle(name));
                adapter.notifyDataSetChanged();

                hideInputMethod(v);
            }
        });
        listView.setAdapter(adapter);
    }

    private void hideInputMethod(View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
