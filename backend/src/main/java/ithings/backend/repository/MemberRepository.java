package ithings.backend.repository;
import ithings.backend.domain.MemberBasic;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    MemberBasic save(MemberBasic member);

    Optional<MemberBasic> findById(Long id);

    Optional<MemberBasic> findByName(String name);

    List<MemberBasic> findAll();
}
