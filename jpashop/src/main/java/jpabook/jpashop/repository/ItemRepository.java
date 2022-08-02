package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {


    private final EntityManager entityManager;

    public void save(Item item) {
        // item.id 는 DB에 저장될 떄 부여된다. 이미 ID 값이 있으면 해당 객체는 값을 수정하여 저장하는 것으로 판단
        if (item.getId() == null) {
            entityManager.persist(item);
        } else {
            entityManager.merge(item);
        }
    }

    public Item findOne(Long id) {
        return entityManager.find(Item.class, id);
    }

    public List<Item> findAll() {
        return entityManager.createQuery("select i from item i", Item.class).getResultList();
    }
}
