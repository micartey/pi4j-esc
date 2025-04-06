# pi-esc

<div align="center">
    <img src="https://img.shields.io/badge/Rhaspberry%20Pi-4B-red?style=for-the-badge&logo=raspberrypi&logoSize=large" />
</div>

<br />

<div align="center">
    <img src="assets/pinout.webp" height="300px" width="550px">
    <br />
    <a href="https://randomnerdtutorials.com/raspberry-pi-pwm-python/">
        Source of image
    </a>
</div>

<br />

## Introduction

This simple example controlls a brushless motor using an electronic speed controller (ESC) using an XBox Controller.

### Enable PWM for GPIO 18 and 19

`sudo vim /boot/firmware/config.txt`

```txt
...

[all]
dtoverlay=pwm-2chan
# GPIO 18 = channel 0
# GPIO 19 = channel 0
```

### Install joystick 

```bash
sudo apt-get install joystick

# To validate that your input can be read correctly, execute
jstest /dev/input/js0
```

