package fr.enseirb.glrt.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
	private int teacherCount;
	private int seanceCount;
	private int inscriptionCount;
	private Connection conn;

	public Model(String[] bddArgs) throws ClassNotFoundException, SQLException {
		this.labCount = 0;
		this.atelierCount = 0;
		this.teacherCount = 0;
		this.seanceCount = 0;
		this.inscriptionCount = 0;
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

	public void createTeacherTable() throws ClassNotFoundException, SQLException {
		PreparedStatement stmt = conn.prepareStatement(
				"CREATE TABLE Teachers (id int primary key, nom varchar(255) not null, etablissement varchar(255) not null, tel varchar(255) not null, email varchar(255) not null, mot_de_passe varchar(255) not null);");
		stmt.executeUpdate();
		stmt.close();
	}
	
	public void createAtelierTable() throws ClassNotFoundException, SQLException {
		PreparedStatement stmt = conn.prepareStatement(
				"CREATE TABLE Ateliers (id int primary key, lab_id int not null references Laboratoires(id), titre varchar(255) not null, disciplines array not null, type varchar(255) not null, lieu varchar(255) not null, duree int not null, capacite int not null, resume varchar(255) not null, animateurs array not null, publics array not null);");
		stmt.executeUpdate();
		stmt.close();
	}
	
	public void createSeanceTable() throws ClassNotFoundException, SQLException {
		PreparedStatement stmt = conn.prepareStatement(
				"CREATE TABLE Seances (id int primary key, atelier_id int not null references Ateliers(id), nom varchar(255) not null, inscrits int not null);");
		stmt.executeUpdate();
		stmt.close();
	}

	public void createInscriptionTable() throws ClassNotFoundException, SQLException {
		PreparedStatement stmt = conn.prepareStatement(
				"CREATE TABLE Inscriptions (id int primary key, teacher_id int not null references Teachers(id), atelier_id int not null references Ateliers(id), validated boolean not null, seance varchar(255) not null, public varchar(255) not null, nombre int not null);");
		stmt.executeUpdate();
		stmt.close();
	}
	
	public int createLab(Laboratoire lab) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(lab.getMotDePasse().getBytes());
        
        byte byteData[] = md.digest();
 
        StringBuffer password = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         password.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
     
        
		labCount++;
		int labId = labCount;
		PreparedStatement stmt = conn.prepareStatement("insert into laboratoires VALUES (?, ?, ?, ?, ?, ?)");
		stmt.setInt(1, labId);
		stmt.setString(2, lab.getNom());
		stmt.setString(3, lab.getResponsable());
		stmt.setString(4, lab.getTel());
		stmt.setString(5, lab.getEmail());
		stmt.setString(6, password.toString());
		stmt.executeUpdate();
		stmt.close();
		return labId;
	}

	public int checkLab(String email, String mot_de_passe) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(mot_de_passe.getBytes());
        
        byte byteData[] = md.digest();
 
        StringBuffer password = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         password.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        
		PreparedStatement stmt = conn.prepareStatement("select id,mot_de_passe from laboratoires where email=?");
		stmt.setString(1, email);
		ResultSet rs = stmt.executeQuery();
		int check = 0;
		if (rs.next() && password.toString().equals(rs.getString("mot_de_passe"))) {
			check = rs.getInt("id");
		}
		stmt.close();
		return check;
	}

	public int createAtelier(Atelier atelier) throws ClassNotFoundException, SQLException {
		atelierCount++;
		int id = atelierCount;
		PreparedStatement stmt = conn
				.prepareStatement("insert into ateliers VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		stmt.setInt(1, id);
		stmt.setInt(2, atelier.getLabId());
		stmt.setString(3, atelier.getTitre());

		String[] arrayDisciplines = new String[atelier.getDisciplines().size()];
		int i = 0;
		for (String t : atelier.getDisciplines()) {
			arrayDisciplines[i] = t;
			i++;
		}
		stmt.setObject(4, arrayDisciplines);
		stmt.setString(5, atelier.getType());

		stmt.setString(6, atelier.getLieu());
		stmt.setInt(7, atelier.getDuree());
		stmt.setInt(8, atelier.getCapacite());
		stmt.setString(9, atelier.getResume());
		String[] arrayAnimateurs = atelier.getAnimateurs().toArray(new String[atelier.getAnimateurs().size()]);
		stmt.setObject(10, arrayAnimateurs);

		String[] arrayPublics = new String[atelier.getPublics().size()];
		int k = 0;
		for (String t : atelier.getPublics()) {
			arrayPublics[k] = t;
			k++;
		}
		stmt.setObject(11, arrayPublics);
		
		stmt.executeUpdate();
		stmt.close();
		
		for (Seance t : atelier.getSeances()) {
			seanceCount++;
			int sId = seanceCount;
			PreparedStatement stmt1 = conn
					.prepareStatement("insert into seances VALUES (?, ?, ?, ?)");
			stmt1.setInt(1, sId);
			stmt1.setInt(2, id);
			stmt1.setString(3, t.getNom());
			stmt1.setInt(4, t.getInscrit());
			stmt1.executeUpdate();
			stmt1.close();
		}

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

	public List<Atelier> getAllAteliers() throws ClassNotFoundException, SQLException {
		List<Atelier> ateliers = new ArrayList<Atelier>();
		PreparedStatement stmt = conn.prepareStatement("select id,titre,type,disciplines from ateliers");
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
			atelier.setId(rs.getInt("id"));
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
		
		List<Seance> seances = new ArrayList<Seance>();

		PreparedStatement stmt2= conn.prepareStatement("select * from seances where atelier_id=?");
		stmt2.setInt(1, atelierId);
		ResultSet rs2 = stmt2.executeQuery();
		while (rs2.next()) {
			Seance seance = new Seance(rs2.getString("nom"), rs2.getInt("inscrits"));
			seances.add(seance );
		}
		atelier.setSeances(seances);
		stmt2.close();
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

	public boolean atelierOfLab(int atelierId, int labId) throws SQLException {
		boolean b =false;
		PreparedStatement stmt = conn.prepareStatement("select * from ateliers where id=?");
		stmt.setInt(1, atelierId);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()){
			PreparedStatement stmt1 = conn.prepareStatement("select * from ateliers where id=? and lab_id=?");
			stmt1.setInt(1, atelierId);
			stmt1.setInt(2, labId);
			ResultSet rs1 = stmt1.executeQuery();
			if(rs1.next())
				b=true;
			stmt1.close();
		}
		stmt.close();
		return b;
	}
	
	public void deleteAtelier(int atelierId) throws SQLException{
		
		PreparedStatement stmt1 = conn.prepareStatement("delete from seances where atelier_id=?");
		stmt1.setInt(1, atelierId);
		stmt1.executeUpdate();
		stmt1.close();
		
		PreparedStatement stmt2 = conn.prepareStatement("delete from inscriptions where atelier_id=?");
		stmt2.setInt(1, atelierId);
		stmt2.executeUpdate();
		stmt2.close();
		
		PreparedStatement stmt = conn.prepareStatement("delete from ateliers where id=?");
		stmt.setInt(1, atelierId);
		stmt.executeUpdate();
		stmt.close();
		

	}
	
	public void editAtelier(Atelier atelier) throws SQLException{
		PreparedStatement stmt = conn
				.prepareStatement("update ateliers VALUES set titre=?, disciplines=?, type=?, lieu=?, duree=?, capacite=?, resume=?, animateurs=?, publics=? where id=?");
		stmt.setString(1, atelier.getTitre());

		String[] arrayDisciplines = new String[atelier.getDisciplines().size()];
		int i = 0;
		for (String t : atelier.getDisciplines()) {
			arrayDisciplines[i] = t;
			i++;
		}
		stmt.setObject(2, arrayDisciplines);
		stmt.setString(3, atelier.getType());


		stmt.setString(4, atelier.getLieu());
		stmt.setInt(5, atelier.getDuree());
		stmt.setInt(6, atelier.getCapacite());
		stmt.setString(7, atelier.getResume());
		String[] arrayAnimateurs = atelier.getAnimateurs().toArray(new String[atelier.getAnimateurs().size()]);
		stmt.setObject(8, arrayAnimateurs);

		String[] arrayPublics = new String[atelier.getPublics().size()];
		int k = 0;
		for (String t : atelier.getPublics()) {
			arrayPublics[k] = t;
			k++;
		}
		stmt.setObject(9, arrayPublics);
		stmt.setInt(10, atelier.getId());
		stmt.executeUpdate();
		stmt.close();
		
		PreparedStatement stmt2 = conn.prepareStatement("delete from seances where atelier_id=?");
		stmt2.setInt(1, atelier.getId());
		stmt2.executeUpdate();
		stmt2.close();
		
		for (Seance t : atelier.getSeances()) {
			seanceCount++;
			int sId = seanceCount;
			PreparedStatement stmt1 = conn
					.prepareStatement("insert into seances VALUES (?, ?, ?, ?)");
			stmt1.setInt(1, sId);
			stmt1.setInt(2, atelier.getId());
			stmt1.setString(3, t.getNom());
			stmt1.setInt(4, t.getInscrit());
			stmt1.executeUpdate();
			stmt1.close();
		}
	}

	public int createTeacher(Teacher teacher) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(teacher.getMotDePasse().getBytes());
        
        byte byteData[] = md.digest();
 
        StringBuffer password = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         password.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
		teacherCount++;
		int teacherId = teacherCount;
		PreparedStatement stmt = conn.prepareStatement("insert into teachers VALUES (?, ?, ?, ?, ?, ?)");
		stmt.setInt(1, teacherId);
		stmt.setString(2, teacher.getNom());
		stmt.setString(3, teacher.getEtablissement());
		stmt.setString(4, teacher.getTel());
		stmt.setString(5, teacher.getEmail());
		stmt.setString(6, password.toString());
		stmt.executeUpdate();
		stmt.close();
		return teacherId;
	}

	public int checkTeacher(String email, String mot_de_passe) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(mot_de_passe.getBytes());
        
        byte byteData[] = md.digest();
 
        StringBuffer password = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         password.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
		PreparedStatement stmt = conn.prepareStatement("select id,mot_de_passe from teachers where email=?");
		stmt.setString(1, email);
		ResultSet rs = stmt.executeQuery();
		int check = 0;
		if (rs.next() && password.toString().equals(rs.getString("mot_de_passe"))) {
			check = rs.getInt("id");
		}
		stmt.close();
		return check;
	}
	public boolean checkLabExiste(String email) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("select id from laboratoires where email=?");
		stmt.setString(1, email);
		ResultSet rs = stmt.executeQuery();
		boolean check = false;
		if (rs.next()) {
			check  = true;
		}
		stmt.close();		
		
		return check;
	}

	public boolean checkTeacherExiste(String email) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("select id from teachers where email=?");
		stmt.setString(1, email);
		ResultSet rs = stmt.executeQuery();
		boolean check = false;
		if (rs.next()) {
			check  = true;
		}
		stmt.close();		
		
		return check;
	}

	public List<Inscription> getTeacherInscriptionsValidated(String teacherId) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("select * from inscriptions where teacher_id=? and validated=true");
		stmt.setInt(1, Integer.parseInt(teacherId));
		ResultSet rs = stmt.executeQuery();
		List<Inscription> inscriptions = new ArrayList<Inscription>();
		while(rs.next()){
			Inscription ins = new Inscription(rs.getInt("id"), rs.getInt("teacher_id"), rs.getInt("atelier_id"), rs.getBoolean("validated"), rs.getString("seance"), rs.getString("public"), rs.getInt("nombre"));
			inscriptions.add(ins);
		}
		stmt.close();
		return inscriptions;
	}

	public List<Inscription> getTeacherInscriptionsWaiting(String teacherId) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("select * from inscriptions where teacher_id=? and validated=false");
		stmt.setInt(1, Integer.parseInt(teacherId));
		ResultSet rs = stmt.executeQuery();
		List<Inscription> inscriptions = new ArrayList<Inscription>();
		while(rs.next()){
			Inscription ins = new Inscription(rs.getInt("id"), rs.getInt("teacher_id"), rs.getInt("atelier_id"), rs.getBoolean("validated"), rs.getString("seance"), rs.getString("public"), rs.getInt("nombre"));
			inscriptions.add(ins);
		}
		stmt.close();
		return inscriptions;
	}
	
	public void teacherInscrireAtelier(String teacherId, String atelierId, String seance, String pub, int nombre) throws SQLException {
		inscriptionCount++;
		int sId = inscriptionCount;
		PreparedStatement stmt1 = conn
				.prepareStatement("insert into inscriptions VALUES (?, ?, ?, false, ?, ?, ?)");
		stmt1.setInt(1, sId);
		stmt1.setInt(2, Integer.parseInt(teacherId));
		stmt1.setInt(3, Integer.parseInt(atelierId));
		stmt1.setString(4, seance);
		stmt1.setString(5, pub);
		stmt1.setInt(6, nombre);
		stmt1.executeUpdate();
		stmt1.close();
	}

	public String getAtelierNameFromId(int atelierId) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("select titre from ateliers where id=?");
		stmt.setInt(1, atelierId);
		ResultSet rs = stmt.executeQuery();		
		String ret = "";
		if(rs.next())
			ret = rs.getString("titre");
		return ret;
	}

	public String getTeacherNameFromId(int teacherId) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("select nom from teachers where id=?");
		stmt.setInt(1, teacherId);
		ResultSet rs = stmt.executeQuery();		
		String ret = "";
		if(rs.next())
			ret = rs.getString("nom");
		return ret;
	}
	
	public List<Inscription> getLabInscriptionsValidated(int labId) throws SQLException, ClassNotFoundException {
		List<Inscription> inscriptions = new ArrayList<Inscription>();

		for(Atelier at : getAteliers(labId)){
			PreparedStatement stmt = conn.prepareStatement("select * from inscriptions where atelier_id=? and validated=true");
			stmt.setInt(1, at.getId());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Inscription ins = new Inscription(rs.getInt("id"), rs.getInt("teacher_id"), rs.getInt("atelier_id"), rs.getBoolean("validated"), rs.getString("seance"), rs.getString("public"), rs.getInt("nombre"));
				inscriptions.add(ins);
			}
			stmt.close();
		}
		
		return inscriptions;
	}
	
	public List<Inscription> getLabInscriptionsWaiting(int labId) throws SQLException, ClassNotFoundException {
		List<Inscription> inscriptions = new ArrayList<Inscription>();

		for(Atelier at : getAteliers(labId)){
			PreparedStatement stmt = conn.prepareStatement("select * from inscriptions where atelier_id=? and validated=false");
			stmt.setInt(1, at.getId());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Inscription ins = new Inscription(rs.getInt("id"), rs.getInt("teacher_id"), rs.getInt("atelier_id"), rs.getBoolean("validated"), rs.getString("seance"), rs.getString("public"), rs.getInt("nombre"));
				inscriptions.add(ins);
			}
			stmt.close();
		}
		
		return inscriptions;
	}

	public void deleteInscription(int insId) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("delete from inscriptions where id=?");
		stmt.setInt(1, insId);
		stmt.executeUpdate();
		stmt.close();
		
	}

	public boolean inscriptionOfLab(int insId, int labId) throws SQLException {
		boolean b =false;
		PreparedStatement stmt2 = conn.prepareStatement("select * from inscriptions where id=?");
		stmt2.setInt(1, insId);
		ResultSet rs2 = stmt2.executeQuery();
		if(rs2.next()){
			int atelierId = rs2.getInt("atelier_id");
			PreparedStatement stmt = conn.prepareStatement("select * from ateliers where id=?");
			stmt.setInt(1, atelierId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				PreparedStatement stmt1 = conn.prepareStatement("select * from ateliers where id=? and lab_id=?");
				stmt1.setInt(1, atelierId);
				stmt1.setInt(2, labId);
				ResultSet rs1 = stmt1.executeQuery();
				if(rs1.next())
					b=true;
				stmt1.close();
			}
			stmt.close();
		}
		stmt2.close();
		return b;
	}

	public Inscription getInscription(int insId) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("select * from inscriptions where id=?");
		stmt.setInt(1, insId);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return new Inscription(rs.getInt("id"), rs.getInt("teacher_id"), rs.getInt("atelier_id"), rs.getBoolean("validated"), rs.getString("seance"), rs.getString("public"), rs.getInt("nombre"));
		}
		
		return null;
	}

	public void validateInscription(int insId) throws SQLException {
		PreparedStatement stmt = conn
				.prepareStatement("update inscriptions VALUES set validated=? where id=?");
		stmt.setBoolean(1, true);
		stmt.setInt(2, insId);
		stmt.executeUpdate();
		stmt.close();
		
		Inscription ins = getInscription(insId);
		
		PreparedStatement stmt2 = conn
				.prepareStatement("select inscrits from seances where atelier_id=? and nom=?");
		stmt2.setInt(1, ins.getAtelierId());
		stmt2.setString(2, ins.getSeance());
		ResultSet rs = stmt2.executeQuery();
		int insc=0;
		if(rs.next()){
			insc=rs.getInt("inscrits");
			PreparedStatement stmt1 = conn
					.prepareStatement("update seances VALUES set inscrits=? where atelier_id=? and nom=?");
			stmt1.setInt(1, insc+ins.getNombre());
			stmt1.setInt(2, ins.getAtelierId());
			stmt1.setString(3, ins.getSeance());
			stmt1.executeUpdate();
			stmt1.close();
		}
		stmt2.close();
		


	}
}
