package es.ricardo.hibernate.tests;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.ricardo.hibernate.modelo.Empleado;

public class TestEmpleados {

	
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");;
	
	/*public static void main(String[] args) {
		//Crear el gestor de pestistencia (EM)
		
		insertInicial();
		
		imprimirTodo();
		
		EntityManager man = emf.createEntityManager();
		
		man.getTransaction().begin();
		
		Empleado e = man.find(Empleado.class, 10L);
		
		
		e.setNombre("David");
		
		e.setApellidos("Lopez");
		
		man.getTransaction().commit();
		
		imprimirTodo();
		
		man.close();
	}*/
	
	public static void main(String[] args) {
		EntityManager man = emf.createEntityManager();
		Empleado e = new Empleado(10L, "Perez", "Pepito", LocalDate.of(1979, 6,10));
		man.getTransaction().begin();
		man.persist(e);
		man.getTransaction().commit();
		man.close();
		
		imprimirTodo();
		
		man = emf.createEntityManager();
		man.getTransaction().begin();
		//man.merge(e) o la siguiente linea
		e = man.merge(e);
		e.setNombre("Dani");
		//man.merge(e);
		man.remove(e);
		man.getTransaction().commit();
		man.close();
		
		imprimirTodo();
	}

	@SuppressWarnings("unused")
	private static void insertInicial() {
		EntityManager man = emf.createEntityManager();
		
		Empleado e = new Empleado(10L, "Perez", "Pepito", LocalDate.of(1979, 6,6));
		man.getTransaction().begin();
		man.persist(e);
		man.getTransaction().commit();
		man.close();
	}
	
	@SuppressWarnings("unchecked")
	private static void imprimirTodo() {
		EntityManager man = emf.createEntityManager();
		List<Empleado> emps = (List<Empleado>) man.createQuery("FROM Empleado").getResultList();
		System.out.println("Hay "+emps.size()+" empleados en el sistema");
		for (Empleado emp:emps) {
			System.out.println(emp.toString());
		}
		man.close();
	}
	
}
