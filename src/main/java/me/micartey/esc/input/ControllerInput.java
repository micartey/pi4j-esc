package me.micartey.esc.input;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ControllerInput {

    LEFT_JOYSTICK_LEFT_RIGHT(0),
    LEFT_JOYSTICK_UP_DOWN(1),
    LEFT_TRIGGER(2),
    RIGHT_JOYSTICK_LEFT_RIGHT(3),
    RIGHT_JOYSTICK_UP_DOWN(4),
    RIGHT_TRIGGER(5),
    LEFT_RIGHT_ARROW(6),
    TOP_BOTTON_ARROW(7),
    A_BOTTON(8),
    B_BOTTON(9),
    X_BOTTON(10),
    Y_BOTTON(11),
    LEFT_SHOULDER(12),
    RIGHT_SHOULDER(13),
    HOME(16)

    ;

    private final int value;
}
