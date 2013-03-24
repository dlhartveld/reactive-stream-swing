package com.hartveld.stream.reactive.swing;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.hartveld.stream.reactive.Observer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ActionEventSubjectTest {

	private ActionEventSubject subject;

	@Mock
	private Observer<ActionEvent> observer;

	@Mock
	private AbstractButton button;

	@Before
	public void setUp() {
		subject = new ActionEventSubject(button);
	}

	@Test
	public void testThatSubscribeAddsListenerToButton() throws Exception {
		subject.subscribe(observer);

		verify(button).addActionListener(any(ActionListener.class));

		verifyNoMoreInteractions(button, observer);
	}

	@Test
	public void testThatClosingSubscriptionRemovesListenerFromButton() throws Exception {
		ArgumentCaptor<ActionListener> listener = ArgumentCaptor.forClass(ActionListener.class);

		try (final AutoCloseable subscription = subject.subscribe(observer)) {
			verify(button).addActionListener(listener.capture());
		}

		verify(button).removeActionListener(listener.getValue());

		verifyNoMoreInteractions(button, observer);
	}

	@Test
	public void testThatObserverIsNotifiedOfButtonClick() throws Exception {
		button = new JButton();

		subject = new ActionEventSubject(button);
		subject.subscribe(observer);

		button.doClick();

		verify(observer).onNext(any(ActionEvent.class));
		verifyNoMoreInteractions(observer);
	}

}
