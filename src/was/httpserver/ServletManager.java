package was.httpserver;

import was.httpserver.servlet.InternalErrorServlet;
import was.httpserver.servlet.NotFoundServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServletManager {
    private final Map<String, HttpServlet> servletMap = new HashMap<>();
    private HttpServlet defaultServlet;
    private HttpServlet internalErrorServlet = new InternalErrorServlet();
    private HttpServlet notFoundServlet = new NotFoundServlet();

    public ServletManager() {
    }

    public void add(String path, HttpServlet servlet) {
        servletMap.put(path, servlet);
    }

    public void setDefaultServlet(HttpServlet servlet) {
        defaultServlet = servlet;
    }

    public void setNotFoundErrorServlet(HttpServlet servlet) {
        notFoundServlet = servlet;
    }

    public void setInternalErrorServlet(HttpServlet servlet) {
        internalErrorServlet = servlet;
    }

    public void execute(HttpRequest request, HttpResponse response) throws IOException {
        try {
            HttpServlet servlet = servletMap.getOrDefault(request.getPath(), defaultServlet);

            if (servlet == null) {
                throw new PageNotFoundException("request url=" + request.getPath() + " not found");
            }

            servlet.service(request, response);

        } catch (PageNotFoundException e) {
            e.printStackTrace();
            notFoundServlet.service(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            internalErrorServlet.service(request, response);
        }
    }
}
