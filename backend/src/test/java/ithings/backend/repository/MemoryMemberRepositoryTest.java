package ithings.backend.repository;

import ithings.backend.domain.MemberBasic;
import ithings.backend.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }
    @Test
    public void save() {
        //given
        MemberBasic member = new MemberBasic();
        member.setName("spring");
        //when
        repository.save(member);
        //then
        MemberBasic result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }
    @Test
    public void findByName() {
        //given
        MemberBasic member1 = new MemberBasic();
        member1.setName("spring1");
        repository.save(member1);
        MemberBasic member2 = new MemberBasic(); //shift+f6 꿀팁
        member2.setName("spring2");
        repository.save(member2);
        //when
        MemberBasic result = repository.findByName("spring1").get();
        //then
        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll() {
        //given
        MemberBasic member1 = new MemberBasic();
        member1.setName("spring1");
        repository.save(member1);
        MemberBasic member2 = new MemberBasic();
        member2.setName("spring2");
        repository.save(member2);
        //when
        List<MemberBasic> result = repository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
    }
}
