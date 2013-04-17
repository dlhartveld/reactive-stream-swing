package com.hartveld.stream.reactive.component;

import com.hartveld.stream.reactive.Observable;
import java.awt.event.WindowEvent;

public interface ReactiveWindow {

	Observable<WindowEvent> closing();

}
