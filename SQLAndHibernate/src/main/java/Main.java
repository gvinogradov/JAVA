import Entity.*;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {

            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<LinkedPurchaseList> linkedPCriteria = builder.createQuery(LinkedPurchaseList.class);
            Root<LinkedPurchaseList> rootLinkedPList = linkedPCriteria.from(LinkedPurchaseList.class);
            linkedPCriteria.select(rootLinkedPList);
            HashSet<LinkedPurchaseList> linkedPSet = (HashSet<LinkedPurchaseList>) session.createQuery(linkedPCriteria)
                    .getResultList().stream().collect(Collectors.toSet());

            CriteriaQuery<PurchaseList> pCriteria = builder.createQuery(PurchaseList.class);
            Root<PurchaseList> root = pCriteria.from(PurchaseList.class);
            pCriteria.select(root);
            List<PurchaseList> purchaseLists = session.createQuery(pCriteria).getResultList();



            Transaction transaction = session.beginTransaction();

            purchaseLists.forEach(p -> {
                LinkedPurchaseList record = new LinkedPurchaseList(
                        p.getStudent().getId(),
                        p.getCourse().getId());
                if (linkedPSet.contains(record)) {
                    return;
                }
                session.persist(record);
            });

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        sessionFactory.close();
    }
}
