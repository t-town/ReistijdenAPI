package be.ttown.reistijd;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.*;

import com.fasterxml.jackson.jr.ob.JSON;




@SuppressWarnings("serial")
public class Reistijden_Servlet extends HttpServlet {
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    try {
    resp.setContentType("text/plain");
    List<Node> nodes =  JSON.std.listOfFrom(Node.class, req.getParameter("nodes"));
    Scraper scraper = new Scraper();
    List<Reistijd> path = scraper.assemblePath(nodes.get(0), nodes.get(1));
    System.out.println(path);
    JSON.std.write(path, resp.getWriter());
    } catch(Exception e) {
      log(e.getMessage());
      resp.getWriter().write("Error 500");
    }
  }
}
