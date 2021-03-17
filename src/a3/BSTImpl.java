package a3;

public class BSTImpl implements BST {

    private Node root;
    private int size=0;
    //private BSTImpl subTree;

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
        if (c==null) return -1;
        int lht = height_recursive(c.getLeft());
        int rht = height_recursive(c.getRight());
        return Math.max(lht,rht) + 1;
    }

    @Override
    public Node getRoot() {//returns the root (null if the root is null)
        if(root==null){

            return null;
        }
        else{return root;}
    }
    

    @Override
    public String insert(String value) {//calls the recursive method to insert a string into the bst
        if(value==null){return null;}
        else if(root==null){
            NodeImpl node=new NodeImpl(value);
            root=node;
            size++;
            return value;
        }
        else{
        NodeImpl node=new NodeImpl(value);
        root.insert_r(node);
        size++;
        return value;}
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
            if (c.getLeft()==null && c.getRight()==null) { c = null; } // leaf
            else if (c.getLeft()==null && c.getRight()!=null) { c = c.getRight(); } // RC only
            else if (c.getLeft()!=null && c.getRight()==null) { c = c.getLeft(); } // LC only
            else { // 2 children
                Node max = maxCell(c.getLeft());
                c.setValue(max.getValue());
                c.setLeft(remove_r(c.getValue(), c.getLeft()));   // recurse
            }
            return c;
        }

    }

    private Node maxCell(Node c) { // this is used in remove too
        if (c.getRight()==null) return c;
        return maxCell(c.getRight());
    } ;

    @Override
    public boolean isFull() {//calls the recursive method to decide if the tree is full
        if(root==null){return true;}
        else{return root.isFull_r();}
    }


    @Override
    public String findMax() {//calls the recursive method to find the maximum string in the tree
        if(root==null){return null;}
        else{return root.findMax_r();}
    }


    @Override
    public String findMin() {//calls the recursive method to find the minimum string in the tree
        if(root==null){return null;}
        else{return root.findMin_r();}
    }

    @Override
    public boolean contains(String s) {//calls the recursive method to determine if the tree contains a certain string
        if(s==null){return false;}
        else if(root==null){return false;}
        else{return root.contains_r(s);}
    }


    @Override
    public Node get(String s) {//If the tree doesn't contain s, then it returns null. Otherwise, it runs its recursive method
        if(!contains(s)){return null;}
        else{Node node= root.get_r(s);
        return node;
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
