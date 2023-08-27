package com.avid.collaboration.tree;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NAryTreeTest {
    NAryTree<Item> tree;

    @Test
    void test1() throws Exception {

        Item rootTtem = new Item(1);
        NAryTreeNode<Item> root = new NAryTreeNode<>(rootTtem);
        tree = new NAryTree<>(root);
        Item item11 = new Item(11);
        Item item12 = new Item(12);
        Item item13 = new Item(13);
        Item item14 = new Item(14);
        Item item15 = new Item(15);
        final var node11 = tree.add(root, item11);
        final var node12 = tree.add(root, item12);
        final var node13 = tree.add(root, item13);
        final var node14 = tree.add(root, item14);
        final var node15 = tree.add(root, item15);

        Item item111 = new Item(111);
        Item item112 = new Item(112);
        Item item113 = new Item(113);
        final var node111 = tree.add(node11, item111);
        final var node112 = tree.add(node11, item112);
        final var node113 = tree.add(node11, item113);

        Item item121 = new Item(121);
        Item item122 = new Item(122);
        Item item123 = new Item(123);
        final var node121 = tree.add(node12, item121);
        final var node122 = tree.add(node12, item122);
        final var node123 = tree.add(node12, item123);

        Item item131 = new Item(131);
        Item item132 = new Item(132);
        final var node131 = tree.add(node13, item131);
        final var node132 = tree.add(node13, item132);

        Item item141 = new Item(141);
        final var node141 = tree.add(node14, item141);

        Item item151 = new Item(151);
        Item item152 = new Item(152);
        Item item153 = new Item(153);
        final var node151 = tree.add(node15, item151);
        final var node152 = tree.add(node15, item152);
        final var node153 = tree.add(node15, item153);

        tree.removeChildren(node11);
        tree.removeChildren(root, List.of(item15));
        tree.removeChildren(node13, List.of(item132));

        final var expected = expectedTree();
        assertEquals(expected, tree);
        assertEquals(expected.hashCode(), tree.hashCode());

        final var result =
                tree.search(new FilterBYItems(List.of(new Item(111), new Item(122))));

        assertEquals(1, result.size());
    }

    @Test
    void test2() throws Exception {

        Item rootTtem = new Item(1);
        NAryTreeNode<Item> root = new NAryTreeNode<>(rootTtem);
        tree = new NAryTree<>(root);
        Item item11 = new Item(11);
        Item item12 = new Item(12);
        Item item13 = new Item(13);
        Item item14 = new Item(14);
        Item item15 = new Item(15);
        final var node11 = tree.add(root, item11);
        final var node12 = tree.add(root, item12);
        final var node13 = tree.add(root, item13);
        final var node14 = tree.add(root, item14);
        final var node15 = tree.add(root, item15);

        Item item111 = new Item(111);
        Item item112 = new Item(112);
        Item item113 = new Item(113);
        final var node111 = tree.add(node11, item111);
        final var node112 = tree.add(node11, item112);
        final var node113 = tree.add(node11, item113);

        Item item121 = new Item(121);
        Item item122 = new Item(122);
        Item item123 = new Item(123);
        final var node121 = tree.add(node12, item121);
        final var node122 = tree.add(node12, item122);
        final var node123 = tree.add(node12, item123);

        Item item131 = new Item(131);
        Item item132 = new Item(132);
        final var node131 = tree.add(node13, item131);
        final var node132 = tree.add(node13, item132);

        Item item141 = new Item(141);
        final var node141 = tree.add(node14, item141);

        Item item151 = new Item(151);
        Item item152 = new Item(152);
        Item item153 = new Item(153);
        final var node151 = tree.add(node15, item151);
        final var node152 = tree.add(node15, item152);
        final var node153 = tree.add(node15, item153);

        tree.removeChildren(node11);
        tree.removeChildren(root, List.of(item15));
        tree.removeChildren(node13, List.of(item132));

        assertEquals(expectedTree(), tree);
    }

    @Test
    void find() {
    }

    class FilterBYItems implements ArbitraryTree.Filter<Item> {
        List<Item> items;

        FilterBYItems(List<Item> items) {
            this.items = items;
        }

        @Override
        public boolean filter(Item item) {
            return items.contains(item);
        }
    }

    NAryTree<Item> expectedTree() throws Exception {
        Item item1 = new Item(1);
        final var root = new NAryTreeNode<Item>(item1);
        final var expectedTree = new NAryTree<Item>(root);

        Item item11 = new Item(11);
        Item item12 = new Item(12);
        Item item13 = new Item(13);
        Item item14 = new Item(14);
        expectedTree.add(root, item11);
        final var node12 = expectedTree.add(root, item12);
        final var node13 = expectedTree.add(root, item13);
        final var node14 = expectedTree.add(root, item14);

        Item item121 = new Item(121);
        Item item122 = new Item(122);
        Item item123 = new Item(123);
        expectedTree.add(node12, item121);
        expectedTree.add(node12, item122);
        expectedTree.add(node12, item123);

        Item item131 = new Item(131);
        expectedTree.add(node13, item131);

        Item item141 = new Item(141);
        expectedTree.add(node14, item141);

        return expectedTree;
    }
}