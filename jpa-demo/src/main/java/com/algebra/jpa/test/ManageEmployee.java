package com.algebra.jpa.test;

import com.algebra.jpa.entity.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

/**
 * @author al
 * @date 2021/2/5 14:05
 * @description
 */
public class ManageEmployee {
    private static SessionFactory factory;

    public static void main(String[] args) {

        try {
//            factory = new Configuration().configure().buildSessionFactory();
            factory = new Configuration()
                    .configure()
//                    .addPackage("com.algebra.jpa.entity")
                    .addAnnotatedClass(Employee.class)
                    .buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.err.println("Failed to create sessionFactory object." + e);
        }

        ManageEmployee me = new ManageEmployee();

//        me.addEmployee("lily", "jackson", new BigDecimal("12000.25"));


        me.listEmployees();

        me.getEmployeeById(1);

        me.getEmployeeListPage();

    }

    public void getEmployeeListPage(){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "FROM Employee AS e";
            Query query = session.createQuery(hql);
            query.setFirstResult(1);
            query.setMaxResults(2);
            List list = query.list();
            System.out.println(list.toString());
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) {
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void getEmployeeById(Integer id){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "FROM Employee AS e where e.id = :eid";
            Query query = session.createQuery(hql);
            query.setParameter("eid",id);
            List list = query.list();
            System.out.println(list.get(0).toString());
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) {
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }


    /* Method to CREATE an employee in the database */
    public Integer addEmployee(String fname, String lname, BigDecimal salary){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeID = null;
        try{
            tx = session.beginTransaction();
            Employee employee = new Employee(fname, lname, salary);
            employeeID = (Integer) session.save(employee);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) {
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        return employeeID;
    }
    /* Method to  READ all the employees */
    public void listEmployees( ){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Employee").list();
            for (Iterator iterator =
                 employees.iterator(); iterator.hasNext();){
                Employee employee = (Employee) iterator.next();
                System.out.print("First Name: " + employee.getFirstName());
                System.out.print("  Last Name: " + employee.getLastName());
                System.out.println("  Salary: " + employee.getSalary());
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) {
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    /* Method to UPDATE salary for an employee */
    public void updateEmployee(Integer EmployeeID, BigDecimal salary ){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Employee employee =
                    (Employee)session.get(Employee.class, EmployeeID);
            employee.setSalary( salary );
            session.update(employee);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) {
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    /* Method to DELETE an employee from the records */
    public void deleteEmployee(Integer EmployeeID){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Employee employee =
                    (Employee)session.get(Employee.class, EmployeeID);
            session.delete(employee);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) {
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

}
