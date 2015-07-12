package strategies;

import data.Area;
import data.Constants;
import main.LordCon;
import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;

public class ConBanking implements Strategy{
    Area HOME = new Area(new Tile(3204, 3429, 0), new Tile(3204, 3441, 0), new Tile(3223, 3441, 0), new Tile(3223, 3429, 0));
    private SceneObject[] banks;

    public boolean activate() {
        if ((banks = Bank.getNearestBanks()) != null && banks.length > 0) {
            if (Inventory.getCount(Constants.OAK_PLANKS) <= 3) {
                return true;

            }
        }
        return false;
    }


    public void execute() {
        Logger.addMessage("Banking",true);
        banks[0].interact(SceneObjects.Option.OPEN);
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Bank.isOpen();
            }
        }, 2500);
        Bank.withdraw(Constants.OAK_PLANKS,27,500);
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Inventory.isFull();
            }
        }, 2500);
        Bank.close();
        LordCon.trips += 1;
    }
}
