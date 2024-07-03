# Data Hiding Using Code Based Steganography

This project demonstrates data hiding techniques using steganography in images, audio, and video files. Steganography is the practice of concealing a secret message within another medium, such as an image, audio, or video file, in a way that prevents detection of the hidden message.

This Program uses LSB (Least Significant Bit) Algorithm to achieve the data hiding. LSB works by replacing the least significant bit of pixel values (typically in image or audio files) with hidden data. This alteration is often imperceptible to human senses but can carry additional information within the file.

## Features 
* **Image Steganography:** Embed and extract hidden messages within PNG images using the Least Significant Bit (LSB) method.
* **Audio Steganography:** Embed and extract hidden messages within WAV audio files.
* **Video Steganography:** Embed and extract hidden messages within MP4 video files using FFmpeg for processing.

## Installation

**Prerequisites**

Last Run on these versions

* Java (v19.0.2+7-44)
* FFMPEG (v7.0.1)

## Run

* Make sure you have the above prerequisites installed.
* Download the code with the same Directory Structure.
* Open the terminal and copy the following commands one after the other.

  ```bash
  cd DirectoryPath_of_Steganography
  ```

  ```bash
  javac -d . AudioBased/*java ImageBased/*java VideoBased/*java
  ```

  ```bash
  javac -d . Main.java
  ```

  ```bash
  java Main.java
  ```
