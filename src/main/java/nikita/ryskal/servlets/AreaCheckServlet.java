package nikita.ryskal.servlets;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import nikita.ryskal.modul.Point;

import java.io.IOException;
import java.util.*;

@WebServlet("/areaChecker")
public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        Enumeration<String> headers  = request.getHeaderNames();
//        while (headers.hasMoreElements()){
//            String name = headers.nextElement();
//            String value = request.getHeader(name);
//            System.out.println(name+" = "+value);
//        }
        HttpSession session = request.getSession(true);
        try {
            var x = Float.parseFloat(request.getParameter("X"));
            var y = Float.parseFloat(request.getParameter("Y"));
            var r = Integer.parseInt(request.getParameter("R"));
            var point = new Point(x, y, r);
            var bean = (List<Point>) session.getAttribute("array");
            if (bean == null) {
                bean = new ArrayList<Point>();
                session.setAttribute("array", bean);
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
            response.sendRedirect(request.getContextPath() + "/controller");
        }
    }
}