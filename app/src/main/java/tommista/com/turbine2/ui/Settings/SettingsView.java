package tommista.com.turbine2.ui.Settings;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import javax.inject.Inject;

import tommista.com.turbine2.DataFuser;
import tommista.com.turbine2.Handles;
import tommista.com.turbine2.R;
import tommista.com.turbine2.TurbineApp;
import tommista.com.turbine2.TurbineModule;
import tommista.com.turbine2.Tweets;
import tommista.com.turbine2.adapters.HandleAdapter;

public class SettingsView extends LinearLayout {

    private Context context;
    private Button addButton;
    private EditText newHandleEditText;
    private Button refreshButton;
    private HandleAdapter adapter;

    @Inject Handles handles;
    @Inject Tweets tweets;
    @Inject DataFuser dataFuser;
    @Inject @TurbineModule.IconFont
    Typeface icomoonFont;

    public SettingsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TurbineApp.get(context).inject(this);
        adapter = new HandleAdapter(context, handles, tweets);
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();

        final ListView listView = (ListView) this.findViewById(R.id.listview);

        refreshButton = (Button) findViewById(R.id.refresh_button);
        refreshButton.setTypeface(icomoonFont);
        refreshButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dataFuser.refresh();
            }
        });

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

                handles.addHandle(name);
                dataFuser.getTweetsForHandle(name);
                adapter.notifyDataSetChanged();
                newHandleEditText.setText("");

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
