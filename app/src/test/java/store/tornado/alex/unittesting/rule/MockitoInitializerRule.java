package store.tornado.alex.unittesting.rule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.mockito.MockitoAnnotations;

public class MockitoInitializerRule implements TestRule {

    private Object target;

    public MockitoInitializerRule(Object target) {
        this.target = target;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new MockitoInitializationStatement(base, target);
    }

    private class MockitoInitializationStatement extends Statement {
        private final Statement base;
        private Object test;

        MockitoInitializationStatement(Statement base, Object test) {
            this.base = base;
            this.test = test;
        }

        @Override
        public void evaluate() throws Throwable {
            MockitoAnnotations.initMocks(test);
            base.evaluate();
        }
    }
}