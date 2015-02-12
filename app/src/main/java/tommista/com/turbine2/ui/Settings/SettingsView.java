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

import tommista.com.turbine2.R;
import tommista.com.turbine2.adapters.HandleAdapter;
import tommista.com.turbine2.models.Handle;

/**
 * Created by tbrown on 2/9/15.
 */
public class SettingsView extends LinearLayout {

    private Context context;
    private HandleAdapter adapter;
    private Button addButton;
    private EditText newHandleEditText;


    public SettingsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();

        ArrayList<Handle> tempList = new ArrayList<>();
        tempList.add(new Handle("@asdf"));
        tempList.add(new Handle("@qwerasdf"));

        adapter = new HandleAdapter(context, /*HandleManager.getInstance().getHandleList()*/ tempList) ;

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
                //HandleManager.getInstance().addHandle(name);
                //MainActivity.getInstance().resetSettings();
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
