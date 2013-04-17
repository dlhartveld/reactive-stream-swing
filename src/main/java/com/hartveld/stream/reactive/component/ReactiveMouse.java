package com.hartveld.stream.reactive.component;

import com.hartveld.stream.reactive.Observable;
import java.awt.event.MouseEvent;

public interface ReactiveMouse {

	Observable<MouseEvent> moved();

	Observable<MouseEvent> dragged();

}
