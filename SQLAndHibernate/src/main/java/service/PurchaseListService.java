package service;

import dao.PurchaseListDAO;
import entity.PurchaseList;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import utils.SessionUtility;

import java.sql.SQLException;
import java.util.List;

public class PurchaseListService extends SessionUtility implements PurchaseListDAO {
    @Override
    public void add(PurchaseList purchaseList) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.persist(purchaseList);
        closeTransactionSession();
    }

    @Override
    public void addAll(List<PurchaseList> purchaseLists) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        purchaseLists.forEach(p -> session.persist(p));
        closeTransactionSession();
    }

    @Override
    public void remove(PurchaseList purchaseList) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.remove(purchaseList);
        closeTransactionSession();
    }

    @Override
    public void update(PurchaseList purchaseList) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.merge(purchaseList);
        closeTransactionSession();
    }

    @Override
    public List<PurchaseList> getAll() throws SQLException {
        openTransactionSession();
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PurchaseList> pCriteriaQuery = builder.createQuery(PurchaseList.class);
        Root<PurchaseList> root = pCriteriaQuery.from(PurchaseList.class);
        pCriteriaQuery.select(root);
        List<PurchaseList> courseList = session.createQuery(pCriteriaQuery).getResultList();
        closeTransactionSession();
        return courseList;
    }
}
