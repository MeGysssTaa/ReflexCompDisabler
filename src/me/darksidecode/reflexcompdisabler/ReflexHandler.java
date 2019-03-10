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

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import rip.reflex.api.ReflexAPI;
import rip.reflex.api.ReflexAPIProvider;
import rip.reflex.api.event.ReflexLoadEvent;

@RequiredArgsConstructor
public class ReflexHandler implements Listener {

    private final ReflexCompDisabler instance;

    @Getter
    private ReflexAPI rapi;

    @EventHandler
    public void reflexLoaded(ReflexLoadEvent e) {
        rapi = ReflexAPIProvider.getAPI();
        instance.getLogger().info("[ReflexCompDisabler] Successfully hooked into Reflex " +
                "v" + e.getVersion() + " implementing API version " + e.getApiVersion());
    }

}
