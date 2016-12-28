package be.ttown.reistijd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Graph with only one purpose
 */
public class Graph {

  private HashMap<Node,ArrayList<Node>> graph;
  private ArrayList<Node> reachables;

  private void addToMap(HashMap<Node,ArrayList<Node>> map,Node key, Node value) {
    if(map.containsKey(key)) {
      map.get(key).add(value);
    } else {
      map.put(key, new ArrayList<Node>());
      map.get(key).add(value);
    }
  }

  public Graph() {
    Node noord = new Node("Noord",51.263645,4.435604);
    Node oost = new Node("Oost",51.216248,4.449903);
    Node oostN = new Node("OostN","Oost",51.216248,4.449903);
    Node oostZ = new Node("OostZ","Oost",51.216248,4.449903);

    Node zuid = new Node("Zuid",51.190248,4.413430);
    Node centrum = new Node("Centrum",51.195210,4.385868);
    Node west = new Node("West",51.214346,4.357262);
    Node beveren = new Node("Beveren",51.242967,4.246838);
    Node haven = new Node("Haven",51.316978,4.355920);
    Node niklaas = new Node("E17 St. Niklaas",51.140238,4.168692);
    Node kontich = new Node("E19 Kontich",51.126325,4.430341);
    Node bareel = new Node("E19 Kleine bareel",51.268144,4.460528);
    Node geelO = new Node("Geel-Oost",51.108266,5.019052);
    Node geelW = new Node("Geel-West",51.129601,4.939413);
    Node herentalsO = new Node("Herent.-Oost",51.150940,4.854590);
    Node herentalsI = new Node("Herent.-Ind.",51.162179,4.798351);
    Node herentalsW = new Node("Herent.-West",51.175066,4.757039);
    Node massenhoven = new Node("Massenh.",51.193121,4.642870);
    Node ranst = new Node("Ranst",51.201665,4.575453);
    Node wommelgem = new Node("Wommelgem",51.211118,4.491432);
    Node wommelgemN = new Node("Wommelgem (via 1)",0,0);
    Node wommelgemZ = new Node("Wommelgem (via 2)",0,0);

    Node halle = new Node("Halle",50.724021,4.269115);
    Node bijgaarden = new Node("Gr.-Bijgaarden",50.876254,4.274699);
    Node strombeek = new Node("Strombeek",50.913087,4.342760);
    Node machelen = new Node("Machelen",50.902805,4.434971);
    Node zaventem = new Node("Zaventem",50.891809,4.453760);
    Node woluwe = new Node("St-S.Woluwe",50.868925,4.469332);
    Node woluwe40 = new Node("E40 St-S.Woluwe",0,0);
    Node leonard = new Node("Leonard",50.800990,4.464974);
    Node mechelenN = new Node("E19 Mechelen-N",51.042682,4.453383);
    Node aalst = new Node("E40 Aalst",50.857923,4.634734);
    Node meise = new Node(" A12 Meise",50.943906,4.324303);
    Node heverlee40 = new Node("E40 Heverlee",50.918451,4.031843);
    Node haasrode = new Node("E40 Haasrode",50.844934,4.71270);
    Node wilsele = new Node("E314 Wilsele",50.894195,4.688134);
    Node overijse = new Node("E411 Overijse",50.771752,4.502006);
    Node heverlee = new Node("Heverlee",50.857166,4.640998);
    Node aarschot = new Node("Aarschot",50.964572,4.833390);
    Node heverlee314 = new Node("E314 Heverlee",50.860518,4.344722);
    
    //added nodes for e34
    Node turnhout = new Node("Turnhout",51.297105,4.912426);
    Node beerse = new Node("Beerse",51.285840,4.856509);
    Node lille = new Node("lille",51.256692,4.804941);
    Node zoersel = new Node("Zoersel",51.246322,4.701183);
    Node oelegem = new Node("Oelegem",51.212037,4.625028);

    graph = new HashMap<Node,ArrayList<Node>>();
    //binnenring
    this.addToMap(graph,noord, oost);
    this.addToMap(graph, noord, oostN);
    this.addToMap(graph,oost, zuid);
    this.addToMap(graph,zuid, centrum);
    this.addToMap(graph,centrum, west);
    this.addToMap(graph,west, beveren);
    //we leave out Liefkenshoektunnel
    this.addToMap(graph,haven, noord);

    //buitenring
    this.addToMap(graph,noord, haven);
    //we leave out liefkenshoek
    this.addToMap(graph,beveren, west);
    this.addToMap(graph,west, centrum);
    this.addToMap(graph,centrum, zuid);
    this.addToMap(graph,zuid, oost);
    this.addToMap(graph, zuid, oostZ);
    this.addToMap(graph,oost, noord);

    //toekomende snelwegen
    this.addToMap(graph,niklaas,west);
    this.addToMap(graph,kontich, zuid);
    this.addToMap(graph,bareel, noord);

    //e313
    this.addToMap(graph,geelO, geelW);
    this.addToMap(graph,geelW, herentalsO);
    this.addToMap(graph,herentalsO, herentalsI);
    this.addToMap(graph,herentalsI, herentalsW);
    this.addToMap(graph,herentalsW, massenhoven);
    this.addToMap(graph,massenhoven, ranst);
    this.addToMap(graph,ranst, wommelgem);
    this.addToMap(graph, wommelgem, oost);
    
    this.addToMap(graph, oostZ, wommelgemZ);
    this.addToMap(graph, oostN, wommelgemN);
    this.addToMap(graph, wommelgemN, wommelgem);
    this.addToMap(graph, wommelgemZ, wommelgem);
    this.addToMap(graph, wommelgem, ranst);
    this.addToMap(graph, ranst, massenhoven);
    this.addToMap(graph, massenhoven, herentalsW);
    this.addToMap(graph, herentalsW, herentalsI);
    this.addToMap(graph, herentalsI, herentalsO);
    this.addToMap(graph, herentalsO, geelW);
    this.addToMap(graph,geelW, geelO);
    
    //e34
    this.addToMap(graph, ranst, oelegem);
    this.addToMap(graph, oelegem, zoersel);
    this.addToMap(graph, zoersel, lille);
    this.addToMap(graph, lille, beerse);
    this.addToMap(graph, beerse, turnhout);
    
    this.addToMap(graph, oelegem, ranst);
    this.addToMap(graph, zoersel, oelegem);
    this.addToMap(graph, lille, zoersel);
    this.addToMap(graph, beerse, lille);
    this.addToMap(graph, turnhout, beerse);

    //brusselse ring

    this.addToMap(graph, halle, bijgaarden);
    this.addToMap(graph, bijgaarden, strombeek);
    this.addToMap(graph, strombeek, machelen);
    this.addToMap(graph, machelen, zaventem);
    this.addToMap(graph, zaventem, woluwe);
    this.addToMap(graph, woluwe, leonard);

    this.addToMap(graph, leonard, woluwe);
    this.addToMap(graph, woluwe, zaventem);
    this.addToMap(graph, zaventem, machelen);
    this.addToMap(graph, machelen, strombeek);
    this.addToMap(graph, strombeek, bijgaarden);
    this.addToMap(graph, bijgaarden, halle);

    //inkomend
    this.addToMap(graph, aalst, bijgaarden);
    this.addToMap(graph, meise, strombeek);
    this.addToMap(graph, mechelenN, machelen);
    this.addToMap(graph, heverlee40, woluwe);
    this.addToMap(graph, haasrode, heverlee);
    this.addToMap(graph, wilsele, heverlee);
    this.addToMap(graph, overijse, leonard);
    this.addToMap(graph, heverlee, heverlee40);

    //uitgaand
    this.addToMap(graph, woluwe, woluwe40);
    this.addToMap(graph, woluwe40, heverlee);
    this.addToMap(graph, heverlee, heverlee314);
    this.addToMap(graph, heverlee314, aarschot);

    //verbinding antwerpen brussel
    this.addToMap(graph, machelen, mechelenN);
    this.addToMap(graph, mechelenN, kontich);
    this.addToMap(graph, zuid, kontich);
    this.addToMap(graph, kontich, mechelenN);

    //added for connectivity:
    this.addToMap(graph, noord, bareel);
    this.addToMap(graph, west, niklaas);
    this.addToMap(graph, bijgaarden, aalst);
    this.addToMap(graph, strombeek, meise);
    this.addToMap(graph, heverlee, haasrode);
    this.addToMap(graph, aarschot, wilsele);
    this.addToMap(graph, heverlee314, heverlee);

    Set<Node> reach = new HashSet<Node>(this.graph.keySet());
    reach.remove(wommelgemZ);
    reach.remove(wommelgemN);
    reach.remove(oostZ);
    reach.remove(oostN);
    reach.remove(woluwe40);
    this.reachables = new ArrayList<Node>(reach);
    Collections.sort(this.reachables, new Comparator<Node>(){

      @Override
      public int compare(Node o1, Node o2) {
        return o1.getName().compareTo(o2.getName());
      }
      
    });
    return;
  }

