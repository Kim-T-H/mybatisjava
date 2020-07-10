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
 * 1. �л� ���̺��� ��ϵ� ���ڵ��� �Ǽ��� ����ϱ�. => 
 * 2. �л� ���̺� ��ϵ� ���ڵ� ��� ������ ����ϱ�.
 * 3. �л� ���̺� ��ϵ� ���ڵ� �г⺰ ������ ����ϱ�.
 * 4. �л� ���̺� ��ϵ� ���ڵ� �̸��� ������ ������ ����ϱ�.
 */

public class Exam1 {

	public static void main(String[] args) {
		SqlSessionFactory sqlMap = null; // sql�� �����͸� ã����.
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("mapper/mybatis-config1.xml");
			sqlMap = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int x = 0;
		SqlSession session = sqlMap.openSession(); // db�� ���� session ���
		// selectOne : ��ȸ�Ǵ� ���ڵ尡 �Ѱ��� ���
		x = (Integer) session.selectOne("Student.count");
		System.out.println("Student ���̺��� ���ڵ� ����:" + x);
		
		//�л� ���̺� ��ϵ� ���ڵ� ��� ������ ����ϱ�.
		System.out.println("�л� ���̺� ��ϵ� ���ڵ� ��� ������ ����ϱ�========");
		List<Student> list =session.selectList("Student.list");
		for(Student m: list) {
			System.out.println(m);
		}
		//�л� ���̺� ��ϵ� ���ڵ� �г⺰ ������ ����ϱ�.
		System.out.println("�л� ���̺� ��ϵ� ���ڵ� �г⺰ ������ ����ϱ�========");
		list = session.selectList("Student.selectgrade");
		for(Student m :list) {
			System.out.println(m);
		}
		System.out.println("1.�л� ���̺� ��ϵ� ���ڵ� �̸��� ������ ������ ����ϱ�.==========");
		list = session.selectList("Student.selectname","__");
		for(Student m : list) {
			System.out.println(m);
		}
		
	
		
	}

}
