#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;

public class RowTest {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeClass
    public static void initEntityManager() {
        emf = Persistence.createEntityManagerFactory("PU");
        em = emf.createEntityManager();
    }

    @Test
    public void itShouldCreateEntity() {
        Row row = new Row("value");

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(row);
        tx.commit();
        em.clear();

        Row newRow = em.createQuery("select r from Row r where r.value = :value", Row.class)
                .setParameter("value", "value").getSingleResult();
        assertNotNull(newRow);
    }

    @AfterClass
    public static void closeEntityManager() {
        Optional.ofNullable(em).ifPresent(EntityManager::close);
        Optional.ofNullable(emf).ifPresent(EntityManagerFactory::close);
    }
}
