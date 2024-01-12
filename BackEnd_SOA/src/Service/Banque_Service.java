package Service;

import java.util.List;

import Model.Compte;
import Model.Historique;
import Model.Lesactions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import DataBase.Database;

@WebService(serviceName="BanqueWS")
	public class Banque_Service {

		 public Banque_Service()
	        {}    
		@WebMethod(operationName="retirer")
		public int retirer(Compte c,float mont)
		{
			java.sql.Connection conn=Database.getConnection();
			int i=-1;
			try {
			java.sql.PreparedStatement ps= conn.prepareStatement("UPDATE compte SET solde=? WHERE codecin=?;");
			ps.setFloat(1, c.getSolde()-mont);
			ps.setInt(2, c.getCode());
		     i=ps.executeUpdate();
			ps.close();
			} catch (SQLException e)
			{
			e.printStackTrace();
			}
			return i;
		}
		@WebMethod(operationName="verser")
		public int verser(Compte c,Float mont)
		{
			java.sql.Connection conn=Database.getConnection();
			int i=-1;
			try {
			java.sql.PreparedStatement ps= conn.prepareStatement("UPDATE compte SET solde=? WHERE codecin=?;");
			ps.setFloat(1, c.getSolde()+mont);
			ps.setInt(2, c.getCode());
		     i=ps.executeUpdate();
			ps.close();
			} catch (SQLException e)
			{
			e.printStackTrace();
			}
			return i;
		}
		@WebMethod(operationName="transfert")
		public int transfert(Compte c,Float mont)
		{
			java.sql.Connection conn=Database.getConnection();
			int i=-1;
			try {
			java.sql.PreparedStatement ps= conn.prepareStatement("UPDATE compte SET solde=? WHERE codecin=?;");
			ps.setFloat(1, c.getSolde()+mont);
			ps.setInt(2, c.getCode());
		     i=ps.executeUpdate();
		     
			ps.close();
			} catch (SQLException e)
			{
			e.printStackTrace();
			}
			return i;
		}
		@WebMethod
		public List<Compte> getComptes() {
			java.sql.Connection conn=Database.getConnection();
			List<Compte> p =new ArrayList();
			try {

			java.sql.PreparedStatement ps= conn.prepareStatement("select * from compte");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				p.add(new Compte(rs.getInt("codecin"),rs.getFloat("solde"),rs.getString("pass")));
				}
			ps.close();
			} catch (SQLException e)
			{
			e.printStackTrace();
			}
			return p;
		}
		@WebMethod
		public Compte getCompte(int codecin) {
		    java.sql.Connection conn = Database.getConnection();
		    Compte c = new Compte();
		    try {
		        java.sql.PreparedStatement ps = conn.prepareStatement("select * from compte where codeCin = ?");
		        ps.setInt(1, codecin);
		        ResultSet rs = ps.executeQuery();
		        while (rs.next()) {
		            c.setCode(rs.getInt("codecin"));
		            c.setSolde(rs.getFloat("solde"));
		            c.setPass(rs.getString("pass"));
		        }
		        ps.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return c;
		}

		@WebMethod(operationName = "saveCompte")
		public Compte saveUser(@WebParam Compte c) {
			java.sql.Connection conn = Database.getConnection();
			try {

				java.sql.PreparedStatement ps = conn.prepareStatement("INSERT INTO compte VALUES(?,?,?)");

				ps.setInt(1, c.getCode());
				ps.setFloat(2, c.getSolde());
				ps.setString(3, c.getPass());
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return c;
		}
		@WebMethod(operationName = "exist")
		public boolean exist(@WebParam Compte c) {
			Connection conn = (Connection) Database.getConnection();
			try {

				PreparedStatement ps = (PreparedStatement) conn
						.prepareStatement("select * from compte where codeCin= ? and pass = ?");

				ps.setInt(1, c.getCode());
				ps.setString(2, c.getPass());
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return true;
				}

				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
			
			
		}
		@WebMethod(operationName = "setHistorique")
		public int setHistorique(int codecin,int idActe) {
			LocalDateTime currentDateTime = LocalDateTime.now();
			Timestamp currentTimestamp = Timestamp.valueOf(currentDateTime);
			java.sql.Connection conn = Database.getConnection();
			int i=0;
			try {

				java.sql.PreparedStatement ps = conn.prepareStatement("INSERT INTO historique VALUES(?,?,?)");

				ps.setInt(1, codecin);
				ps.setInt(2, idActe);
				ps.setTimestamp(3, currentTimestamp);;
				 i=ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return i;
		}
		@WebMethod
		public List<Historique> getHist(int codecin) {
			java.sql.Connection conn=Database.getConnection();
			List<Historique> p =new ArrayList();
			try {

			java.sql.PreparedStatement ps= conn.prepareStatement("select * from historique where codecin =?");
			ps.setInt(1, codecin);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String formattedDate = dateFormat.format(rs.getTimestamp("date"));
				p.add(new Historique(rs.getInt("codecin"), rs.getInt("idActe"), formattedDate));
		
				}
			ps.close();
			} catch (SQLException e)
			{
			e.printStackTrace();
			}
			return p;
		}
		@WebMethod
		public String getActe(int idcode) {
			java.sql.Connection conn=Database.getConnection();
			Lesactions act=new Lesactions ();
			try {

			java.sql.PreparedStatement ps= conn.prepareStatement("select * from lesactions where id =?");
			ps.setInt(1, idcode);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				act.setId(rs.getInt("id"));
				act.setAction(rs.getString("action"));
				}
			ps.close();
			} catch (SQLException e)
			{
			e.printStackTrace();
			}
			return act.getAction();
		}

		
	}
