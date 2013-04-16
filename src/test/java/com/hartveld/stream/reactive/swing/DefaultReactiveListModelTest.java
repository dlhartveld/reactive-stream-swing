package com.hartveld.stream.reactive.swing;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DefaultReactiveListModelTest {

	private static final String HELLO = "Hello";
	private static final String WORLD = "World";

	private DefaultReactiveListModel<String> model;

	@Mock
	private ListDataListener listener;
	@Captor
	private ArgumentCaptor<ListDataEvent> captor;

	@Before
	public void setUp() {
		model = new DefaultReactiveListModel() { };
	}

	@Test
	public void testThatOnNextAddsElementToModel() {
		model.onNext(HELLO);

		assertThat(model.getElementAt(0), is(sameInstance(HELLO)));
	}

	@Test
	public void testThatSecondOnNextAddsElementToModelAsSecond() {
		model.onNext(HELLO);
		model.onNext(WORLD);

		assertThat(model.getElementAt(1), is(sameInstance(WORLD)));
	}

	@Test
	public void testThatModelSizeIsZeroAfterClear() {
		model.onNext(HELLO);
		model.onNext(WORLD);

		model.clear();

		assertThat(model.getSize(), is(0));
	}

	@Test
	public void testThatClearOnEmptyModelDoesNotNotifyListDataListeners() {
		model.addListDataListener(listener);

		model.clear();

		verifyZeroInteractions(listener);
	}

	@Test
	public void testThatNotificationForFirstElementHasCorrectBoundaries() {
		model.addListDataListener(listener);

		model.onNext(HELLO);

		verify(listener).intervalAdded(captor.capture());

		final ListDataEvent event = captor.getValue();

		assertThat(event.getIndex0(), is(0));
		assertThat(event.getIndex1(), is(0));
	}

}
