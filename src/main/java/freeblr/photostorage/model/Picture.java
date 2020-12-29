package freeblr.photostorage.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * How to create an object (example):
 * <p><code>
 *     Picture picture = new Picture.Builder()<br>
 * <pre>                .withId(1L)<br></pre>
 * <pre>                .withHeight(100)<br></pre>
 * <pre>                .withSize(5554512132L)<br></pre>
 * <pre>                .withDescription("description")<br></pre>
 * <pre>                .withType("jpg")<br></pre>
 * <pre>                .withWidth(100)<br></pre>
 * <pre>                .createdAt(LocalDateTime.now())<br></pre>
 * <pre>                .updatedAt(LocalDateTime.now())<br></pre>
 * <pre>                .build();<br></pre>
 *
 * </code></p>
 * After add lombok it will stay more compact, and Builder will be deleted after adding repo
 */
public class Picture {

    private Long id;
    private int width;
    private int height;
    private long size;
    private String type;
    private String description;
    private LocalDateTime created;
    private LocalDateTime updated;
    //CloudinaryObj temporarily absent

    public static class Builder {

        private final Picture picture = new Picture();

        public Builder withId(Long id) {
            this.picture.id = id;
            return this;
        }

        public Builder withWidth(int width) {
            this.picture.width = width;
            return this;
        }

        public Builder withHeight(int height) {
            this.picture.height = height;
            return this;
        }

        public Builder withSize(long size) {
            this.picture.size = size;
            return this;
        }

        public Builder withType(String type) {
            this.picture.type = type;
            return this;
        }

        public Builder withDescription(String description) {
            this.picture.description = description;
            return this;
        }

        public Builder createdAt(LocalDateTime created) {
            this.picture.created = created;
            return this;
        }

        public Builder updatedAt(LocalDateTime updated) {
            this.picture.updated = updated;
            return this;
        }

        public Picture build() {
            return picture;
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Picture picture = (Picture) o;
        return id.equals(picture.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
