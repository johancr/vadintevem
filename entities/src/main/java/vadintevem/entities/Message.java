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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        return getContent() != null ? getContent().equals(message.getContent()) : message.getContent() == null;
    }

    @Override
    public int hashCode() {
        return getContent() != null ? getContent().hashCode() : 0;
    }
}
