package info.freeit.photostorage.model;

import java.util.Arrays;

public enum Role {
    USER, ADMIN;

    public Role fromString(String roleName) {
        return Arrays.stream(Role.values())
                .filter(it -> it.name().toLowerCase().equalsIgnoreCase(roleName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("provided string has no нужная role"));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
