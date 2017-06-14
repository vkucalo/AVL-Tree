package AVL;

import static java.lang.Math.max;

/**
 * Created by Viktor on 8.6.2017..
 */
public class AVLTree<T extends Comparable<T>>{

    private Node root;

    public AVLTree(T root){
        this.root = new Node(root);
    }


    public void insert(Node node){
        insert(root, node);
    }
    public Node<T> insert(Node current, Node node){
        if(current == null){
            current = node;
            return current;
        }
        else if(current.key.compareTo(node.key) < 0){
            current.right = insert(current.right, node);
        }
        else if(current.key.compareTo(node.key) > 0){
            current.left = insert(current.left, node);
        }

        calculateHeight(current);
        if(current.left == null){
            if(current.right == null){
                return current;
            }
            else{
                if(current.right.height > 0){
                    current = balance(current);
                }
            }
        }
        else if(current.right == null){
            if(current.left.height > 0){
                current = balance(current);
            }
        }
        else if (Math.abs(current.left.height - current.right.height) > 1){
            current = balance(current);
        }
        return current;
    }
    public void calculateHeight(Node node){
        int height = 0;
        if(node.left != null){
            height = node.left.height + 1;
        }
        if(node.right != null) {
            height = max(height, node.right.height + 1);
        }
        node.height = height;
    }

    public Node<T> balance(Node pivot){
        if(pivot.left == null){
            if(pivot.right.left != null) {
                pivot.right = rotateRight(pivot.right);
            }
            pivot = rotateLeft(pivot);
        }
        else if (pivot.right == null){
            if(pivot.left.right != null) {
                pivot.left = rotateLeft(pivot.left);
            }
            pivot = rotateRight(pivot);
        }
        else{
            if(pivot.left.height - pivot.right.height > 1){
                pivot = rotateRight(pivot);
            }
            else{
                pivot = rotateLeft(pivot);
            }
        }
        calculateHeight(pivot);
        return pivot;
    }
    public void inOrder(Node node){
        if(node == null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.key);
        inOrder(node.right);
    }
    public void postOrder(Node node){

    }

    public Node<T> rotateRight(Node pivot){
       boolean check = (pivot == root);
       Node temp = new Node(pivot);
       temp.left = pivot.left.right;
       pivot = pivot.left;
       pivot.right = temp;
       if(check){
           root = pivot;
       }
       calculateHeight(temp);
       return pivot;
    }

    public Node<T> rotateLeft(Node pivot){
        boolean check = pivot == root;
        Node temp = new Node(pivot);
        pivot = pivot.right;
        temp.right = pivot.left;
        pivot.left = temp;
        if(check == true){
            root = pivot;
        }
        calculateHeight(temp);
        return pivot;
    }

    public static void main(String []args){
        AVLTree<Integer> tree = new AVLTree<>(100);
        tree.insert(new Node(90));
        tree.insert(new Node(95));
        //        tree.insert(tree.root, new Node(28));
        tree.insert(new Node(12));
        tree.insert(new Node(5));
        tree.insert(new Node(3));
        tree.insert(new Node(4));
//        tree.insert(tree.root, new Node(2));
//        tree.insert(tree.root, new Node(1));
        tree.inOrder(tree.root);
        System.out.println("root = " +tree.root.key);



    }
}
