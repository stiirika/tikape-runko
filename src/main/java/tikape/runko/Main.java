package tikape.runko;

import java.util.HashMap;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.AiheDao;
import tikape.runko.database.Database;
import tikape.runko.database.KayttajaDao;
import tikape.runko.database.KeskustelunavausDao;
import tikape.runko.database.ViestiDao;
import tikape.runko.domain.Aihe;
import tikape.runko.domain.Kayttaja;
import tikape.runko.domain.Keskustelunavaus;
import tikape.runko.domain.Viesti;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:sovellus.db");
        database.init();

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            AiheDao AiheDao = new AiheDao(database);
            map.put("aiheet", AiheDao.findAll());
            
            return new ModelAndView(map, "aihe");
        }, new ThymeleafTemplateEngine());
        

        post("/aihe/:id", (req, res) -> {
            
            String otsikko = req.queryParams("otsikko").trim();
            String sisalto = req.queryParams("sisalto").trim(); 
            String tunnus = req.queryParams("tunnus").trim();  
            KeskustelunavausDao keskustelunavaus = new KeskustelunavausDao(database);
            ViestiDao viesti = new ViestiDao(database);
            int aihe=Integer.parseInt(req.params(":id"));
            int row1=keskustelunavaus.selectId(aihe, 0)+1;       
            int row2=viesti.selectId(aihe,row1)+1;    
            
           if (!otsikko.isEmpty() && !sisalto.isEmpty()) {              
                keskustelunavaus.AddOne(new Keskustelunavaus(row1,aihe,"Pekka",otsikko,""));
                viesti.AddOne(new Viesti(row2,aihe,tunnus,row1,sisalto,""));              
            }

            res.redirect("/aihe/" + aihe);
            
            return "";
        });
        
        
        get("/aihe/:id", (Request req, Response res) -> {
            HashMap map = new HashMap<>();
            KeskustelunavausDao keskustelunavaus = new KeskustelunavausDao(database);
            ViestiDao viesti = new ViestiDao(database);
            map.put("keskustelunavaus", keskustelunavaus.findAll(Integer.parseInt(req.params(":id"))));
            map.put("lkm",viesti.CountAll(Integer.parseInt(req.params(":id")),keskustelunavaus.selectId(Integer.parseInt(req.params(":id")),0)));            
            map.put("date",viesti.selectDate(Integer.parseInt(req.params(":id")),keskustelunavaus.selectId(Integer.parseInt(req.params(":id")),0)));         
        return new ModelAndView(map, "keskustelunavaus");
        }, new ThymeleafTemplateEngine());
        
        
        post("/avaus/:id1/:id2", (req, res) -> {
            String sisalto = req.queryParams("sisalto").trim();
            String tunnus = req.queryParams("tunnus").trim();            
            ViestiDao viesti = new ViestiDao(database);
            int aihe=Integer.parseInt(req.params(":id1"));           
            int keskustelunavaus=Integer.parseInt(req.params(":id2"));
            int row=viesti.selectId(aihe,keskustelunavaus)+1;
            
           if (!sisalto.isEmpty() && !tunnus.isEmpty()) {              
                viesti.AddOne(new Viesti(row,aihe,tunnus,keskustelunavaus,sisalto,""));
            }

            res.redirect("/avaus/" + aihe + "/" + keskustelunavaus);
            return "";
        });        
        
        
        get("/avaus/:id1/:id2", (req, res) -> {
           HashMap map = new HashMap<>();
           ViestiDao viesti = new ViestiDao(database);       
           map.put("viesti", viesti.findAll(Integer.parseInt(req.params(":id1")),Integer.parseInt(req.params(":id2"))));
           
        return new ModelAndView(map, "viesti");
        }, new ThymeleafTemplateEngine());     
        
    }
}
