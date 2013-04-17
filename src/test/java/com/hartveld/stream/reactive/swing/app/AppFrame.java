package com.hartveld.stream.reactive.swing.app;

import com.hartveld.stream.reactive.component.ReactiveButton;
import com.hartveld.stream.reactive.component.ReactiveListModel;
import com.hartveld.stream.reactive.swing.ReactiveSwingButton;
import com.hartveld.stream.reactive.swing.ReactiveSwingFrame;
import com.hartveld.stream.reactive.swing.ReactiveSwingList;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class AppFrame extends ReactiveSwingFrame {

	private final ReactiveSwingButton button;
	private final ReactiveSwingList<String> list;

	public AppFrame(final ReactiveListModel<String> model) {
		super("App");

		this.button = new ReactiveSwingButton("Go!");
		this.button.setName("go");

		this.list = new ReactiveSwingList<>(model);
		final JScrollPane scrollPane = new JScrollPane(this.list);

		this.getContentPane().add(new JLabel("Hello, world!"), BorderLayout.WEST);
		this.getContentPane().add(button, BorderLayout.EAST);
		this.getContentPane().add(scrollPane, BorderLayout.SOUTH);
	}

	public final ReactiveButton go() {
		return this.button;
	}

}
