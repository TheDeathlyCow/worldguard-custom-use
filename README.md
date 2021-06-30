# Worldguard Custom Use

Adds a WorldGuard set flag called `deny-interact-with`. This flag takes a set of block IDs any denies any non-member the ability to interact (or right click) with blocks of that type within the region. Note: redstone will still work as normal, this will only stop non-member players from interacting with blocks. 

Example: deny use of trapdoors within a region named `spawn-region`. Use the command `/region flag spawn-region deny-interact-with oak_trapdoor,spruce_trapdoor,birch_trapdoor,jungle_trapdoor,acacia_trapdoor,dark_oak_trapdoor,crimson_trapdoor,warped_trapdoor,iron_trapdoor`. 
