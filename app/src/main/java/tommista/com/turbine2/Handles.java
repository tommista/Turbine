package tommista.com.turbine2;

import java.util.ArrayList;

import tommista.com.turbine2.util.StringPreference;

public class Handles {

    private ArrayList<String> handleList;
    private StringPreference stringPreference;

    public Handles(StringPreference stringPreference){
        this.stringPreference = stringPreference;
        handleList = new ArrayList<>();
        deserialize();
    }

    public boolean addHandle(String handle){
        if(handleExists(handle)){
            return false;
        }else{
            handleList.add(handle);
            serialize();
            return true;
        }

    }

    public void removeHandle(String handle){
        handleList.remove(handle);
        serialize();
    }

    public boolean handleExists(String handle){
        for(String str : handleList){
            if(str.compareTo(handle) == 0){
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getHandleList(){
        return handleList;
    }

    private void serialize(){
        String handlesCombo = "";
        for(int i = 0; i < handleList.size(); i++){
            if(i == handleList.size() - 1){
                handlesCombo += handleList.get(i);
            } else{
                handlesCombo += handleList.get(i) + "&";
            }
        }

        stringPreference.set(handlesCombo);
    }

    private void deserialize(){
        String savedHandles = stringPreference.get();
        if(savedHandles != null && savedHandles.length() > 0){
            String[] parts = savedHandles.split("&");
            for(int i = 0; i < parts.length; i++){
                handleList.add(parts[i]);
            }
        }
    }

}