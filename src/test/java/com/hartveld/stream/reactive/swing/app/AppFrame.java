package com.hartveld.stream.reactive.swing.app;

import com.hartveld.stream.reactive.Observable;
import com.hartveld.stream.reactive.swing.ReactiveButton;
import com.hartveld.stream.reactive.swing.ReactiveFrame;
import com.hartveld.stream.reactive.swing.ReactiveList;
import com.hartveld.stream.reactive.swing.ReactiveListModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class AppFrame extends ReactiveFrame {

	private final ReactiveButton button;
	private final ReactiveList<String> list;

	public final Observable<ActionEvent> go;

	public AppFrame(final ReactiveListModel<String> model) {
		super("App");

		this.button = new ReactiveButton("Go!");
		this.button.setName("go");

		this.list = new ReactiveList<>(model);
		final JScrollPane scrollPane = new JScrollPane(this.list);

		this.getContentPane().add(new JLabel("Hello, world!"), BorderLayout.WEST);
		this.getContentPane().add(button, BorderLayout.EAST);
		this.getContentPane().add(scrollPane, BorderLayout.SOUTH);

		this.go = this.button.events;
	}

}
