package strings.tries;

public class App {
  private static String[] testData = {
    "she", "sells", "sea", "shells", "by", "the", "sea", "shore"
  };
  
  public static void main(String[] args) {
    System.out.println("Multiway Trie");
    TrieST<Integer> st = new TrieST<Integer>();
    for (int i = 0; i < testData.length; i++) {
      st.put(testData[i], i);
    }

    for (String key : st.keys()) {
      System.out.println(key + " " + st.get(key));
    }
    System.out.println("Longest prefix of \"shellsort\"");
    System.out.println(st.longestPrefixOf("shellsort"));
    System.out.println("Keys with prefix \"shor\"");
    for (String s : st.keysWithPrefix("shor")) {
      System.out.println(s);
    }
    System.out.println("Keys that match \".he.l.\"");
    for (String s : st.keysThatMatch(".he.l.")) {
      System.out.println(s);
    }
    
    System.out.println("Ternary Search Trie");
    TST<Integer> tst = new TST<Integer>();
    for (int i = 0; i < testData.length; i++) {
      tst.put(testData[i], i);
    }

    for (String key : tst.keys()) {
      System.out.println(key + " " + tst.get(key));
    }
    System.out.println("Longest prefix of \"shellsort\"");
    System.out.println(tst.longestPrefixOf("shellsort"));
    System.out.println("Keys with prefix \"shor\"");
    for (String s : tst.keysWithPrefix("shor")) {
      System.out.println(s);
    }
    System.out.println("Keys that match \".he.l.\"");
    for (String s : tst.keysThatMatch(".he.l.")) {
      System.out.println(s);
    }
  }
}