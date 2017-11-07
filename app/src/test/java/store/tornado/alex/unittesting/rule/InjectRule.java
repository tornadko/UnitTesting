package store.tornado.alex.unittesting.rule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.HashMap;

import store.tornado.alex.unittesting.TestBindingModule;

public class InjectRule implements TestRule {

	public interface BindingBuilderFactory {
		BindingBuilder create();
	}

	public static class BindingBuilder {
		private HashMap<Class<?>, Object> bindings = new HashMap<>();

		public BindingBuilder add(Class<?> dependencyClass, Object implementation) {
			bindings.put(dependencyClass, implementation);
			return this;
		}

		HashMap<Class<?>, Object> buildBindings() {
			return this.bindings;
		}
	}

	private Object target;
	private BindingBuilderFactory bindingBuilderFactory;

	public InjectRule(Object target, BindingBuilderFactory bindingBuilderFactory) {
		this.target = target;
		this.bindingBuilderFactory = bindingBuilderFactory;
	}

	private void overrideTestInjections(Object target) {
		TestBindingModule module = new TestBindingModule();
		module.addBindings(this.bindingBuilderFactory.create().buildBindings());
		TestBindingModule.setUp(target, module);
	}

	@Override
	public Statement apply(Statement base, Description description) {
		return new StatementDecorator(base);
	}


	private class StatementDecorator extends Statement {

		private Statement baseStatement;

		StatementDecorator(Statement b) {
			baseStatement = b;
		}

		@Override
		public void evaluate() throws Throwable {
			before();
			try {
				baseStatement.evaluate();
			} catch (Error e) {
				throw e;
			} finally {
				after();
			}
		}

		void after() {
			TestBindingModule.tearDown();
		}

		void before() {
			overrideTestInjections(target);
		}
	}

}