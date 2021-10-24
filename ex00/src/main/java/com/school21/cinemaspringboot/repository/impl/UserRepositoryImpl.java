//package com.school21.cinemaspringboot.repository.impl;
//
//import com.school21.cinemaspringboot.model.User;
//import com.school21.cinemaspringboot.repository.UserRepository;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.NoResultException;
//import javax.persistence.PersistenceContext;
//
//@Repository
//public class UserRepositoryImpl implements UserRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    @Transactional
//    public Long save(User user) {
//        entityManager.persist(user);
//        return user.getId();
//    }
//
//    @Override
//    @Transactional
//    public User findByLogin(String login) {
//        try {
//            return entityManager.createQuery("SELECT new User(u.id, u.login) FROM User u WHERE u.login = :login", User.class)
//                    .setParameter("login", login)
//                    .getSingleResult();
//        } catch (NoResultException e) {
//            return null;
//        }
//    }
//}
