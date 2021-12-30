package ithings.backend.repository;
import ithings.backend.domain.MemberBasic;

import java.util.*;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, MemberBasic> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public MemberBasic save(MemberBasic member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    @Override
    public Optional<MemberBasic> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    @Override
    public List<MemberBasic> findAll() {
        return new ArrayList<>(store.values());
    }
    @Override
    public Optional<MemberBasic> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }
    public void clearStore() {
        store.clear();
    }
}