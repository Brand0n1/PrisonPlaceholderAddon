# PrisonPlaceholderAddon
Fixes how Prison handels some placeholders

## Placeholders that can be used:
```
1. %prisonAddon_prison_rcf_default%
2. %prisonAddon_prison_rcf_default::nFormat:#,##0.00:0:kmbt%
3. %prisonAddon_prison_rcb_default%
4. %prisonAddon_prison_rcb_prestiges%
```

This plugin makes it so that if you are in rank "Z" on the server the progress bar ("prison_rcb_default") will switch from the default progress bar
to the prestige progress bar, showing your players how much more progress they have until they can prestige.

If the player is in any rank other than Z once the amount needed to rankup is reached the dallor amount will be changed to /rankup
If the player is in rank Z and the prestige progress bar is filled up then the dallor amount will change to /prestige
