# Worldguard Custom Use

This plugin is an extension of [WorldGuard](https://dev.bukkit.org/projects/worldguard) that allows for greater customisation of what blocks players can and cannot interact with in protected regions.

This plugin adds the following flags to WorldGuard:

* `deny-interact-with`: List of block ID's. Sets which blocks non-members are NOT allowed to use.
* `deny-interact-with-buttons`: Boolean, default false. If true, denies non-members from being able to use with buttons.
* `deny-interact-with-doors`: Boolean, default false. If true, denies non-members from being able to use with doors.
* `deny-interact-with-fence-gates`: Boolean, default false. If true, denies non-members from being able to use with fence gates.
* `deny-interact-with-trapdoors`: Boolean, default false. If true, denies non-members from being able to use with trapdoors.

Example: To deny interaction with chests, barrels, and trapped chests within a region named `spawn-region`. Use the command:
```
/region flag spawn-region deny-interact-with chest,barrel,trapped_chest
```


