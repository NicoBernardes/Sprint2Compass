import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateConnection {
	public static Connection reCon() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:mysql://localhost/first_question?useTimezone=true&serverTimezone=UTC", "root", "root");
	}

}
