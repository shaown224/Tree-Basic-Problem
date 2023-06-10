import java.net.PortUnreachableException;
import java.security.PublicKey;
import java.util.*;

public class Tree {

    static class Node { // a single node
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree {

        static int idx = -1;

        public static Node buildTree(int nodes[]) {

            idx++;

            if (nodes[idx] == -1) { // jodi kono array value -1 hoi tarmane oi node null.tahole oi node tai return
                                    // korbo
                return null;
            }

            Node newNode = new Node(nodes[idx]); // ekta notun node create kore tar vitor data rakhlam

            newNode.left = buildTree(nodes); // left child e baki node gula pathai dilam
            newNode.right = buildTree(nodes); // right child e baki node gula pathai dilam

            return newNode;

        }

    }

    public static void Preorder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        Preorder(root.left);
        Preorder(root.right);

    }

    public static void Inorder(Node root) {

        if (root == null) {
            return;
        }
        Inorder(root.left);
        System.out.print(root.data + " ");
        Inorder(root.right);

    }

    public static void Postorder(Node root) {
        if (root == null) {
            return;
        }

        Postorder(root.left);
        Postorder(root.right);
        System.out.print(root.data + " ");

    }

    public static void Levelorder(Node root) {
        Queue<Node> q = new LinkedList<>();

        q.add(root);
        q.add(null);

        while (!q.isEmpty()) {
            Node currNode = q.remove();

            if (currNode == null) {

                System.out.println(" ");
                if (q.isEmpty()) {
                    break;
                } else {
                    q.add(null);
                }

            } else {
                System.out.print(currNode.data + " ");

                if (currNode.left != null) {
                    q.add(currNode.left);
                }
                if (currNode.right != null) {
                    q.add(currNode.right);
                }

            }

        }
    }

    public static int CountOfNode(Node root) {
        if (root == null) {
            return 0;
        }

        return CountOfNode(root.left) + CountOfNode(root.right) + 1;

    }

    public static int SumOfNode(Node root) {
        if (root == null) {
            return 0;
        }

        return SumOfNode(root.left) + SumOfNode(root.right) + root.data;

    }

    public static int HeightOfTree(Node root) {
        if (root == null) {
            return 0;
        }

        return Math.max(HeightOfTree(root.left), HeightOfTree(root.right)) + 1;

    }

    public static int Diameter(Node root) { // Time Complexity:O(n^2)
        if (root == null) {
            return 0;
        }

        int left = Diameter(root.left);
        int right = Diameter(root.right);

        int withroot = HeightOfTree(root.left) + HeightOfTree(root.right) + 1;

        return Math.max(left, Math.max(right, withroot));

    }

    static class TreeInfo {
        int ht;
        int diam;

        TreeInfo(int ht, int diam) {
            this.ht = ht;
            this.diam = diam;
        }
    }

    public static TreeInfo Diameter2(Node root) {

        if (root == null) {
            return new TreeInfo(0, 0);
        }

        TreeInfo left = Diameter2(root.left);
        TreeInfo right = Diameter2(root.right);

        int myHeight = Math.max(left.ht, right.ht) + 1;

        int diam1 = left.diam;
        int diam2 = right.diam;
        int diam3 = left.ht + right.ht + 1;

        int mydiam = Math.max(diam1, Math.max(diam2, diam3));

        TreeInfo tf = new TreeInfo(myHeight, mydiam);

        return tf;

    }

    public static void main(String[] args) {

        int nodes[] = { 1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1 };

        BinaryTree bt = new BinaryTree();
        Node root = bt.buildTree(nodes);
        // Preorder(root);
        // Inorder(root);
        // Postorder(root);
        // Levelorder(root);
        System.out.println(CountOfNode(root));
        System.out.println(SumOfNode(root));
        System.out.println(HeightOfTree(root));
        System.out.println(Diameter(root));

        System.out.println(Diameter2(root).diam + " " + Diameter2(root).ht);

    }
}
