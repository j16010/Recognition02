package recognition02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class MySQL {
	String driver;// JDBCドライバの登録
	String server, dbname, url, user, password;// データベースの指定
	Connection con;
	Statement stmt;
	Map<String, Object> lng = new HashMap<>();

	public MySQL() {
		this.driver = "org.gjt.mm.mysql.Driver";
        this.server = "J16010";
        this.dbname = "J16010";
        this.url = "jdbc:mysql://" + server + "/" + dbname + "?useUnicode=true&characterEncoding=UTF-8";
        this.user = "J16010";
        this.password = "J16010";
	    
	    
	    try {
	        this.con = DriverManager.getConnection(url, user, password);
	        this.stmt = con.createStatement ();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    try {
	        Class.forName (driver);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}

	//値の無い物全て
	public ResultSet getID() {
		ResultSet rs = null;
		String sql = "SELECT * FROM  `images` WHERE  `age_min` = -1";
		try {
			rs = stmt.executeQuery (sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public void updateImage(String class1, double score1, String class2, double score2, String class3, double score3) {
		//keywordテーブルへ格納
		StringBuffer buf = new StringBuffer();
		buf.append("INSERT INTO  `recognitions` (`user_id`,`class1` ,`score1` ,`class2` ,`score2` ,`class3`,`score3`) VALUES ( 'J16010', '" + class1 + "', "+ score1 +", '" + class2 + "', "+ score2 +", '" + class3 + "', "+ score3 + "  );");
		String sql = buf.toString();
		try {
			stmt.execute (sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}