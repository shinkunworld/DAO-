package DaoEmployee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
	public List<Employee> findAll() {
		Connection conn = null;
		List<Employee> empList = new ArrayList<Employee>();

		try {
			//jdbcドライバを読み込み

			Class.forName("com.mysql.jdbc.Driver");

			//DATABASE接続
			conn = DriverManager.getConnection(

					"jdbc:mysql://localhost/example?useSSL=false",
					"root", "root");

			//select文の準備

			String sql = "SELECT ID, NAME, AGE FROM EMPLOYEE";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//selectを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			//結果表に格納されたレコードの内容を
			//employeeインスタンスに設定し、arraylistインスタンスに追加
			while (rs.next()) {
				//レコドの値を取得する
				int id = rs.getInt("ID");//Stirng이여도 오류는 발생하지 않지만 추후 계산이 있는 데이터는 int로 변환시키지 않으면 안 된다.
				String name = rs.getString("NAME");
				int age = rs.getInt("AGE");
				Employee employee = new Employee(id, name, age);
				//取得した値をEMployee instanse 格納する
				empList.add(employee);
				//ArrayList instanse に　Employee instanceを追加する
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;

		} finally {
			//detabase logout
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
					//切断失敗の処理
					return null;
				}

			}
		}
		return empList;
	}
}