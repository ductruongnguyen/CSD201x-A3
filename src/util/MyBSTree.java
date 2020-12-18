package util;

import entity.Product;

import java.util.ArrayList;
import java.util.List;

public class MyBSTree {

    //a root of tree
    Node<Product> root;
    
    public MyBSTree() {
        root = null;
    }

    //visit a node of a tree -> output information of visited node
    public void visit(Node<Product> p) {
        System.out.println(p.info);
    }

    //return true if tree is empty otherwise return false
    public boolean isEmpty() {
    	return root == null;
    }

    //in order a tree
    public void inOrder(Node<Product> p) {
        if(p == null) return;
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }
    
    //count number of products
    public int count(Node<Product> root) {
    	if(isEmpty())
    		return 0;
    	return 1  + count(root.left) + count(root.right);
    }
    
    //breadth-first traverse a tree
    public void BFT() {
    	
    	if(root == null) return; //fix
    	
    	MyQueue q = new MyQueue();
    	
    	q.enqueue(root); //fix

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
    
    //insert a new Product to a tree
    public void insert(Product product) {
    	if(root == null) {
    		root = new Node(product);
    		return;
    	}

    	Node<Product> f, p;

    	p = root;
    	
    	f = null;

    	while(p != null) {
    		
			if(p.info == product){
				System.out.println("The product " + product.getName() + " already exists, no insertion");
				return;
			}

	    	f = p;
	
	    	if(product.getCode().compareTo(p.info.getCode()) < 0)
	
	    	p = p.left;
	
	    	else
	
	    	p = p.right;

    	}

    	if(product.getCode().compareTo(f.info.getCode()) < 0)

    		f.left = new Node(product);

    	else

    		f.right = new Node(product);
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
    	int  mid =(f + l)/2;
    	Node<Product> p = list.get(mid);
    	insert(p.info);
    	balance(list, f, mid - 1);
    	balance(list, mid + 1, l);
    }

    public void balance() {
        List<Node<Product>> list = new ArrayList<Node<Product>>();
        buildArray(list, root);
        MyBSTree tree = new MyBSTree();
        tree.balance(list, 0, list.size() - 1);
        root = tree.root;
    }

    //search a Node of tree by product code
    //return null if given code does not exists
    public Node<Product> search(Node<Product> note, String code, Node<Product> leftParent, Node<Product> rightParent) {
    	
    	String bBcode = note.info.getCode();
    	
    	if(note == null) {
    		return null;
    	}
    	if(bBcode.equalsIgnoreCase(code)) {
    		return note;
    	}
    	if(bBcode.compareTo(code) > 0)
    		return search(note.left, code, note, null);
    	return search(note.right, code, null, note);
    }
 
    //delete a node by a given product code
    public void delete(String code) {
    	//lam sau
    	Node<Product> leftParent = null;
    	Node<Product> rightParent = null;
    	Node<Product> note = search(root, code, leftParent, rightParent);
    	
    	if(note != null) {
    		//case 1
    		if(note.left == null && note.right == null) {
    			if(leftParent != null) {
    				leftParent.left = null;
    			} else {
    				rightParent.right = null;
    			}
    		}
    		//case 2
    		else if(note.left != null && note.right == null) {
    			if(leftParent != null) {
    				leftParent.left = note.left;
    			} else {
    				rightParent.right = note.left;
    			}
    		}
    		
    		else if(note.right != null && note.left == null) {
    			if(leftParent != null) {
    				leftParent.left = note.right;
    			} else {
    				rightParent.right = note.right;
    			}
    		}
    		
    		//case 3
    		else if(note.left != null && note.right != null) {
    			Node<Product> replace = getRightSuccessor(note);
    			note.info = replace.info;
    			
    			delete(replace.info.getCode());
    		}
    	}
    	
    }
    
    public Node<Product> getRightSuccessor(Node<Product> note) {
    	if(note.left == null) {
    		return note;
    	}
    	return getRightSuccessor(note.left);
    }
}
