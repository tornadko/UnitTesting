package store.tornado.alex.unittesting;

import android.app.Activity;
import android.widget.Button;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import store.tornado.alex.unittesting.generator.Generator;
import store.tornado.alex.unittesting.rule.InjectRule;
import store.tornado.alex.unittesting.rule.InjectWithMocksRule;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

/**
 * Created by: anna
 * Date: 11/5/17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {

	@Mock
	private Generator generator;
	@Mock
	private MVP.Presenter presenter;

	private Activity activity;

	@Rule
	public InjectWithMocksRule injectWithMocksRule = new InjectWithMocksRule(this,
			() -> new InjectRule.BindingBuilder()
					.add(Generator.class, generator)
					.add(MVP.Presenter.class, presenter));

	@Before
	public void setUp() {
		activity = Robolectric.buildActivity(MainActivity.class).create().start().resume().visible().get();
	}

	@Test
	public void testOnCreate() throws Exception {
		verify(presenter).setView((MVP.View) activity);
		verify(presenter).onViewCreated();
	}

	@Test
	public void testGenerateClicked() throws Exception {
		Button btn = activity.findViewById(R.id.button);
		assertNotNull(btn);
		btn.performClick();
		verify(presenter).onGenerateClicked(anyString());
	}

}