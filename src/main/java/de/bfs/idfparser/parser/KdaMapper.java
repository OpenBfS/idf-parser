package de.bfs.idfparser.parser;

import java.util.HashMap;
import java.util.Map;

public class KdaMapper {
  private static Map<Integer, Integer> map;
  static {
    map = new HashMap<Integer, Integer>();
    map.put(1, 4);
    map.put(2, 1);
    map.put(3, 2);
    map.put(5, 6);
  };
  public static int translate(int from) {
    if (map.containsKey(from)) {
      return map.get(from);
    }
    return -1;
  }
}
