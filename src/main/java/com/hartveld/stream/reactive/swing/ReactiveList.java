package com.hartveld.stream.reactive.swing;

import com.hartveld.stream.reactive.Observable;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;

public class ReactiveList<T> extends JList<T> {

	public final Observable<ListSelectionEvent> selection;

	public ReactiveList(final ReactiveListModel<T> model) {
		super(model);

		this.selection = new ListSelectionSubject<>(this);
	}

}
