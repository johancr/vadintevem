package vadintevem.message.selector;

public interface MessageSelectorFactory {

    MessageSelector create(Algorithm algorithm);
}
