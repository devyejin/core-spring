package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000; //1000원 할인
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) { //ENUM 타입은 equals()가 아닌 == 이용
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
