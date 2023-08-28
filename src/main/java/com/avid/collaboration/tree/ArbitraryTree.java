package com.avid.collaboration.tree;

import com.avid.exception.ValidationException;

import java.util.List;
import java.util.Optional;

public interface ArbitraryTree<ITEM extends Comparable<ITEM>> {
    /**
     * Add items to the nodes contain specified item
     *
     * @param item the items be added to
     * @param items items to be added
     * @return all nodes the specified items were added to
     */
    List<NAryTreeNode<ITEM>> add(ITEM item, List<ITEM> items);

    /**
     * Add items to the specified parent node
     *
     * @param parent node the items be added to
     * @param items items to be added to the specified parent node
     * @return List of added nodes contain specified items
     * @throws ValidationException if parent is not in the tree or null
     */
    List<NAryTreeNode<ITEM>> add(NAryTreeNode<ITEM> parent, List<ITEM> items) throws ValidationException;

    /**
     * Add item to the parent
     *
     * @param parent the item be added to
     * @param item item to be added to the parent
     * @return Node contains the item
     * @throws ValidationException if parent is not in the tree or null
     */
    NAryTreeNode<ITEM> add(NAryTreeNode<ITEM> parent, ITEM item) throws ValidationException;

    /**
     * Get subtrees starting from the particular parent
     *
     * @param parent the subtrees started from
     * @return subtrees starting from the specified parent
     * @throws ValidationException if parent is not in the tree or null
     */
    List<NAryTree<ITEM>> getSubTrees(NAryTreeNode<ITEM> parent) throws ValidationException;

    /**
     * Remove all children from the nodes which contains the specified item
     * Tree may contain several nodes with specified item,
     *     the specified item be reomed from all such nodes
     *
     * @param item the children to be removed
     * @return list of affected nodes
     */
    List<NAryTreeNode<ITEM>> removeChildren(ITEM item);

    /**
     * Remome all children from the specified parent
     *
     * @param parent node the children be removed from
     * @return All removed children
     * @throws ValidationException if parent are not in the tree or null
     */
    List<NAryTreeNode<ITEM>> removeChildren(NAryTreeNode<ITEM> parent) throws ValidationException;

    /**
     * Remove specified children from the parent
     *
     * @param parent Node the children be removed from
     * @param children the children be removed from the specified parent
     * @return list of children nodes removed from the specified parent
     *           may contain fewer children as specified in the param if parent was not already contained some of them
     * @throws ValidationException if parent are not in the tree
     */
    List<NAryTreeNode<ITEM>> removeChildren(final NAryTreeNode<ITEM> parent, final List<ITEM> children) throws ValidationException;

    /**
     * search for items based on the specified filter
     *
     * @param filter the items be searched based on
     * @return the list of nodes satisfying the specified filter
     */
    List<NAryTreeNode<ITEM>> search(Filter<ITEM> filter);

    /**
     * Main Filter interface
     *
     * @param <ITEM> item the filter be applied on
     */
    interface Filter<ITEM> {
        boolean filter(ITEM item);
    }
}