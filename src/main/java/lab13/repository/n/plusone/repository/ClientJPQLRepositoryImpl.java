package lab13.repository.n.plusone.repository;

import lab13.domain.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ClientJPQLRepositoryImpl extends CustomRepositorySupport implements ClientJPQLRepository {
    @Override
    public List<Client> findAllJPQL() {
        EntityManager manager = getEntityManager();
        Query query = manager.createQuery(
                "select distinct c from  " + Client.class.getName() +
                        " c left join fetch c.rentals ");

        return new ArrayList<Client>(query.getResultList());
    }

}