  public Node findClosest(Node currentLoc) {

    Node nearest = null;
    double nearestDist = Double.MAX_VALUE;
    for(Node n: this.reachables) {
      if(n.getDistance(currentLoc) < nearestDist) {
        nearest = n;
        nearestDist = n.getDistance(currentLoc);
      }
    }
    return nearest;
  }
  
  public Set<Node> getNodes() {
      return this.graph.keySet();
  }
  
  public List<Node> getReachableNodes() {
    return this.reachables;
  }

  public List<Node> findPath(Node start, Node end) {
    ArrayList<ArrayList<Node>> path = new ArrayList<ArrayList<Node>>();
    Set<Node> visited = new HashSet<Node>();
    ArrayList<Node> temp = new ArrayList<Node>();
    temp.add(start);
    path.add(temp);
    visited.add(start);

    while(path.size() != 0 ) {
      System.out.println(path);
      int lastIndex = path.size()-1;
      if(path.get(lastIndex).contains(end)) {
        System.out.println("the end is near");
        //reconstruct path
        System.out.println("we will return");
        return reconstructPath(path, end);
      } else {
        ArrayList<Node> last = path.get(lastIndex);
        //possibility1: the last list is empty
        if(last.isEmpty()) {
          System.out.println("the last list is empty");
          path.remove(lastIndex);
          lastIndex--;
          //remove last node.
          if(lastIndex >= 0) {
            path.get(lastIndex).remove(path.get(lastIndex).size()-1);
          }
        } else {
          Node lastNode = last.get(last.size()-1);
          //possibility 2: the last node is not an end node
          if(this.graph.containsKey(lastNode)) {
            System.out.println("the last node is not an end node");
            ArrayList<Node> newAdd = new ArrayList<Node>();
            for(Node n:graph.get(lastNode)) {
              if(!visited.contains(n)) {
                newAdd.add(n);
              }
            }
            visited.addAll(newAdd);
            if(newAdd.isEmpty()) {
              last.remove(last.size()-1);
            } else {
              path.add(newAdd);
            }
          } else {
            //possibility 3: The last one is an end node
            System.out.println(lastNode + "it is an endnode");
            last.remove(last.size()-1);
          }
        }
      }
    }
    throw new IllegalStateException("No route found");
  }

  private List<Node> reconstructPath(ArrayList<ArrayList<Node>> path, Node end) {
    System.out.println("reconstructing");
    List<Node> returnList = new ArrayList<Node>();
    for(int i = 0; i <= path.size() - 2; i++) {
      ArrayList<Node> lst = path.get(i);
      returnList.add(lst.get(lst.size() - 1));
    }
    returnList.add(end);
    return returnList;
  }

  public String test() {
    StringBuffer buffer = new StringBuffer();
    buffer.append("finding route between ranst and aarschot\n");
    Node ranst = new Node("Ranst",51.201665,4.575453);
    Node aarschot = new Node("Aarschot",50.964572,4.833390);

    List<Node> lst = findPath(ranst,aarschot);
    buffer.append(lst.toString() + "\n");
    
    buffer.append("finding route between aalst and ranst\n");
    Node aalst = new Node("E40 Aalst",50.857923,4.634734);

    lst = findPath(aalst, ranst);
    buffer.append(lst.toString());
    return buffer.toString();

  }


}


