package be.ttown.reistijd;

import java.util.HashMap;
import java.util.concurrent.Callable;

public class BruScraper implements Callable<HashMap<String,Reistijd>> {

  @Override
  public HashMap<String, Reistijd> call() throws Exception {
    return new HashMap<String,Reistijd>();
  }

}
