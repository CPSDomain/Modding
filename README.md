# Changelog - Tiered Industries (formerly Tiered Power)

## 1.50.3 - Matrix crafting without assemblers

### Fixed
- Starting a craft said "Add a Molecular Assembler with patterns to the network" even when an Assembly Matrix held the patterns. A network with only a Matrix (plus Machine Connectors or buses on the machines) now crafts as it should; Molecular Assemblers are optional.

## 1.50.2 - Buses work with the Matrix

### Fixed
- Machines with only an **Import Bus** or **Export Bus** on them didn't count as connected, so the Assembly Matrix couldn't use them. Buses now work as machine interfaces too: they appear in **Connected machines** and the Matrix sends pattern inputs through them.
- Results an Import Bus pulls out of a machine into storage now count towards the running craft, so the job doesn't wait forever for output the bus already took.
- A bus only reaches the block it faces, so a bus between two machines doesn't pick up the wrong one.

## 1.50.1 - Matrix fixes and no mobs in multiblocks

### Fixed
- Big storage networks: the network scan stopped after 2,048 blocks, so machines connected far from the controller (for example Alloy Smelters) never showed in the Matrix and couldn't be used for crafting. The limit is now 32,768.
- Mobs no longer spawn on machines, Matrix parts, Pattern Banks, Accelerators, reactor and bank casings, or solar/wind panels, so they can't spawn inside a multiblock.
- The Assembly Matrix interior now ignores grass, snow layers and other replaceable blocks instead of refusing to form.

### Added
- Matrix **Connected machines** panel: warns when the network is too big, and lists any Machine Connector that isn't touching a machine, with its position.
- Right-click a **Machine Connector** with an empty hand to see whether it's linked to the network and which machine it serves.

## 1.50.0 - Logic Controller

### Added
- **Logic Controller**: joins the storage network and runs up to 4 rules: IF a condition THEN a redstone signal out of a chosen side (or all sides).
  - **Item**: how many of an item storage holds, below or above a number.
  - **Power**: how full the batteries touching the controller are, in percent.
  - **Redstone**: the controller is receiving a signal.
- Each rule shows a light (lit when true) and its current reading on hover. Rules are checked twice a second.

## 1.49.0 - Energy Core

### Added
- **Energy Core**: a steel cage with a glowing orb inside that grows, brightens and spins faster as it fills, coloured by tier.
  - Five tiers: 1 billion FE (I), 10 billion, 100 billion, 1 trillion, 10 trillion FE (V).
  - **Core Upgrades** II-V: right-click the core, in order.
  - Keeps its tier and energy when broken. Comparator output by fill.
- **Input Pylon** and **Output Pylon**: link to the nearest core within 8 blocks by themselves. Each moves 1M FE/t at tier I up to 256M FE/t at tier V, with sparks travelling along the link. Output Pylons push into anything touching them.
- Jade and right-click readouts for the core and pylons.

## 1.48.0 - Liquid Experience

### Added
- **Liquid Experience** (20 mB = 1 XP point), with a bucket. It glows faintly when poured out.
- The **Mob Grinder** pushes the XP it collects into tanks and machines next to it as Liquid Experience, and pipes can pull it out. Right-clicking to collect XP still works.
- The **Enchanting Machine** has an XP tank (16,000 mB). With 40 mB per enchantment level in the tank, it enchants on XP instead of power, twice as fast. Without XP it runs on power as before.

## 1.47.1 - 3D gas cylinders

### Changed
- The **Gas Cylinder** is now a 3D canister: rounded steel body, a shoulder coloured by the gas inside, and a brass valve and handle. It shows up that way in hand, on the ground, in item frames and in the inventory.

## 1.47.0 - Biodiesel

