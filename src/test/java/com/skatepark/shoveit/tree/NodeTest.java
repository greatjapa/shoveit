package com.skatepark.shoveit.tree;

import org.junit.Assert;
import org.junit.Test;

public class NodeTest {

    @Test
    public void testSize() {
        Node<Integer> node = new Node<>(10);
        Assert.assertEquals(1, node.size());

        node.add(5);
        Assert.assertEquals(2, node.size());

        node.add(6);
        Assert.assertEquals(3, node.size());

        node.add(12);
        Assert.assertEquals(4, node.size());

        node.add(15);
        Assert.assertEquals(5, node.size());
    }

    @Test
    public void testContains() {
        Node<Integer> node = new Node<>(10);
        Assert.assertTrue(node.contains(10));
        Assert.assertFalse(node.contains(5));

        node.add(5);
        Assert.assertTrue(node.contains(10));
        Assert.assertTrue(node.contains(5));
        Assert.assertFalse(node.contains(6));

        node.add(6);
        Assert.assertTrue(node.contains(10));
        Assert.assertTrue(node.contains(5));
        Assert.assertTrue(node.contains(6));
        Assert.assertFalse(node.contains(12));

        node.add(12);
        Assert.assertTrue(node.contains(10));
        Assert.assertTrue(node.contains(5));
        Assert.assertTrue(node.contains(6));
        Assert.assertTrue(node.contains(12));
        Assert.assertFalse(node.contains(15));

        node.add(15);
        Assert.assertTrue(node.contains(10));
        Assert.assertTrue(node.contains(5));
        Assert.assertTrue(node.contains(6));
        Assert.assertTrue(node.contains(12));
        Assert.assertTrue(node.contains(15));
        Assert.assertFalse(node.contains(16));
    }
}
