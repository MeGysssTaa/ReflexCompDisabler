/*
 * Copyright 2019 DarksideCode
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.darksidecode.reflexcompdisabler;

import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import rip.reflex.api.ReflexAPI;

import java.util.regex.Pattern;

@RequiredArgsConstructor
public class CommandHandler implements CommandExecutor {

    private final ReflexCompDisabler instance;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            ReflexAPI rapi = instance.getReflexHandler().getRapi();

            if (rapi == null) {
                sender.sendMessage("Reflex is not ready yet. Please wait until it finishes starting up!");
                return true;
            }

            if (args.length == 2) {
                String op = args[0].trim().toLowerCase();
                String comp = args[1].trim().toLowerCase();

                if (comp.split(Pattern.quote(".")).length != 2) {
                    sender.sendMessage("Illegal component name. Components are named in the 'parent.child' format. " +
                            "See Reflex Wiki for details and a list of all components Reflex has: " +
                            "https://github.com/MeGysssTaa/ReflexIssueTracker/wiki/Checks'-Components");

                    return false;
                }

                switch (op) {
                    case "on":
                        if (rapi.isComponentEnabled(comp))
                            sender.sendMessage("The specified component is already enabled");
                        else {
                            sender.sendMessage("Enabled component " + comp);
                            rapi.enableComponent(comp);
                        }

                        return true;

                    case "off":
                        if (rapi.isComponentDisabled(comp))
                            sender.sendMessage("The specified component is already disabled");
                        else {
                            sender.sendMessage("Disabled component " + comp);
                            rapi.disableComponent(comp);
                        }

                        return true;

                    default:
                        return false;
                }
            } else
                return false;
        } catch (Throwable t) {
            sender.sendMessage("Error processing your command. Check console - there's the stacktrace.");
            t.printStackTrace();

            return true;
        }
    }

}
