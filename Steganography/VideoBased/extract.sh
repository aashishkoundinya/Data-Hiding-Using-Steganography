#!/bin/bash
mkdir -p frames
ffmpeg -i Steganography/Assets/video.mp4 -vf "fps=1" Steganography/frames/frame_%04d.png