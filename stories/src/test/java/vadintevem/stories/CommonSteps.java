package vadintevem.stories;

import cucumber.api.java8.En;
import vadintevem.authors.admin.AuthorsAdmin;
import vadintevem.messages.admin.MessagesAdmin;
import vadintevem.stories.spi.ScenarioListener;
import vadintevem.stories.spi.ScenarioListenerLoader;

import javax.inject.Inject;
import java.util.Collection;

public class CommonSteps implements En {

    @Inject
    public CommonSteps(MessagesAdmin messagesAdmin,
                       AuthorsAdmin authorsAdmin) {

        Collection<ScenarioListener> scenarioListeners = ScenarioListenerLoader.load();

        Before(() -> {
            scenarioListeners.forEach(ScenarioListener::onStart);
            messagesAdmin.deleteAll();
            authorsAdmin.deleteAll();
        });

        After(() -> {
            scenarioListeners.forEach(ScenarioListener::onEnd);
        });
    }
}
