package com.avid.collaboration.tree;

import com.avid.exception.NodeNullPointerException;
import com.avid.exception.NodeNotFoundException;
import com.avid.exception.RuntimeValidationException;
import com.avid.exception.ValidationException;
import net.jcip.annotations.NotThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
        return search(root, filter);
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

        final var searchByItemVisitor = new SearchByItemVisitor(item);
        traversal(root, searchByItemVisitor);

        return searchByItemVisitor.result;
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
            throw new NodeNullPointerException();
        }

        final var validateVisitor = new ValidateVisitor(nodeToBeValidated);

        traversal(root, validateVisitor);

        if(validateVisitor.result == null) {
            throw new NodeNotFoundException();
        } else {
           return validateVisitor.result;
        }
    }

    private List<NAryTreeNode<ITEM>> search(final NAryTreeNode<ITEM> startNode, final Filter<ITEM> filter) {
        if(startNode == null) {
            return Collections.emptyList();
        }

        final var searchVisitor = new SearchByFilterVisitor(filter);
        traversal(startNode, searchVisitor);

        return searchVisitor.result;
    }

    private boolean equals(final NAryTree<ITEM> other) {
        if(other == null) {
            return false;
        }

        Queue<NAryTreeNode<ITEM>> queue = new LinkedList<>();
        queue.offer(root);
        Queue<NAryTreeNode<ITEM>> otherQueue = new LinkedList<>();
        otherQueue.offer(other.root);

        while(!queue.isEmpty() && !otherQueue.isEmpty()) {
            while(!queue.isEmpty() && !otherQueue.isEmpty()) {
                var node = queue.poll();
                var otherNode = otherQueue.poll();
                if (queue.isEmpty() && !otherQueue.isEmpty()
                        || !queue.isEmpty() && otherQueue.isEmpty()) {
                    return false;
                }

                if (!node.getItem().equals(otherNode.getItem())) {
                    return false;
                }

                for (var child : node.getChildren()) {
                    queue.offer(child);
                }
                for (var child : otherNode.getChildren()) {
                    otherQueue.offer(child);
                }
            }
        }

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NAryTree<ITEM> nAryTree = (NAryTree<ITEM>) o;
        return equals(nAryTree);
    }

    @Override
    public int hashCode() {
        final var hasCodeVisitor = new HashCodeVisitor();

        traversal(root, hasCodeVisitor);

        return hasCodeVisitor.hashCode;
    }

    private void traversal(final NAryTreeNode<ITEM> startNode, final Visitor<NAryTreeNode<ITEM>> visitor) {
        Queue<NAryTreeNode<ITEM>> queue = new LinkedList<>();
        queue.offer(startNode);

        int h = 0;
        while(!queue.isEmpty()) {
            while(!queue.isEmpty()) {
                NAryTreeNode<ITEM> node = queue.poll();

                if (visitor.apply(node)) {
                   return;
                }

                for (var child : node.getChildren()) {
                    queue.offer(child);
                }
            }
        }
    }

    interface Visitor<T> {
        boolean apply(T node);
    }

    class ValidateVisitor implements Visitor<NAryTreeNode<ITEM>> {
        final NAryTreeNode<ITEM> nodeToBeValidated;
        NAryTreeNode<ITEM> result;

        ValidateVisitor(final NAryTreeNode<ITEM> nodeToBeValidated) {
            this.nodeToBeValidated = nodeToBeValidated;
        }

        @Override
        public boolean apply(final NAryTreeNode<ITEM> node) {
            if (nodeToBeValidated.equals(node)) {
                result = node;

                // node is found, no need to continue
                return true;
            }

            // continue
            return false;
        }
    }

    class HashCodeVisitor implements Visitor<NAryTreeNode<ITEM>> {
        int hashCode = 0;

        @Override
        public boolean apply(NAryTreeNode<ITEM> node) {
            hashCode += node.getItem().hashCode();

            // always continue
            return false;
        }
    }

    class SearchByFilterVisitor implements Visitor<NAryTreeNode<ITEM>> {
        final List<NAryTreeNode<ITEM>> result;
        final Filter<ITEM> filter;

        public SearchByFilterVisitor(Filter<ITEM> filter) {
            this.filter = filter;
            result = new ArrayList<>();
        }

        @Override
        public boolean apply(NAryTreeNode<ITEM> node) {
            if (filter.filter(node.getItem())) {
                result.add(node);
            }

            // always continue
            return false;
        }
    }

    class SearchByItemVisitor implements Visitor<NAryTreeNode<ITEM>> {
        private final List<NAryTreeNode<ITEM>> result;
        private final ITEM item;

        public SearchByItemVisitor(final ITEM item) {
            this.item = item;
            this.result = new ArrayList<>();
        }

        @Override
        public boolean apply(NAryTreeNode<ITEM> node) {
            if (item.equals(node.getItem())) {
                result.add(node);
            }

            // always continue
            return false;
        }
    }
}