package com.hartveld.stream.reactive.swing.app;

import static com.google.common.base.Preconditions.checkState;
import static javax.swing.SwingUtilities.isEventDispatchThread;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import com.hartveld.commons.test.swing.AbstractSwingFrameTest;
import com.hartveld.stream.reactive.Observable;
import com.hartveld.stream.reactive.ObservableFactory;
import com.hartveld.stream.reactive.swing.ReactiveSwingButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(MockitoJUnitRunner.class)
public class AppTest extends AbstractSwingFrameTest {

	private static final Logger LOG = LoggerFactory.getLogger(AppTest.class);

	private static final String ABC = "abc";
	private static final String DEF = "def";

	private AppControl control;

	private ReactiveSwingButton button;

	@Mock
	private RandomStringsService service;

	@Override
	protected JFrame createTestableFrame() throws Exception {
		LOG.trace("Creating frame ...");

		checkState(isEventDispatchThread(), "Not called on EDT");

		this.control = new AppControl(service);

		return this.control.frame();
	}

	@Override
	protected void lookupComponents() {
		LOG.trace("Looking up components ...");

		this.button = lookup("go", ReactiveSwingButton.class);
	}

	@Test
	public void testMyScenario() throws Exception {
		LOG.trace("Scenario ...");

		final Observable<String> strings = ObservableFactory.observableOf(ABC, DEF);

		when(this.service.retrieveSeveralRandomStrings()).thenReturn(strings);

		SwingUtilities.invokeAndWait(() -> this.button.doClick());

		// TODO: Get a hold of the different schedulers to see whether they have
		// anything in the queue left. If not, we're done. Otherwise, spin some
		// more on the 'lock' ...
		Thread.sleep(100);

		assertThat(this.control.model.getSize(), is(2));
		assertThat(this.control.model.getElementAt(0), is(sameInstance(ABC)));
		assertThat(this.control.model.getElementAt(1), is(sameInstance(DEF)));
	}

}
