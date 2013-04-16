package com.hartveld.stream.reactive.swing;

import static com.google.common.base.Preconditions.checkNotNull;

import com.hartveld.stream.reactive.Observer;
import com.hartveld.stream.reactive.subjects.EventSubject;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class ListSelectionSubject<T> extends EventSubject<ListSelectionEvent, ListSelectionListener> {

	private final JList<T> list;

	ListSelectionSubject(final JList<T> list) {
		checkNotNull(list, "list");

		this.list = list;
	}

	@Override
	protected ListSelectionListener onSubscribe(final Observer<? super ListSelectionEvent> observer) {
		checkNotNull(observer, "observer");

		final ListSelectionListener listener = event -> onNext(event);

		this.list.addListSelectionListener(listener);

		return listener;
	}

	@Override
	protected void onClose(final ListSelectionListener source) {
		checkNotNull(source, "source");

		this.list.removeListSelectionListener(source);
	}

}
