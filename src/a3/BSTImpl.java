package a3;

public class BSTImpl implements BST {

    private Node root;
    private int size=0;
    private BSTImpl subTree;

    public BSTImpl() {
        root = null;
        size = 0;
    }

    public BSTImpl(String s) {
        root = new NodeImpl(s);
        size = 0;
    }

    // The implementation of "height" is given to you below
    // it is here to illustrate for you how to set up
    // the method implementation as recursion.
    // You should follow this pattern for implementing the other recursive methods
    // by adding your own private recursive methods

    @Override
    public int height() { // public interface method signature
        // this method is the public interface method
        // it is not recursive, but it calls a recursive
        // private method and passes it access to the tree cells
        return height_recursive(this.root);
    }
    private int height_recursive(Node c) {
        // inner method with different signature
        // this helper method uses recursion to traverse
        // and process the recursive structure of the tree of cells
        if (c.getValue()==null) return -1;
        int lht = height_recursive(c.getLeft());
        int rht = height_recursive(c.getRight());
        return Math.max(lht,rht) + 1;
    }

    @Override
    public Node getRoot() {//returns the root (null if the root is null)
        if(root==null){
            Node nullNode=new NodeImpl(null);
            return nullNode;
        }
        else{return root;}
    }
    

    @Override
    public String insert(String value) {//calls the recursive method to insert a string into the bst
        if(value==null){return null;}
        else{
        NodeImpl node=new NodeImpl(value);
        insert_r(node);
        size++;
        return value;}
    }

    private void insert_r(NodeImpl node){//compares the node to the root of the tree, and it will call itself if
        //the node needs to be compared to a subtree.
        if(root==null){root=node;}
        else if(root.getValue().compareTo(node.getValue())<0){
            if (root.getRight().getValue()==null){
                root.setRight(node);
            }
            else {
                subTree=new BSTImpl("");
                subTree.setRoot(root.getRight());
                subTree.insert_r(node);
            }
        }
        else if(root.getValue().compareTo(node.getValue())>0){
            if (root.getLeft().getValue()==null){
                root.setLeft(node);
            }
            else {
                subTree=new BSTImpl("");
                subTree.setRoot(root.getLeft());
                subTree.insert_r(node);
            }
        }
        else{
            Node savedNode=new NodeImpl();
            savedNode=root.getRight();
            root.setRight(node);
            node.setRight(savedNode);
        }
    }

    // remove implementation given to you, do NOT change
    @Override
    public void remove(String value) {
        remove_r(value,this.root);
        size--;
    }
    private Node remove_r(String k, Node c) {
        if (c==null) return null; // item not found, nothing to do

        // now we know we have a real node to examine
        int cflag = k.compareTo(c.getValue());

        if (cflag<0) { // k is smaller than node
            c.setLeft(remove_r(k,c.getLeft()));
            return c;
        }
        else
        if (cflag>0) { // k is larger than node
            c.setRight(remove_r(k,c.getRight()));
            return c;
        }
        else //cflag==0
        { // found it... now figure out how to rearrange

            // cases
            if (c.getLeft().getValue()==null && c.getRight().getValue()==null) { c = null; } // leaf
            else if (c.getLeft().getValue()==null && c.getRight().getValue()!=null) { c = c.getRight(); } // RC only
            else if (c.getLeft().getValue()!=null && c.getRight().getValue()==null) { c = c.getLeft(); } // LC only
            else { // 2 children
                Node max = maxCell(c.getLeft());
                c.setValue(max.getValue());
                c.setLeft(remove_r(c.getValue(), c.getLeft()));   // recurse
            }
            return c;
        }

    }

    private Node maxCell(Node c) { // this is used in remove too
        if (c.getRight().getValue()==null) return c;
        return maxCell(c.getRight());
    } ;

    @Override
    public boolean isFull() {//calls the recursive method to decide if the tree is full
        return isFull_r();
    }
    private boolean isFull_r(){//returns true if the tree is empty and uses recursion to determine if every subtree is full
        if(root==null){return true;}
        else if(root.children()==1){
            return false;
        }
        else if(root.children()==0){
            return true;
        }
        else{
            subTree=new BSTImpl("");
            subTree.setRoot(root.getLeft());
            boolean leftIsFull=subTree.isFull_r();
            subTree.setRoot(root.getRight());
            return leftIsFull&&subTree.isFull_r();
        }
    }

    @Override
    public String findMin() {//calls the recursive method to find the minimum string in the tree

        return findMin_r();
    }
    private String findMin_r(){//keeps going left until it reaches the leftmost leaf
        if(root==null){return null;}
        else if(root.getLeft().getValue()==null){
            return root.getValue();
        }
        else{
            subTree=new BSTImpl("");
            subTree.setRoot(root.getLeft());
            return subTree.findMin_r();
        }
    }

    @Override
    public String findMax() {//calls the recursive method to find the maximum string in the tree
        return findMax_r();
    }
    private String findMax_r(){//keeps going right until it reaches the rightmost leaf
        if(root==null){return null;}
        else if(root.getRight().getValue()==null){
            return root.getValue();
        }
        else{
            subTree=new BSTImpl("");
            subTree.setRoot(root.getRight());
            return subTree.findMax_r();
        }
    }

    @Override
    public boolean contains(String s) {//calls the recursive method to determine if the tree contains a certain string
        if(s==null){return false;}
        else if(root==null){return false;}
        else{return contains_r(s);}
    }
    private boolean contains_r(String s){//compares the string with the root, and if the strings are different, it
        //calls itself on the appropriate subtree. If there is no subtree, then it returns false.

        if(s.equals(root.getValue())){return true;}

        else if(s.compareTo(root.getValue())>0){
            if(root.getRight().getValue()==null){return false;}
            else {
                subTree=new BSTImpl("");
                subTree.setRoot(root.getRight());
                return subTree.contains_r(s);
            }
        }
        else{
            if(root.getLeft().getValue()==null){return false;}
            else {
                subTree=new BSTImpl("");
                subTree.setRoot(root.getLeft());
                    return subTree.contains_r(s);

            }
        }
    }

    @Override
    public Node get(String s) {//If the tree doesn't contain s, then it returns null. Otherwise, it runs its recursive method
        if(!contains(s)){return null;}
        else{Node node= get_r(s);
        if(node.getValue()==null){return null;}
        else{return node;}
        }
    }
    private Node get_r(String s){//checks if the root is s, and if it isn't, then it determines which subtree s would be in and runs itself on that subtree.
        if(s.equals(root.getValue())){return root;}
        else if(s.compareTo(root.getValue())>0){
            subTree=new BSTImpl("");
            subTree.setRoot(root.getRight());
            return subTree.get_r(s);
        }
        else{
            subTree=new BSTImpl("");
            subTree.setRoot(root.getLeft());
            return subTree.get_r(s);


        }
    }

    @Override
    public int size() {//returns the size attribute of the tree, which increases by one each time a node is inserted
        return this.size;
    }


    private void setRoot(Node node){//sets the root of a tree (used as a helper method to deal with subtrees)
        root=node;
    }
}
