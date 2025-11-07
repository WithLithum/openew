# OpenEW

OpenEW is a mod for Minecraft: Java Edition that aims to aid with the porting
efforts of _The Ender War: An Assassin of Steve Prequel_.

The reason of creation of this mod is because that many parts of the map
mechanics requires painfully slow function files that often has no faster
alternative implementation.

## Building

JDK 21 is required. If you don't yet have one installed,
[Eclipse Temurin](https://adoptium.net) is recommended on Windows. (on Linux,
use the OpenJDK available in the package manager is sufficient).

Note that you need the J**DK**, not JRE.

Execute `./gradlew build` with your favourite shell (on Windows, use the new
[PowerShell 7](https://microsoft.com/powershell) or newer versions, not Windows
PowerShell).

## License

[Apache-2.0](LICENSE)