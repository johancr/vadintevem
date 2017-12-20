package vadintevem.message.selector.impl;

import vadintevem.message.selector.Algorithm;
import vadintevem.message.selector.MessageSelector;
import vadintevem.message.selector.MessageSelectorFactory;

import javax.inject.Inject;

import static vadintevem.message.selector.Algorithm.POPULAR;
import static vadintevem.message.selector.Algorithm.RANDOM;
import static vadintevem.message.selector.Algorithm.UNREAD;

public class DefaultMessageSelectorFactory implements MessageSelectorFactory {

    private final RandomMessageSelector randomMessageSelector;
    private final PopularMessageSelector popularMessageSelector;
    private final UnreadMessageSelector unreadMessageSelector;

    @Inject
    public DefaultMessageSelectorFactory(RandomMessageSelector randomMessageSelector,
                                         PopularMessageSelector popularMessageSelector,
                                         UnreadMessageSelector unreadMessageSelector) {
        this.randomMessageSelector = randomMessageSelector;
        this.popularMessageSelector = popularMessageSelector;
        this.unreadMessageSelector = unreadMessageSelector;
    }

    @Override
    public MessageSelector create(Algorithm algorithm) {
        if (RANDOM.equals(algorithm))
        {
            return randomMessageSelector;
        }
        else if (POPULAR.equals(algorithm))
        {
            return popularMessageSelector;
        }
        else if (UNREAD.equals(algorithm))
        {
            return unreadMessageSelector;
        }
        else
        {
            return randomMessageSelector;
        }
    }
}
