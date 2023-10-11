package nikita.ryskal.servlets;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {
//    List<Integer> ArrayXs = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3);
//    List<Integer> ArrayRs = Arrays.asList(1, 2, 3, 4, 5);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getParameter("X") != null) {
                float x = Float.parseFloat(request.getParameter("X"));
                float y = Float.parseFloat(request.getParameter("Y"));
                int r = Integer.parseInt(request.getParameter("R"));
//            if (!ArrayXs.contains(x)) throw new Exception("Incorrect x");
//            if (-5 > y || y > 3) throw new Exception("Incorrect y");
//            if (!ArrayRs.contains(r)) throw new Exception("Incorrect r");
                System.out.println(x + " " + y + " " + r);
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
                    System.out.println(map);

                    float x = Float.parseFloat(String.valueOf(map.get("X")));
                    float y = Float.parseFloat(String.valueOf(map.get("Y")));
                    int r = Integer.parseInt(String.valueOf(map.get("R")).substring(0, 1));
                    System.out.println(x + " " + y + " " + r);
                    response.sendRedirect("./areaChecker?" + "X=" + x + "&Y=" + y + "&R=" + r + "&action=withOutRedirect");
                }
            }
        } catch (Exception e) {
            System.out.println("ERORR");
            System.out.println(e.getMessage());
            doGet(request, response);
        }
    }
}
//
//package nikita.ryskal.servlets;
//        import jakarta.servlet.ServletException;
//        import jakarta.servlet.annotation.WebServlet;
//
//
//        import com.google.gson.Gson;
//
//        import java.io.IOException;
//        import java.util.HashMap;
//        import java.util.Map;
//
//        import jakarta.servlet.http.HttpServlet;
//        import jakarta.servlet.http.HttpServletRequest;
//        import jakarta.servlet.http.HttpServletResponse;
//
//
//        import java.util.Arrays;
//        import java.util.List;
//
//@WebServlet("/controller")
//public class ControllerServlet extends HttpServlet {
//    List<Integer> ArrayXs = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3);
//    List<Integer> ArrayRs = Arrays.asList(1, 2, 3, 4, 5);
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        System.out.println("get");
//        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
//        final var INVALID_DATA_MSG = "Please set the data values in correct form.";
//        System.out.println(1);
//        try {
//            if (
//                    request.getParameter("R") == null
//                            || request.getParameter("X") == null
//                            || request.getParameter("Y") == null
//            ) {
//                sendError(response, INVALID_DATA_MSG);
//                return;
//            }
//            if (
//                    request.getParameter("R").isEmpty()
//                            || request.getParameter("X").isEmpty()
//                            || request.getParameter("Y").isEmpty()
//            ) {
//                sendError(response, INVALID_DATA_MSG);
//                return;
//            }
//            if (
//                    Double.parseDouble(request.getParameter("Y")) < -3
//                            || Double.parseDouble(request.getParameter("Y")) > 5
//            ) {
//                sendError(response, INVALID_DATA_MSG);
//                return;
//            }
//
//            Double.parseDouble(request.getParameter("X"));
//            Integer.parseInt(request.getParameter("R"));
//
//            response.sendRedirect("./areaChecker?" + "X="+request.getParameter("X")+"&Y="+request.getParameter("Y")+"&R="+request.getParameter("R"));
//
//        } catch (Exception e) {
//            sendError(response, e.toString());
//        }
//    }
//
//    private void sendError(HttpServletResponse response, String errorMessage) throws IOException {
//        var json = new Gson();
//        Map<String, Object> jsonResponse = new HashMap<>() {{
//            put("error", errorMessage);
//            put("status", "UNPROCESSABLE_ENTITY");
//        }};
//
//        response.setContentType("application/json");
//        response.getWriter().write(json.toJson(jsonResponse));
//        response.setStatus(422);
//    }
//}
