package hello.hello_spring.domain;

public class Member {

    //id 와 이름 변수 선언
    //id 는 시스템이 저장하도록 설정할 것임
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
