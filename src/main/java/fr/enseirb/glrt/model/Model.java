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
	private Connection conn;

	public Model(String[] bddArgs) throws ClassNotFoundException, SQLException {
		this.labCount = 0;
		this.atelierCount = 0;
		Class.forName("org.h2.Driver");
		this.conn = DriverManager.getConnection(bddArgs[0], bddArgs[1], bddArgs[2]);
	}

	public void closeBDDConnection() throws SQLException {
		conn.close();
	}

	public void createLabTable() throws ClassNotFoundException, SQLException {
		PreparedStatement stmt = conn.prepareStatement(
				"CREATE TABLE Laboratoires (id int primary key, nom varchar(255) not null, responsable varchar(255) not null, tel varchar(255) not null, email varchar(255) not null, mot_de_passe varchar(255) not null);");
		stmt.executeUpdate();
		stmt.close();
	}

	public void createAtelierTable() throws ClassNotFoundException, SQLException {
		PreparedStatement stmt = conn.prepareStatement(
				"CREATE TABLE Ateliers (id int primary key, lab_id int not null references Laboratoires(id), titre varchar(255) not null, disciplines array not null, type varchar(255) not null, seances array not null, inscrits array not null, lieu varchar(255) not null, duree int not null, capacite int not null, resume varchar(255) not null, animateurs array not null, publics array not null);");
		stmt.executeUpdate();
		stmt.close();
	}

	public int createLab(Laboratoire lab) throws ClassNotFoundException, SQLException {
		labCount++;
		int labId = labCount;
		PreparedStatement stmt = conn.prepareStatement("insert into laboratoires VALUES (?, ?, ?, ?, ?, ?)");
		stmt.setInt(1, labId);
		stmt.setString(2, lab.getNom());
		stmt.setString(3, lab.getResponsable());
		stmt.setString(4, lab.getTel());
		stmt.setString(5, lab.getEmail());
		stmt.setString(6, lab.getMotDePasse());
		stmt.executeUpdate();
		stmt.close();
		return labId;
	}

	public int checkLab(String email, String password) throws ClassNotFoundException, SQLException {
		PreparedStatement stmt = conn.prepareStatement("select id,mot_de_passe from laboratoires where email=?");
		stmt.setString(1, email);
		ResultSet rs = stmt.executeQuery();
		int check = 0;
		if (rs.next() && password.equals(rs.getString("mot_de_passe"))) {
			check = rs.getInt("id");
		}
		stmt.close();
		return check;
	}

	public int createAtelier(Atelier atelier) throws ClassNotFoundException, SQLException {
		atelierCount++;
		int id = atelierCount;
		PreparedStatement stmt = conn
				.prepareStatement("insert into ateliers VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		stmt.setInt(1, id);
		stmt.setInt(2, atelier.getLabId());
		stmt.setString(3, atelier.getTitre());

		String[] arrayDisciplines = new String[atelier.getDisciplines().size()];
		int i = 0;
		for (String t : atelier.getDisciplines()) {
			arrayDisciplines[i] = t.toString();
			i++;
		}
		stmt.setObject(4, arrayDisciplines);
		stmt.setString(5, atelier.getType());

		String[] arraySeances = new String[atelier.getSeances().size()];
		int j = 0;
		for (Seance t : atelier.getSeances()) {
			arraySeances[j] = t.getSeance().toString();
			j++;
		}
		stmt.setObject(6, arraySeances);

		String[] arrayInscrits = new String[atelier.getSeances().size()];
		int l = 0;
		for (Seance t : atelier.getSeances()) {
			arrayInscrits[l] = t.getInscrit().toString();
			l++;
		}
		stmt.setObject(7, arrayInscrits);

		stmt.setString(8, atelier.getLieu());
		stmt.setInt(9, atelier.getDuree());
		stmt.setInt(10, atelier.getCapacite());
		stmt.setString(11, atelier.getResume());
		String[] arrayAnimateurs = atelier.getAnimateurs().toArray(new String[atelier.getAnimateurs().size()]);
		stmt.setObject(12, arrayAnimateurs);

		String[] arrayPublics = new String[atelier.getPublics().size()];
		int k = 0;
		for (String t : atelier.getPublics()) {
			arrayPublics[k] = t.toString();
			k++;
		}
		stmt.setObject(13, arrayPublics);
		stmt.executeUpdate();
		stmt.close();
		return id;
	}

	public List<Atelier> getAteliers(Integer labId) throws ClassNotFoundException, SQLException {
		List<Atelier> ateliers = new ArrayList<Atelier>();
		PreparedStatement stmt = conn.prepareStatement("select id,titre,type,disciplines from ateliers where lab_id=?");
		stmt.setInt(1, labId);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Atelier atelier = new Atelier();
			atelier.setId(Integer.parseInt(rs.getString("id")));
			atelier.setTitre(rs.getString("titre"));
			atelier.setType(rs.getString("type"));
			List<String> disc = new ArrayList<String>();

			for (Object obj : (Object[]) rs.getArray("disciplines").getArray()) {
				String dis = (String) obj;
				disc.add(dis);
			}
			atelier.setDisciplines(disc);
			ateliers.add(atelier);
		}
		stmt.close();
		return ateliers;
	}

	public Atelier getAtelier(Integer atelierId) throws SQLException {
		Atelier atelier = new Atelier();
		PreparedStatement stmt = conn.prepareStatement("select * from ateliers where id=?");
		stmt.setInt(1, atelierId);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			atelier.setTitre(rs.getString("titre"));
			atelier.setType(rs.getString("type"));
			atelier.setDuree(Integer.parseInt(rs.getString("duree")));
			atelier.setCapacite(Integer.parseInt(rs.getString("capacite")));
			atelier.setLabId(Integer.parseInt(rs.getString("lab_id")));
			atelier.setLieu(rs.getString("lieu"));
			atelier.setResume(rs.getString("resume"));

			List<String> disc = new ArrayList<String>();

			for (Object obj : (Object[]) rs.getArray("disciplines").getArray()) {
				String dis = (String) obj;
				disc.add(dis);
			}
			atelier.setDisciplines(disc);

			List<Seance> seances = new ArrayList<Seance>();
			
			int i;
			for (i = 0; i < ((Object[]) rs.getArray("seances").getArray()).length ; i++) {
				String jour = (String) ((Object[]) rs.getArray("seances").getArray())[i];
				Integer ins = Integer.parseInt((String) ((Object[]) rs.getArray("inscrits").getArray())[i]);
				Seance seance = new Seance(jour,ins);
				seances.add(seance );
			}
			atelier.setSeances(seances);

			
			List<String> publics = new ArrayList<String>();

			for (Object obj : (Object[]) rs.getArray("publics").getArray()) {
				String pub = (String) obj;
				publics.add(pub);
			}
			atelier.setPublics(publics);

			List<String> animateurs = new ArrayList<String>();

			for (Object obj : (Object[]) rs.getArray("animateurs").getArray()) {
				String anim = (String) obj;
				animateurs.add(anim);
			}
			atelier.setAnimateurs(animateurs);


			
			PreparedStatement stmt1 = conn.prepareStatement("select nom from laboratoires where id=?");
			stmt1.setInt(1, Integer.parseInt(rs.getString("lab_id")));
			ResultSet rs1 = stmt1.executeQuery();
			if (rs1.next()) {
				atelier.setLabName(rs1.getString("nom"));
			}
			rs1.close();
		}
		stmt.close();
		return atelier;
	}
	
	public boolean checkAtelierId(int id) throws SQLException{
		boolean b =false;
		PreparedStatement stmt = conn.prepareStatement("select * from ateliers where id=?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
			b=true;
		stmt.close();
		return b;
	}

}
