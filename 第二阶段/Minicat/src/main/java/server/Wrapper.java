package server;

import java.util.HashMap;
import java.util.Map;

public class Wrapper {
    private Map<String, HttpServlet> servletMapper = new HashMap<String, HttpServlet>();

    public Map<String, HttpServlet> getServletMapper() {
        return servletMapper;
    }

    public void setServletMapper(Map<String, HttpServlet> servletMapper) {
        this.servletMapper = servletMapper;
    }

    @Override
    public String toString() {
        return "Wrapper{" +
                "servletMapper=" + servletMapper +
                '}';
    }
}