### Added
- **Bio Refinery**: seeds (sunflowers best) are pressed into plant oil, and sugary crops are fermented into ethanol. They blend 1:1 into **Biodiesel**, which is pushed out to Fluid Pipes. 20 FE/t.
- **Diesel Generator**: burns 1 mB a tick. Biodiesel 200 FE/t, Rocket Fuel 300 FE/t, Creosote Oil 40 FE/t. Holds 200,000 FE and pushes out of every side.
- **Biodiesel** liquid and bucket.

## 1.46.0 - Gas containers, 3D Digital Miner

### Added
- **Gas Cylinder**: a handheld bottle for 8,000 mB of one gas. Right-click a machine or tank to fill or empty it. Its shoulder is coloured by the gas. The creative tab has a full one of every gas.
- **Gas Tank**: a pressure cylinder for 64,000 mB of one gas. Pipes connect on any side, and it keeps its contents when broken.

### Changed
- The **Digital Miner** is a 3D drilling rig: base plate, corner struts, radar screen, antenna and drill.

## 1.45.0 - Coke Oven, Industrial Blast Furnace, flat buses

### Added
- **Coke Oven** (no power): coal -> **Coal Coke** + 250 mB **Creosote Oil** (30 s); logs -> charcoal + 125 mB Creosote (15 s). Coal Coke burns twice as long as coal.
- **Industrial Blast Furnace** (no power): iron ingot + Coal Coke -> steel ingot (20 s).
- **Creosote Oil** (pipes or buckets) and **Treated Planks** (8 planks + a Creosote bucket).
- JEI pages for both machines.

### Changed
- **Import Bus** and **Export Bus** are now flat plates against the inventory, with cable stubs, so Storage Cable joins up neatly like the other panels.

## 1.44.1 - Build fix

### Fixed
- Compile error in 1.44.0: the Digital Miner used `ItemStack.hashItemAndTag`, which doesn't exist in Minecraft 1.20.1. It now fingerprints its slots from each item and its data.

## 1.44.0 - Digital Miner

### Added
- **Digital Miner**: a quarry that mines only the ores you choose.
  - Item Filters in its slots pick the ores (none = every ore).
  - An Enchanted Book of Silk Touch or Fortune I-III sets how they're mined (not used up).
  - It scans a 33x33 area under itself, layer by layer down to the bottom of the world, mining only wanted ores (800 FE each, up to 4 a second) and leaving everything else in place.
  - Changing the filters or book rescans from the top. The front's radar animates while mining. Jade shows its progress.

## 1.43.0 - Greenhouse system

### Added
- **Sprinkler**: hangs above crops and runs on water (pipe or bucket, 20 mB a second). It keeps farmland in a 9x9 area up to 6 below wet and gives a 15% chance of extra growth each second, with a shower of water.
- **Grow Lamp**: hanging lamp, 40 FE/t. Full light (crops grow indoors and at night) and a 10% chance of extra growth each second. The bulb glows when powered.
- **Fertilizer** (2 bone meal + rotten flesh + dirt -> 4): three bone meal in one. The **Crop Farmer** now takes it in its seed slots and fertilises unripe crops automatically.
- **Greenhouse Glass**: green-framed glass for building the greenhouse.

## 1.42.0 - Plenisher, Block Mover, Ore Scanner, drill filters

### Added
- **Fluidic Plenisher**: fills the space below and around it with piped-in fluid. One source block every half second (1,000 mB + 100 FE), up to 32 blocks, never above itself.
- **Block Mover**: picks up a chest, machine or tank with its contents and puts it down elsewhere, contents intact.
- **Ore Scanner**: powered handheld. Lists ores within 16 blocks with counts and the direction and distance to the nearest of each (5,000 FE per scan).
- **Laser Drill filters**: Item Filters in the drill's input slots block (or allow) ores.
- **Item Filter "Name" mode**: matches items whose id starts with the same word, so one deepslate ore blocks every `deepslate_...` ore. This works on pipes too.

## 1.41.0 - Automated brewing, gem ore processing

