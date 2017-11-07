package store.tornado.alex.unittesting;

import store.tornado.alex.unittesting.generator.Generator;

/**
 * Created by: anna
 * Date: 11/5/17.
 */

public class MainModel implements MVP.Model {

	private Generator generator;

	public MainModel(Generator generator) {
		this.generator = generator;
	}

	@Override
	public void generate(String prefix) {
		generator.generate(prefix);
	}
}
