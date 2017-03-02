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
        // asetetaan portti jos heroku antaa PORT-ympäristömuuttujan
        if (System.getenv("PORT") != null) {
            port(Integer.valueOf(System.getenv("PORT")));
        }
        // käytetään oletuksena paikallista sqlite-tietokantaa
        String jdbcOsoite = "jdbc:sqlite:sovellus.db";
        // jos heroku antaa käyttöömme tietokantaosoitteen, otetaan se käyttöön
        if (System.getenv("DATABASE_URL") != null) {
            jdbcOsoite = System.getenv("DATABASE_URL");
        }

        Database database = new Database(jdbcOsoite);

        // Database database = new Database("jdbc:sqlite:sovellus.db");
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
            int aihe = Integer.parseInt(req.params(":id"));
            int row1 = keskustelunavaus.selectId() + 1;
            int row2 = viesti.selectId() + 1;

            if (!otsikko.isEmpty() && !sisalto.isEmpty() && !tunnus.isEmpty()) {
                keskustelunavaus.AddOne(new Keskustelunavaus(row1, aihe, tunnus, otsikko, ""));
                viesti.AddOne(new Viesti(row2, aihe, tunnus, row1, sisalto, ""));
            }

            res.redirect("/aihe/" + aihe);

            return "";
        });

        get("/aihe/:id", (Request req, Response res) -> {
            HashMap map = new HashMap<>();
            KeskustelunavausDao keskustelunavaus = new KeskustelunavausDao(database);
            ViestiDao viesti = new ViestiDao(database);
            map.put("keskustelunavaus", keskustelunavaus.findAll(Integer.parseInt(req.params(":id"))));

            // Tulee vielä tehdä lukumäärien ja viimeisempien viestien laskeminen
            //   map.put("lkm", viesti.CountAll(Integer.parseInt(req.params(":id")), i));
            //  map.put("date",viesti.selectDate(Integer.parseInt(req.params(":id")),keskustelunavaus.selectId(Integer.parseInt(req.params(":id")),0)));   
            return new ModelAndView(map, "keskustelunavaus");
        }, new ThymeleafTemplateEngine());

        post("/avaus/:id1/:id2", (req, res) -> {
            String sisalto = req.queryParams("sisalto").trim();
            String tunnus = req.queryParams("tunnus").trim();
            ViestiDao viesti = new ViestiDao(database);
            int aihe = Integer.parseInt(req.params(":id1"));
            int keskustelunavaus = Integer.parseInt(req.params(":id2"));
            int row = viesti.selectId() + 1;

            if (!sisalto.isEmpty() && !tunnus.isEmpty()) {
                viesti.AddOne(new Viesti(row, aihe, tunnus, keskustelunavaus, sisalto, ""));
            }

            res.redirect("/avaus/" + aihe + "/" + keskustelunavaus);
            return "";
        });

        get("/avaus/:id1/:id2", (req, res) -> {
            HashMap map = new HashMap<>();
            ViestiDao viesti = new ViestiDao(database);
            map.put("viesti", viesti.findAll(Integer.parseInt(req.params(":id1")), Integer.parseInt(req.params(":id2"))));

            return new ModelAndView(map, "viesti");
        }, new ThymeleafTemplateEngine());

    }
}
