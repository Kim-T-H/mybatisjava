package mybatisjava;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mapper.Student;
import mapper.StudentMapper;

public class Main4 {
	private final static String NS="mapper.StudentMapper.";
	private static SqlSessionFactory sqlMap;
	static {
		InputStream input=null;
		try {
			input =Resources.getResourceAsStream("mapper/mybatis-config.xml");
		}	catch(IOException e ) {
			e.printStackTrace();
		}
		sqlMap = new SqlSessionFactoryBuilder().build(input);
	}
	public static void main(String[] args) {
		SqlSession session = sqlMap.openSession();
		System.out.println("모든 학생 정보 조회하기");
		System.out.println("xml 방식으로 호출하기");
		List<Student> list = session.selectList(NS+"select");
		for(Student s : list) System.out.println(s);
		
		System.out.println("인터페이스 방식으로 호출하기");
		
		
		//StudentMapper 클래스의 Class 객체 리턴
		Class<StudentMapper> css= StudentMapper.class;
		list = session.getMapper(StudentMapper.class).select(null);
		for(Student s : list) System.out.println(s);
		
		System.out.println("1학년 학생 정보 조회하기");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("grade", 1);
		
		list = session.getMapper(StudentMapper.class).select(map);
		for(Student s: list) System.out.println(s);
				
		System.out.println("981213  학번 학생 정보 조회하기");
		map.clear();
		map.put("studno",981213);
		list =session.getMapper(StudentMapper.class).select(map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("몸무게가 80이상인 학생 정보 조회하기");
		map.clear();
		map.put("weight",80);
		list = session.getMapper(StudentMapper.class).select(map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("김삿갓 학생정보 추가하기");
		Student st= new Student();
		st.setStudno(1001);
		st.setName("김삿갓");
		st.setJumin("1234561234567");
		st.setId("kimsk");
		int result=session.getMapper(StudentMapper.class).insert(st);
		System.out.println(result+"건 추가");
		
		System.out.println("김삿갓 학생정보 조회하기");
		map.clear();
		map.put("studno", 1001);
		list=session.getMapper(StudentMapper.class).select(map);
		for(Student s: list) System.out.println(s);
		
		System.out.println("김삿갓 학생의 학년:1, 몸무게:80 , 키:175, 변경하기");
		st.setName("김삿갓");
		st.setGrade(1);
		st.setWeight(80);
		st.setHeight(175);
		result =session.getMapper(StudentMapper.class).update(st);
		for(Student s : list) System.out.println(s);
		System.out.println(result+"건 변경");
		
		System.out.println("김삿갓 학생정보 조회하기");
		map.clear();
		map.put("studno", 1001);
		list = session.getMapper(StudentMapper.class).select(map);
		for(Student s: list)	System.out.println(s);
		
		System.out.println("학번이 1001번인 학생정보 삭제하기");
		result = session.getMapper(StudentMapper.class).delete("김삿갓",1001);
		System.out.println(result+"건 삭제");
		System.out.println("김삿갓 학생정보 조회하기");
		map.clear();
		map.put("studno",1001);
		list = session.getMapper(StudentMapper.class).select(map);
	
		

	}

}
