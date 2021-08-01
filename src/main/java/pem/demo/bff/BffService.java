package pem.demo.bff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BffService {
    @Autowired
    BffRepository bffRepository;

}
