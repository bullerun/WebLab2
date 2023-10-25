package nikita.ryskal.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/areaChecker/*")
public class AreaCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession(true);
        var filter = session.getAttribute("filter");
        if (filter != null && (boolean) filter) {
            session.setAttribute("filter", false);
            chain.doFilter(request, response);
        }
        else request.getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
    }
}
