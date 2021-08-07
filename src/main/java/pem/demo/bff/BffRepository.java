package pem.demo.bff;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface BffRepository extends JpaRepository<Bff, Long> {

    public Bff findBffByMemberName(String name);
}
