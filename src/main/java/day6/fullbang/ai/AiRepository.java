package day6.fullbang.ai;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import day6.fullbang.ai.domain.AiInputData;

@Repository
public class AiRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(AiInputData aiInputData) {
        em.persist(aiInputData);
        return aiInputData.getId();
    }

    public List<AiInputData> findAll() {
        return em.createQuery("SELECT a FROM AiInputData a", AiInputData.class)
            .getResultList();
    }

    public List<AiInputData> findNoSend() {
        return em.createQuery("SELECT a FROM AiInputData a "
                + "WHERE a.isSend = FALSE", AiInputData.class)
            .getResultList();
    }

    public void checkEmail(Long id) {
        em.createQuery("UPDATE AiInputData a SET a.isSend = true "
                + "WHERE a.id = :id")
            .setParameter("id", id)
            .executeUpdate();
        em.clear();
    }
}
