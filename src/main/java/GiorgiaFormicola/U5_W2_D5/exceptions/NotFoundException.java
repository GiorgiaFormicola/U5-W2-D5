package GiorgiaFormicola.U5_W2_D5.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String resourceType, UUID resourceId) {
        super("The " + resourceType + " with id " + resourceId + " has not been found.");
    }
}
