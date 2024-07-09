#!/bin/bash
mkdir -p output_frames
ffmpeg -framerate 1 -i output_frames/frame_%04d.png -c:v libx264 -r 30 -pix_fmt yuv420p output.mp4