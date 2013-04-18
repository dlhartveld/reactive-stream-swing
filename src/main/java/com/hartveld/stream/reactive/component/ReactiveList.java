package com.hartveld.stream.reactive.component;

import com.hartveld.stream.reactive.Observable;
import javax.swing.event.ListSelectionEvent;

public interface ReactiveList<T> {

	Observable<ListSelectionEvent> selection();

	ReactiveListModel<T> model();

}
