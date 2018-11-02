import org.junit.*;

public class TreeTest {
  Tree tree = new Tree();

  @Test
  public void TreeTest() throws Exception {
    tree.create("/", "root");
    tree.create("/a", "va");
    tree.watch("/a", new ValueCallBack());
    tree.create("/a/c", "vc");
    tree.watch("/a/c", new ValueCallBack());
    tree.create("/a/c/g", "foo");
    //Set value for /a/c/g will call the two callbacks registered on /a and /a/c
    tree.set_value("/a/c/g", "foo2");

    Assert.assertEquals("root", tree.get_value("/"));
    Assert.assertEquals("va", tree.get_value("/a"));
    Assert.assertEquals("vc", tree.get_value("/a/c"));
    Assert.assertEquals("foo2", tree.get_value("/a/c/g"));

    try {
      tree.create("/a/c/g", "foo3");
    } catch (Exception e) {
      Assert.assertEquals("Path already exist.", e.getMessage());
    }

    try {
      tree.create("/f/g/h", "bar");
    } catch (Exception e) {
      Assert.assertEquals("Path Invalid.", e.getMessage());
    }

    try {
      tree.set_value("/f/g/h", "bar");
    } catch (Exception e) {
      Assert.assertEquals("Path Valid in function set_value().", e.getMessage());
    }

    try {
      tree.get_value("/f/g/h");
    } catch (Exception e) {
      Assert.assertEquals("Path Valid in function get_value().", e.getMessage());
    }
  }
}