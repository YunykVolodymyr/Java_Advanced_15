package ua.yunyk;

import java.util.Arrays;
import java.util.HashSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Application {

	public static void main(String[] args) {
		
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();
		
		Post post = new Post();
		post.setTitle("Java");
		
		Comment comment1 = new Comment("commentator1", post);
		Comment comment2 = new Comment("commentator2", post);
		Comment comment3 = new Comment("commentator3", post);
		Comment comment4 = new Comment("commentator4", post);

		post.setComments(new HashSet<Comment>( Arrays.asList(comment1, comment2, comment3, comment4)));
		
		session.getTransaction().begin();
		session.persist(post);		
		session.getTransaction().commit();
		
		System.out.println("\n \n \n");
		Post p = session.get(Post.class, 1);
		System.out.println(p);
		for(Comment comment: p.getComments()) {
			System.out.println(comment);
		}
		session.close();
		sessionFactory.close();
	}
	
}
