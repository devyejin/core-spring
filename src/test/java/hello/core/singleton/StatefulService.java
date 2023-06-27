package hello.core.singleton;

public class StatefulService {

//    private int price; //상태를 유지하는 필드

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
//        this.price = price; //여기가 문제!( 싱글턴은 Stateful이라 필드에 값 저장하면 안댕)

        //싱글턴을 stateless로 유지하는 방법
        //객체에 값을 저장하지말고 넘겨버리자!
        return price;
    }


//    public int getPrice() {
//        return this.price;
//    }
}
