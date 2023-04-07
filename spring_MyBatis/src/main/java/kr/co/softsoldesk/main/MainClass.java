package kr.co.softsoldesk.main;

import java.util.List;

import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.co.softsoldesk.beans.JdbcBean;
import kr.co.softsoldesk.config.BeanConfigClass;
import kr.co.softsoldesk.mapper.MapperInterface;

public class MainClass  {

	public static void main(String[] args) {
		
		System.out.println("===============java=============================");
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfigClass.class);
		
		MapperInterface mapper = ctx.getBean("test_mapper", MapperInterface.class);
		
		//칼럼오라클명과  Bean의 변수이름이 같으면 생략
		/*
		@Results({
			@Results(column = "int_data", property ="int_data"),
			@Result(column = "str_data", property ="str_data")
		})
		*/
		
		
		//insert
		JdbcBean bean2 = new JdbcBean();
		bean2.setInt_data(13);
		bean2.setStr_data("spring frame work4");
		
		mapper.insert_data(bean2);
		System.out.println("저장");
		//select
		

		List<JdbcBean> list = mapper.select_data();
		for(JdbcBean bean1:list) {
			System.out.println("int_data" + bean1.getInt_data());
			System.out.println("str_data" + bean1.getStr_data());
			System.out.println("-----------------------");
		}
		
		//update
		/*
		JdbcBean bean3 = new JdbcBean();
		bean3.setInt_data(17);
		bean3.setStr_data("jsp");
		mapper.update_data(bean3);
		*/
		//delete
		JdbcBean bean4 = new JdbcBean();
		
		mapper.delete_data(11);
		
		
		ctx.close();
	}
}