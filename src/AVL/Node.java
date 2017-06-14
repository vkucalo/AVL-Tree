package AVL;

/**
 * Created by Viktor on 8.6.2017..
 */
public class Node<T extends Comparable<T>> {
    T key;
    Node left;
    Node right;
    int height;

    public Node(T value){
        key = value;
        left = null;
        right = null;
        height = 0;
    }
    public Node(Node<T> node){
        this.key = node.key;
        this.right = node.right;
        this.left = node.left;
        this.height = 0;

    }
}
