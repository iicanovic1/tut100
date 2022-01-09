package ba.unsa.etf.rpr;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class GeografijaDAO {
    private static GeografijaDAO instance = null;
    ArrayList<Drzava> drzave = new ArrayList<>();
    private  Connection conn;
    private PreparedStatement upitGradovi,upitNadjiDrzavu,upitNadjiGrad,upitNadjiGradNaziv
            ,brisiGradove,brisiDrzavu,upitDodajGrad,upitNadjiDrzavuID,
            upitNabaviMAXIDGrad,upitNabaviMAXIDDrzava,upitDodajDrzavu,upitUpdateGrad,upitNadjiGradID;

    public static GeografijaDAO getInstance(){
        if (instance == null) instance = new GeografijaDAO();
        return instance;
    }
    private GeografijaDAO(){
        String url = "jdbc:sqlite:baza.db";
        try{
            conn = DriverManager.getConnection(url);
            upitGradovi = conn.prepareStatement("SELECT * FROM grad");
            upitNadjiDrzavu = conn.prepareStatement("SELECT * FROM drzava WHERE naziv=?");
            upitNadjiGradNaziv = conn.prepareStatement("SELECT * FROM grad WHERE naziv=?");
            upitNadjiGradID = conn.prepareStatement("SELECT * FROM grad WHERE id=?");
            upitNadjiDrzavuID = conn.prepareStatement("SELECT * FROM drzava WHERE id=?");
            upitNadjiGrad = conn.prepareStatement("SELECT * FROM grad WHERE id=?");
            brisiGradove = conn.prepareStatement("DELETE FROM grad WHERE drzava=?");
            upitUpdateGrad = conn.prepareStatement("UPDATE grad SET naziv=?,broj_stanovnika=? WHERE id=?");
            brisiDrzavu = conn.prepareStatement("DELETE FROM drzava WHERE id=?");
            upitDodajGrad = conn.prepareStatement("INSERT INTO grad VALUES (?,?,?,?)");
            upitDodajDrzavu = conn.prepareStatement("INSERT INTO drzava VALUES (?,?,?)");
            upitNabaviMAXIDGrad = conn.prepareStatement("SELECT MAX(id)+1 FROM grad");
            upitNabaviMAXIDDrzava = conn.prepareStatement("SELECT MAX(id)+1 FROM drzava");
        }catch(SQLException e) {
            kreirajBazu();
            try {
                upitGradovi = conn.prepareStatement("SELECT * FROM grad");
                upitNadjiDrzavu = conn.prepareStatement("SELECT * FROM drzava WHERE naziv=?");
                upitNadjiGradNaziv = conn.prepareStatement("SELECT * FROM grad WHERE naziv=?");
                upitNadjiGradID = conn.prepareStatement("SELECT * FROM grad WHERE id=?");
                upitNadjiDrzavuID = conn.prepareStatement("SELECT * FROM drzava WHERE id=?");
                upitNadjiGrad = conn.prepareStatement("SELECT * FROM grad WHERE id=?");
                brisiGradove = conn.prepareStatement("DELETE FROM grad WHERE drzava=?");
                upitUpdateGrad = conn.prepareStatement("UPDATE grad SET naziv=?,broj_stanovnika=? WHERE id=?");
                brisiDrzavu = conn.prepareStatement("DELETE FROM drzava WHERE id=?");
                upitDodajGrad = conn.prepareStatement("INSERT INTO grad VALUES (?,?,?,?)");
                upitDodajDrzavu = conn.prepareStatement("INSERT INTO drzava VALUES (?,?,?)");
                upitNabaviMAXIDGrad = conn.prepareStatement("SELECT MAX(id)+1 FROM grad");
                upitNabaviMAXIDDrzava = conn.prepareStatement("SELECT MAX(id)+1 FROM drzava");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void removeInstance() {
        if(instance == null )return;
        try {
                instance.conn.close();
                instance = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    public void vratiBazuNaDefault() {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM grad");
            stmt.executeUpdate("DELETE FROM drzava");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        kreirajBazu();
    }


    private void kreirajBazu() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("baza.sql"));
            String sqlUpit = "";
            while (ulaz.hasNext()) {
                sqlUpit += ulaz.nextLine();
                if ( sqlUpit.length() > 1 && sqlUpit.charAt( sqlUpit.length()-1 ) == ';') {
                    try {
                        Statement stmt = conn.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }   ulaz.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ne postoji SQL datoteka... nastavljam sa praznom bazom");
        }
    }

  

    public ArrayList<Grad> gradovi(){
        ArrayList<Grad> gradovi = new ArrayList<>();
        try {
            ResultSet rs = upitGradovi.executeQuery();
            while(rs.next()){
                Grad novi = new Grad(rs.getInt(1),rs.getString(2),
                        rs.getInt(3),rs.getInt(4));
                novi.setDrzava(nadjiDrzavuID(rs.getInt(4)));
                gradovi.add(novi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(gradovi, Comparator.comparing(Grad::getBrojStanovnika).reversed());
        return gradovi;
    }

    public Grad nadjiGradID(int id) {
        Grad grad = null;
        try {
            upitNadjiGradID.setInt(1,id);
            ResultSet rs = upitNadjiGradID.executeQuery();
            if(rs.next()){
                grad = new Grad();
                grad.setId(rs.getInt(1));
                grad.setNaziv(rs.getString(2));
                grad.setBrojStanovnika(rs.getInt(3));
                grad.setIdDrzave(rs.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grad;
    }

    public Grad nadjiGrad(String imeGrada) {
        Grad grad = null;
        try {
            upitNadjiGradNaziv.setString(1,imeGrada);
            ResultSet rs = upitNadjiGradNaziv.executeQuery();
            if(rs.next()){
                grad = new Grad();
                grad.setId(rs.getInt(1));
                grad.setNaziv(rs.getString(2));
                grad.setBrojStanovnika(rs.getInt(3));
                grad.setIdDrzave(rs.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grad;
    }

    public Drzava nadjiDrzavu(String imeDrzave) {
        Drzava drzava = null;
        try {
            upitNadjiDrzavu.setString(1,imeDrzave);
            ResultSet rs = upitNadjiDrzavu.executeQuery();
            if(rs.next()){
                drzava = new Drzava();
                drzava.setId(rs.getInt(1));
                drzava.setNaziv(rs.getString(2));
                drzava.setIdGlavniGrad(rs.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drzava;
    }

    public Drzava nadjiDrzavuID(int id) {
        Drzava drzava = null;
        try {
            upitNadjiDrzavuID.setInt(1,id);
            ResultSet rs = upitNadjiDrzavuID.executeQuery();
            if(rs.next()){
                drzava = new Drzava();
                drzava.setId(rs.getInt(1));
                drzava.setNaziv(rs.getString(2));
                drzava.setIdGlavniGrad(rs.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drzava;
    }

    public Grad glavniGrad(String imeDrzave) {
        Grad grad =null;
        try {
            Drzava drzava = nadjiDrzavu(imeDrzave);
            if(drzava != null){
                upitNadjiGrad.setInt(1,drzava.getIdGlavniGrad());
                ResultSet rs = upitNadjiGrad.executeQuery();
                if(rs.next()){
                    grad = new Grad();
                    grad.setId(rs.getInt(1));
                    grad.setNaziv(rs.getString(2));
                    grad.setBrojStanovnika(rs.getInt(3));
                    grad.setIdDrzave(drzava.getId());
                    grad.setDrzava(nadjiDrzavu(imeDrzave));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grad;
    }

    public void obrisiDrzavu(String imeDrzave) {
        Drzava drzava = nadjiDrzavu(imeDrzave);
        try {
            if(drzava!= null){
                brisiGradove.setInt(1,drzava.getId());
                brisiGradove.executeUpdate();
                brisiDrzavu.setInt(1,drzava.getId());
                brisiDrzavu.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajGrad(Grad grad) {
        try {
            ResultSet rs = upitNabaviMAXIDGrad.executeQuery();
            if(rs.next()){
                grad.setId(rs.getInt(1));
                upitDodajGrad.setInt(1,grad.getId());
                upitDodajGrad.setString(2,grad.getNaziv());
                upitDodajGrad.setInt(3,grad.getBrojStanovnika());
                Drzava drzava = nadjiDrzavu(grad.getDrzava().getNaziv());
                upitDodajGrad.setInt(4,drzava.getId());
                upitDodajGrad.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajDrzavu(Drzava drzava) {
        try {
            ResultSet rs = upitNabaviMAXIDDrzava.executeQuery();
            if(rs.next()){
                drzava.setId(rs.getInt(1));
                upitDodajDrzavu.setInt(1,drzava.getId());
                upitDodajDrzavu.setString(2,drzava.getNaziv());
                ResultSet rs1 = upitNabaviMAXIDGrad.executeQuery();
                upitDodajDrzavu.setInt(3, rs1.getInt(1)-1);
                upitDodajDrzavu.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public void izmijeniGrad(Grad poslani) {
        Grad grad = nadjiGradID(poslani.getId());
        if(grad != null)
        try {
            upitUpdateGrad.setString(1,poslani.getNaziv());
            upitUpdateGrad.setInt(2,poslani.getBrojStanovnika());
            upitUpdateGrad.setInt(3,grad.getId());
            upitUpdateGrad.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
