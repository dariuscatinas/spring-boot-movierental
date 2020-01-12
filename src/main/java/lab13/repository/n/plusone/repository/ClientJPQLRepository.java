package lab13.repository.n.plusone.repository;

import lab13.domain.Client;

import java.util.List;

public interface ClientJPQLRepository{
    List<Client> findAllJPQL();
}
