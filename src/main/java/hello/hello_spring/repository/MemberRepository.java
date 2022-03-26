package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    //회원을 저장하도록 하는 저장된 회원이 반환됨
    Member save(Member member);
    //id로 회원을 찾는 기능
    Optional<Member> findById(Long id);
    //name 으로 회원을 찾는 기능
    Optional<Member> findByName(String name);
    //저장된 멤버들의 리스트를 반환해줌
    List<Member> findAll();

}
