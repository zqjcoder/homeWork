package server;

import java.util.ArrayList;
import java.util.List;


public class Host {
    private String name;
    private String appBase;

    private List<Context> contexts = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppBase() {
        return appBase;
    }

    public void setAppBase(String appBase) {
        this.appBase = appBase;
    }

    public List<Context> getContexts() {
        return contexts;
    }

    public void setContexts(List<Context> contexts) {
        this.contexts = contexts;
    }

    @Override
    public String toString() {
        return "Host{" +
                "name='" + name + '\'' +
                ", appBase='" + appBase + '\'' +
                ", contexts=" + contexts +
                '}';
    }
}
