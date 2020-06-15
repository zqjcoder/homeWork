package server;

import java.util.ArrayList;
import java.util.List;


public class Context {

    private String name;

    private List<Wrapper> wrappers = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Wrapper> getWrappers() {
        return wrappers;
    }

    public void setWrappers(List<Wrapper> wrappers) {
        this.wrappers = wrappers;
    }

    @Override
    public String toString() {
        return "Context{" +
                "name='" + name + '\'' +
                ", wrappers=" + wrappers +
                '}';
    }
}
