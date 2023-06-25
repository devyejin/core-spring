package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService(); //자기 자신을 내부에 가짐
    //static 이기 때문에, 클래스로더에 클래스가 올라갈 때 가장 먼저 생성되고 instance 변수에 참조되겠지

    //private 생성자로 생성을 막아버려라
    private SingletonService() {}


    public static SingletonService getInstance() {
        return instance;
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }




}
