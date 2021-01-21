package info.freeit.photostorage.model;

import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Value
@NoArgsConstructor
public class RegistrationDto {

    private String phoneNumber = null;

    @NotBlank(message = "Given name cannot be empty")
    private String givenName = null ;

    @NotBlank(message = "Family name cannot be empty")
    private String familyName = null;

    @NotBlank(message = "Picture url cannot be empty")
    private String pictureUrl = null;

}
