package vadintevem.explorer;

import vadintevem.entities.Message;

import java.util.List;

public interface ExplorerInteractor {

    List<Message> top(int limit);
}
