package vadintevem.entities;

public abstract class Message {

    public static Message of(String content) {
        return new Message() {
            @Override
            public String getContent() {
                return content;
            }
        };
    }

    public abstract String getContent();
}
