/*
 *  Copyright (C) <2022> <XiaoMoMi>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.momirealms.customcrops.api.object.requirement;

import net.momirealms.customcrops.api.object.action.Action;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TimeImpl extends AbstractRequirement implements Requirement {

    private final List<String> times;

    public TimeImpl(@Nullable String[] msg, @Nullable Action[] actions, List<String> times) {
        super(msg, actions);
        this.times = times;
    }

    @Override
    public boolean isConditionMet(CurrentState currentState) {
        long time = currentState.getLocation().getWorld().getTime();
        for (String range : times) {
            String[] timeMinMax = range.split("~");
            if (time > Long.parseLong(timeMinMax[0]) && time < Long.parseLong(timeMinMax[1])) {
                return true;
            }
        }
        notMetMessage(currentState);
        return false;
    }
}
