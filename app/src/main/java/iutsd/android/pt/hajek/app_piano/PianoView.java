package iutsd.android.pt.hajek.app_piano;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class PianoView extends View {
    public static final int NB=14;
    private Paint black, white, blue,red;
    private ArrayList<Key> whites=new ArrayList<>();
    private ArrayList<Key> blacks=new ArrayList<>();

    private int keyWidth, height;
    private AudioSoundPlayer soundPlayer;

    /** Constructeur */
    public PianoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        black = new Paint();
        black.setColor(Color.BLACK);
        white = new Paint();
        white.setColor(Color.WHITE);
        white.setStyle(Paint.Style.FILL);

        red = new Paint();
        red.setColor(Color.RED);
        red.setStyle(Paint.Style.FILL);

        soundPlayer = new AudioSoundPlayer(context);
    }


        /** Crée les objets Key des touches (calcul largeur,
         * obtient hauteur de la clé, rectF représente à l'écran
         */

        protected void onSizeChanged(int w, int h, int oldw, int oldh){
            super.onSizeChanged(w, h, oldw, oldh);
            keyWidth = w / NB;
            height = h;
            int count = 15;
            for (int i = 0; i < NB; i++) {
                int left = i * keyWidth;
                int right = left + keyWidth;

                if (i == NB - 1) {
                    right = w;
                }

                RectF rect = new RectF(left, 0, right, h);
                whites.add(new Key(rect, i + 1));

                if (i != 0 && i != 3 && i != 7 && i != 10) {
                    rect = new RectF((float) (i - 1) * keyWidth + 0.5f * keyWidth + 0.25f * keyWidth, 0,
                            (float) i * keyWidth + 0.25f * keyWidth, 0.67f * height);
                    blacks.add(new Key(rect, count));
                    count++;
                }
            }
        }

    /** On dessine le piano en parcourant chaques touches (couleurs)
     * * enfoncé/ pas enfoncé / noir /blanc @param canvas
     */
    protected void onDraw(Canvas canvas){
            for (Key k : whites){
                canvas.drawRect(k.rect, k.down ? red : white);
            }

            for(int i=1; i<NB; i++){
                canvas.drawLine(i*keyWidth,0,i*keyWidth,height,black);
            }

            for(Key k : blacks){
                canvas.drawRect(k.rect,k.down ? red : black);
            }
        }
        /** Controle des evenements, enfoncement touche, relachement */
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        boolean isDownAction = action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE;

        for (int touchIndex = 0; touchIndex < event.getPointerCount(); touchIndex++) {
            float x = event.getX(touchIndex);
            float y = event.getY(touchIndex);

            Key k = keyForCoords(x, y);

            if (k != null) {
                k.down = isDownAction;
            }
        }

        ArrayList<Key> tmp = new ArrayList<>(whites);
        tmp.addAll(blacks);

        for (Key k : tmp) {
            if (k.down) {
                if (!soundPlayer.isNotePlaying(k.sound)) {
                    soundPlayer.playNote(k.sound);
                    invalidate();
                } else {
                    releaseKey(k);
                }
            } else {
                soundPlayer.stopNote(k.sound);
                releaseKey(k);
            }
        }

        return true;
    }
    /** on parcours touche du  piano pour clé associé au coordonées */
    private Key keyForCoords(float x, float y) {
        for (Key k : blacks) {
            if (k.rect.contains(x,y)) {
                return k;
            }
        }

        for (Key k : whites) {
            if (k.rect.contains(x,y)) {
                return k;
            }
        }

        return null;
    }

    /** Liberer une clé (objet Handler,
     * implémentation de l'interface Runnable
     */
    private void releaseKey(final Key k) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                k.down = false;
                handler.sendEmptyMessage(0);
            }
        }, 100);
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            invalidate();
        }
    };
}


