package be.ttown.reistijd;

import java.io.IOException;

import javax.servlet.http.*;

import com.fasterxml.jackson.jr.ob.JSON;



@SuppressWarnings("serial")
public class Knooppunten_Servlet extends HttpServlet {
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Graph graph = new Graph();
    JSON.std.write(graph.getReachableNodes(), resp.getWriter());
  }
}
