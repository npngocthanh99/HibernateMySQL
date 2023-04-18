package com.thanhnpn99;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.thanhnpn99.model.Address;
import com.thanhnpn99.model.Course;
import com.thanhnpn99.model.Fresher;
import com.thanhnpn99.model.Group;
import com.thanhnpn99.model.Syllabus;
import com.thanhnpn99.util.HibernateUtil;

public class Management {

	public static void main(String[] args) {
//		createCourseSyllabuses();
//		getCourseSyllabuses(1);
//		createFresherAndAddress();
//		createFresherAndCourse();
//		createFresherAndGroup();
		createGroup();
//		deleteGroupUsingHQL();
		useCriteria();
		HibernateUtil.shutdown();
	}
	
	private static void useCriteria() {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<Group> criteria = builder.createQuery( Group.class );
			Root<Group> root = criteria.from( Group.class );
			
			criteria.select( root.get("name") );
			criteria.where( builder.equal( root.get("id"), 2));
			
			List<Group> results = session.createQuery( criteria ).getResultList();
			System.out.println(results);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private static void deleteGroupUsingHQL() {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String queryStr = "delete from Group Where id = :id";
			Query query = session.createQuery(queryStr);
			query.setParameter("id", 2);
			int result = query.executeUpdate();
			System.out.println(result);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private static void updateGroupUsingHQL() {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String queryStr = "Update Group set name = :name Where id = :id";
			Query query = session.createQuery(queryStr);
			query.setParameter("id", 2);
			query.setParameter("name", "Funny Java Group");
			int result = query.executeUpdate();
			System.out.println(result);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void queryGroupUsingHQL() {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
//			String queryStr = "FROM Group where id = :id and name like :name";
			String queryStr = "SELECT name FROM Group where id = :id and name like :name";
			Query query = session.createQuery(queryStr);
			query.setParameter("id", 2);
			query.setParameter("name", "Java%");
			List<Group> groups = (List<Group>)query.getResultList();
			System.out.println(groups);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private static void deleteGroup() {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Group javaGroup = (Group) session.get(Group.class, 1);
			javaGroup.setName("New Java Group");
			session.delete(javaGroup);
			System.out.println(javaGroup);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	private static void updateGroup() {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Group javaGroup = (Group) session.get(Group.class, 1);
			javaGroup.setName("New Java Group");
			session.update(javaGroup);
			System.out.println(javaGroup);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	private static void getGroup() {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Group javaGroup = (Group) session.get(Group.class, 1);
			System.out.println(javaGroup);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	private static void createGroup() {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Group javaGroup = new Group("Java Group");
			Group jsGroup = new Group("JavaScript Group");
			session.save(javaGroup);
			session.save(jsGroup);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	private static void createFresherAndGroup() {
		Fresher fresher1 = new Fresher();
		Fresher fresher2 = new Fresher();
		Group group1 = new Group("Group 1");
		Group group2 = new Group("Group 2");
		Set<Fresher> freshers = new HashSet<>();
		freshers.add(fresher1);
		freshers.add(fresher2);
		Set<Group> groups = new HashSet<>();
		groups.add(group1);
		groups.add(group2);
		fresher1.setName("Fresher 1");
		fresher2.setName("Fresher 2");
		fresher1.setGroups(groups);
		fresher2.setGroups(groups);
		group1.setFreshers(freshers);
		group2.setFreshers(freshers);
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(fresher1);
			session.save(fresher2);
			session.save(group1);
			session.save(group2);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	private static void createFresherAndCourse() {
		List<Course> courses = new ArrayList<Course>();
		courses.add(new Course("Java"));
		courses.add(new Course("Hibernate"));
		Fresher fresher = new Fresher("Thanh", courses);
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			for (Course course : courses) {
				session.save(course);
			}
			session.save(fresher);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	private static void createFresherAndAddress() {
		Address address = new Address("Quang Trung", "Go Vap");
		Fresher fresher = new Fresher("Thanh", address);
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(address);
			session.save(fresher);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	private static void getCourseSyllabuses(int id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Course course = (Course) session.get(Course.class, id);
			System.out.println(course.getName());
			session.getTransaction().commit();
			System.out.println(course.getSyllabus());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	private static void createCourseSyllabuses() {
		List<Syllabus> syllabuses = new ArrayList<Syllabus>();
		syllabuses.add(new Syllabus("Hibernate Online Content", 30));
		syllabuses.add(new Syllabus("Hibernate Offline Content", 30));

		Course course = new Course("Hibernate", new Date(), syllabuses);
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(course);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
