package by.owm.rest.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.tuckey.web.filters.urlrewrite.Conf;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by haria on 20.08.17.
 */
public class MyUrlRewriteFilter extends UrlRewriteFilter{
    private static final String CONFIG_LOCATION = "urlrewrite.xml";

    //Inject the Resource from the given location
    @Value(CONFIG_LOCATION)
    private Resource resource;

    //Override the loadUrlRewriter method, and write your own implementation
    @Override
    protected void loadUrlRewriter(FilterConfig filterConfig) throws ServletException {
        try {
            //Create a UrlRewrite Conf object with the injected resource
            Conf conf = new Conf(filterConfig.getServletContext(), resource.getInputStream(), resource.getFilename(), "@@yourOwnSystemId@@");
            checkConf(conf);
        } catch (IOException ex) {
            throw new ServletException("Unable to load URL rewrite configuration file from " + CONFIG_LOCATION, ex);
        }
    }
}