### Added
- **Brewing Machine**: 3 bottle slots, an ingredient slot and 3 outputs.
  - Fills glass bottles from its own water tank (piped or bucket), then brews all three with one ingredient: 5 s, 20 FE/t, no blaze powder.
  - Every vanilla potion and modded brewing.
  - JEI's + on a brewing recipe makes a 3-bottle pattern for autocrafting.
- **Pulverizer and Ore Purifier recipes** for redstone, lapis, coal, diamond, emerald, nether quartz and ancient debris ores. They use ore tags, so **deepslate** and other mods' variants work too, and the Chemical Washer picks them up automatically.

### Fixed
- Deepslate redstone ore (and the other gem/dust ores) couldn't be pulverized, purified or washed: those ores had no recipes at all.

## 1.40.0 - Radiation and Hazmat Suit

### Added
- **Radiation**, off by default: set `[radiation] enabled = true` in the config, with `strength` as a percentage.
  - Running fission reactors within 16 blocks and carried nuclear materials (depleted rods, plutonium, MOX, uranium) build up a dose that fades when you're clear.
  - Effects: nausea at 100 rad, weakness and hunger at 300, poison at 600, damage from 1,000. Dying resets it.
- **Hazmat Suit** (4 pieces, 25% protection each).
- **Radiation Shielding Module** for the Quantum Chestplate (100% protection).
- **Geiger Counter**: hold it for exposure and dose readings, with clicks.
- **Iodine Tablets**: -300 rad each.

### Fixed
- The **Pattern Encoder**'s Machine slot sat under the Encode button and couldn't be clicked. It's now bottom-left (labelled "Machine"), and its tooltip wraps.

## 1.39.0 - Orbital Mining Laser

### Added
- **Mining Satellite**: a second Rocket payload.
- **Laser Drill**: place it under open sky and it claims one of your Mining Satellites. While powered, a laser beam comes down from orbit and it drills one ore a second (2,000,000 FE each, about 100,000 FE/t). Ores come from the `forge:ores` tag, other mods' ores included, with rare ores rarer.
- **Laser Lenses** (iron, copper, gold, redstone, diamond, uranium): in the drill's input slots, each makes its ore x10 as likely. They stack.
- Satellites now have a type: Receiver Dishes claim Solar Satellites and Laser Drills claim Mining Satellites. Existing satellites stay solar.

## 1.38.1 - Rocket liquid buckets

### Added
- **Buckets** of **Rocket Fuel**, **Liquid Methane** and **Liquid Oxygen**, in the creative tab and filled from the machines' tanks like any bucket. The liquids can also be poured into the world.
- The **Launch Controller**, **Cryogenic Condenser** and **Fuel Refinery** accept buckets (and other fluid containers) on right-click. A rocket needs 16 buckets of Rocket Fuel.

## 1.38.0 - Rocket program, part 2: launch and orbit

### Added
- The **launch site**:
  - **Launch Pad**: hazard-striped deck plates, 3x3.
  - **Launch Tower**: steel lattice with diagonal bracing.
  - **Launch Controller**: desk console with an angled screen.
- Put a **Rocket** and a **Solar Satellite** in the controller and pipe in 16,000 mB of Rocket Fuel. The rocket stands on the pad.
- Press **LAUNCH**: a 10-second countdown, then lift-off with flame and smoke. The satellite reaches orbit and the server is told. A checklist on the screen shows what's missing.
- **Receiver Dish** (tilted dish on a post): right-click to claim one of your satellites for about 100,000 FE/t, day and night. Sneak + right-click releases it. Holds 10M FE and pushes out of every side.
- The **Rocket** has a 3D model (body, nose cone, fins, engine bell), used for the item and on the pad.
- Config: `satelliteOutput`. Jade and the Multimeter show the dish's output.

## 1.37.0 - Rocket program, part 1: fuel and parts

