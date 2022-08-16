package com.hwang.staste.repository;

import com.hwang.staste.model.entity.Sticker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StickerRepository {

    private final EntityManager em;

    public Sticker findOne(Long id) { return em.find(Sticker.class, id); };

    public List<Sticker> findStickers() {
        return em.createQuery("select s from Sticker s", Sticker.class)
                .getResultList();
    };

    public List<Sticker> findStickers(Long storeId){
        return em.createQuery("select s from Sticker s where s.id = :storeId", Sticker.class)
                .setParameter("id",storeId)
                .getResultList();
    };

}