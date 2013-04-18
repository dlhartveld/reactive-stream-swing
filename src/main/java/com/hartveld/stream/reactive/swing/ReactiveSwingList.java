package com.hartveld.stream.reactive.swing;

import com.hartveld.stream.reactive.Observable;
import com.hartveld.stream.reactive.component.ReactiveList;
import com.hartveld.stream.reactive.component.ReactiveListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;

public class ReactiveSwingList<T> extends JList<T> implements ReactiveList<T> {

	private final ListSelectionSubject<T> selection;

	private final ReactiveListModel<T> model;

	public ReactiveSwingList(final ReactiveListModel<T> model) {
		super(model);

		this.selection = new ListSelectionSubject<>(this);

		this.model = model;
	}

	@Override
	public Observable<ListSelectionEvent> selection() {
		return selection;
	}

	@Override
	public ReactiveListModel<T> model() {
		return this.model;
	}

}
