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
        bst.insert("hello");
        bst.insert("world");
        bst.insert("b");
        bst.insert("enough");
        bst.insert("aaaa");
        bst.insert("comp210");
        bst.insert("flamingo");
        bst.insert("ab");
        bst.insert("aa");
        bst.insert("a");

        bst.insert("jump");
        bst.insert("zealous");
        bst.remove("zealous");
        System.out.println(bst.height());
        System.out.println(bst.getRoot().getValue());
        System.out.println(bst.getRoot().getLeft().getValue());
        System.out.println(bst.getRoot().getRight().getValue());
        System.out.println(bst.findMin());
        System.out.println(bst.findMax());
        System.out.println(bst.size());
        System.out.println(bst.isFull());
        System.out.println(bst.contains("c"));


    }
}
