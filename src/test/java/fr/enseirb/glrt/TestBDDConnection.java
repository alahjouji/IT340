package fr.enseirb.glrt;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class TestBDDConnection {

	@Test
	public void test() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:mem:it340");
		assertTrue(conn.isValid(0));
		conn.close();
		assertTrue(conn.isClosed());
	}

}
