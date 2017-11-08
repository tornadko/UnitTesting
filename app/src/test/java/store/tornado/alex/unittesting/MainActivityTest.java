package store.tornado.alex.unittesting;

import android.app.Activity;

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

import static org.mockito.Mockito.verify;

/**
 * Created by: anna
 * Date: 11/5/17.
 */
@Config(manifest = "app/src/main/AndroidManifest.xml", packageName = "store.tornado.alex.unittesting")
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

	@Mock
	public Generator generator;
	@Mock
	public MVP.Presenter presenter;

	Activity activity;

	@Rule
	public InjectWithMocksRule injectWithMocksRule = new InjectWithMocksRule(this,
			() -> new InjectRule.BindingBuilder()
					.add(Generator.class, generator)
					.add(MVP.Presenter.class, presenter));

	@Before
	public void setUp() {
		activity = Robolectric.buildActivity(SecondActivity.class).create().start().visible().get();
	}

	@Test
	public void onCreate() throws Exception {
//		activity.;
		//verify(presenter).onViewCreated();
	}

	@Test
	public void updateResult() throws Exception {
	}

}