package store.tornado.alex.unittesting;

import com.google.inject.AbstractModule;

import store.tornado.alex.unittesting.generator.Generator;
import store.tornado.alex.unittesting.generator.StringGenerator;

/**
 * Created by: anna
 * Date: 11/5/17.
 */

public class BindingModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(MVP.Model.class).to(MainModel.class);
		bind(Generator.class).to(StringGenerator.class);
	}
}
