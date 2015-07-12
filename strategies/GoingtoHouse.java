package strategies;

import data.Area;
import data.Constants;
import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;


public class GoingtoHouse implements Strategy {
    SceneObject[] portal;
    Area HOME = new Area(new Tile(3204, 3429, 0), new Tile(3204, 3441, 0), new Tile(3223, 3441, 0), new Tile(3223, 3429, 0));



    public boolean activate() {
        if ((portal = SceneObjects.getNearest(Constants.PORTAL)) != null && portal.length > 0) {
            if (Inventory.getCount(Constants.OAK_PLANKS) >= 27
                    && Inventory.contains(Constants.HAMMER)
                    && Players.getMyPlayer().getLocation().getY() != 5122
                    && HOME.contains(Players.getMyPlayer().getLocation()))
                return true;
        }
        return false;
    }


    public void execute() {
        if (portal != null && portal.length > 0
                && portal[0].distanceTo() < 25
                && HOME.contains(Players.getMyPlayer().getLocation()))
        {
            portal[0].interact(SceneObjects.Option.FIRST);
            Logger.addMessage("Going to player house",true);
            Time.sleep(new SleepCondition() {
                public boolean isValid() {
                    return Game.getOpenBackDialogId() > 0;
                }
            }, 1000);
            if (Game.getOpenBackDialogId() == 2469) {
                clickOption(5);
                final int x = Players.getMyPlayer().getLocation().getX();
                final int y = Players.getMyPlayer().getLocation().getY();
                Time.sleep(5500);

            }
        }
    }

    private void clickOption(int option) {

        int optionAction = 2494;
        switch (option) {
            case 0:
                optionAction = 2494;
                break;
            case 1:
                optionAction = 2495;
                break;
            case 2:
                optionAction = 2496;
                break;
            case 3:
                optionAction = 2497;
                break;
            case 4:
                optionAction = 2498;
                break;
            case 5:
                optionAction = 2472;
        }
        Menu.sendAction(315, 0, 0, optionAction, 0);
        Time.sleep(1500);
    }
}
