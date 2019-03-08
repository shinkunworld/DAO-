package DbEmployee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import에 mysql등록이 필요
public class SelectEmployeeSample {
	public static void main(String[] args) {
		Connection conn = null;

		try {
			//drive접속
			Class.forName("com.mysql.jdbc.Driver"); //Jdbc드라이브 이름
			//데이터베이스에 접속
			conn = DriverManager.getConnection(

					"jdbc:mysql://localhost/example?useSSL=false",
					"root", "root");//접속하는 db 유저명 패스워드

			//select문
			String sql = "SELECT ID,NAME, AGE FROM EMPLOYEE";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//準備したSQLをDBに届けるPreparedStatement　インスタンスを取得する


			// 셀렉트를 실행하여 결과표를 획득
			ResultSet rs = pStmt.executeQuery();//Resultset インスタンスにselect文の結果が格納される
			//結果表に格納されたレコードの内容の表示
			while (rs.next()) {
				String id  = rs.getString("id");//Stirng이여도 오류는 발생하지 않지만 추후 계산이 있는 데이터는 int로 변환시키지 않으면 안 된다.
				String name = rs.getString("name");
				int age = rs.getInt("Age");


				//획득한 데이터를 출력
				System.out.println("ID:" + id);
				System.out.println("name:" + name);
				System.out.println("age:" + age + "\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			//接続やSQL処理の失敗の処理
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//JDBCドライバが見つからない時の処理
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
					//切断失敗の処理
				}
			}
		}
	}
}