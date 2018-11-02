import java.util.Map;

public class Node {
  private String value;
  private ValueCallBack callback;
  private Node parent;
  private Map<String, Node> children;

  public void setValue(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }

  public void setParent(Node node) {
    this.parent = node;
  }

  public Node getParent() {
    return this.parent;
  }

  public void setCallback(ValueCallBack callback) {
    this.callback = callback;
  }

  public ValueCallBack getCallback() {
    return this.callback;
  }

  public Map<String, Node> getChildren() {
    return this.children;
  }

  public void setChildren(Map children) {
    this.children = children;
  }
}
