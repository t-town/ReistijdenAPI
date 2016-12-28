package be.ttown.reistijd;


public class Node {
  
  private double xCo;
  private double yCo;
  private String name;
  private String verkeerscentrumName;
  
  public double getxCo() {
    return xCo;
  }
  public double getyCo() {
    return yCo;
  }
  public String getName() {
    return name;
  }
  public String getVerkeerscentrumName() {
    return verkeerscentrumName;
  }

  public Node(String name, String verkeerscentrumName, double xCo, double yCo) {
    this.name = name;
    this.verkeerscentrumName = verkeerscentrumName;
    this.xCo = xCo;
    this.yCo = yCo;
  }

  
  public Node(String name, double xCo, double yCo) {
    this.name = name;
    this.verkeerscentrumName = name;
    this.xCo = xCo;
    this.yCo = yCo;
  }
  
  public Node() {
    
  }
  
  
  
  public void setxCo(double xCo) {
    this.xCo = xCo;
  }
  public void setyCo(double yCo) {
    this.yCo = yCo;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setVerkeerscentrumName(String verkeerscentrumName) {
    this.verkeerscentrumName = verkeerscentrumName;
  }
  public double getDistance(Node other) {
    return Math.sqrt((other.xCo-this.xCo)*(other.xCo-this.xCo) + (other.yCo-this.yCo)*(other.yCo-this.yCo));
  }
  

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((verkeerscentrumName == null) ? 0 : verkeerscentrumName.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Node other = (Node) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (verkeerscentrumName == null) {
      if (other.verkeerscentrumName != null)
        return false;
    } else if (!verkeerscentrumName.equals(other.verkeerscentrumName))
      return false;
    return true;
  }
  @Override
  public String toString() {
    return "Node [name=" + name + "," + verkeerscentrumName + "]";
  }
  
  
}
