package com.hartveld.stream.reactive.swing;

import com.hartveld.stream.reactive.Observable;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class ReactiveButton extends JButton {

	public final Observable<ActionEvent> events;

	public ReactiveButton(final String text) {
		super(text);

		this.events = new ActionEventSubject(this);
	}

}
