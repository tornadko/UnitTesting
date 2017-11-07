package store.tornado.alex.unittesting.rule;

import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class InjectWithMocksRule implements TestRule {

	private final RuleChain delegate;

	public InjectWithMocksRule(Object target, InjectRule.BindingBuilderFactory bindingBuilderFactory) {
		delegate = RuleChain
				.outerRule(new MockitoInitializerRule(target))
				.around(new InjectRule(target, bindingBuilderFactory));
	}

	@Override
	public Statement apply(Statement base, Description description) {
		return delegate.apply(base, description);
	}
}