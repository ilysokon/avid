package com.avid.collaboration.tree;

import com.avid.exception.NodeNotFoundException;
import com.avid.exception.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NAryTreeTest {

    @Test
    void childrenRemovedCorrectly() throws Exception {
        final var rootTtem = new Item(1);
        final var root = new NAryTreeNode<>(rootTtem);
        final var tree = new NAryTree<>(root);

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
        tree.add(node11, List.of(item111, item112, item113));

        final var item121 = new Item(121);
        final var item122 = new Item(122);
        final var item123 = new Item(123);
        tree.add(node12, List.of(item121, item122, item123));

        final var item131 = new Item(131);
        final var item132 = new Item(132);
        tree.add(node13, List.of(item131, item132));

        final var item141 = new Item(141);
        tree.add(node14, item141);

        final var item151 = new Item(151);
        final var item152 = new Item(152);
        final var item153 = new Item(153);
        tree.add(node15, List.of(item151, item152, item153));

        tree.removeChildren(node11);
        tree.removeChildren(root, List.of(item15));
        tree.removeChildren(node13, List.of(item132));

        final var expected = expectedTree();
        assertEquals(expected, tree);
        assertEquals(expected.hashCode(), tree.hashCode());
    }

    @Test
    void subtree() throws Exception {
        final var rootTtem = new Item(1);
        final var root = new NAryTreeNode<>(rootTtem);
        final var tree = new NAryTree<>(root);

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
        tree.add(node11, List.of(item111, item112, item113));

        final var item121 = new Item(121);
        final var item122 = new Item(122);
        final var item123 = new Item(123);
        tree.add(node12, List.of(item121, item122, item123));

        final var item131 = new Item(131);
        final var item132 = new Item(132);
        tree.add(node13, List.of(item131, item132));

        final var item141 = new Item(141);
        tree.add(node14, item141);

        final var item151 = new Item(151);
        final var item152 = new Item(152);
        final var item153 = new Item(153);
        tree.add(node15, List.of(item151, item152, item153));

        final var subTrees = tree.getSubTrees(node11);

        assertEquals(expectedSubTrees(), subTrees);
    }

    @Test
    void nodeNotFound() throws ValidationException {
        final var rootTtem = new Item(1);
        final var root = new NAryTreeNode<>(rootTtem);
        final var tree = new NAryTree<>(root);

        final var item11 = new Item(11);
        final var item12 = new Item(12);
        final var item13 = new Item(13);
        final var item14 = new Item(14);
        final var item15 = new Item(15);
        final var node11 = tree.add(root, item11);
        final var node12 = tree.add(root, item12);
        final var node13 = tree.add(root, item13);
        final var node14 = tree.add(root, item14);
        final var node15 = new NAryTreeNode<>(item15);

        final var item111 = new Item(111);
        final var item112 = new Item(112);
        final var item113 = new Item(113);
        tree.add(node11, item111);
        tree.add(node11, item112);
        tree.add(node11, item113);

        final var item121 = new Item(121);
        final var item122 = new Item(122);
        final var item123 = new Item(123);
        tree.add(node12, item121);
        tree.add(node12, item122);
        tree.add(node12, item123);

        final var item131 = new Item(131);
        final var item132 = new Item(132);
        tree.add(node13, item131);
        tree.add(node13, item132);

        final var item141 = new Item(141);
        tree.add(node14, item141);

        final var item151 = new Item(151);

        assertThrows(NodeNotFoundException.class, () -> tree.add(node15, item151));
    }

    @Test
    void search() throws Exception {
        final var rootTtem = new Item(1);
        final var root = new NAryTreeNode<>(rootTtem);
        final var tree = new NAryTree<>(root);

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
        tree.add(node11, item111);
        tree.add(node11, item112);
        tree.add(node11, item113);

        final var item121 = new Item(121);
        final var item122 = new Item(122);
        final var item123 = new Item(123);
        tree.add(node12, item121);
        tree.add(node12, item122);
        tree.add(node12, item123);

        final var item131 = new Item(131);
        final var item132 = new Item(132);
        tree.add(node13, item131);
        tree.add(node13, item132);

        final var item141 = new Item(141);
        tree.add(node14, item141);

        final var item151 = new Item(151);
        final var item152 = new Item(152);
        final var item153 = new Item(153);
        tree.add(node15, item151);
        tree.add(node15, item152);
        tree.add(node15, item153);

        final var result1 =
                tree.search(new FilterBYItems(List.of(new Item(111), new Item(122))));

        assertEquals(2, result1.size());

        tree.removeChildren(node11);
        tree.removeChildren(root, List.of(item15));
        tree.removeChildren(node13, List.of(item132));

        final var result2 =
                tree.search(new FilterBYItems(List.of(new Item(111), new Item(122))));

        assertEquals(1, result2.size());
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

    private NAryTree<Item> expectedTree() throws Exception {
        final var item1 = new Item(1);
        final var root = new NAryTreeNode<>(item1);
        final var expectedTree = new NAryTree<>(root);

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

    private List<NAryTree<Item>> expectedSubTrees() throws Exception {
        final var item111 = new Item(111);
        final var node111 = new NAryTreeNode<Item>(item111);
        final var expectedSubTree1 = new NAryTree<>(node111);

        final var item112 = new Item(112);
        final var node112 = new NAryTreeNode<Item>(item112);
        final var expectedSubTree2 = new NAryTree<>(node112);

        final var item113 = new Item(113);
        final var node113 = new NAryTreeNode<Item>(item113);
        final var expectedSubTree3 = new NAryTree<>(node113);

        return List.of(expectedSubTree1, expectedSubTree2, expectedSubTree3);
    }
}