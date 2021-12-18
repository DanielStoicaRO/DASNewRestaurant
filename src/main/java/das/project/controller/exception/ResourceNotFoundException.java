package das.project.controller.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String detailMessage) {
        super(detailMessage);
    }

}
