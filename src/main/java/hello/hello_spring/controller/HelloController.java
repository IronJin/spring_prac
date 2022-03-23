package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello";
    }

    /*
    3월 20일 공부내용(MVC 부분)
    name 을 받아서 hello-tamplate 로 넘어간다.
    hello-template.html 의 ${name} 부분이 컨트롤러에서 받은 name 을 치환해줘서 값이 출력되는것임
    모델에 키(name)의 value(spring)이 전달되고 스프링에도 전달된다. 스프링은 뷰에 전달을 해주고 템플릿엔진이
    html 을 변환해서 웹 브라우저에 넘겨준다.
     */
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        //"name" 은 키값, name 은 value
        model.addAttribute("name", name);
         return "hello-template";
    }

    /*
    3월 20일 공부내용(API부분)
    @ResponseBody 란 http에서 header 와 body부가 있는데 body부에 데이터를 직접 넣어주겠다는 소리이다.
     빌드 후 http://localhost:8080/hello-string?name=spring!!! 을 통해 name 에 spring!!!을 전달했다.
     이 경우 페이지 소스보기를 통해 확인했을때 나오는 값은 html 텍 하나 없이
     hello spring 만 소스코드로 나오게 된다.
     */
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; //만약 name 으로 spring을 받았다면 출력은 hello spring
    }

    /*
    3월 20일 API 부분
    Hello 라는 클래스 객체를 만들어주고 HTTP 에서 name 에 직접 값을 넣어주고
    Hello 객체를 선언해주고 그것을 setName 을 통해 값을 받아줌
    return 값으로 객체를 넘겨주는 방식이다.
    출력은 key와 value 로 이루어진 json 방식으로 전달이 된다.
     */
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    //hello-api 에 전달될 객체 클래스
    //여기서 key 는 name/ value 는 name 에 전달된 spring!! 이다.
    static class Hello{
        //컨트롤 + n 을 눌러서 getter and setter 를 찾아주고 생성
        private String name;

        //private 로 name을 선언했으므로 getter와 setter 등 메소드를 통해 받아야함
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }





}
