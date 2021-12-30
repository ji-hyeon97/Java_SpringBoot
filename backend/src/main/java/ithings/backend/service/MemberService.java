package ithings.backend.service;

import ithings.backend.domain.MemberBasic;
import ithings.backend.repository.MemberRepository;
import ithings.backend.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService { //control + shift + t
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    /**
     * 회원가입
     */
    public Long join(MemberBasic member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(MemberBasic member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /**
     * 전체 회원 조회
     */
    public List<MemberBasic> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<MemberBasic> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}