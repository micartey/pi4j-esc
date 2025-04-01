# pi-esc

<div align="center">
    <img src="https://img.shields.io/badge/Rhaspberry%20Pi-4B-red?style=for-the-badge&logo=raspberrypi&logoSize=large" />
</div>

<br />

<div align="center">
    <img src="assets/pinout.webp" height="300px">
    <br />
    <a href="https://randomnerdtutorials.com/raspberry-pi-pwm-python/">
        Source of image
    </a>
</div>

<br />

As the pinout implies, PWM can only be used on the following GPIO pins:

- GPIO 12
- GPIO 13
- GPIO 18
- GPIO 19

That means a maximum of 4 signals can be generated

## Enable PWM for GPIO 18 and 19



`sudo vim /boot/firmware/config.txt`

```txt
...

[all]
dtoverlay=pwm-2chan
# GPIO 18 = channel 0
# GPIO 19 = channel 0
```