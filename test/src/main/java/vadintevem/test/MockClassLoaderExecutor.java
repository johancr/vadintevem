package vadintevem.test;

public class MockClassLoaderExecutor {

    public void execute(MockClassLoaderTask task) {
        MockClassLoader mockClassLoader = new MockClassLoader();
        execute(task, mockClassLoader);
    }

    public void execute(MockClassLoaderTask task, MockClassLoader mockClassLoader) {
        configure(task, mockClassLoader);
        ClassLoader current = Thread.currentThread().getContextClassLoader();
        try
        {
            Thread.currentThread().setContextClassLoader(mockClassLoader);
            task.run();
        }
        finally
        {
            Thread.currentThread().setContextClassLoader(current);
        }
    }

    private void configure(MockClassLoaderTask task, MockClassLoader mockClassLoader) {
        task.configure(mockClassLoader);
        for (Resource resource : task.getResources())
        {
            mockClassLoader.addResource(resource);
        }
    }
}
