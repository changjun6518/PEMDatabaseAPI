package pem.demo.domain.bff;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BffRepository extends JpaRepository<Bff, Long> {

    public Bff findBffByMemberName(String name);
}
