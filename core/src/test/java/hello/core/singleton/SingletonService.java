package hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();

    // Singleton 객체를 얻어가기 위해서 get 메소드를 생성해주도록 한다.
    public static SingletonService getInstance() {
        return instance;
    }

    // 외부에서 new 호출을 막기 위해 생성자를 private 으로 생성한다.
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
