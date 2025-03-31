# pi-esc

## Enable PWM for GPIO 0

`sudo vim /boot/firmware/config.txt`

```toml
[all]
dtoverlay=pwm-2chan
# GPIO 18 = channel 0
# GPIO 19 = channel 0
```

Yes these are commented out but this is correct for some reason.