### Added
- **Liquid Methane**, **Liquid Oxygen** and **Rocket Fuel**: liquids, so they go in Fluid Pipes.
- **Cryogenic Condenser**: 40 mB methane or oxygen + 4 mB nitrogen + 200 FE -> 20 mB of the liquid.
- **Fuel Refinery**: 10 mB Liquid Methane + 20 mB Liquid Oxygen + 100 FE -> 30 mB Rocket Fuel.
- Rocket parts from existing machines:
  - **Hull Plate**: Compressor, 4 steel plates.
  - **Rocket Engine**: Smithing Press, Rocket Nozzle + netherite template + Quantum Alloy.
  - **Fuel Tank Section**, **Guidance Computer** and **Nose Cone**.
- The **Rocket** and the **Solar Satellite** payload.
- JEI pages for both new machines.

The Launch Pad, launch and Receiver Dish come in part 2.

## 1.36.0 - Quantum Suit modules

### Added
- **Quantum Suit modules**: wear the piece and right-click the module to fit it.
  - **Magnet** (chestplate): pulls items and XP orbs from 8 blocks. Sneak to pause it.
  - **Flight** (chestplate): creative-style flight while charged, 100 FE/t while flying.
  - **Auto-Feed** (helmet): eats the most filling safe food when you're hungry.
  - **Jump Boost** (leggings): about three-block jumps.
  - **Water Walking** (boots): walk on water. Sneak to go under.
- **Suit Modules key** (default **V**): a screen listing fitted modules with On/Off and Remove (the module is returned).

## 1.35.0 - New logistics

### Added
- **Wireless Sender** and **Wireless Receiver**: item and fluid transport at any distance, across dimensions. The Sender holds 9 item slots and a 16,000 mB tank. Each linked Receiver takes up to 32 items and 2,000 mB twice a second and pushes them into its neighbours. Link them with the Wireless Linker.
- **Item Buffer** (27 slots) and **Fluid Buffer** (32,000 mB): fill from any side except the front, and push out of the front (16 items / 4 ticks, or 1,000 mB / tick).
- Jade shows each new block's state.

### Changed
- The **Power Linker** is now the **Wireless Linker**: it links Power Transmitters to Receivers and Wireless Senders to Receivers. Existing linkers keep working.

## 1.34.0 - Polish and usability

### Added
- **JEI recipe pages** for the Chemical Washer, Oxygen Furnace, Salt Evaporator, Brine Electrolyzer, Bio-Digester, Isotope Separator, Air Separator, Tritium Breeder, Particle Collider and Antimatter Reactor. The Smithing Press is listed on vanilla smithing. JEI's + in the Pattern Encoder fills in the machine from these pages.
- **Power history graph**: right-click a Power Monitor for generated / used / battery fill over the last 10 minutes or 2 hours, with hover values and auto-refresh. Sneak + right-click shows the old readout.
- **Job steps**: in a terminal's Jobs screen, click a job to see each step: how many are left, how many are running, and on which machine.
- **Config [endgame]** section: antimatter FE per mB, Particle Collider cost, Power Transmitter rate, Quantum Drill FE per block and Lightning Collector FE per strike.

## 1.33.0 - Antimatter, fluid filters, Quantum Drill

### Added
- **Antimatter** gas, made by the **Particle Collider**: 10 mB deuterium + 10 mB tritium + 20,000 FE -> 1 mB antimatter, one per tick (more with Speed Upgrades).
- **Antimatter Reactor**: burns antimatter at 50,000 FE per mB, up to 20 mB/t (1,000,000 FE/t). Holds 50M FE and pushes out of every side. That's 2.5x the power used to make the antimatter.
- **Fluid Filter**: up to 9 fluids, Allow or Block, for fluid and gas pipe connections. Right-click a connection to install; sneak + right-click with an empty hand to remove. On Pull connections, an allow-list pulls just those fluids from tanks holding several.
- **Quantum Drill**: drill + shovel + axe + hoe, very fast, mines 1x1 / 3x3 / 5x5 / 7x7 (shift + right-click to switch). 50M FE, 400 FE per block.

### Changed
- Drill area mining is generalised to any radius. The Advanced Drill still switches between 1x1 and 3x3.

