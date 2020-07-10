package mybatisjava;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mapper.Student;

/*
 * 1. 학생 테이블의 등록된 레코드의 건수를 출력하기. => 
 * 2. 학생 테이블에 등록된 레코드 모든 정보를 출력하기.
 * 3. 학생 테이블에 등록된 레코드 학년별 정보를 출력하기.
 * 4. 학생 테이블에 등록된 레코드 이름이 두자인 정보를 출력하기.
 */

public class Exam1 {

	public static void main(String[] args) {
		SqlSessionFactory sqlMap = null; // sql의 데이터를 찾아줌.
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("mapper/mybatis-config1.xml");
			sqlMap = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int x = 0;
		SqlSession session = sqlMap.openSession(); // db에 접속 session 얻기
		// selectOne : 조회되는 레코드가 한개인 경우
		x = (Integer) session.selectOne("Student.count");
		System.out.println("Student 테이블의 레코드 갯수:" + x);
		
		//학생 테이블에 등록된 레코드 모든 정보를 출력하기.
		System.out.println("학생 테이블에 등록된 레코드 모든 정보를 출력하기========");
		List<Student> list =session.selectList("Student.list");
		for(Student m: list) {
			System.out.println(m);
		}
		//학생 테이블에 등록된 레코드 학년별 정보를 출력하기.
		System.out.println("학생 테이블에 등록된 레코드 학년별 정보를 출력하기========");
		list = session.selectList("Student.selectgrade");
		for(Student m :list) {
			System.out.println(m);
		}
		System.out.println("1.학생 테이블에 등록된 레코드 이름이 두자인 정보를 출력하기.==========");
		list = session.selectList("Student.selectname","__");
		for(Student m : list) {
			System.out.println(m);
		}
		
	
		
	}

}
