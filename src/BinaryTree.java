public class BinaryTree<T> {
    Node<T> root;

    public Node<T> findNode(int key) {
        Node focusNode = root;
        while(focusNode.key != key) {
            if(key < focusNode.key) {
                focusNode = focusNode.leftChild;
            } else {
                focusNode = focusNode.rightChild;
            }
            if(focusNode == null)
                return null;
        }
        return focusNode;
    }
    public void addNode(int key, T data) {
        Node n = new Node(data, key);
        if (root == null) {
            root = n;
        } else {
            Node<T> focusNode = root;
            Node<T> parent; //keep track of the parent node to add the new node to
            while(true) {

                parent = focusNode;

                if(key < focusNode.key) {
                    focusNode = focusNode.leftChild;
                    if(focusNode == null) {
                        parent.leftChild = n;
                        return;
                    }
                } else {
                    focusNode = focusNode.rightChild;
                    if(focusNode == null) {
                        parent.rightChild = n;
                        return;
                    }
                }
            }
        }

    }
    public void inOrderTraversal(Node<T> focusNode) {
        if(focusNode!= null) {
            inOrderTraversal(focusNode.leftChild);
            System.out.println(focusNode.data);
            inOrderTraversal(focusNode.rightChild);
        }
    }
    class Node<T> {
        T data;
        int key;
        Node<T> leftChild;
        Node<T> rightChild;

        Node(T data, int key) {
            this.data = data;
            this.key = key;
        }
    }
    public Node<T> getReplacementNode(Node<T> replacedNode) {
        Node<T> replacementParent = replacedNode;
        Node<T> replacement = replacedNode;

        Node<T> focusNode = replacedNode.rightChild;
        while(focusNode!= null) {
            replacementParent = replacement;
            replacement = focusNode;
            focusNode = focusNode.leftChild;
        }
        if(replacement != replacedNode.rightChild) {

            replacementParent.leftChild = replacement.rightChild;
            replacement.rightChild = replacedNode.rightChild;
        }
        return replacement;

    }
    public boolean remove(int key) {
        Node<T> focusNode = root;
        Node<T> parent = root;

        boolean isALeftChild = true;
        while (focusNode.key != key) {
            parent = focusNode;

            if (key < focusNode.key) {
                isALeftChild = true;
                focusNode = focusNode.leftChild;
            } else { //key > focusNode.key
                isALeftChild = false;
                focusNode = focusNode.rightChild;
            }
            if (focusNode == null)
                return false;
        }
        if (focusNode.leftChild == null && focusNode.rightChild == null) {
            if (focusNode == root) {
                root = null;
            } else if (isALeftChild)
                parent.leftChild = null;
            else
                parent.rightChild = null;

        }
        else if(focusNode.rightChild == null) {
            if(focusNode == root)
                root = focusNode.leftChild;
            else if(isALeftChild)
                parent.leftChild = focusNode.leftChild;
            else
                parent.rightChild = focusNode.leftChild;
        }
        else if(focusNode.leftChild == null) {
            if(focusNode == root)
                root = focusNode.rightChild;
            else if(isALeftChild)
                parent.leftChild = focusNode.rightChild;
            else
                parent.rightChild = focusNode.rightChild;
        }
        else {
            Node<T> replacement = getReplacementNode(focusNode);
            if(focusNode == root)
                root = replacement;
            else if(isALeftChild)
                parent.leftChild = replacement;
            else
                parent.rightChild = replacement;

            replacement.leftChild = focusNode.leftChild;
        }
        return true;
    }
    public static void main(String[] args) {
        BinaryTree Tree = new BinaryTree<String>();
        Tree.addNode(50, "Boss");
        Tree.addNode(25, "vice Pres");
        Tree.addNode(15, "Office manager");
        Tree.addNode(30, "Secreatary");
        Tree.addNode(75, "Sales Manager");
        Tree.addNode(86, "Salesman 1");
        Tree.remove(50);
        Tree.inOrderTraversal(Tree.root);

        System.out.println(Tree.findNode(75).data);

    }


}
