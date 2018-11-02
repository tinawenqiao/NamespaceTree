public class Main {
  public static void main(String[] args) {
    String str = "a";
    String[] strArray = str.split("/");
    System.out.println("strArray.len = " + strArray.length);
    for (int i = 0; i < strArray.length; i++) {
      System.out.println(strArray[i]);
    }
  }
}
