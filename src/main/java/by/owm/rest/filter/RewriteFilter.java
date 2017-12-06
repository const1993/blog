package by.owm.rest.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.tuckey.web.filters.urlrewrite.Conf;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import java.io.IOException;

public class RewriteFilter extends UrlRewriteFilter {

    private static final String CONFIG_LOCATION = "urlrewrite.xml";

    @Value(CONFIG_LOCATION)
    private Resource resource;

    @Override
    protected void loadUrlRewriter(final FilterConfig filterConfig) throws ServletException {
        try {
            final Conf conf = new Conf(filterConfig.getServletContext(), resource.getInputStream(), resource.getFilename(), "@@yourOwnSystemId@@");
            checkConf(conf);
        } catch (IOException ex) {
            throw new ServletException("Unable to load URL rewrite configuration file ", ex);
        }
    }
}
