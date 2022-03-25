package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

//테스트케이스를 작성하는 부분
public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();


    //각 메소드를 테스트하고 끝날때마다 저장소를 다 비워주는 메소드임임
   @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("Yang");
        repository.save(member);
        Member result = repository.findById(member.getId()).get();

        //기대한값이랑 실제한 값이랑 같다면 정상적으로 run
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring2").get();

        assertThat(result).isEqualTo(member2);

    }

    @Test
    public void findAll(){

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member1.setName("spring2");
        repository.save(member1);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }

}
