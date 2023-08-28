package com.avid.collaboration.tree;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NAryTreeTest {
    NAryTree<Item> tree;

    @Test
    void test1() throws Exception {

        final var rootTtem = new Item(1);
        final var root = new NAryTreeNode<>(rootTtem);
        tree = new NAryTree<>(root);
        final var item11 = new Item(11);
        final var item12 = new Item(12);
        final var item13 = new Item(13);
        Item item14 = new Item(14);
        final var item15 = new Item(15);
        final var node11 = tree.add(root, item11);
        final var node12 = tree.add(root, item12);
        final var node13 = tree.add(root, item13);
        final var node14 = tree.add(root, item14);
        final var node15 = tree.add(root, item15);

        final var item111 = new Item(111);
        final var item112 = new Item(112);
        final var item113 = new Item(113);
        final var node111 = tree.add(node11, item111);
        final var node112 = tree.add(node11, item112);
        final var node113 = tree.add(node11, item113);

        final var item121 = new Item(121);
        final var item122 = new Item(122);
        final var item123 = new Item(123);
        final var node121 = tree.add(node12, item121);
        final var node122 = tree.add(node12, item122);
        final var node123 = tree.add(node12, item123);

        final var item131 = new Item(131);
        final var item132 = new Item(132);
        final var node131 = tree.add(node13, item131);
        final var node132 = tree.add(node13, item132);

        final var item141 = new Item(141);
        final var node141 = tree.add(node14, item141);

        final var item151 = new Item(151);
        final var item152 = new Item(152);
        final var item153 = new Item(153);
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
        final var rootTtem = new Item(1);
        NAryTreeNode<Item> root = new NAryTreeNode<>(rootTtem);
        tree = new NAryTree<>(root);
        final var item11 = new Item(11);
        final var item12 = new Item(12);
        final var item13 = new Item(13);
        final var item14 = new Item(14);
        final var item15 = new Item(15);
        final var node11 = tree.add(root, item11);
        final var node12 = tree.add(root, item12);
        final var node13 = tree.add(root, item13);
        final var node14 = tree.add(root, item14);
        final var node15 = tree.add(root, item15);

        final var item111 = new Item(111);
        final var item112 = new Item(112);
        final var item113 = new Item(113);
        final var node111 = tree.add(node11, item111);
        final var node112 = tree.add(node11, item112);
        final var node113 = tree.add(node11, item113);

        final var item121 = new Item(121);
        final var item122 = new Item(122);
        final var item123 = new Item(123);
        final var node121 = tree.add(node12, item121);
        final var node122 = tree.add(node12, item122);
        final var node123 = tree.add(node12, item123);

        final var item131 = new Item(131);
        final var item132 = new Item(132);
        final var node131 = tree.add(node13, item131);
        final var node132 = tree.add(node13, item132);

        final var item141 = new Item(141);
        final var node141 = tree.add(node14, item141);

        final var item151 = new Item(151);
        final var item152 = new Item(152);
        final var item153 = new Item(153);
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

    static class FilterBYItems implements ArbitraryTree.Filter<Item> {
        final List<Item> items;

        FilterBYItems(final List<Item> items) {
            this.items = items;
        }

        @Override
        public boolean filter(final Item item) {
            return items.contains(item);
        }
    }

    NAryTree<Item> expectedTree() throws Exception {
        final var item1 = new Item(1);
        final var root = new NAryTreeNode<Item>(item1);
        final var expectedTree = new NAryTree<Item>(root);

        final var item11 = new Item(11);
        final var item12 = new Item(12);
        final var item13 = new Item(13);
        final var item14 = new Item(14);
        expectedTree.add(root, item11);
        final var node12 = expectedTree.add(root, item12);
        final var node13 = expectedTree.add(root, item13);
        final var node14 = expectedTree.add(root, item14);

        final var item121 = new Item(121);
        final var item122 = new Item(122);
        final var item123 = new Item(123);
        expectedTree.add(node12, item121);
        expectedTree.add(node12, item122);
        expectedTree.add(node12, item123);

        final var item131 = new Item(131);
        expectedTree.add(node13, item131);

        final var item141 = new Item(141);
        expectedTree.add(node14, item141);

        return expectedTree;
    }
}