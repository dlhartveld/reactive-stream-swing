package com.hartveld.stream.reactive.swing;

import com.hartveld.stream.reactive.component.ReactiveListModel;
import com.hartveld.stream.reactive.Observable;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;

public class ReactiveSwingList<T> extends JList<T> {

	public final Observable<ListSelectionEvent> selection;

	public ReactiveSwingList(final ReactiveListModel<T> model) {
		super(model);

		this.selection = new ListSelectionSubject<>(this);
	}

}
