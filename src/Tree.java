import java.util.HashMap;

public class Tree {
  Node root;

  public Node create(String path, String value) throws Exception {
    if (path.equals("/")) {
      root = new Node();
      root.setValue(value);
      root.setParent(null);
      root.setChildren(new HashMap<String, Node>());
      return root;
    }

    String[] keys = path.split("/");
    if (keys.length < 1) {
      throw new Exception("Path Invalid.");
    }

    Node node = root;
    int depth = keys.length - 1;
    for (int i = 1; i < depth; i++) {
      if (node.getChildren() != null && node.getChildren().containsKey(keys[i])) {
        node = node.getChildren().get(keys[i]);
      } else {
        throw new Exception("Path Invalid.");
      }
    }

    if (node.getChildren() == null) {
      node.setChildren(new HashMap<String, Node>());
    }
    if (node.getChildren().containsKey(keys[depth])) {
      throw new Exception("Path already exist.");
    }

    Node newNode = new Node();
    newNode.setValue(value);
    newNode.setParent(node);
    node.getChildren().put(keys[depth], newNode);

    return newNode;
  }

  public String get_value(String path) throws Exception {
    Node node = getNode(path);
    if (node == null) {
      throw new Exception("Path Valid in function get_value().");
    }

    return node.getValue();
  }

  public void set_value(String path, String value) throws Exception {
    Node node = getNode(path);
    if (node == null) {
        throw new Exception("Path Valid in function set_value().");
    }
    node.setValue(value);

    while(node != null) {
      if (node.getCallback() != null) {
        node.getCallback().onValueChanged(path, value);
      }
      node = node.getParent();
    }
  }

  public Node getNode(String path) {
    String[] keys = path.split("/");
    Node node = root;
    for (int i = 1; i < keys.length; i++) {
      if (node.getChildren() != null && node.getChildren().containsKey(keys[i])) {
        node = node.getChildren().get(keys[i]);
      } else {
        return null;
      }
    }

    return node;
  }

  public void watch(String path, ValueCallbackInterface callback) throws Exception {
    Node node = getNode(path);
    if (node == null) {
      throw new Exception();
    }

    node.setCallback(callback);
  }
}
