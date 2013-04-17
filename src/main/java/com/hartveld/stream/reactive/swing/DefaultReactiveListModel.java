package com.hartveld.stream.reactive.swing;

import com.hartveld.stream.reactive.component.ReactiveListModel;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultReactiveListModel<T> extends AbstractListModel<T> implements ReactiveListModel<T> {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultReactiveListModel.class);

	private final List<T> contents = new ArrayList<>();

	@Override
	public final int getSize() {
		return this.contents.size();
	}

	@Override
	public T getElementAt(final int index) {
		checkArgument(index < contents.size(), "index >= contents.size()");

		return this.contents.get(index);
	}

	@Override
	public final void clear() {
		LOG.trace("clear()");

		final int size = this.contents.size();

		if (size > 0) {
			this.contents.clear();

			super.fireIntervalRemoved(this, 0, size - 1);
		}
	}

	@Override
	public final void onNext(final T value) {
		LOG.trace("onNext(): {}", value);

		checkNotNull(value, "value");

		this.contents.add(value);

		super.fireIntervalAdded(this, this.contents.size() - 1, this.contents.size() - 1);
	}

	@Override
	public final void onError(final Exception e) {
		LOG.error("TODO: Not implemented: onError({})", e.getClass().getName(), e);
	}

	@Override
	public final void onCompleted() {
		LOG.trace("onCompleted()");

		super.fireIntervalAdded(this, 0, this.contents.size() - 1);
	}

}
