package be.ttown.reistijd;

import java.io.IOException;

import javax.servlet.http.*;

import com.fasterxml.jackson.jr.ob.JSON;



@SuppressWarnings("serial")
public class Knooppunt_Servlet extends HttpServlet {
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Graph graph = new Graph();
    String x = req.getParameter("x");
    String y = req.getParameter("y");
    JSON.std.write(graph.findClosest(new Node("",Double.parseDouble(x),Double.parseDouble(y))), resp.getWriter());
  }
}
