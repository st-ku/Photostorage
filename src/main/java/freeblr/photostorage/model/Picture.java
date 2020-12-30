package freeblr.photostorage.model;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Picture {

    private Long id;
    private int width;
    private int height;
    private long size;
    private String type;
    private String description;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String url;
}
