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
	private final static String NS="student.";	//���
	private static SqlSessionFactory sqlMap;	//Ŭ���� ����
	static {	//static �ʱ�ȭ ��: Ŭ���� ������ �ʱ�ȭ
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
		System.out.println("Student ���̺� ���ڵ� �߰��ϱ�");
		Student st= new Student();
		st.setStudno(1000);
		st.setName("ȫ�浿");
		st.setGrade(1);
		st.setId("hongkd2");
		st.setJumin("123456");
		st.setMajor1(101);
		//cnt : ���ڵ� ���� �Ǽ�. �߰��� ���ڵ� ��.
		int cnt=session.insert(NS+"insert", st);
		System.out.println("student ���ڵ� �߰�:"+cnt);
		Student stu=session.selectOne(NS+"selectno",st.getStudno());
		System.out.println(stu);
		/*
		 * 1000�� �л��� �г��� 2�г�����, ������ 80, Ű��175�� 
		 * ����������  1001�� �����ϱ�
		 */
		
		st.setStudno(1000);
		st.setGrade(2);
		st.setWeight(80);
		st.setHeight(175);
		st.setProfno(1001);
		cnt= session.update(NS+"update",st);
		stu=session.selectOne(NS+"selectno",st.getStudno());
		System.out.println(stu);
	//	session.commit(); // ���������� ���� �Ϸ�.
		
		System.out.println("���� �达�� �л� ���� ����ϱ�======");
		List<Student> list =session.selectList(NS+"selectname1","��");
		for(Student m : list) {
			 System.out.println(m);
		}
		System.out.println("�����԰� 75�̻��� �л� ���� ����ϱ�======");
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("col", "weight");
		map.put("val", 75);
		list = session.selectList(NS+"select2",map);
		for(Student m : list) {
			System.out.println(m);
		}

		System.out.println("Ű��  175�̻��� �л� ���� ����ϱ�======");
		map.put("col", "height");
		map.put("val", 175);
		list = session.selectList(NS+"select2",map);
		for(Student m: list) {
			System.out.println(m);
		}
		
		System.out.println("�ֹι�ȣ�� 970101 ������ �л� ����======================");		//�Խ��� �����Ҷ� ����Ҽ� ����.
		map.put("col", "jumin");
		map.put("val", "970101");
		list = session.selectList(NS+"select2",map);
		for(Student m: list) {
			System.out.println(m);
		}
		
		System.out.println("�л� �� ������ �л� ���� �����ϱ�");
		map.clear();
		map.put("col", "name");
		map.put("val", "������");
		cnt = session.delete(NS+"delete",map);
		System.out.println("student ���ڵ� ����:" +cnt);
		stu = session.selectOne(NS+"select4",map);
		System.out.println(stu); //null
		
		System.out.println("1�г� �л� ���� �����ϱ�");
		map.clear();
		map.put("col", "grade");
		map.put("val", 1);
		cnt = session.delete(NS+"delete",map);
		System.out.println("student ���ڵ� ����:" +cnt);
		
	}
	
	}


