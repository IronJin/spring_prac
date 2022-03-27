package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//컨트롤 + 쉬프트 + T 를 눌러서 테스트를 생성해줄 수 있음

public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입 함수
    //비지니스 로직(rule) : 중복회원이 있으면 안되므로 같은이름을 가진 중복회원은 안됨
    public Long join(Member member){

        //같은 이름이 있는 중복회원을 받지 않음
        //Refactor를 사용하여 함수화 해준것임
        validateduplicatemember(member);
        //중복회원을 검사하고 없다면 저장해준다.
        memberRepository.save(member);
        return member.getId();
    }

    private void validateduplicatemember(Member member) {
        memberRepository.findByName(member.getName())
        //ifPresent는 Optional의 메소드로써
        //만약 이 값이 있으면 작동하는 메소드임
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다");
            });
    }


    //전체회원 조회
    public List<Member> findMembers(){

        return memberRepository.findAll();

    }

    public Optional<Member> findOne(Long memberId){

        return memberRepository.findById(memberId);

    }

}
