package com.hartveld.stream.reactive.component;

import com.hartveld.stream.reactive.Observer;
import javax.swing.ListModel;

public interface ReactiveListModel<T> extends ListModel<T>, Observer<T> {

	void clear();

}
