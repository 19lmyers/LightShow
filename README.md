# LightShow

A sample "light show" library for use in a classroom setting.

## Structure

The library is built to control a "light show" system controlled by relays and a Raspberry Pi. 
Since the intention of the project is to provide an exercise for new Java students, as much of the complexity is designed to be hidden as is possible.

The library was built for our specific "light show" hardware, but can be modified to fit other configurations.

The `API` module defines the structure of a Light Show project, and is simple for students to implement.
It also contains some more advanced tools that can be used to showcase advanced Java features.

The two `platform` modules define tools to run a light show on different platforms:
* The `desktop` platform implements `LightController` to provide a Swing-based simulated show display for local testing.
* The `RPI` platform implements `LightController` to control the live show via the [Pi4J](https://pi4j.com/) library.

## Installation

Simply download the required .jar files and add them to your classpath. 

For projects running on a Raspberry Pi, the [Pi4J](https://pi4j.com/) library needs to be installed as well.

### Gradle

```groovy
repositories {
    /* Jitpack */
    maven {
        url 'https://jitpack.io'
    }
    
    /* Pi4J snapshot repository - required for RPI platform */
    maven {
        url 'https://oss.sonatype.org/content/groups/public'
    }
}
```

```groovy
dependencies {
    // All projects should include the API.
    implementation 'com.github.19lmyers.LightShow:api:1.0'

    // The Desktop platform provides tools to display your light show on a computer.
    implementation 'com.github.19lmyers.LightShow:platform-desktop:1.0'

    // The RPI platform provides tools to run your show through a Raspberry Pi, and requires Pi4J to be installed.
    implementation 'com.github.19lmyers.LightShow:platform-rpi:1.0'
    implementation 'com.pi4j:pi4j-core:1.2-SNAPSHOT'
}
```

## Usage

A sample project is included in the `sample-show` directory, and is the definitive guide for using this library.

### Implementing the API

Light shows are expected to extend the `Show` class, and use the provided `LightController` and `MusicController` to display itself.

A `Trick` class is provided that can be used to structure and control patterns in an OOP framework.
Advanced extensions are provided for developers who wish to use functional programming or an event scheduler framework.

### Running the show

To run a light show, simply instantiate it and run it with the controller objects of your desired platform.

## Credits

Thanks to David Hedin-Abreu for creating the original Light Show framework, and to Donovan Cunningham for writing the original desktop implementation.