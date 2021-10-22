package pem.demo.domain.mobilityData.exception;

import java.sql.SQLDataException;

public class DuplicationException extends SQLDataException {
    public DuplicationException() {
        super("데이터가 중복되었습니다!");
    }
}
