package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    /**
     * @param member 회원 객체
     * @param price  상품의 가격
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);
}
