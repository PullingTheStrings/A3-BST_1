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

      return left;
    }

    @Override
    public Node getRight() {
        return right;
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

    public void insert_r (Node node){//compares the node to itself, and the method will call itself if
        //the node needs to be compared to a child.

        if(value.compareTo(node.getValue())<0){
            if (right==null){
                right=node;
            }
            else {
                right.insert_r(node);
            }
        }
        else if(value.compareTo(node.getValue())>0){
            if (left==null){
                left=node;
            }
            else {
                left.insert_r(node);
            }
        }
        else if(value.compareTo(node.getValue())==0){
            Node savedNode=right;
            right=node;
            right.setRight(savedNode);

        }
        else{
            throw new RuntimeException("Conditionals didn't cover all possibilities");
        }
    }

    public boolean isFull_r(){//uses recursion to determine if every subtree is full

        if(children()==1){
            return false;
        }
        else if(children()==0){
            return true;
        }
        else{
            return left.isFull_r()&&right.isFull_r();
        }
    }
    public String findMin_r(){//keeps going left until it reaches the leftmost leaf

        if(left==null){
            return value;
        }
        else{
            return left.findMin_r();
        }
    }
    public String findMax_r(){//keeps going right until it reaches the rightmost leaf

        if(right==null){
            return value;
        }
        else{
            return right.findMax_r();
        }
    }
    public boolean contains_r(String s){//compares the string with itself, and if the strings are different, the method
        //calls itself on the appropriate child. If there is no child in the correct spot, then it returns false.

        if(s.equals(value)){return true;}

        else if(s.compareTo(value)>0){
            if(right==null){return false;}
            else {
                return right.contains_r(s);
            }
        }
        else{
            if(left==null){return false;}
            else {

                return left.contains_r(s);

            }
        }
    }
    public Node get_r(String s){//checks if the value is s, and if it isn't, then it determines which subtree s would be in and runs itself on that subtree.
        if(s.equals(value)){return this;}//if it's equal to the string, return itself
        else if(s.compareTo(value)>0){//if it's bigger than the root string, recurse on the right child

            return right.get_r(s);
        }
        else if(s.compareTo(value)<0){//if it's smaller than the root string, recurse on the left child
            return left.get_r(s);
        }
        else{
            throw new RuntimeException("Conditionals didn't cover all possibilities");

        }
    }



}