## 1.32.0 - Fusion upgrades

### Added
- **Plasma Coils Mk I, II, III**: right-click a Fusion Reactor or Fusion Controller, fitting them in order, for x2 / x3 / x4 output. Fuel burns x1.5 / x2 / x2.5 as fast, so you get more power per fuel pair at every tier. Coils drop back out when the reactor is broken. Jade shows the tier.
- The **Reactor Gauge** now works on **Fusion Reactors**, single-block and multiblock (including built into the multiblock's walls). It shows plasma temperature, output and plasma tier.

### Changed
- The Fusion Controller's output limit is raised to 512,000 FE/t so a Mk III reactor can deliver its power.

## 1.31.5 - Panels plug into cables, wavier power line

### Fixed
- **Panels and Storage Cable left a gap**: the panel sits flat against its machine, while the cable ends at the middle of the panel's block space. Panels (both terminal panels, the Assembler Panel and the Machine Connector) now draw a short cable piece from each side with Storage Cable back to the plate, so the cable visibly plugs in.

### Changed
- The power cables' glowing line is **thinner** (1 pixel) and animates as a smooth **travelling wave**: a bright pulse rolling along the cable.

## 1.31.4 - Assembler Panel, see-through cables

### Added
- **Assembler Panel**: a flat Molecular Assembler (9 patterns, upgrades, same screen) that sticks onto a machine's face. Sneak + right-click to place it on a machine. It converts to and from a Molecular Assembler in the crafting grid.

### Changed
- **Power cables are see-through** (every tier). A **glowing yellow line** flows through them while power is moving, and goes dark when the network is idle. It updates about once a second.

## 1.31.3 - Set a pattern's machine in the Matrix

### Added
- In the **Matrix Controller**, click a processing pattern while holding a machine's item to set which machine it runs on. There's no need to re-encode older patterns. Hovering a processing pattern with no machine shows a reminder.

### Fixed
- JEI's item list drew over the Matrix Controller's Connected machines panel.
- The page arrows overlapped the page number.

## 1.31.2 - Easier Matrix building

### Changed
- **Pattern Banks and Crafting Accelerators can take the place of any wall block** of the Assembly Matrix, as well as going inside. There's no need to open the box up to add them.

## 1.31.1 - Matrix fixes, flat Machine Connector

### Fixed
- **Patterns could vanish** when put into a Matrix Controller with no Pattern Banks inside. The slots now refuse patterns until there's a Bank, and the screen says "Formed - put Pattern Banks inside".
- The Matrix Controller's status line overlapped the "Inventory" label. It has its own row now.

### Changed
- The **Machine Connector** is now a **flat panel**, like the terminal panels. Sneak + right-click to stick it onto a machine's face, then connect Storage Cable to it.

### Added
- The **Matrix Controller** screen shows a **Connected machines** panel: every machine touching a Molecular Assembler or Machine Connector on the network, counted by type.

## 1.31.0 - Assembly Matrix and Machine Connector

### Added
- **Assembly Matrix** multiblock: a hollow 3x3x3-7x7x7 box of **Matrix Casing** / **Matrix Glass** with a **Matrix Controller** in a wall, holding **Pattern Banks** (27 patterns each) and **Crafting Accelerators**. The controller's screen pages through the banks.
  - Crafting patterns are crafted inside the Matrix: 2 per cycle, +2 per accelerator.
  - Processing patterns run on any machine of the right type that a Molecular Assembler or Machine Connector touches, anywhere on the network, in parallel across machines.
- **Machine Connector**: joins the machine it touches to the network for the Matrix's patterns. No slots, no screen, 4 operations per cycle.
- Patterns can name their **Machine**. The Pattern Encoder (processing mode) has a Machine slot, and JEI's + fills it in for this mod's machines, furnace recipes and smithing. Pattern tooltips show it.

### Changed
- **Molecular Assemblers are back to 9 pattern slots.** Patterns from the extra slots added in 1.28.1 drop out beside the assembler the first time it loads, so nothing is lost.
- Results from machines are now credited per job step (each step only takes what it's owed), so two jobs making the same item no longer grab each other's results.

## 1.30.1 - Build fix

### Fixed
- Compile error in 1.30.0: the four automation machines' menu registrations referred to themselves by simple name inside their own initializer, which Java doesn't allow. They now refer to themselves through the class name.

## 1.30.0 - Storage Monitor

### Added
- **Storage Monitor**: right-click it with an item to show that item and its live count in the storage network on its face (green when stocked, red at zero). Sneak + right-click with an empty hand to clear it. Comparator output: 0 at none, +1 per doubling up to 15, for low-stock alarms or redstone-triggered production. Respects storage security.

## 1.29.0 - More automation

### Added
- **Block Breaker**: breaks the block in front once a second (diamond-pickaxe drops), 400 FE per block.
- **Block Placer**: places blocks from its inputs in front, twice a second, 100 FE per block.
- **Tree Farm**: plants saplings on a 9x9 patch in front, uses bone meal, and fells whole grown trees (logs and leaves) keeping everything. 200 FE per job.
- **Animal Ranch**: on a 9x9 area in front, it breeds pairs with feed (up to 24 animals), shears sheep, milks cows into empty buckets, and collects eggs and feathers. 200 FE per job.
- All four have 3 input slots, a 3x3 output buffer pushed into neighbouring inventories, Speed/Energy upgrades and redstone control.
- The **Molecular Assembler**'s core is now animated: a swirl while working, a slow pulse when idle.

## 1.28.1 - Bigger, see-through assemblers

### Changed
- **Molecular Assemblers hold 27 patterns** (3 rows of 9) instead of 9. Existing assemblers keep their patterns and upgrades where they were.
- **Assemblers work from any face** of this mod's machines: the machine's Sides settings no longer apply to assemblers. Items still go into the right slots (ingredients in, results out). Other mods' machines still use their own sided rules.
- **Transparent assemblers**: a glass case with a crafting core inside that glows while working.

### Notes
- Furnace recipes don't need patterns at all: an assembler touching an Electric Furnace gives the network every furnace recipe (since 1.27.3). Several assemblers can also share one machine.

## 1.28.0 - Wireless power

### Added
- **Power Transmitter** and **Power Receiver**: link receivers to a transmitter with the **Power Linker** (right-click the transmitter, then each receiver). Receivers draw from their transmitter and push power into everything around them. Each transmitter supplies up to 64,000 FE/t in total, shared between its receivers, and both hold 1M FE. Works at any distance and **across dimensions** (10% loss). Both ends' chunks must be loaded. Jade shows each end's status.

## 1.27.3 - Smarter autocrafting

### Added
- **Built-in smelting**: if any Molecular Assembler touches an Electric Furnace, the planner knows every furnace recipe without patterns. A missing Iron Ingot is smelted from raw iron, iron ore or iron dust in storage, whichever is most plentiful. Encoded patterns always take priority.
- **Exact job status**: a stuck job names the machine it's waiting on (name and position), the ingredient it needs ("Needs 2 Raw Iron in storage"), a machine that won't take its inputs, or that no assembler holds the pattern. Shown after a couple of seconds in the Jobs screen.

### Fixed
- **Ore Purifier** (and any pattern made with JEI's + for a machine that uses water) never received anything from an assembler: the pattern listed water as an input and storage had none. Water now never counts as missing; it's sent if storage has some, otherwise the machine's own Sink or pipe supplies it.
- Processing patterns now send whichever accepted ingredient is in stock, instead of only the first one encoded.

## 1.27.2 - Charger actually charges

### Fixed
- The **Charger** accepted items but never charged them: its energy buffer is closed for output (like every machine's), so the transfer it used always moved 0 FE. It now gives the item energy straight from its own buffer, up to 8,000 FE/t. The Wireless Charger was already doing this correctly.

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
