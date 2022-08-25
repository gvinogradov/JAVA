package service;

import dao.LinkedPurchaseListDAO;
import entity.LinkedPurchaseList;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import utils.SessionUtility;

import java.sql.SQLException;
import java.util.List;

public class LinkedPurchaseListService extends SessionUtility implements LinkedPurchaseListDAO {

    @Override
    public void add(LinkedPurchaseList linkedPurchaseList) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.merge(linkedPurchaseList);
        closeTransactionSession();
    }

    @Override
    public void addAll(List<LinkedPurchaseList> linkedPurchaseLists) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        linkedPurchaseLists.forEach(l -> session.persist(l));
        closeTransactionSession();
    }

    @Override
    public void remove(LinkedPurchaseList linkedPurchaseList) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.remove(linkedPurchaseList);
        closeTransactionSession();
    }

    @Override
    public void update(LinkedPurchaseList linkedPurchaseList) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.merge(linkedPurchaseList);
        closeTransactionSession();
    }

    @Override
    public List<LinkedPurchaseList> getAll() throws SQLException {
        openTransactionSession();
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<LinkedPurchaseList> lCriteriaQuery = builder.createQuery(LinkedPurchaseList.class);
        Root<LinkedPurchaseList> root = lCriteriaQuery.from(LinkedPurchaseList.class);
        lCriteriaQuery.select(root);
        List<LinkedPurchaseList> courseList = session.createQuery(lCriteriaQuery).getResultList();
        closeTransactionSession();
        return courseList;
    }
}
