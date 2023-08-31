package com.avid.collaboration.tree;

import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.avid.exception.RuntimeValidationException;
import com.avid.exception.ValidationException;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class ThreadSafeNAryTree <ITEM extends Comparable<ITEM>> implements ArbitraryTree<ITEM> {
	private final NAryTree<ITEM> nAryTree;

	private final ReentrantReadWriteLock.ReadLock readLock;
	private final ReentrantReadWriteLock.WriteLock writeLock;

	/**
	 * {@inheritDoc}
	 */
	public ThreadSafeNAryTree(final NAryTreeNode<ITEM> node) throws RuntimeValidationException {
		nAryTree = new NAryTree<>(node);

		final var reentrantReadWriteLock = new ReentrantReadWriteLock();
		readLock = reentrantReadWriteLock.readLock();
		writeLock = reentrantReadWriteLock.writeLock();
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Thread safe.
	 */
	@Override
	public List<NAryTreeNode<ITEM>> add(final ITEM item, final List<ITEM> items) {
		writeLock.lock();
		try {
			return nAryTree.add(item, items);
		} finally {
			writeLock.unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Thread safe.
	 */
	@Override
	public List<NAryTreeNode<ITEM>> add(final NAryTreeNode<ITEM> parent, final List<ITEM> items) throws ValidationException {
		writeLock.lock();
		try {
			return nAryTree.add(parent, items);
		} finally {
			writeLock.unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Thread safe.
	 */
	@Override
	public NAryTreeNode<ITEM> add(final NAryTreeNode<ITEM> parent, final ITEM item) throws ValidationException {
		writeLock.lock();
		try {
			return nAryTree.add(parent, item);
		} finally {
			writeLock.unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Thread safe.
	 */
	@Override
	public List<NAryTree<ITEM>> getSubTrees(final NAryTreeNode<ITEM> parent) throws ValidationException {
		readLock.lock();
		try {
			return nAryTree.getSubTrees(parent);
		} finally {
			readLock.unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Thread safe.
	 */
	@Override
	public List<NAryTreeNode<ITEM>> removeChildren(final ITEM item) {
		writeLock.lock();
		try {
			return nAryTree.removeChildren(item);
		} finally {
			writeLock.unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Thread safe.
	 */
	@Override
	public List<NAryTreeNode<ITEM>> removeChildren(final NAryTreeNode<ITEM> parent) throws ValidationException {
		writeLock.lock();
		try {
			return nAryTree.removeChildren(parent);
		} finally {
			writeLock.unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Thread safe.
	 */
	@Override
	public List<NAryTreeNode<ITEM>> removeChildren(final NAryTreeNode<ITEM> parent, final List<ITEM> children) throws ValidationException {
		writeLock.lock();
		try {
			return nAryTree.removeChildren(parent, children);
		} finally {
			writeLock.unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Thread safe.
	 */
	@Override
	public List<NAryTreeNode<ITEM>> search(Filter<ITEM> filter) {
		readLock.lock();
		try {
			return nAryTree.search(filter);
		} finally {
			readLock.unlock();
		}
	}
}
