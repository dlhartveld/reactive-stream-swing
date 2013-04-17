package com.hartveld.stream.reactive.component;

import com.hartveld.stream.reactive.Observable;
import java.awt.event.ActionEvent;

public interface ReactiveButton {

	Observable<ActionEvent> clicks();

}
