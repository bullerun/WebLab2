package nikita.ryskal.servlets;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute("filter", true);
        try {
            if (request.getParameter("X") != null) {
                float x = Float.parseFloat(request.getParameter("X"));
                float y = Float.parseFloat(request.getParameter("Y"));
                int r = Integer.parseInt(request.getParameter("R"));
                response.sendRedirect("./areaChecker?" + "X=" + x + "&Y=" + y + "&R=" + r + "&action=withRedirect");
            } else {
                BufferedReader reader = request.getReader();
                int intValueOfChar;
                StringBuilder result = new StringBuilder();
                while ((intValueOfChar = reader.read()) != -1) {
                    result.append((char) intValueOfChar);
                }
                if (!result.toString().isEmpty()) {
                    Map<String, Object> map = new Gson().fromJson(result.toString(), Map.class);
                    float x = Float.parseFloat(String.valueOf(map.get("X")));
                    float y = Float.parseFloat(String.valueOf(map.get("Y")));
                    int r = Integer.parseInt(String.valueOf(map.get("R")).substring(0, 1));
                    response.sendRedirect("./areaChecker?" + "X=" + x + "&Y=" + y + "&R=" + r + "&action=withOutRedirect");
                }
            }
        } catch (Exception e) {
            doGet(request, response);
        }
    }
}