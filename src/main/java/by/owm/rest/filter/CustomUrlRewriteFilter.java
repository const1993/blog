package by.owm.rest.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.tuckey.web.filters.urlrewrite.Conf;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import java.io.IOException;

@Component
public class CustomUrlRewriteFilter extends UrlRewriteFilter {

    private final ClassPathResource resource;
    private final String systemId;

    @Autowired
    public CustomUrlRewriteFilter(@Value("${url.rewrite.xml.path}") final String path,
                                  @Value("${url.rewrite.xml.systemId}") final String systemId) {
        this.resource = new ClassPathResource(path);
        this.systemId = systemId;
    }

    @Override
    protected void loadUrlRewriter(final FilterConfig filterConfig) throws ServletException {
        try {
            final Conf conf = new Conf(filterConfig.getServletContext(), resource.getInputStream(), resource.getFilename(), systemId);
            checkConf(conf);
        } catch (final IOException ex) {
            throw new ServletException("Unable to load URL rewrite configuration file", ex);
        }
    }
}
