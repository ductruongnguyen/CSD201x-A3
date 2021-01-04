import entity.Product;
import util.MyBSTree;

public class MyProduct {
    
    //a list of products
    MyBSTree tree;

    public MyProduct() {
        tree = new MyBSTree();
    }
    
    //1.1 input and insert a new product to tree
    public void insert(Product product) {
        tree.insert(product);
    }
    
    //1.2 in-order traverse
    public void inOrder() {
        tree.inOrder(tree.getRoot());
    }
    //1.3 BFT a tree
    public void BFT() {
        tree.BFT();
    }
    //1.4 search a product by product code
    public void search() {
        throw new UnsupportedOperationException("Remove this line and implement your code here!");
    }
    //1.5 delete a product by product code
    public void delete() {
        throw new UnsupportedOperationException("Remove this line and implement your code here!");
    }
    //1.6 simply balancing a tree
    public void balance() {
        throw new UnsupportedOperationException("Remove this line and implement your code here!");
    }
    //1.7 count the number of products in the tree
    public int size() {
        throw new UnsupportedOperationException("Remove this line and implement your code here!");
    }
}
