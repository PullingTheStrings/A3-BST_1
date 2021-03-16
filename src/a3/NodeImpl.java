package a3;

public class NodeImpl implements Node {

    private Node left;
    private Node right;
    private String value;

    public NodeImpl(NodeImpl left0, NodeImpl right0, String value) {
        left = left0;
        right = right0;
        this.value = value;
    }
    public NodeImpl(){

    }

    public NodeImpl(String value) {
        left = null;
        right = null;
        this.value = value;
    }
    @Override
    public Node getLeft() {

        if(left==null){
            Node nullNode=new NodeImpl(null);
            return nullNode;}
        else{return left;}
    }

    @Override
    public Node getRight() {
        if(right==null){
            Node nullNode=new NodeImpl(null);
            return nullNode;}
        else{return right;}
    }

    @Override
    public void setLeft(Node left0) {
        left = left0;
    }

    @Override
    public void setRight(Node right0) {
        right = right0;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int children(){
        if(left==null&&right==null){
            return 0;
        }
        else if(left==null||right==null){
            return 1;
        }
        else{
            return 2;
        }
    }


}
