package com.hartveld.stream.reactive.swing;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.hartveld.stream.reactive.Observer;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ListSelectionSubjectTest {

	private ListSelectionSubject<?> subject;

	@Mock
	private JList<?> list;

	@Mock
	private Observer<ListSelectionEvent> observer;
	@Captor
	private ArgumentCaptor<ListSelectionListener> listener;

	@Before
	public void setUp() {
		this.subject = new ListSelectionSubject<>(list);
	}

	@Test
	public void testThatSubscribeAddsListenerToButton() throws Exception {
		subject.subscribe(observer);

		verify(list).addListSelectionListener(any(ListSelectionListener.class));

		verifyNoMoreInteractions(list, observer);
	}

	@Test
	public void testThatClosingSubscriptionRemovesListenerFromButton() throws Exception {
		try (final AutoCloseable subscription = subject.subscribe(observer)) {
			verify(list).addListSelectionListener(listener.capture());
		}

		verify(list).removeListSelectionListener(listener.getValue());

		verifyNoMoreInteractions(list, observer);
	}

	@Test
	public void testThatObserverIsNotifiedOfSelection() {
		list = new JList<>(new Object [] { new Object() });

		subject = new ListSelectionSubject<>(list);
		subject.subscribe(observer);

		assertThat(list.getSelectedIndex(), is(-1));
		list.setSelectedIndex(0);

		verify(observer).onNext(any(ListSelectionEvent.class));
	}
}
