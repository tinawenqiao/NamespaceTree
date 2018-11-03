public class ValueCallbackSample implements ValueCallbackInterface {
  public void onValueChanged(String path, String value) {
    System.out.println("Value of path:" + path + " is changed to:" + value);
  }
}
