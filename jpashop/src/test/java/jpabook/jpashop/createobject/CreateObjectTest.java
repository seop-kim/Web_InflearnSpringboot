package jpabook.jpashop.createobject;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CreateObjectTest {

    @Autowired MemberService memberService;
    @Autowired ItemService itemService;
    @Autowired OrderService orderService;


    @Test
    @DisplayName("멤버생성")
    public Long createMember() {
        Member member = new Member();
        member.setName("taeseop");
        member.setAddress(new Address("서울", "초록마을로", "33-26"));
        Long memberId = memberService.join(member);
        return memberId;
    }

    @Test
    @DisplayName("아이템 생성")
    public Item createItem() {
        Book book = new Book();
        book.setName("JPA");
        book.setPrice(5000);
        book.setAuthor("seop");
        book.setIsbn("1234");
        book.setStockQuantity(100);
        itemService.saveItem(book);
        return book;
    }

    @Test
    @DisplayName("주문 생성")
    public void createOrder() {
        Long memberId = createMember();
        Long bookId = createItem().getId();
        orderService.order(memberId, bookId, 10);
    }
}