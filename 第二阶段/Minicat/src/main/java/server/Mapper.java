
package server;


import java.util.ArrayList;
import java.util.List;


public final class Mapper {

    private List<Host> hosts = new ArrayList<>();

    public List<Host> getHosts() {
        return hosts;
    }

    public void setHosts(List<Host> hosts) {
        this.hosts = hosts;
    }

    @Override
    public String toString() {
        return "Mapper{" +
                "hosts=" + hosts +
                '}';
    }
}
