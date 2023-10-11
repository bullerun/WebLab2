package nikita.ryskal.servlets;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nikita.ryskal.modul.Point;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/areaChecker")
public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            System.out.println(2);
            System.out.println(request.getParameter("X"));
            System.out.println(request.getParameter("Y"));
            System.out.println(request.getParameter("R"));

            var x = Float.parseFloat(request.getParameter("X"));
            var y = Float.parseFloat(request.getParameter("Y"));
            var r = Integer.parseInt(request.getParameter("R"));

            var point = new Point(x, y, r);

            var session = request.getSession();
            var bean = (List<Point>) session.getAttribute("bean");
            if (bean == null) {
                bean = new ArrayList<Point>();
                session.setAttribute("bean", bean);
            }
            bean.add(0, point);
            if ("withOutRedirect".equals(request.getParameter("action"))) {
                var gson = new Gson();
                Map<String, Object> json = new HashMap<>();
                json.put("x", x);
                json.put("y", y);
                json.put("r", r);
                json.put("result", point.getHit());
                var msg = gson.toJson(json);
                response.setContentType("application/json");
                response.getWriter().write(msg);
            } else if ("withRedirect".equals(request.getParameter("action"))) {
                getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
            }
        } catch (Exception e) {
            response.sendRedirect("/controller");
        }
    }
}