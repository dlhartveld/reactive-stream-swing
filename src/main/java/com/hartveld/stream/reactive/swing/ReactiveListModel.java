package com.hartveld.stream.reactive.swing;

import com.hartveld.stream.reactive.Observer;
import javax.swing.ListModel;

public interface ReactiveListModel<T> extends ListModel<T>, Observer<T> {

	void clear();

}
