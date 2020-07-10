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

public class Main2 {
	private final static String NS="student.";	//상수
	private static SqlSessionFactory sqlMap;	//클래스 변수
	static {	//static 초기화 블럭: 클래스 변수의 초기화
		InputStream input=null;
		try {
			input =Resources.getResourceAsStream("mapper/mybatis-config.xml");
		} catch(IOException e) {
			e.printStackTrace();
		}
		sqlMap= new SqlSessionFactoryBuilder().build(input);
	}
	public static void main(String[] args) {
		SqlSession session=sqlMap.openSession();
		System.out.println("Student 테이블에 레코드 추가하기");
		Student st= new Student();
		st.setStudno(1000);
		st.setName("홍길동");
		st.setGrade(1);
		st.setId("hongkd2");
		st.setJumin("123456");
		st.setMajor1(101);
		//cnt : 레코드 변경 건수. 추가된 레코드 수.
		int cnt=session.insert(NS+"insert", st);
		System.out.println("student 레코드 추가:"+cnt);
		Student stu=session.selectOne(NS+"selectno",st.getStudno());
		System.out.println(stu);
		/*
		 * 1000번 학생의 학년을 2학년으로, 몸무게 80, 키를175로 
		 * 지도교수를  1001로 수정하기
		 */
		
		st.setStudno(1000);
		st.setGrade(2);
		st.setWeight(80);
		st.setHeight(175);
		st.setProfno(1001);
		cnt= session.update(NS+"update",st);
		stu=session.selectOne(NS+"selectno",st.getStudno());
		System.out.println(stu);
	//	session.commit(); // 물리적으로 변경 완료.
		
		System.out.println("성이 김씨인 학생 정보 출력하기======");
		List<Student> list =session.selectList(NS+"selectname1","김");
		for(Student m : list) {
			 System.out.println(m);
		}
		System.out.println("몸무게가 75이상인 학생 정보 출력하기======");
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("col", "weight");
		map.put("val", 75);
		list = session.selectList(NS+"select2",map);
		for(Student m : list) {
			System.out.println(m);
		}

		System.out.println("키가  175이상인 학생 정보 출력하기======");
		map.put("col", "height");
		map.put("val", 175);
		list = session.selectList(NS+"select2",map);
		for(Student m: list) {
			System.out.println(m);
		}
		
		System.out.println("주민번호가 970101 이후인 학생 정보======================");		//게시판 정렬할때 사용할수 있음.
		map.put("col", "jumin");
		map.put("val", "970101");
		list = session.selectList(NS+"select2",map);
		for(Student m: list) {
			System.out.println(m);
		}
		
		System.out.println("학생 중 유진성 학생 정보 삭제하기");
		map.clear();
		map.put("col", "name");
		map.put("val", "유진성");
		cnt = session.delete(NS+"delete",map);
		System.out.println("student 레코드 삭제:" +cnt);
		stu = session.selectOne(NS+"select4",map);
		System.out.println(stu); //null
		
		System.out.println("1학년 학생 정보 삭제하기");
		map.clear();
		map.put("col", "grade");
		map.put("val", 1);
		cnt = session.delete(NS+"delete",map);
		System.out.println("student 레코드 삭제:" +cnt);
		
	}
	
	}


