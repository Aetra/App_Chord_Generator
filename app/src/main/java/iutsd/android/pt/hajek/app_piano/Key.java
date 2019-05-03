package iutsd.android.pt.hajek.app_piano;

import android.graphics.RectF;

/** Création des touches du piano */
public class Key {
    public int sound;
    public RectF rect;
    public boolean down;

    /**Constructeur */
    public Key(RectF rect, int sound)
    {
        this.sound=sound;
        this.rect=rect;
    }
}
