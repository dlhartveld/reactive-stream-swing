package com.hartveld.stream.reactive.awt;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.hartveld.stream.reactive.Observer;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MouseDraggedSubjectTest {

	private MouseDraggedSubject subject;

	@Mock
	private Observer<MouseEvent> observer;

	@Mock
	private Component component;

	@Before
	public void setUp() {
		this.subject = new MouseDraggedSubject(component);
	}

	@Test
	public void testThatSubscribeAddsMouseMotionListenerToSubject() throws Exception {
		subject.subscribe(observer);

		verify(component).addMouseMotionListener(any(MouseAdapter.class));
		verifyNoMoreInteractions(component, observer);
	}

	@Test
	public void testThatClosingSubscriptionRemovesMouseMotionListener() throws Exception {
		ArgumentCaptor<MouseMotionAdapter> captor = ArgumentCaptor.forClass(MouseMotionAdapter.class);

		try (AutoCloseable subscription = subject.subscribe(observer)) {
			verify(component).addMouseMotionListener(captor.capture());
		}

		verify(component).removeMouseMotionListener(captor.getValue());
		verifyNoMoreInteractions(component, observer);
	}
}
