package vadintevem.entities;

public abstract class Message {

    public static Message of(String content) {
        return new Message() {
            @Override
            public String getContent() {
                return content;
            }

            @Override
            public Long getId() {
                return null;
            }
        };
    }

    public abstract String getContent();

    public abstract Long getId();

    public Message setId(Long id) {
        Message original = this;

        return new Message() {
            @Override
            public String getContent() {
                return original.getContent();
            }

            @Override
            public Long getId() {
                return id;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        return getId() != null ? getId().equals(message.getId()) : message.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
