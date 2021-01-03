package info.freeit.photostorage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

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
