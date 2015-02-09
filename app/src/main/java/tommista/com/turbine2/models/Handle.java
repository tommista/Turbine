package tommista.com.turbine2.models;

public class Handle {

    private String twitterHandle;

    public Handle(String twitterHandle){
        this.twitterHandle = twitterHandle;
    }

    public String getTwitterHandle()
    {
        return twitterHandle;
    }

    public void setTwitterHandle(String twitterHandle){
        this.twitterHandle = twitterHandle;
    }


}