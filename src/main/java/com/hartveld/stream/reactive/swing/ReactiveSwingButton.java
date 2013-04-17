package com.hartveld.stream.reactive.swing;

import com.hartveld.stream.reactive.Observable;
import com.hartveld.stream.reactive.component.ReactiveButton;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class ReactiveSwingButton extends JButton implements ReactiveButton {

	private final Observable<ActionEvent> events;

	public ReactiveSwingButton(final String text) {
		super(text);

		this.events = new ActionEventSubject(this);
	}

	@Override
	public Observable<ActionEvent> clicks() {
		return events;
	}

}
