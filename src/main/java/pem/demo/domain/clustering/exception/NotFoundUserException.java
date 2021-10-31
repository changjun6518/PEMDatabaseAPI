package pem.demo.domain.clustering.exception;

import javassist.NotFoundException;

public class NotFoundUserException extends NotFoundException {
    public NotFoundUserException() {
        super("해당 유저가 없습니다!");
    }
}
