package com.hartveld.stream.reactive.awt;

import com.hartveld.stream.reactive.awt.WindowClosingEventSubject;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.hartveld.stream.reactive.Observer;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WindowClosingEventSubjectTest {

	private WindowClosingEventSubject subject;

	@Mock
	private Window window;

	@Mock
	private Observer<WindowEvent> observer;

	@Before
	public void setUp() {
		this.subject = new WindowClosingEventSubject(window);
	}

	@Test
	public void testThatOnSubscribeAddsWindowAdapterAsListener() {
		this.subject.subscribe(observer);

		verify(window).addWindowListener(any(WindowAdapter.class));
	}

	@Test
	public void testThatClosingSubscriptionRemovesWindowListener() throws Exception {
		ArgumentCaptor<WindowAdapter> listener = ArgumentCaptor.forClass(WindowAdapter.class);

		try (AutoCloseable subscription = this.subject.subscribe(observer)) {
			verify(window).addWindowListener(listener.capture());
		}

		verify(window).removeWindowListener(listener.getValue());
		verifyNoMoreInteractions(window, observer);
	}

}
