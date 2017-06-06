package skatepark.shoveit.tree;

import java.util.Objects;

public class Node<T extends Comparable> {

    private Node<T> left;
    private Node<T> right;
    private T value;

    public Node(T value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    public T getValue() {
        return value;
    }

    public void add(T value) {
        if (this.value.compareTo(value) > 0) {
            if (left != null) {
                left.add(value);
            } else {
                left = new Node<>(value);
            }
        } else {
            if (right != null) {
                right.add(value);
            } else {
                right = new Node<>(value);
            }
        }
    }

    public boolean contains(T value) {
        int result = this.value.compareTo(value);
        if (result == 0) {
            return true;
        }
        if (result > 0) {
            return left != null && left.contains(value);
        }
        return right != null && right.contains(value);
    }

    public int size() {
        int leftSize = left == null ? 0 : left.size();
        int rightSize = right == null ? 0 : right.size();
        return leftSize + rightSize + 1;
    }

    public T min() {
        return left == null ? this.value : left.min();
    }

    public T max() {
        return right == null ? this.value : right.max();
    }

    public boolean remove(T value, Node<T> parent) {
        if (value.compareTo(this.value) < 0) {
            if (left != null) {
                return left.remove(value, this);
            } else {
                return false;
            }
        } else if (value.compareTo(this.value) > 0) {
            if (right != null) {
                return right.remove(value, this);
            } else {
                return false;
            }
        } else {
            if (left != null && right != null) {
                this.value = right.min();
                right.remove(this.value, this);
            } else if (parent.left == this) {
                parent.left = left != null ? left : right;
            } else if (parent.right == this) {
                parent.right = left != null ? left : right;
            }
            return true;
        }

    }
}
