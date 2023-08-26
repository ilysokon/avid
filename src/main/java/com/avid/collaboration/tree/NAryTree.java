package com.avid.collaboration.tree;

import com.avid.exception.NullNodeValidationException;
import com.avid.exception.NodeIsNotInTheTreeValidationException;
import com.avid.exception.RuntimeValidationException;
import com.avid.exception.ValidationException;
import net.jcip.annotations.NotThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.function.Function;

@NotThreadSafe
public class NAryTree<ITEM extends Comparable<ITEM>> implements ArbitraryTree<ITEM> {
    private final NAryTreeNode<ITEM> root;

    /**
     * Creates new Tree started from the specified node
     *
     * @param node node the tree be created at
     * @throws RuntimeValidationException if specified node is null
     */
    NAryTree(final NAryTreeNode<ITEM> node) throws RuntimeValidationException {
        validateNonNull(node);
        this.root = new NAryTreeNode<>(node);
    }

    @Override
    public List<NAryTreeNode<ITEM>> add(final ITEM item, final List<ITEM> items) {
        final var nodes = find(item);
        nodes.forEach(node -> node.add(items));

        return nodes;
    }

    @Override
    public List<NAryTreeNode<ITEM>> add(final NAryTreeNode<ITEM> parent, final List<ITEM> items) throws ValidationException {
        return validate(parent)
                .add(items);
    }

    @Override
    public NAryTreeNode<ITEM> add(final NAryTreeNode<ITEM> parent, final ITEM item) throws ValidationException {
        return validate(parent)
                .add(item);
    }

    @Override
    public List<NAryTreeNode<ITEM>> removeChildren(final ITEM item) {
        final var nodes = find(item);
        nodes.forEach(NAryTreeNode::removeChildren);

        return nodes;
    }

    @Override
    public List<NAryTreeNode<ITEM>> removeChildren(final NAryTreeNode<ITEM> parent) throws ValidationException {
        return validate(parent)
                    .removeChildren();
    }

    @Override
    public List<NAryTreeNode<ITEM>> removeChildren(final NAryTreeNode<ITEM> parent, final List<ITEM> children) throws ValidationException {
        return validate(parent)
                    .removeChildren(children);
    }

    @Override
    public List<NAryTreeNode<ITEM>> search(Filter<ITEM> filter) {
        return search(root, filter::filter);
    }

    @Override
    public List<NAryTree<ITEM>> getSubTrees(final NAryTreeNode<ITEM> parent) throws ValidationException {
        return validate(parent)
                .getChildren().stream()
                .map(NAryTree::new)
                .toList();
    }

    /**
     * Validate node is not null
     *
     * @param node to be validated for null
     * @throws RuntimeException if node is null
     */
    private void validateNonNull(final NAryTreeNode<ITEM> node) throws RuntimeValidationException {
        if(node == null) {
            throw new RuntimeValidationException();
        }
    }

    /**
     * Find all nodes in the tree contains the specified item
     *
     * @param item
     * @return List of nodes which contains the specified item
     */
    private List<NAryTreeNode<ITEM>> find(final ITEM item) {
        if(item == null) {
            return Collections.emptyList();
        }

        final var result = new ArrayList<NAryTreeNode<ITEM>>();
        Queue<NAryTreeNode<ITEM>> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            while(!queue.isEmpty()) {
                NAryTreeNode<ITEM> node = queue.poll();
                if (item.equals(node.getItem())) {
                    result.add(node);
                }

                for (NAryTreeNode<ITEM> child : node.getChildren()) {
                    queue.offer(child);
                }
            }
        }

        return result;
    }

    /**
     * Validate that the specified node is in the tree
     *
     * @param nodeToBeValidated node to be validated
     * @return nodeToBeValidated if valid otherwise throws one of ValidationException
     * @exception ValidationException in case node is null or not in the tree
     */
    private NAryTreeNode<ITEM> validate(final NAryTreeNode<ITEM> nodeToBeValidated) throws ValidationException {
        if(nodeToBeValidated == null) {
            throw new NullNodeValidationException();
        }

        Queue<NAryTreeNode<ITEM>> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            while(!queue.isEmpty()) {
                NAryTreeNode<ITEM> node = queue.poll();
                if (nodeToBeValidated == node) {
                    return nodeToBeValidated;
                }

                for (NAryTreeNode<ITEM> child : node.getChildren()) {
                    queue.offer(child);
                }
            }
        }

        throw new NodeIsNotInTheTreeValidationException();
    }

    private List<NAryTreeNode<ITEM>> search(final NAryTreeNode<ITEM> startNode, final Function<ITEM, Boolean> function) {
        if(startNode == null) {
            return Collections.emptyList();
        }

        List<NAryTreeNode<ITEM>> result = preorderTraversal(startNode, function);

        return result;
    }

    private List<NAryTreeNode<ITEM>> preorderTraversal(final NAryTreeNode<ITEM> startNode, final Function<ITEM, Boolean> action) {
        Queue<NAryTreeNode<ITEM>> queue = new LinkedList<>();
        queue.offer(startNode);
        List<NAryTreeNode<ITEM>> result = new ArrayList<>();
        while(!queue.isEmpty()) {
            while(!queue.isEmpty()) {
                NAryTreeNode<ITEM> node = queue.poll();
                if (action.apply(node.getItem())) {
                    result.add(node);
                }

                for (NAryTreeNode<ITEM> child : node.getChildren()) {
                    queue.offer(child);
                }
            }
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NAryTree<?> nAryTree = (NAryTree<?>) o;
        return Objects.equals(root, nAryTree.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }
}