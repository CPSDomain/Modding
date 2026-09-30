READ / Download the HTML file to see what the mod does.


# Changelog

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
