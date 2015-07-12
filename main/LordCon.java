package main;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import strategies.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

@ScriptManifest(author = "LordgMage",
        category = Category.OTHER,
        description = "Makes oak racks",
        name = "LordCon",
        servers = {"Ikov"},
        version = 2.0)

public class LordCon extends Script implements Paintable {
    private ArrayList<Strategy> strategies = new ArrayList<Strategy>();
    public static int racksMade;
    public static int trips;
    private long startTime = System.currentTimeMillis();


    public boolean onExecute() {
        strategies.add(new GoingtoHouse());
        strategies.add(new MakeRack());
        strategies.add(new Remove());
        strategies.add(new Restock());
        strategies.add(new ConBanking());
        strategies.add(new Login());
        provide(strategies);
        return true;
    }

    private String runTime(long i) {

        DecimalFormat nf = new DecimalFormat("00");
        long millis = System.currentTimeMillis() - i;
        long hours = millis / (1000 * 60 * 60);
        millis -= hours * (1000 * 60 * 60);
        long minutes = millis / (1000 * 60);
        millis -= minutes * (1000 * 60);
        long seconds = millis / 1000;
        return nf.format(hours) + ":" + nf.format(minutes) + ":" + nf.format(seconds);
    }

    private Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch (IOException e) {
            return null;
        }
    }

    private final Color color1 = new Color(255, 255, 255);
    private final Color color2 = new Color(0, 0, 0);


    private final Font font1 = new Font("Arial", 0, 9);

    private final Image img1 = getImage("http://i.imgur.com/uP09kFJ.png");

    public void paint(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        g.drawImage(img1, 1, 269, null);
        g.setFont(font1);
        g.setColor(color2);
        g.drawString("Time Run" + runTime(startTime), 202, 375);
        g.drawString("Racks Made:" + racksMade, 220, 395);
        g.drawString("Trips Made:" + trips, 219, 414);
    }
}

