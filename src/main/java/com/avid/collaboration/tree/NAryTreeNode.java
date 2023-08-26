package com.avid.collaboration.tree;

import net.jcip.annotations.NotThreadSafe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@NotThreadSafe
public class NAryTreeNode<ITEM extends Comparable<ITEM>> {
    private final ITEM item;
    private List<NAryTreeNode<ITEM>> children = new LinkedList<>();

    NAryTreeNode(final ITEM item) {
        this.item = item;
    }

    NAryTreeNode(final NAryTreeNode<ITEM> node) {
        this.item = node.item;
        this.children = new ArrayList<>(node.children);
    }

    ITEM getItem() {
        return item;
    }

    public List<NAryTreeNode<ITEM>> getChildren() {
        return children;
    }

    List<NAryTreeNode<ITEM>> add(final List<ITEM> items) {
        final var newNodes = items.stream().map(NAryTreeNode::new).toList();
        this.children.addAll(newNodes);

        return newNodes;
    }

    NAryTreeNode<ITEM> add(final ITEM item) {
        final var node = new NAryTreeNode<>(item);
        this.children.add(node);
        return node;
    }

    List<NAryTreeNode<ITEM>> removeChildren() {
        final var removed = children.stream().toList();
        children.clear();

        return removed;
    }

    List<NAryTreeNode<ITEM>> removeChildren(final List<ITEM> toRemove) {
        final var nodesToBeRemoved = children.stream()
                .filter(node -> toRemove.contains(node.item))
                .toList();
        children.removeAll(nodesToBeRemoved);

        return nodesToBeRemoved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NAryTreeNode<?> that = (NAryTreeNode<?>) o;
        return Objects.equals(item, that.item) && Objects.equals(children, that.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, children);
    }
}