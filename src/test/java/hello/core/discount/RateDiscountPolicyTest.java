package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o() {
        //given
        Member memberVIP = new Member(1L, "memberVIP", Grade.VIP);

        //when
        int discount = discountPolicy.discount(memberVIP, 20000);

        //then
        Assertions.assertThat(discount).isEqualTo(2000);
    }

    @Test
    @DisplayName("일반은 0% 할인이 적용되어야 한다.")
    void default_x() {
        //given
        Member memberDefault = new Member(2L, "memberDefault", Grade.BASIC);

        //when
        int discount = discountPolicy.discount(memberDefault, 20000);

        //then
        Assertions.assertThat(discount).isEqualTo(0);
    }
}