package com.example.visualcamp.realm;

import io.realm.RealmObject;

/**
 * Created by visualcamp on 2017. 10. 4..
 */

public class Data extends RealmObject {
  public String q;
  public String a;

  public String getQ() {
    return q;
  }

  public void setQ(String q) {
    this.q = q;
  }

  public String getA() {
    return a;
  }

  public void setA(String a) {
    this.a = a;
  }
}
