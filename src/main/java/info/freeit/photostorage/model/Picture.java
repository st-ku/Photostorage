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
    private String type;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String url;
    private String metaData;

}
