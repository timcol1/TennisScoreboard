package avlyakulov.timur.filter;

import avlyakulov.timur.util.HibernateUtilH2;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class HibernateInitializeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("HibernateInitializeFilter filter was created and initialize Hibernate connection");
        HibernateUtilH2.initSessionFactory();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }


    @Override
    public void destroy() {
        log.info("HibernateInitializeFilter filter was created and close Hibernate connection");
        HibernateUtilH2.closeSessionFactory();
    }
}
