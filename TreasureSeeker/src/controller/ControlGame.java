package controller;
import game.Game;
import view.*;
import model.*;


public class ControlGame {
        public static void main(String[] args) {
        ControlGame cg = new ControlGame();
        cg.loginAdmin();
    }
    public void registrasi(){
        RegisterView rv = new RegisterView();
        rv.formRegis();
    }
    
    public void loginAdmin(){
        LoginAdminView av = new LoginAdminView();
        av.formLogin();
    }
    
    public void inputPemain(String skor){
        Pemain p = new Pemain();
        p.inputPemain(skor);
    }
    
    public void menuAdmin(String[] data){
       ActionModel am = new ActionModel();
       am.cekLogin(data);
    }
    
    public void menuAdminView(){
      new MenuAdminView();
    }
    
    public void cekUser(String[] data){
       ActionModel am = new ActionModel();
       am.cekUser(data);
    }
    
    public void cekAdmin(String[] data){
       ActionModel am = new ActionModel();
       am.cekLogin(data);
    }
         
    public void inputRegis(String[] data){
        ModelRegis mr=new ModelRegis();
        mr.inputRegis(data);
    }
    
    public void inputAdmin(String[] data){
        ModelRegis mr=new ModelRegis();
        mr.inputAdmin(data);
    }
    
    public void delete(String nama){
        ActionModel model = new ActionModel();
        model.delete(nama);
    }
    
    public void lihatData(){
        ActionModel model = new ActionModel();
        String[][] data = model.lihatData();
        if(data == null)
            System.out.println("Tidak ada data!");
        else
            new DataView(data);
    }
    
    public void lihatSkor(){
        ActionModel model = new ActionModel();
        String[][] data = model.lihatSkor();
        if(data == null)
            System.out.println("Tidak ada data!");
        else
            new SkorView(data);
    }

  public void lihatData(String nama){
        ActionModel model = new ActionModel();
        DetailDataView view = new DetailDataView();
        view.openDetail(model.lihatData(nama));
}
    public void openMenu(){
      mainGame mg = new mainGame();
      mg.Games();
}
}
