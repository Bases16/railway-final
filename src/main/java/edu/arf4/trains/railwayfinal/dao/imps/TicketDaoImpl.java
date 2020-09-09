package edu.arf4.trains.railwayfinal.dao.imps;

import edu.arf4.trains.railwayfinal.dao.TicketDao;
import edu.arf4.trains.railwayfinal.model.Ticket;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Repository
public class TicketDaoImpl implements TicketDao {

    @PersistenceContext(unitName = "entityManagerFactory", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    public Long addTicket(Ticket ticket) {

        em.persist(ticket);
        return ticket.getId();
    }


//    @Override
//    public List<Ticket> getTicketsByTrainId(Long trainId) {
//
//        String query = "SELECT ticket FROM Ticket ticket WHERE ticket.train.id = :id";
//
//        List<Ticket> tickets = null;
//
//        tickets = em.createQuery(query, Ticket.class).setParameter("id", trainId).getResultList();
//
//        return tickets;
//    }
}
