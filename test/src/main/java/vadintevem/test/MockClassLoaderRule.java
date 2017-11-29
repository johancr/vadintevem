package vadintevem.test;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.Arrays;
import java.util.Collection;

public class MockClassLoaderRule implements TestRule {

    private final MockClassLoaderExecutor executor = new MockClassLoaderExecutor();
    private final Collection<Resource> resources;
    private final MockClassLoader mockClassLoader;

    private MockClassLoaderRule(Resource... resources) {
        this.resources = Arrays.asList(resources);
        this.mockClassLoader = new MockClassLoader();
    }

    public static MockClassLoaderRule with(Resource... resources) {
        return new MockClassLoaderRule(resources);
    }

    public static MockClassLoaderRule create() {
        return new MockClassLoaderRule();
    }

    @Override
    public Statement apply(final Statement base, Description description) {

        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                executor.execute(new MockClassLoaderTask() {

                    @Override
                    public void configure(MockClassLoader mockClassLoader) {
                        for (Resource resource : resources)
                        {
                            addResource(resource);
                        }
                    }

                    @Override
                    public void run() {
                        try
                        {
                            base.evaluate();
                        }
                        catch (Throwable throwable)
                        {
                            throw new RuntimeException(throwable);
                        }
                    }
                }, mockClassLoader);
            }
        };
    }

    public void addResource(Resource resource) {
        mockClassLoader.addResource(resource);
    }
}
