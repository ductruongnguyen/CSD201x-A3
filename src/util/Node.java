package util;

public class Node<T> {
    public T info;
    public Node<T> left, right;
    
    public Node() {
    }

    public Node(T info, Node<T> left, Node<T> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }
    
    public Node(T info) {
        this(info,null,null);
    }
}
