package store.tornado.alex.unittesting;

import android.app.Application;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.util.Modules;

import org.robolectric.RuntimeEnvironment;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import roboguice.RoboGuice;
import roboguice.config.DefaultRoboModule;
import roboguice.inject.RoboInjector;

public class TestBindingModule extends AbstractModule {

	private HashMap<Class<?>, Object> bindings = new HashMap<Class<?>, Object>();

	@Override
	@SuppressWarnings("unchecked")
	protected void configure() {
		Set<Entry<Class<?>, Object>> entries = bindings.entrySet();
		for (Entry<Class<?>, Object> entry : entries) {
			bind((Class<Object>) entry.getKey()).toInstance(entry.getValue());
		}
	}

	public void addBinding(Class<?> type, Object object) {
		bindings.put(type, object);
	}

	public void addBindings(HashMap<Class<?>, Object> bindings) {
		this.bindings.putAll(bindings);
	}

	public static void setUp(Object testObject, TestBindingModule module) {
		Module roboGuiceModule = RoboGuice.newDefaultRoboModule(RuntimeEnvironment.application);
		Module testModule = Modules.override(roboGuiceModule).with(module);
		RoboGuice.getOrCreateBaseApplicationInjector(RuntimeEnvironment.application, RoboGuice.DEFAULT_STAGE, testModule);
		RoboInjector injector = RoboGuice.getInjector(RuntimeEnvironment.application);
		injector.injectMembers(testObject);
	}

	public static void tearDown() {
		Application app = RuntimeEnvironment.application;
		DefaultRoboModule defaultModule = RoboGuice.newDefaultRoboModule(app);
		RoboGuice.getOrCreateBaseApplicationInjector(app, RoboGuice.DEFAULT_STAGE, defaultModule);
	}

}