package a3;

public class Main {

    public static void main(String[] args){
        /*
         * you will test your own bst implementation in here
         *
         * In order to test you should create TreeMap objects,
         * put data into them, take data out, look for values stored
         * in it, checking size, and looking at the Nodes to see if they
         * are all linked up correctly as a BST.
         *
         * A simple example is shown below
         *
         */
        BST bst = new BSTImpl();
        bst.insert("b");

        System.out.println(bst.insert(null));
        //System.out.println(bst.getRoot());
        //System.out.println(bst.getRoot().getLeft());
        //System.out.println(bst.getRoot().getRight());
        System.out.println(bst.get("def")==null);




    }
}
