package com.skatepark.shoveit.tree;

import org.junit.Assert;
import org.junit.Test;

public class NodeTest {

    @Test
    public void testSize() {
        Node<Integer> node = new Node<>(4);
        Assert.assertEquals(1, node.size());

        node.add(2);
        Assert.assertEquals(2, node.size());

        node.add(6);
        Assert.assertEquals(3, node.size());

        node.add(1);
        Assert.assertEquals(4, node.size());

        node.add(5);
        Assert.assertEquals(5, node.size());
    }

    @Test
    public void testContains() {
        Node<Integer> node = new Node<>(4);
        Assert.assertTrue(node.contains(4));
        Assert.assertFalse(node.contains(2));

        node.add(2);
        Assert.assertTrue(node.contains(4));
        Assert.assertTrue(node.contains(2));
        Assert.assertFalse(node.contains(6));

        node.add(6);
        Assert.assertTrue(node.contains(4));
        Assert.assertTrue(node.contains(2));
        Assert.assertTrue(node.contains(6));
        Assert.assertFalse(node.contains(3));

        node.add(3);
        Assert.assertTrue(node.contains(4));
        Assert.assertTrue(node.contains(2));
        Assert.assertTrue(node.contains(6));
        Assert.assertTrue(node.contains(3));
        Assert.assertFalse(node.contains(7));

        node.add(7);
        Assert.assertTrue(node.contains(4));
        Assert.assertTrue(node.contains(2));
        Assert.assertTrue(node.contains(6));
        Assert.assertTrue(node.contains(3));
        Assert.assertTrue(node.contains(7));
        Assert.assertFalse(node.contains(1));
    }

    @Test
    public void testMin() {
        Node<Integer> node = new Node<>(4);
        Assert.assertEquals(4, (int) node.min());

        node.add(2);
        Assert.assertEquals(2, (int) node.min());

        node.add(6);
        Assert.assertEquals(2, (int) node.min());

        node.add(3);
        Assert.assertEquals(2, (int) node.min());

        node.add(7);
        Assert.assertEquals(2, (int) node.min());

        node.add(1);
        Assert.assertEquals(1, (int) node.min());
    }

    @Test
    public void testMax() {
        Node<Integer> node = new Node<>(4);
        Assert.assertEquals(4, (int) node.max());

        node.add(2);
        Assert.assertEquals(4, (int) node.max());

        node.add(6);
        Assert.assertEquals(6, (int) node.max());

        node.add(3);
        Assert.assertEquals(6, (int) node.max());

        node.add(7);
        Assert.assertEquals(7, (int) node.max());

        node.add(1);
        Assert.assertEquals(7, (int) node.max());
    }

    @Test
    public void testRemove() {
        Node<Integer> node = new Node<>(4);
        node.add(2);
        node.add(1);
        node.add(3);
        node.add(6);
        node.add(5);
        node.add(7);
        Assert.assertEquals(7, node.size());
        Assert.assertEquals(1, (int) node.min());

        node.remove(3, node);
        Assert.assertEquals(6, node.size());
        Assert.assertEquals(1, (int) node.min());
        Assert.assertEquals(7, (int) node.max());

        node.remove(1, node);
        Assert.assertEquals(5, node.size());
        Assert.assertEquals(2, (int) node.min());
        Assert.assertEquals(7, (int) node.max());

        node.remove(2, node);
        Assert.assertEquals(4, node.size());
        Assert.assertEquals(4, (int) node.min());
        Assert.assertEquals(7, (int) node.max());

        node.remove(6, node);
        Assert.assertEquals(3, node.size());
        Assert.assertEquals(4, (int) node.min());
        Assert.assertEquals(7, (int) node.max());

        node.remove(7, node);
        Assert.assertEquals(2, node.size());
        Assert.assertEquals(4, (int) node.min());
        Assert.assertEquals(5, (int) node.max());
    }
}
