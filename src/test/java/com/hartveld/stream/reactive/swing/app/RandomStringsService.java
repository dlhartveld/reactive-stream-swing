package com.hartveld.stream.reactive.swing.app;

import static com.hartveld.stream.reactive.concurrency.Schedulers.defaultScheduler;
import com.hartveld.stream.reactive.Observable;
import com.hartveld.stream.reactive.subjects.TaskSubject;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomStringsService {

	private static final Logger LOG = LoggerFactory.getLogger(RandomStringsService.class);

	private static final String URL = "http://www.random.org/strings/?num=10&len=10&digits=on&upperalpha=on&loweralpha=on&unique=off&format=plain&rnd=new";

	public Observable<String> retrieveSeveralRandomStrings() {
		return new TaskSubject<>(
						defaultScheduler(),
						this::executeService
				)
				.flatMap(ss -> {
					LOG.trace("Flattening: {}", ss);
					return ss.stream();
				});
	}

	private List<String> executeService() throws Exception {
		LOG.trace("Executing service call ...");

		final HttpClient client = new DefaultHttpClient();
		final HttpGet get = new HttpGet(URL);
		final HttpResponse response = client.execute(get);

		final String contents = EntityUtils.toString(response.getEntity());
		final String[] split = contents.split("\n");

		final List<String> result = Arrays.asList(split);

		LOG.trace("Got service call results: {}", result);

		return result;
	}

}
