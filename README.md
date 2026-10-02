# Changelog - Tiered Industries (formerly Tiered Power)

## 1.27.1 - Charger fix

### Fixed
- The **Charger** refused an empty jetpack (and possibly other FE items). It now recognises this mod's powered items (jetpacks, Quantum Suit, drills, chainsaws, Portable Battery) directly, and accepts any item with an energy store that has room. The **Wireless Charger** uses the same lookup.

### Added
- Holding an item over the Charger's input slot that it won't accept now shows why in a red tooltip.

## 1.27.0 - Smithing and charging in autocrafting

### Added
- **Smithing Press**: a powered smithing table (netherite upgrades, armour trims, other mods' smithing recipes). Each input only goes into the slot a recipe uses it in, so assemblers and pipes can feed it. Use it with processing patterns, for example from JEI's + on a smithing recipe.
- **Charging patterns**: in Processing mode the Pattern Encoder's ⚡ button fills energy items in the outputs to full. With the same item as input, an assembler touching a Charger sends the item in and takes it back fully charged.
- **Quantum Power Core**: 4 Quantum Alloy + 4 Superconductor + an Ultimate Battery Box.

### Changed
- The **Quantum Suit** uses a Quantum Power Core instead of a Quantum Energy Cell. That removes the 20 Nether Stars and about 64 netherite from the full suit, and the whole suit can now be autocrafted.
- **Crafting patterns ignore charge**: a charged copy of an energy item (jetpack, tool, battery box or energy cell that kept its charge) in storage counts like an empty one, just as at a crafting table. Enchanted or renamed items are never used this way. Processing patterns stay exact, so charging patterns still work.
- The web guide has a **Changelog** section built from this file.

## 1.26.0 - Quantum Suit

### Added
- **Quantum Suit** (helmet, chestplate, leggings, boots): powered armour tougher than netherite (20 armour, 16 toughness for the full set). Each piece stores FE (10M, chestplate 20M) and charges in a Charger or near a Wireless Charger.
  - Helmet: night vision and water breathing (20 FE/t).
  - Chestplate: built-in jetpack, the fastest in the mod (120 FE/t while thrusting).
  - Leggings: Speed II (10 FE/t).
  - Boots: no fall damage, plus step assist (walk up full blocks).
  - **Energy shield**: each charged piece absorbs 22.5% of damage (90% for the full suit) at 1,000 FE per point absorbed. Damage that bypasses invulnerability (/kill, the void) isn't shielded.
- The creative tab has both empty and fully charged suit pieces.

## 1.25.0 - Nuclear fuel cycle

### Added
- **Plutonium**: the Chemical Washer reprocesses a Depleted Fuel Rod (with chlorine and water) into 1 Plutonium Dust. Smelt it into Plutonium Ingots.
- **MOX Fuel Rod**: steel + 2 uranium + 1 plutonium makes 2. Works in both Fission Reactors and their Fuel Ports. Lasts twice as long, with +50% power and +50% heat. The multiblock burns MOX first. Depletes into a normal depleted rod.

### Fixed
- The **craft request screen**'s plan list ran under the Back/Start buttons and overlapped the "scroll for more" hint. The list now sits in its own clipped box above the buttons (7 rows), with a scrollbar and an "x-y of n" counter.

## 1.24.1 - Reactor Gauge in reactor walls

### Changed
- The **Reactor Gauge** is now a multiblock part: build it into a Fission Reactor's wall in place of any Casing or Glass block (not edges or corners), and the reactor still forms. The controller links it, so it shows the reactor's status from anywhere on the walls. Placing it against a single-block reactor still works.

## 1.24.0 - Chemical ore processing

### Added
- **Chlorine** gas and **Salt** (tagged forge:dusts/salt).
- **Salt Evaporator**: 1,000 mB water -> 1 salt (5 s, 40 FE/t).
- **Brine Electrolyzer**: 1 salt + 500 mB water -> 250 mB chlorine + 250 mB hydrogen (2 s, 200 FE/t).
- **Chemical Washer**: 1 ore or raw ore + 100 mB chlorine + 500 mB water -> 4 dust (6 s, 300 FE/t). It works on every ore the Ore Purifier handles, including other mods' ores, and takes Tier Installers.

## 1.23.0 - Reactor extras and more tiered machines

### Added
- **Reactor Gauge**: placed against a Fission Reactor or Fission Controller, its face shows heat %, FE/t, status (Running / Idle / SCRAM) and fitted add-ons. Its comparator output follows heat (0-15).
- **Neutron Reflector**: right-click a fission reactor so its fuel rods last 50% longer. Shown in Jade; drops back out when the reactor is broken.
- **Tier Installers** now also work on the **Alloy Smelter** and **Fluid Mixer** (3 / 5 / 7 / 9 at once).

## 1.22.0 - Disk partitioning and storage security

### Added
- **Disk Workbench**: partition a Storage or Fluid Disk to up to 18 chosen items or fluids (empty = anything), with Fill-from-contents and Clear. Set a priority from -9 to +9. Settings are stored on the disk.
- Storing now fills **higher-priority disks first**. At the same priority, disks already holding the item come first, then disks partitioned for it, then the fullest.
- **Security Terminal**: locks a storage network to its owner (the placer) and trusted players, added by name. Others can't open its terminals, Drive Bays, buses, assemblers or Stock Keeper; can't use or link wireless terminals; can't break its blocks; and can't place storage parts, pipes or inventories against it. Only the owner can break the terminal, and server operators always have access. Networks without one are unchanged.

## 1.21.2 - Tiered Industries

### Changed
- The mod is now called **Tiered Industries**: mod list, guidebook, creative tabs, key bindings and the web guide. The internal id stays `tieredpower`, so existing worlds, items, configs and the jar's file name are unaffected.
- The web guide has a **Roadmap** section: what's done, what's next, and ideas.

## 1.21.1 - Quarry control and autocrafting loop fix

### Added
- **Quarry Planner**: mark two corners, then right-click a Quarry to dig exactly that rectangle (up to 65 x 65) from the higher corner down.

### Fixed
- **Autocrafting loops**: the planner no longer uses the item it's making as an ingredient further down the same chain. For example, it won't pulverize stored gold ingots just to smelt them back into gold ingots, which a Stock Keeper would repeat forever. Such requests now show as "can't craft".
- The **Quarry** now only pushes mined items out through faces set to Out (or In + Out) in its Sides window.

### Changed
- Author is now BobRoflza.

## 1.21.0 - Machine upgrades

### Added
- **Upgrades window** on every machine with upgrades (the green arrow button). It holds Speed and Energy cards and shows the Muffler and the machine's tier.
- **Tier Installers** (Advanced, Elite, Ultimate, Quantum): right-click to upgrade the Electric Furnace, Pulverizer, Compressor, Electric Sawmill, Ore Purifier or Rock Crusher in place, so it runs 3 / 5 / 7 / 9 operations at once. Install them in order. Power scales with how many run. Installers drop back out when the machine is broken.
- **Muffler**: right-click any machine to silence it, and remove it from the Upgrades window.
- Jade shows a machine's tier and whether it's muffled.

### Changed
- Upgrade cards stack to **8** per slot (was 4).
- **Efficiency Upgrade** is renamed **Energy Upgrade**. It's the same item and effect (-15% power each).
- Upgrade slots moved from the left of machine screens into the Upgrades window. Existing upgrades stay in place.

## 1.20.0 - Gas system part 2: oxygen steel and biogas

### Added
- **Methane** gas (biogas).
- **Oxygen Furnace**: 1 iron ingot or dust + 100 mB oxygen -> 1 steel ingot in 3 s at 80 FE/t, with no coal.
- **Bio-Digester**: crops, seeds, saplings, leaves, flowers, rotten flesh and more -> 80 mB methane each, with no power needed. What it accepts is set by the `tieredpower:biomass` item tag.

### Changed
- The **Gas Burner Generator** also burns methane: 12 FE/mB, or 15 with the same amount of oxygen. Hydrogen is still burned first.

## 1.19.0 - Gas system: fusion fuel, air separation, nitrogen cooling

### Added
- New gases: **Deuterium**, **Tritium** and **Nitrogen**.
- **Isotope Separator**: water -> deuterium (50:1), 400 FE/t.
- **Tritium Breeder**: lithium ingot -> 250 mB tritium, next to a running fission reactor. No power needed.
- **Air Separator**: power -> nitrogen (16 mB/t) + oxygen (4 mB/t). Needs open air.
- **Cryo Injector**: feeds nitrogen (20 mB/t) to a fission reactor for +50% power.

### Changed
- **Fusion Reactors** (single-block and multiblock) take deuterium and tritium by pipe: 250 mB of each equals one pair of cells. Gas is burned before cells, and cells still work.

## 1.18.0 - Storm Caller and quality of life

### Added
- **Storm Caller**: uses 4,000,000 FE and a **Storm Charge** to start a 5-minute thunderstorm. It follows its redstone setting, so it can be triggered by a comparator on a Lightning Collector.
- **Jade info**: Storage Controller (items, fluids, jobs), Drive Bay (disks, fill), Molecular Assembler (patterns, speed), Stock Keeper, Lightning Collector (stored, strikes), Storm Caller (status), Geothermal Generator and Water Wheel (output or what's missing).

### Changed
- The **creative tab** is split into five: Power, Machines, Pipes & Cables, Storage & Crafting, Tools & Materials.
- The **Wrench** now rotates six-way blocks (Import/Export Buses, terminal panels) through every direction, as well as machines.

## 1.17.0 - Lightning, geothermal and water power

### Added
- **Lightning Collector**: put a vanilla Lightning Rod on it. Every strike on the rod adds 2,500,000 FE. It stores 25M FE and outputs up to 20k FE/t. It glows after a strike, has a comparator output, and the Multimeter shows strikes caught.
- **Geothermal Generator**: 20 FE/t per touching lava source (up to 120 FE/t) and 5 FE/t per magma block. No fuel.
- **Water Wheel**: 8 FE/t per side with flowing water (up to 32 FE/t). Animated while turning.

## 1.16.1 - Fluids no longer break pipes and cables

### Fixed
- Water and lava washed away (broke and dropped) energy cables, item/fluid/gas pipes, Storage Cable, Import/Export Buses, terminal panels and other thin blocks. They now count as solid for fluids, so a pipe or cable blocks the flow like any other block.

## 1.16.0 - Fluids in autocrafting

### Added
- **Fluid and gas inputs/outputs in processing patterns** (3 each). Set them with a bucket or tank in the Encoder, or with JEI's + on machine recipes. Assemblers fill the machine from storage and drain fluid/gas results back into storage. Fluid-only patterns (e.g. water -> hydrogen) are allowed.
- The **planner** uses stored fluids, runs fluid-making patterns when a fluid is short, and counts byproducts as available for later steps. Plans show fluid lines.
- **Speed Upgrades in Molecular Assemblers**: +1 craft or machine operation per cycle each, up to 4.

## 1.15.0 - Stock Keeper and Jobs screen

### Added
- **Stock Keeper**: keeps up to 9 items at a target amount. It starts crafting jobs automatically when stock runs low, and shows per-item status (in stock / crafting / can't craft).
- **Jobs screen**: a Jobs button in every terminal lists all crafting jobs with live progress and a Cancel button for each.

## 1.14.0 - Autocrafting (part 2: processing and substitutes)

### Added
- **Processing patterns** (machine recipes): up to 9 inputs and 3 outputs with amounts. A Molecular Assembler holding one pushes the inputs into the machine it touches and pulls the results back into storage. The planner chains machine and crafting steps together.
- **Substitutes** for crafting patterns (on by default): any item the recipe accepts in a slot can be used, such as any planks for a piston. It uses whatever you have most of. Toggle with the S button.
- Pattern Encoder **C/P mode button**. JEI's + now works on machine recipes (Pulverizer, Alloy Smelter, furnace, other mods) to fill a processing pattern.

### Changed
- Pattern tooltips show inputs/outputs, and how many substitutes each slot accepts.
- 1.13.0 crafting patterns still work, but without substitutes. Re-encode them to get substitutes.

## 1.13.0 - Autocrafting (part 1: crafting recipes)

### Added
- **Pattern Encoder**: records crafting recipes onto **Blank Patterns**. Click items into its grid (copies only) or use JEI's + button.
- **Molecular Assembler**: holds 9 Crafting Patterns and does one craft every 8 ticks. More assemblers craft faster.
- **Crafting CPU**: runs one crafting job at a time. Right-click to see job progress; sneak + right-click to cancel.
- **Crafting from the terminals**: items your patterns can make appear marked "Craft" (or with a green + if some are already stored). Click, pick an amount, see the plan (from storage / to craft / missing) and press Start. Sub-parts are crafted automatically. Jobs survive a restart, and you get a chat message when a job finishes or is stuck waiting for ingredients.

## 1.12.3 - Item flow diagnostics

### Added
- **Multimeter on an item pipe**: lists every connection with its mode, the block on each side, what each Pull connection can take out, whether each Push destination would accept those items, and whether the network is stuck.
- **Multimeter on a machine**: shows each face's item setting, what the Out faces have ready, the power face and whether auto-output is on.

### Changed
- The **Sides** window now names faces as you see the machine standing in front of it: Front, Back, Left, Right and Top, with the bottom as the power face. The world compass direction shows when you hover a face. Existing settings are kept.

## 1.12.2 - Two-ingredient automation

### Fixed
- **Alloy Smelter with pipes, buses and hoppers:** each ingredient now goes into its own input slot. Before, one ingredient could fill both slots and block the recipe. Items that don't make a recipe with what's already loaded are refused, so the machine can't get stuck with an impossible pair. Players can still place items by hand as before.

## 1.12.1 - Machine sides rework

### Fixed
- An **Out** face now hands over finished items to pipes, Import Buses and hoppers on any side. Before, output slots were only reachable from the bottom, whatever the side setting said.

### Changed
- **Powered machines take power through the bottom face only**, leaving the other five faces for items. The Electric Pump and Quarry still take power on any side. To go back to any-side power, set `[machines] powerFromBottomOnly = false` in tieredpower-common.toml.
- The **Sides** window now opens to the right of the machine screen. Faces are shown by compass direction (N/E/S/W and Up) with the machine's front marked, plus the bottom shown as the power face and a colour legend. JEI's item list keeps clear of it.

## 1.12.0 - Tier 5 (Quantum)

### Added
- **Quantum Alloy Ingot**: made in the Alloy Smelter from 1 netherite and 4 superconductor ingots (makes 2).
- **Quantum tier** blocks:
  - Cable (512k FE/t)
  - Battery Box (2B FE, 2M FE/t)
  - Energy Cell (16.4B FE in an Energy Bank)
  - Fluid and Gas Pipes (256k mB/t)
  - Item Pipe (2,048 items/s)
  - Fluid Tank (4,096 buckets)
  - Solar Panel (7,500 FE/t)
- Config entries for the Quantum cable rate and battery capacity/rate.

### Changed
- Cleared all deprecation warnings: the supported replacements for `new ResourceLocation(...)`, JEI's `getBackground()` and `ModLoadingContext.get()`.

## 1.11.0 - Access points and creative tools

### Added
- **Wireless Access Point**: wireless terminals work within 128 blocks of any access point on their network (+10 FE/t each on the controller).
- **Creative Energy Cell**: unlimited power for testing.
- **Creative Storage Disk** and **Creative Fluid Disk**: practically unlimited capacity (63 types each).

### Changed
- Very large numbers show as T (trillions) or infinity instead of long digit strings.

## 1.10.0 - Wireless terminals

### Added
- **Wireless Terminal** and **Wireless Crafting Terminal**: link one to a Storage Controller (sneak + right-click) and open your storage from up to 64 blocks away. It runs on FE (500k, 5 FE/t while open), and the JEI + button works on the crafting version.
- **Open Wireless Terminal** key (unbound by default; set it under Controls > Key Binds > Tiered Power).

## 1.9.0 - Fluid and gas storage

### Added
- **Fluid Storage Disks** (4K-256K buckets, 63 types) for fluids and gases, in the same Drive Bays.
- **Storage Interface**: pipes, machines and reactors can push into, or pull out of, storage from anywhere on the cable.
- **Storage / Crafting Terminal Panels**: flat, AE2-style terminals for walls, floors and ceilings. Craft them from the blocks and back.
- The terminals show fluids and gases: fill a held bucket or tank from storage, or pour one in. There's a new All / Items / Fluids view button.
- The Import and Export Buses now move fluids and gases too. Filter a fluid with a bucket or tank of it.

### Changed
- The bus screens now spell out the direction (block -> storage or storage -> block) and show whether the bus is connected to a powered network.

## 1.8.0 - Storage expansion

### Added
- **Crafting Terminal**: craft straight from storage. Grid slots refill from storage, so shift-clicking the result crafts in bulk. There's a clear button, and the grid empties into storage when closed.
- **JEI "+" button** for the Crafting Terminal: fills the grid from storage and your inventory, with missing items shown in red. The Crafting Terminal is also listed as a JEI crafting station.
- **Import Bus**: pulls items from the inventory it faces into the network, with an optional filter.
- **Export Bus**: pushes filtered items from the network into the inventory it faces.

## 1.7.0 - Item storage network

### Added
- **Storage Controller**: the powered heart of the network (20 FE/t + 5 per Drive Bay). It works as one big inventory for Item Pipes and hoppers.
- **Storage Cable**: links network parts together.
- **Drive Bay**: holds 8 Storage Disks.
- **Storage Disks**: 4K / 16K / 64K / 256K items, 63 types each. Contents stay on the disk.
- **Storage Terminal**: a searchable grid of everything stored. Take, store, shift-click, search by name or @mod, and sort by count or name.

## 1.6.0 - Monitoring and control

### Added
- **Multimeter**: right-click a cable for the whole network's power (in, to machines, into storage, stored), or any block for its energy, output and tanks.
- **Energy Meter**: an in-line meter. Power goes in the left side and out the right, and the screen shows FE/t and a total. A redstone signal turns the flow off, and a comparator reads how much is flowing.
- **Power Monitor**: shows a whole cable network on its screen. A comparator reads how full the batteries are.
- Both Fission Reactors give comparators a signal for their heat.
- Cable networks now measure their own power flow.

## 1.5.0 - Gases

### Added
- **Hydrogen and Oxygen** gases. They travel in Gas Pipes and can be kept in Fluid Tanks.
- **Gas Electrolyzer**: splits water into hydrogen and oxygen (10 mB water -> 20 mB H2 + 10 mB O2 per tick, 200 FE/t). It refuels Hydrogen Jetpacks.
- **Gas Burner Generator**: burns hydrogen at 7 FE/mB (280 FE/t), or 9 FE/mB (360 FE/t) with oxygen. It idles when full.
- **Steam Engine**: a cheap early steam generator, 1 FE per mB of steam, up to 40 FE/t.
- **Steam Hammer**: runs Pulverizer recipes on steam (20 mB/t, 7.5 s per item).
- **Hydrogen Jetpack**: a 64,000 mB tank, 2 mB/t while thrusting.

## 1.4.0 - Fixes and quality of life
- Teleporters work (crouching on a pad now triggers it), and pads can be named: right-click a pad to rename it and pick a destination from every pad in the world.
- Machine side configuration: a Sides button in every machine GUI sets each face to In / Out / In+Out / Off for hoppers, pipes and auto-output.
- Fuel rods stack to 16, depleted rods to 64.
- Coal Generator, Boiler and both Fission Reactors pause instead of wasting fuel when their output is full.
- New Condenser: steam back into water (10:1), for a closed water loop on fission reactors.

## 1.3.0 - Polish and balance
- Balance review of every tier (see docs/BALANCE.md). Enchanting Machine now 400 FE/t.
- New config knobs: generationMultiplier and machineEnergyMultiplier scale the whole mod.
- Wind Turbine has a real spinning three-blade rotor.
- Coal Generator has a brick chimney that smokes while it burns.
- Every item: "Hold Shift for details" shows its guide description in the tooltip.

## 1.2.1
- Pipe connections now have four Wrench modes: Push, Pull, Push + Pull and Disabled (Item, Fluid and Gas Pipes). Pipe-to-pipe: connect / disconnect.
- Wrench picks the right connection more reliably (clicking the centre uses the face you clicked).
- Jade shows each pipe connection's mode and warns when a pipe network has nothing to deliver to.

## 1.2.0 - More machines
- Enchanting Machine: enchant items and books at level 10/20/30 with power and lapis instead of XP.
- Mob Grinder: kills mobs in front of it with player drops; stores XP (take it from the GUI); hostile-only or all mobs.
- Spawner Controller: powered vanilla spawners work without a player nearby and spawn faster.
- Fluid Mixer: concrete powder -> concrete, dirt -> mud, lava + water -> obsidian.
- Teleporter pads + Teleporter Linker: crouch to travel between linked pads, even across dimensions.

## 1.1.2
- Fission Fuel Port now only takes fuel in - right-click it with rods to load them (or use hoppers/pipes); empty hand shows what's loaded.
- New Fission Waste Port pushes depleted rods out into pipes, chests or hoppers.

## 1.1.1
- Multiblock Fission Reactor now floods its empty interior with water from the Coolant Ports - the rods sit underwater, and every water block adds 2 cooling. Coolant Channels are now optional.
- Clearer messages: the ports only work once the reactor has formed (it needs at least one Fuel Assembly).

## 1.1.0 - Multiblock Fission Reactor
- Any box from 3x3x3 to 13x13x13: Fission Casing, Fission Glass, Fuel / Coolant / Power Ports and the Fission Controller.
- Inside: Fuel Assemblies (1,000 FE/t each, +10% per touching assembly) and Coolant Channels (+10 heat/t cooling).
- Water cooling makes steam, pushed out of Coolant Ports. SCRAM at 100% heat (or meltdown if enabled).
- Fuel Assemblies glow and pulse while running. The single-block Fission Reactor remains as the starter version.

## 1.0.0 - first public release
- **In-game guidebook**: every player gets the Tiered Power Guide on first join (or craft a book + copper ingot).
  Categories, search, item pages with key numbers, live crafting recipes and everything each machine can process.
- Mod logo and full metadata; MIT license.

## 0.19 - Pipe upgrades
- Items visibly travel through Item Pipes (glass tubes); filter match modes (Item / Tag / Mod); connection priorities 0-9.

## 0.18 - Solar, wind and fission
- Solar Panels (4 tiers), Wind Turbine (height and weather based), uranium ore and the Fission Reactor (heat, water cooling, steam, SCRAM).

## 0.17 - Powered items
- Electric and Advanced Drills (3x3), Electric Chainsaw (tree felling), Jetpacks, Portable Batteries, Wireless Charger.
- See-through reactor, bank and tank glass; Magnet Coils animate while the reactor runs.

## 0.16 - Machine outputs, Item Pipes, filters
- Auto-output button on every machine; Item Pipes (4 tiers) with pull connections; Item Filters; Quarry settings.

## 0.15 - Automation
- Powered Quarry, Crop Farmer, Auto-Crafter, Chunk Loader.

## 0.14 - Processing and fluid/gas pipes
- Ore Purifier (x3), Compressor and plates, Electric Sawmill, Rock Crusher; Fluid and Gas Pipes; steam as a real gas.

## 0.12 - Fluids
- Electric Pump, Fluid Tanks, Freezer.

## 0.11 - Quality of life
- Wrench, redstone control, comparator output, config file, sounds and particles, Jade tooltips, Sink.

## 0.10 - Storage and networks
- Tiered Battery Boxes with configurable faces, the Energy Bank multiblock, network-based cables.

## 0.4 - 0.8 - Machines, JEI, upgrades, fusion multiblock
- Pulverizer, Alloy Smelter, Charger; JEI support and textured GUIs; Speed and Efficiency upgrades; any-size multiblock Fusion Reactor.

## 0.1 - 0.3 - Coal, steam, fusion
- Coal Generator, cables, Battery Box, Electric Furnace; Boiler and Steam Turbine; Electrolyzer and the compact Fusion Reactor.
