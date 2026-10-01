READ / Download the HTML file to see what the mod does.# Changelog

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
