package vadintevem.base;

import org.junit.Rule;
import org.junit.Test;
import vadintevem.test.MetaInfServicesResourceBuilder;
import vadintevem.test.MockClassLoaderRule;
import vadintevem.test.Resource;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContextListenerLoaderTest {

    private final Resource contextListener = MetaInfServicesResourceBuilder
            .service(ContextListener.class)
            .addImplementation(SimpleContextListener.class)
            .build();

    @Rule
    public MockClassLoaderRule mockClassLoaderRule = MockClassLoaderRule.with(contextListener);

    @Test
    public void loadContextListeners() {
        Collection<ContextListener> resources = ContextListenerLoader.load();

        assertThat(resources.size(), is(1));
    }

    public static class SimpleContextListener implements ContextListener {
        @Override
        public void init() {
        }
    }
}
