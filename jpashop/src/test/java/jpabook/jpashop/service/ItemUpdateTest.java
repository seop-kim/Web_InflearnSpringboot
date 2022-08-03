package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ItemUpdateTest {

    @Autowired EntityManager entityManager;

    @Test
    public void updateTest() throws Exception {
        Book book = entityManager.find(Book.class, 1L);

        // TX
        book.setName("asdfasdf");

        // 변경감지 == dirty checking


    }
}
