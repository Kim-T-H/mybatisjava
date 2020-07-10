package mybatisjava;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mapper.Member;

public class Main1 {

	public static void main(String[] args) {
		SqlSessionFactory sqlMap = null; // sql의 데이터를 찾아줌.
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("mapper/mybatis-config.xml");
			sqlMap = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int x = 0;
		SqlSession session = sqlMap.openSession(); // db에 접속 session 얻기
		// selectOne : 조회되는 레코드가 한개인 경우
		x = (Integer) session.selectOne("member.count");
		System.out.println("member 테이블의 레코드 갯수:" + x);

		// member 테이블의 정보 읽어서 화면에 출력하기
		// selectList : 조회된 레코드가 여러건인 경우
		List<Member> list = session.selectList("member.list");
		for (Member m : list) {
			System.out.println(m);
		}
		// admin 정보 조회하기
		System.out.println("admin 정보==========");
		Member mem = session.selectOne("member.selectid", "admin");
		System.out.println(mem);

		// 이름 정보로 조회하기
		System.out.println("이름 정보로 조회하기================");
		list = session.selectList("member.selectname", "동");
		for (Member m : list) {
			System.out.println(m);
		}
		// 이름 정보로 조회하기2
		System.out.println("이름 정보로 조회하기2================");
			list = session.selectList("member.selectname2", "%동%");
			for (Member m2 : list) {
				System.out.println(m2);
			}
			//두 개의 컬럼을 조건으로 사용
			System.out.println("이름 정보와 성별로 조회하기=================");
			//이름에 동 자가 있고 여자인 정보 조회하기
			Map<String,Object> map = new HashMap<>();
			map.put("name", "%동%");
			map.put("gender", 2);
			list = session.selectList("member.selectnamegender", map);
			for(Member m : list) {
				System.out.println(m);
			}
			
		}
	
}
