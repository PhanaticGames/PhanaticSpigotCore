# PhanaticSpigotCore
The core for all phantic serevrs running spigot.

# API
Check wiki for ban,mute and other api features

To include PSC in your plugin, add the following code to your pom.xml
```
<repository>
    <id>psc</id>
    <url>http://75.176.38.251/nexus/content/repositories/snapshots/</url>
</repository>
```

and
```
<dependency>
    <groupId>code.matthew</groupId>
    <artifactId>PSC</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <scope>provided</scope>
</dependency>
```

# Notes
This project uses maven based dependacy management and building.