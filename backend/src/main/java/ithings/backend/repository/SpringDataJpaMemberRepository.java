package ithings.backend.repository;

import ithings.backend.domain.MemberBasic;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface SpringDataJpaMemberRepository extends JpaRepository<MemberBasic,Long>, MemberRepository {

    @Override
    Optional<MemberBasic> findByName(String name);
}