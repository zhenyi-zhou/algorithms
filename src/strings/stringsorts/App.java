package strings.stringsorts;

public class App {
  private static String[] testData = {
    "bed", "bug", "dad", "yes", "zoo",
    "now", "for", "tip", "ilk", "dim",
    "tag", "jot", "sob", "nob", "sky",
    "hut", "men", "egg", "few", "jay",
    "owl", "joy", "rap", "gig", "wee",
    "was", "wad", "fee", "tap", "tar",
    "dug", "jam", "all", "bad", "yet"
  };
  
  public static void main(String[] args) {
    int n = testData.length;
    int w = testData[0].length();
    
    System.out.println("Least Significant Digit First");
    String[] out = testData.clone();
    LSD.sort(out, w);
    for (int i = 0; i < n; i++) {
      System.out.print(out[i] + " ");
    }
    System.out.println();
    
    System.out.println("Most Significant Digit First");
    out = testData.clone();
    MSD.sort(out);
    for (int i = 0; i < n; i++) {
      System.out.print(out[i] + " ");
    }
    System.out.println();
    
    System.out.println("Three-Way String Quicksort");
    out = testData.clone();
    Quick3String.sort(out);
    for (int i = 0; i < n; i++) {
      System.out.print(out[i] + " ");
    }
    System.out.println();
  }
}