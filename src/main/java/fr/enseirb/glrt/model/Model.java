package fr.enseirb.glrt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Model {
	private int labCount;
	private int atelierCount;

	public Model() {
		this.labCount = 0;
		this.atelierCount = 0;
	}

	public int createLab(Laboratoire lab) throws ClassNotFoundException, SQLException {
		labCount++;
		int labId = labCount;
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/it340", "alahjouji", "alahjouji");
		PreparedStatement stmt = conn.prepareStatement("insert into laboratoires VALUES (?, ?, ?, ?, ?, ?)");
		stmt.setInt(1, labId);
		stmt.setString(2, lab.getNom());
		stmt.setString(3, lab.getResponsable());
		stmt.setString(4, lab.getTel());
		stmt.setString(5, lab.getEmail());
		stmt.setString(6, lab.getMotDePasse());
		stmt.executeUpdate();
		conn.close();
		return labId;
	}

	public int checkLab(String email, String password) throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/it340", "alahjouji", "alahjouji");
		PreparedStatement stmt = conn.prepareStatement("select mot_de_passe from laboratoires where email=?");
		stmt.setString(1, email);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		if (password.equals(rs.getString("mot_de_passe"))){
			PreparedStatement stmt1 = conn.prepareStatement("select id from laboratoires where email=?");
			stmt1.setString(1, email);
			ResultSet rs1 = stmt1.executeQuery();
			rs1.next();
			return rs1.getInt("id");
		}
		return 0;
	}

	public int createAtelier(Atelier atelier) throws ClassNotFoundException, SQLException {
		atelierCount++;
		int id = atelierCount;
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/it340", "alahjouji", "alahjouji");
		PreparedStatement stmt = conn.prepareStatement("insert into ateliers VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		stmt.setInt(1, id);
		stmt.setInt(2, atelier.getLabId());
		stmt.setString(3, atelier.getTitre());

		String[] arrayDisciplines = atelier.getDisciplines().toArray(new String[atelier.getDisciplines().size()]);
		stmt.setObject(4, arrayDisciplines  );
		stmt.setString(5, atelier.getType());
		stmt.setString(6, atelier.getLieu());
		String[] arraySeances = atelier.getSeances().toArray(new String[atelier.getSeances().size()]);
		stmt.setObject(7, arraySeances  );
		stmt.setInt(8, atelier.getDuree());
		stmt.setInt(9,  atelier.getCapacite());
		stmt.setString(10, atelier.getResume());
		String[] arrayAnimateurs = atelier.getAnimateurs().toArray(new String[atelier.getAnimateurs().size()]);
		stmt.setObject(11, arrayAnimateurs  );
		String[] arrayPublics = atelier.getPublics().toArray(new String[atelier.getPublics().size()]);
		stmt.setObject(12, arrayPublics  );
		stmt.executeUpdate();
		conn.close();
		return id;
	}
		


	public List<Atelier> getAteliers(Integer labId) throws ClassNotFoundException, SQLException {
		List<Atelier> ateliers = new ArrayList<Atelier>();
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/it340", "alahjouji", "alahjouji");
		PreparedStatement stmt = conn.prepareStatement("select titre,type,disciplines from ateliers where lab_id=?");
		stmt.setInt(1, labId);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()){
			Atelier atelier = new Atelier();
			atelier.setTitre(rs.getString("titre"));
			atelier.setType(rs.getString("type"));
			List<String> disc = new ArrayList<String>();


			 for (Object obj : (Object[])rs.getArray("disciplines").getArray()) {
				 String dis = (String) obj;
				 disc.add(dis);
			 }
			atelier.setDisciplines(disc);
			ateliers.add(atelier);
		}		
		return ateliers ;
	}

}
