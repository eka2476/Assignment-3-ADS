import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> {

    private Node root;
    private int size;

    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    // entry class so user can access key and value during iteration
    public class Entry {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public int size() {
        return size;
    }

    
    public void put(K key, V val) {
        Node newNode = new Node(key, val);

        if (root == null) {
            root = newNode;
            size++;
            return;
        }

        Node current = root;
        while (true) {
            int cmp = key.compareTo(current.key);

            if (cmp == 0) {
                // key already exists.update
                current.val = val;
                return;
            } else if (cmp < 0) {
                if (current.left == null) {
                    current.left = newNode;
                    size++;
                    return;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = newNode;
                    size++;
                    return;
                }
                current = current.right;
            }
        }
    }

    public V get(K key) {
        Node current = root;

        while (current != null) {
            int cmp = key.compareTo(current.key);

            if (cmp == 0) {
                return current.val;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    public void delete(K key) {
        Node current = root;
        Node parent = null;
        boolean isLeftChild = false;

        // find the node to delete
        while (current != null) {
            int cmp = key.compareTo(current.key);

            if (cmp == 0) {
                break;
            } else if (cmp < 0) {
                parent = current;
                isLeftChild = true;
                current = current.left;
            } else {
                parent = current;
                isLeftChild = false;
                current = current.right;
            }
        }

        // node not found
        if (current == null) return;

        // case 1: 
        if (current.left == null && current.right == null) {
            if (parent == null) {
                root = null;
            } else if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
            size--;
        }
        // case 2: 
        else if (current.left == null) {
            if (parent == null) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
            size--;
        }
        // case 3: 
        else if (current.right == null) {
            if (parent == null) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
            size--;
        }
        // case 4: 
        else {
            Node successorParent = current;
            Node successor = current.right;

            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            // replace
            current.key = successor.key;
            current.val = successor.val;

            // delete the successor
            if (successorParent == current) {
                successorParent.right = successor.right;
            } else {
                successorParent.left = successor.right;
            }
            size--;
        }
    }

    public Iterable<Entry> iterator() {
        ArrayList<Entry> list = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // process node
            current = stack.pop();
            list.add(new Entry(current.key, current.val));

            // move to right subtree
            current = current.right;
        }

        return list;
    }
}
