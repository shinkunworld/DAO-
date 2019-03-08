package DaoEmployee;

import java.util.List;
public class SelectEmployee {
	public static void main(String[] args) {

		//employee table の全レコード取得
		EmployeeDAO empDAO=new EmployeeDAO();
		List<Employee> empList=empDAO.findAll();

		//取得した内容を出力
		for(Employee emp:empList) {
			System.out.println("ID:"+emp.getId());
			System.out.println("NAME:"+emp.getName());
			System.out.println("AGE:"+emp.getAge()+"\n");
		}
	}

}
