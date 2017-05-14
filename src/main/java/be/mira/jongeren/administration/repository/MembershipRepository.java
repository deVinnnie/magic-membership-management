package be.mira.jongeren.administration.repository;

import be.mira.jongeren.administration.domain.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

}
