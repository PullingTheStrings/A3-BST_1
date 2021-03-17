package a3;

public interface Node {

    Node getLeft();
    Node getRight();
    void setLeft(Node left);
    void setRight(Node right);
    String getValue();
    void setValue(String value);
    int children();
    void insert_r(Node node);
    boolean isFull_r();
    String findMin_r();
    String findMax_r();
    boolean contains_r(String s);
    Node get_r(String s);
}
