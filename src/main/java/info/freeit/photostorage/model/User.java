package info.freeit.photostorage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User  {
    @Id
    @Email
    @NotBlank(message = "Username cannot be empty")
    private String id;

    private String pictureUrl;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @NotBlank(message = "Given name cannot be empty")
    private String givenName;

    @NotBlank(message = "Family name cannot be empty")
    private String familyName;

    private String phoneNumber;

    @UpdateTimestamp
    private LocalDateTime updated;

    @CreationTimestamp
    private LocalDateTime created;

}
