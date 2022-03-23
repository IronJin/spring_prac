package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        //sequence 값은 0부터 시작하는것임
        //즉, save 는 회원의 정보를 입력받아 저장하는것이다.
        //따라서 sequence 값을 save가 호출될때마다 1증가 시켜주고
        //sequence 값을 setId 메소드를 통해 Id로 받아온다.
        member.setId(++sequence);
        //store 는 Long 값을 키값으로 갖고 id와 이름을 value 로 갖는 맵 자료구조 형태이다.
        //따라서 store.put 을 통해 id 를 키값으로 넣어줬고 member는 member 객체에 이미 받아졌을 것이다.
        //즉, member.setId를 통해 id를 다시 설정해주고 그것을 키값으로 받아주고
        //이미 받아온 member 값을 value에 저장해준다.
        store.put(member.getId(), member);
        //그리고 member를 반환해준다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //store에서 get을 통해 id를 꺼내오면된다.
        //만약 id가 Map에 없는경우가 있을것이다.
        //이때 Optional을 통해 Null값이 오더라도 감싸줄수있다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        //store에서 루프를 돌면서 filter 함수를 통해 member에 저장된 name이
        //findByName 에서 받아온 name 이랑 같은지 확인하고 같다면 그 첫번째 값을 반환시켜주는 것임
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    //실무에서 리스트를 많이 쓰고 store에 value 인 member를 반환해준다.
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    //혼자 연습해본 부분임임
    /*    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

     */




}
