package ithings.backend.repository;

import ithings.backend.domain.MemberBasic;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
public class JpaMemberRepository implements MemberRepository {
    private final EntityManager em;
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }
    public MemberBasic  save(MemberBasic member) {
        em.persist(member);
        return member;
    }
    public Optional<MemberBasic> findById(Long id) {
        MemberBasic  member = em.find(MemberBasic.class, id);
        return Optional.ofNullable(member);
    }
    public List<MemberBasic> findAll() {
        return em.createQuery("select m from MemberBasic m", MemberBasic.class)
                .getResultList();
    }
    public Optional<MemberBasic> findByName(String name) {
        List<MemberBasic> result = em.createQuery("select m from MemberBasic m where m.name = :name", MemberBasic.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }
}
