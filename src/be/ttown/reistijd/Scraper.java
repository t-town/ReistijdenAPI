package be.ttown.reistijd;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;

public class Scraper {
  
  HashMap<String,Reistijd> reistijden = this.scrape();
  public Scraper() {
    
  }

  private HashMap<String,Reistijd> scrape() {
    try {
      System.out.println("Starting scraping");
      URLFetchService fetchservice = URLFetchServiceFactory.getURLFetchService();
      URL bru = new URL("http://www.verkeerscentrum.be/verkeersinfo/reistijden/R0");
      URL antw1 = new URL("http://www.verkeerscentrum.be/verkeersinfo/reistijden/binnenring");
      URL antw2 = new URL("http://www.verkeerscentrum.be/verkeersinfo/reistijden/buitenring");
      URL e313 = new URL("http://www.verkeerscentrum.be/verkeersinfo/reistijden/E313");
      Future<HTTPResponse> bruResultFuture = fetchservice.fetchAsync(bru);
      Future<HTTPResponse> antw1ResultFuture = fetchservice.fetchAsync(antw1);
      Future<HTTPResponse> antw2ResultFuture = fetchservice.fetchAsync(antw2);
      Future<HTTPResponse> e313ResultFuture = fetchservice.fetchAsync(e313);
      HTTPResponse bruResult = bruResultFuture.get();
      HTTPResponse antw1Result = antw1ResultFuture.get();
      HTTPResponse antw2Result = antw2ResultFuture.get();
      HTTPResponse e313Result = e313ResultFuture.get();
      String bruString = new String(bruResult.getContent(),"UTF-8");
      String antw1String = new String(antw1Result.getContent(),"UTF-8");
      String antw2String = new String(antw2Result.getContent(),"UTF-8");
      String e313String = new String(e313Result.getContent(),"UTF-8");

      String patternString = "<tr><tdbgcolor=\"#[EAF0B]*\">([^<]*)<\\/td><tdbgcolor=\"#[EAF0B]*\">([^<]*)<\\/td><tdalign=\"center\"bgcolor=\"#[EAF0B]*\">([^<]*)<\\/td><tdalign=\"center\"bgcolor=\"#[EAF0B]*\">([^<]*)<\\/td><tdalign=\"center\"bgcolor=\"#[EAF0B]*\">([^<]*)<\\/td><tdalign=\"center\"bgcolor=\"#[EAF0B]*\">([^<]*)<\\/td><\\/tr>";
      Pattern pattern = Pattern.compile(patternString);
      HashMap<String, Reistijd> reistijden = new HashMap<>();

      for (String webpage : new String[]{bruString,antw1String,antw2String,e313String}) {
        webpage = webpage.replaceAll("\\s", "");
        webpage = webpage.replace("colspan=\"3\"", "");
        Matcher matcher = pattern.matcher(webpage);
        while(matcher.find()) {
          String name = matcher.group(2).trim().toLowerCase().replace(" ", "");
          int filevrij = Integer.parseInt(matcher.group(3).replace("min", "").trim());
          int nu = Integer.parseInt(matcher.group(4).replace("min", "").trim());
          int vertraging = 0;
          if(!matcher.group(5).trim().equalsIgnoreCase("-")) {
            vertraging = Integer.parseInt(matcher.group(5).replace("min", "").trim());
          }
          String evolutie = matcher.group(6);
          Reistijd newReistijd = new Reistijd(filevrij,nu,vertraging,evolutie);
          reistijden.put(name, newReistijd);
        }

      }
      return reistijden;
    } catch(Exception e) {
      System.out.println(e);
    }
    return null;

  }
  
  
  
  public List<Reistijd> assemblePath(Node start, Node end) {
    Graph graph = new Graph();
    List<Node> nodes = graph.findPath(start, end);
    System.out.println(nodes);
    System.out.println(reistijden);
    System.out.println("calculating path");
    List<Reistijd> path = new ArrayList<Reistijd>();
    for(int i = 0; i< nodes.size() - 1; i++) {
      Node one = nodes.get(i);
      Node two = nodes.get(i+1);
      String key = one.getVerkeerscentrumName() + "->" + two.getVerkeerscentrumName();
      key = key.toLowerCase().replace(" ", "");
      if(this.reistijden.containsKey(key)) {
        Reistijd nodeReistijd = this.reistijden.get(key);
        nodeReistijd.addFromTo(one, two);
        path.add(nodeReistijd);
      } else {
        System.out.println("Not Found: " + key);

      }
    }
    return path;
    
  }

}
