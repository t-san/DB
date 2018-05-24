

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String GET_SQL =
				"SELECT "
						+ "KaiinNo,"
						+ "Name,"
						+ "RegistDate "
						+ "from kaiin "
						+ "WHERE "
						+ "KaiinNo = ?";
		Connection c;
		ResultSet rset = null;

		Integer searchId = 0;
		String searchName = "";
		Date searchDate = null;
		//入力されたIDを取得
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);

		//コネクションの取得------------------------------------
		//接続文字列の構築
		/* ユーザ名 */
		String user = "train2018";
		/* パスワード */
		String pass = "train2018";

		/* サーバ名 */
		String servername = "localhost:3306";
		/* DB名 */
		String dbname = "new_schema";

		// ドライバーのロード
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			//接続の実行とコネクションオブジェクトの取得
			c = DriverManager.getConnection(
					"jdbc:mysql://"
							+ servername
							+ "/"
							+ dbname,
							user,
							pass);
		} catch (ClassNotFoundException | SQLException e)
		{
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		try
		(
				PreparedStatement   stmt = c.prepareStatement(GET_SQL);
				)
		{
			//検索対象となるIDをSQL文にセット
			stmt.setInt(1, id);
			//SQL文実行
			rset = stmt.executeQuery();
			while (rset.next())
			{
				searchId=rset.getInt(1);
				searchName =rset.getString(2);
				searchDate = rset.getDate(3);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		request.setAttribute("id", searchId);
		request.setAttribute("name", searchName);
		request.setAttribute("date", searchDate);
		RequestDispatcher disp = request.getRequestDispatcher("/next.jsp");
		disp.forward(request, response);
	}

}
