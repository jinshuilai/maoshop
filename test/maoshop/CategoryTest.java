package maoshop;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CategoryTest {

	@Test
	public void test() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		SqlSessionFactory sf = (SqlSessionFactory) ac.getBean("sqlSessionFactory");
		System.out.println(sf);
	}

}
