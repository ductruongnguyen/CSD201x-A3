import entity.Product;
import util.MyBSTree;
import util.Node;

public class MyProduct {
    
    //a list of products
    MyBSTree tree;

    public MyProduct() {
        tree = new MyBSTree();
    }
    
    //1.1 input and insert a new product to tree
    public void insert(Product product) {
        if(tree.getRoot() == null) {
            tree.setRoot(new Node<Product>(product));
            System.out.println("Root node " + product.getCode() + " is created!");
        }
        tree.insert(tree.getRoot(), product);
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
    public void search(String code) {
        Node<Product> found = tree.search(tree.getRoot(), code);
        if(found == null) {
            System.out.println("There are no matching products");
            return;
        }
        tree.visit(found);
    }
    //1.5 delete a product by product code
    public void delete(String code) {
        Node<Product> found = tree.search(tree.getRoot(), code);
        if (found == null) {
            System.out.println("There are no matching products");
            return;
        }
        tree.delete(code);
        System.out.println("Delete product with " + code + " successfully!");
    }
    //1.6 simply balancing a tree
    public void balance() {
        tree.balance();
    }
    //1.7 count the number of products in the tree
    public int size() {
        return tree.count(tree.getRoot());
    }
}
