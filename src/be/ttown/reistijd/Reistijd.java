package be.ttown.reistijd;

public class Reistijd {
  
  private int filevrij;
  private int vertraging;
  private int nu;
  private String evolutie;
  private Node from;
  private Node to;
  
  public Reistijd(int filevrij, int nu, int vertraging, String evolutie) {
    this.filevrij = filevrij;
    this.vertraging = vertraging;
    this.nu = nu;
    this.evolutie = evolutie;
  }
  
  public void addFromTo(Node start, Node end) {
    this.from = start;
    this.to = end;
  }
  


  @Override
  public String toString() {
    return "Reistijd [filevrij=" + filevrij + ", vertraging=" + vertraging + ", nu=" + nu
        + ", evolutie=" + evolutie + ", from=" + from + ", to=" + to + "]";
  }

  public int getFilevrij() {
    return filevrij;
  }

  public void setFilevrij(int filevrij) {
    this.filevrij = filevrij;
  }

  public int getVertraging() {
    return vertraging;
  }

  public void setVertraging(int vertraging) {
    this.vertraging = vertraging;
  }

  public int getNu() {
    return nu;
  }

  public void setNu(int nu) {
    this.nu = nu;
  }

  public String getEvolutie() {
    return evolutie;
  }

  public void setEvolutie(String evolutie) {
    this.evolutie = evolutie;
  }

  public Node getFrom() {
    return from;
  }

  public void setFrom(Node from) {
    this.from = from;
  }

  public Node getTo() {
    return to;
  }

  public void setTo(Node to) {
    this.to = to;
  }
  

}
