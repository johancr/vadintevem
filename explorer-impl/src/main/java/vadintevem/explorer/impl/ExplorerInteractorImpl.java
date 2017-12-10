package vadintevem.explorer.impl;

import vadintevem.entities.Message;
import vadintevem.explorer.ExplorerInteractor;
import vadintevem.ranking.Ranker;

import javax.inject.Inject;
import java.util.List;

public class ExplorerInteractorImpl implements ExplorerInteractor {

    private final Ranker ranker;

    @Inject
    public ExplorerInteractorImpl(Ranker ranker) {
        this.ranker = ranker;
    }

    @Override
    public List<Message> top(int limit) {
        return ranker.top(limit);
    }
}
