package util;

import com.sun.org.apache.xpath.internal.objects.XNodeSet;
import entity.Product;

import java.util.ArrayList;
import java.util.List;

public class MyBSTree {

    Node<Product> root;

	public Node<Product> getRoot() {
		return root;
	}

	public void setRoot(Node<Product> root) {
		this.root = root;
	}

	public MyBSTree() {
        root = null;
    }

    public void visit(Node<Product> p) {
        System.out.println(p.info);
    }

    public boolean isEmpty() {
    	return root == null;
    }

    public void inOrder(Node<Product> p) {
        if(p == null) return;
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }

    public int count(Node<Product> root) {
    	if(isEmpty())
    		return 0;

		int count = 1;

    	if(root.left != null)
    		count += count(root.left);
    	if(root.right != null)
    		count += count(root.right);

    	return count;
    }
    
    //breadth-first traverse a tree
    public void BFT() {
    	if(root == null) return;
    	MyQueue q = new MyQueue();
    	q.enqueue(root);
    	Node<Product> p;

    	while(!q.isEmpty()) {
    		p = (Node<Product>) q.dequeue();
	    	if(p.left != null)
	    		q.enqueue(p.left);
	    	if(p.right != null)
	    		q.enqueue(p.right);
	    	visit(p);
    	}
    }

    public Node<Product> insert(Node<Product> root, Product product) {
    	if(root == null) {
    		root = new Node<Product>(product);
			System.out.println("Node " + product.getCode() + " inserted!");
    		return root;
    	}
    	if(product.getCode().compareTo(root.info.getCode()) < 0) {
			root.left = insert(root.left, product);
		} else if (product.getCode().compareTo(root.info.getCode()) > 0){
			root.right = insert(root.right, product);
		}
    	return root;
    }

    //balance a tree
    //step 1: traverse inorder tree and copy all item on tree to an arraylist
    //step 2: insert all items of list to a tree
    private void buildArray(List<Node<Product>> list, Node<Product> p) {
    	if(p == null) {
    		return;
    	}
    	buildArray(list, p.left);
    	list.add(p);
    	buildArray(list, p.right);
    }

    //step 2:
    private void balance(List<Node<Product>> list, int f, int l) {
    	if(f > l) {
    		return;
    	}
    	int  mid = (f + l)/2;
    	Node<Product> p = list.get(mid);
    	insert(root, p.info);
    	balance(list, f, mid - 1);
    	balance(list, mid + 1, l);
    }

    public void balance() {
        List<Node<Product>> list = new ArrayList<Node<Product>>();
        buildArray(list, root);
        MyBSTree tree = new MyBSTree();
        tree.balance(list, 0, list.size() - 1);
		System.out.println("Tree is balanced!");
    }

    //search a Node of tree by product code
	public Node<Product> search(Node<Product> p, String code) {

		if (p == null) return (null);

		if (p.info.getCode().equals(code)) return (p);

		if (code.compareTo(p.info.getCode()) < 0)
			return (search(p.left, code));
		else
			return (search(p.right, code));
	}
 
    //delete a node by a given product code
    public void delete(String code) {
    	delete(this.root, code);
    }

    Node<Product> delete(Node<Product> root, String code) {

		if(code.compareTo(root.info.getCode()) < 0) {
			root.left = delete(root.left, code);
		}
		else if(code.compareTo(root.info.getCode()) > 0) {
			root.right = delete(root.right, code);
		}
		else {
			if(root.left == null && root.right == null) {
				return null;
			}
			else if(root.left == null) {
				return root.right;
			}
			else if(root.right == null) {
				return root.left;
			}
			else {
				Product minProduct = minValue(root.right);
				root.info = minProduct;
				root.right = delete(root.right, minProduct.getCode());
			}
		}
		return root;
	}

	private Product minValue(Node<Product> node) {
		if(node.left != null) {
			return minValue(node.left);
		}
		return node.info;
	}
}
