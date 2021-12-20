import frontcontroller.FrontController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import models.JsonResponse;
import models.Users;
import dto.UsersDTO;

import java.sql.Connection;
import java.sql.DriverManager;


public class Main {

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/frontend", Location.CLASSPATH);
        }).start(9000); // localhost:9000
        new FrontController(app);

        /*
         * What is a session?
         *   temporary storage stored on a server.
         *   - We can utilize this concept for authentication in an application
         *   - If your session is still active, then you dont have to log back into the site, the application will rememeber you
         *   - Generally, session expire after 30 minutes but this can be changed
         *
         *
         * The context object has a session attribute in javalin to utilize sessions.
         *
         * */

       app.post("/api/login", context -> {
            Users user = context.bodyAsClass(Users.class);


             // If this was an actual application, you would validate the users credentials before logging in.
            // //hard coded, but can get this from the database
                //user.setUser_role_id(1);
                context.sessionAttribute("user-session", user);
                context.json(new JsonResponse(true, "login successful", user));



        });



        app.get("/api/check-session", context -> {
            Users user = context.sessionAttribute("user-session");

            if(user == null){
                context.json(new JsonResponse(false, "no session found", null));
            }else{
                context.json(new JsonResponse(true, "session found", user));
            }

        });


        app.delete("/api/logout", context -> {
            context.sessionAttribute("user-session",null);
            context.json(new JsonResponse(true, "Session has been destroyed and you have successfully logged out", null));
            //clear cached browser data, so you can't log back in with the back arrow button
        });


    }
}

